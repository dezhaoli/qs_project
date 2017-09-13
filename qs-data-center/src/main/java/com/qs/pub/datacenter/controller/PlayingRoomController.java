
package com.qs.pub.datacenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.pub.datacenter.model.PlayingRoom;
import com.qs.pub.datacenter.service.IRoomPlayingService;
import com.qs.pub.sys.model.UserEntity;

/**
 * 
 * Created by zsf @date 创建时间：2017年9月7日 下午1:44:56
 * Description: 用户每日参与房间统计
 */
@Controller
@RequestMapping("/roomPlaying/")
public class PlayingRoomController extends BaseController
{
	@Resource
	private IRoomPlayingService roomPlayingService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	/**
	 * 
	 * @author zsf   @date 创建时间：2017年9月7日 下午1:46:26 
	 * @Description: 跳转到用户每日在玩统计首页
	 * @param  
	 * @return
	 */
	@RequestMapping("toRoomPlayListtUi.html")
	public String toRegionPlayListtUi(){
		
		return "WEB-INF/view/web/user/room_playing_list";
	}
	/**
	 * 
	 * @author zsf   @date 创建时间：2017年9月7日 下午2:22:52 
	 * @Description: 用户每日参与房间查询
	 * @param  
	 * @return
	 */
	@RequestMapping("roomPlayList.html")
	@ResponseBody
	public Object regionPlayList(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
//			String mainDataSourceType = dataSourceName + "AgentDataSource";  //不需要切換數據源
//			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
//			parameters.put("dbName", dataSourceName+".");
			parameters.put("gameCode", gameCode);
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<PlayingRoom> list = roomPlayingService
							.queryListBymid(parameters);
					ExportUtils.exportAll(response, pager, list);
					return null;
				} else
				{
					// 3.2、导出当前页数据
					ExportUtils.export(response, pager);
					return null;
				}
			} else
			{
				// 设置分页，page里面包含了分页信息
				Page<Object> page = PageHelper.startPage(pager.getNowPage(),
						pager.getPageSize());
				List<PlayingRoom> list = roomPlayingService
						.queryListBymid(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	@RequestMapping("queryRoomCountTotal.html")
	@ResponseBody
	public Object queryRegionCountTotal(Integer mid,String stime,String etime){
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
//			String mainDataSourceType = dataSourceName + "AgentDataSource";  //不需要切換數據源
//			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			Map<String, Object> parameters = new HashMap<String,Object>();
//	        parameters.put("dbName", dataSourceName+".");
			parameters.put("gameCode", gameCode);
			parameters.put("mid", mid);
			parameters.put("stime", stime);	
			parameters.put("etime", etime);
			Long total = roomPlayingService.queryRoomCountTotal(parameters);
		    return total == null?0:total;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
}
