package com.hzw.monitor.mysqlbinlog.web.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzw.monitor.mysqlbinlog.conf.Constant;
import com.hzw.monitor.mysqlbinlog.web.valid.First;
import com.hzw.monitor.mysqlbinlog.web.vo.MysqlBinlogBean;
import com.hzw.monitor.mysqlbinlog.web.vo.RegexFilter;
import com.hzw.monitor.mysqlbinlog.web.vo.RegexSharding;
import com.hzw.monitor.mysqlbinlog.zookeeper.ZKConnection;

/**
 * @author gqliu
 * 2016年1月18日
 *
 */
@Controller
@RequestMapping("/mysqlbinlog")
public class MysqlBinlogController extends BaseController {
	
	private static final Log logger = LogFactory.getLog(MysqlBinlogController.class);
	
	@Autowired
	private ZKConnection zKConnection;
	
	@RequestMapping("list.do")
	public ModelAndView list() {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<String> children = zKConnection.getChildren(zKConnection.getTaskDir());
			List<MysqlBinlogBean> beans = new ArrayList<MysqlBinlogBean>();
			for(String task:children) {
				MysqlBinlogBean bean = new MysqlBinlogBean();
				bean.setIp(task);
				
				try {
					bean.setRunning(zKConnection.getData(zKConnection.getTaskDir()+"/"+task+"/running/current"));
				} catch(Exception e1) {
					
				}
				
				beans.add(bean);
			}
			res.put("list", beans);
		} catch (Exception e) {
			logger.error("", e);
		}
		return new ModelAndView("mysqlbinlog/mysqlBinlog-list", res);
	}

	@RequestMapping("add.do")
	public String add() {
		return "mysqlbinlog/mysqlBinlog-add";
	}
	
	@RequestMapping("add_submit.do")
	@ResponseBody
	public Map<String, Object> add_submit(@Validated({First.class}) MysqlBinlogBean params, BindingResult bindingResult) {
		Map<String, Object> res = new HashMap<String, Object>();
		if(bindingResult.hasErrors()) {
			res.put("reason", getErrors(bindingResult));
			res.put("code", Constant.CODE_FAIL);
			return res;
		}
		//检查主机端口是否打开
		Socket socket = null;
		try {
			String[] ips = params.getIp().split(",|，");
			for(String ip:ips) {
				socket = new Socket();
				socket.connect(new InetSocketAddress(ip, params.getPort()), 3000);
				socket.close();
				socket = null;
			}
		} catch (IOException e) {
			res.put("code", Constant.CODE_FAIL);
			res.put("reason", "ip或端口不正确");
			return res;
		} finally {
			try {
				if(socket != null)
					socket.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		String path = null;
		try {
			path = zKConnection.create(zKConnection.getTaskDir() + "/" + params.getIp()+":" + params.getPort(), CreateMode.PERSISTENT);
		} catch (Exception e) {
			if(e instanceof NodeExistsException) {
				res.put("code", Constant.CODE_FAIL);
				res.put("reason", "任务已经存在");
			} else {
				logger.error("", e);
				res.put("code", Constant.CODE_FAIL);
				res.put("reason", "创建失败");
			}
			return res;
		}
		try {
			JSONObject obj = new JSONObject();
			obj.put("clientID", params.getClientID());
			obj.put("ur", params.getUser());
			obj.put("pr", params.getPassword());
			obj.put("us", params.getUserSchema());
			obj.put("ps", params.getPasswordSchema());
			obj.put("fn", params.getFilename());
			obj.put("fp", params.getPosition());
			JSONArray array = new JSONArray();
			for(RegexFilter f:params.getFilters()) {
				if(StringUtils.isNotBlank(f.getDatabase()) && StringUtils.isNotBlank(f.getTable())) {
					JSONObject o = new JSONObject();
					o.put("d", f.getDatabase());
					o.put("t", f.getTable());
					array.add(o);
				}
			}
			obj.put("r", array);
			array = new JSONArray();
			for(RegexSharding sharding:params.getShardings()) {
				if(StringUtils.isNotBlank(sharding.getDatabaseRule()) && StringUtils.isNotBlank(sharding.getTableRule())
						&& StringUtils.isNotBlank(sharding.getDatabase()) && StringUtils.isNotBlank(sharding.getTable())) {
					JSONObject o = new JSONObject();
					o.put("rd", sharding.getDatabaseRule());
					o.put("rt", sharding.getTableRule());
					o.put("d", sharding.getDatabase());
					o.put("t", sharding.getTable());
					array.add(o);
				}
			}
			obj.put("sharding", array);
			obj.put("t", System.currentTimeMillis());
			obj.put("s", "new");
			
			logger.debug(obj.toJSONString());
			
			zKConnection.setData(path, obj.toJSONString());
			res.put("code", Constant.CODE_SUCCESS);
		} catch (Exception e) {
			logger.error("", e);
			res.put("code", Constant.CODE_FAIL);
			res.put("reason", "创建失败");
		}
		return res;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String, Object> delete(String ipAndPort) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			zKConnection.deletePath(zKConnection.getTaskDir()+"/"+ipAndPort);
			res.put("code", Constant.CODE_SUCCESS);
		} catch (Exception e) {
			logger.error("", e);
			res.put("code", Constant.CODE_FAIL);
			res.put("reason", "删除失败");
		}
		return res;
	}
	
	@RequestMapping("edit.do")
	public ModelAndView edit(String ipAndPort) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String json = zKConnection.getData(zKConnection.getTaskDir()+"/"+ipAndPort);
			
			MysqlBinlogBean bean = new MysqlBinlogBean();
			String[] temp = ipAndPort.split(":");
			bean.setIp(temp[0]);
			bean.setPort(Integer.parseInt(temp[1]));
			
			JSONObject obj = JSONObject.parseObject(json);
			bean.setUser(obj.getString("ur"));
			bean.setPassword(obj.getString("pr"));
			bean.setUserSchema(obj.getString("us"));
			bean.setPasswordSchema(obj.getString("ps"));
			bean.setFilename(obj.getString("fn"));
			bean.setPosition(obj.getString("fp"));
			
			JSONArray array = obj.getJSONArray("r");
			List<RegexFilter> filters = new ArrayList<RegexFilter>();
			for (int i = 0; i < array.size(); i++) {
				JSONObject filterObj = array.getJSONObject(i);
				RegexFilter rf = new RegexFilter();
				rf.setDatabase(filterObj.getString("d"));
				rf.setTable(filterObj.getString("t"));
				filters.add(rf);
			}
			bean.setFilters(filters);
			
			array = obj.getJSONArray("sharding");
			List<RegexSharding> shardings = new ArrayList<RegexSharding>();
			for(int i=0;i<array.size();i++) {
				JSONObject shardingObj = array.getJSONObject(i);
				RegexSharding sharding = new RegexSharding();
				sharding.setDatabaseRule(shardingObj.getString("rd"));
				sharding.setTableRule(shardingObj.getString("rt"));
				sharding.setDatabase(shardingObj.getString("d"));
				sharding.setTable(shardingObj.getString("t"));
				shardings.add(sharding);
			}
			bean.setShardings(shardings);
			
			res.put("bean", bean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return new ModelAndView("mysqlbinlog/mysqlBinlog-edit", res);
	}
	
	@RequestMapping("edit_submit.do")
	@ResponseBody
	public Map<String, Object> edit_submit(MysqlBinlogBean params, BindingResult bindingResult) {
		Map<String, Object> res = new HashMap<String, Object>();
		if(bindingResult.hasErrors()) {
			res.put("reason", getErrors(bindingResult));
			res.put("code", Constant.CODE_FAIL);
			return res;
		}
		try {
			String path = zKConnection.getTaskDir() + "/" + params.getIp()+":" + params.getPort();
			String oldJson = zKConnection.getData(path);
			JSONObject obj = (JSONObject) JSONObject.parse(oldJson);
			obj.put("fn", params.getFilename());
			obj.put("fp", params.getPosition());
			JSONArray array = new JSONArray();
			if(params.getFilters() != null) {
				for(RegexFilter f:params.getFilters()) {
					if(StringUtils.isNotBlank(f.getDatabase()) && StringUtils.isNotBlank(f.getTable())) {
						JSONObject o = new JSONObject();
						o.put("d", f.getDatabase());
						o.put("t", f.getTable());
						array.add(o);
					}
				}
			}
			obj.put("r", array);
			
			array = new JSONArray();
			if(params.getShardings() != null) {
				for(RegexSharding sharding:params.getShardings()) {
					if(StringUtils.isNotBlank(sharding.getDatabaseRule()) && StringUtils.isNotBlank(sharding.getTableRule())
							&& StringUtils.isNotBlank(sharding.getDatabase()) && StringUtils.isNotBlank(sharding.getTable())) {
						JSONObject o = new JSONObject();
						o.put("rd", sharding.getDatabaseRule());
						o.put("rt", sharding.getTableRule());
						o.put("d", sharding.getDatabase());
						o.put("t", sharding.getTable());
						array.add(o);
					}
				}
			}
			obj.put("sharding", array);
			
			obj.put("t", System.currentTimeMillis());
			obj.put("s", "update");
			
			logger.debug(obj.toJSONString());
			
			zKConnection.setData(path, obj.toJSONString());
			res.put("code", Constant.CODE_SUCCESS);
		} catch (Exception e) {
			logger.error("", e);
			res.put("code", Constant.CODE_FAIL);
			res.put("reason", "修改失败");
		}
		return res;
	}
	
	@RequestMapping("showInfo")
	public ModelAndView showInfo(String ipAndPort) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String json = zKConnection.getData(zKConnection.getTaskDir()+"/"+ipAndPort);
			
			MysqlBinlogBean bean = new MysqlBinlogBean();
			String[] temp = ipAndPort.split(":");
			bean.setIp(temp[0]);
			bean.setPort(Integer.parseInt(temp[1]));
			
			JSONObject obj = JSONObject.parseObject(json);
			bean.setClientID(obj.getString("clientID"));
			bean.setUser(obj.getString("ur"));
			bean.setPassword(obj.getString("pr"));
			bean.setUserSchema(obj.getString("us"));
			bean.setPasswordSchema(obj.getString("ps"));
			bean.setFilename(obj.getString("fn"));
			bean.setPosition(obj.getString("fp"));
			
			JSONArray array = obj.getJSONArray("r");
			List<RegexFilter> filters = new ArrayList<RegexFilter>();
			for (int i = 0; i < array.size(); i++) {
				JSONObject filterObj = array.getJSONObject(i);
				RegexFilter rf = new RegexFilter();
				rf.setDatabase(filterObj.getString("d"));
				rf.setTable(filterObj.getString("t"));
				filters.add(rf);
			}
			bean.setFilters(filters);
			
			array = obj.getJSONArray("sharding");
			List<RegexSharding> shardings = new ArrayList<RegexSharding>();
			for(int i=0;i<array.size();i++) {
				JSONObject shardingObj = array.getJSONObject(i);
				RegexSharding sharding = new RegexSharding();
				sharding.setDatabaseRule(shardingObj.getString("rd"));
				sharding.setTableRule(shardingObj.getString("rt"));
				sharding.setDatabase(shardingObj.getString("d"));
				sharding.setTable(shardingObj.getString("t"));
				shardings.add(sharding);
			}
			bean.setShardings(shardings);
			
			TreeMap<String, String> history = new TreeMap<>();
			//获取历史记录
			String positionPath = zKConnection.getTaskDir()+"/"+ipAndPort+"/binlog-positions";
			if(zKConnection.exists(positionPath)) {
				List<String> children = zKConnection.getChildren(positionPath);
				for(String child:children) {
					String position = zKConnection.getData(positionPath + "/" + child);
					if(position != null && !"".equals(position)) {
						history.put(child, position.substring(0, position.lastIndexOf(":")));
					}
				}
				
				//获取当前文件位置
				String currentPosition = zKConnection.getData(positionPath);
				if(currentPosition != null && !"".equals(currentPosition)) {
					res.put("current", currentPosition.substring(0, currentPosition.lastIndexOf(":")));
				}
			}
			
			res.put("bean", bean);
			res.put("history", history);
		} catch (Exception e) {
			logger.error("", e);
		}
		return new ModelAndView("mysqlbinlog/mysqlBinlog-info", res);
	}
}
