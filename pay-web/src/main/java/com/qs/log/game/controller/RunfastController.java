package com.qs.log.game.controller;

import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.WeixinPayAccount;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.CorpPayment;
import com.foxinmy.weixin4j.payment.mch.CorpPaymentResult;
import com.foxinmy.weixin4j.type.mch.CorpPaymentCheckNameType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.agent.game.model.MemberAgents;
import com.qs.agent.game.service.IMemberAgentService;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.pay.PayUtil;
import com.qs.common.util.PageUtil;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInviteWeek;
import com.qs.log.game.model.TaxesInviteWeekSend;
import com.qs.log.game.service.ITaxesInviteWeekService;
import com.qs.log.game.service.ITaxesInviteWeekSendService;
import com.qs.log.game.util.BusinessDateUtil;
import com.qs.log.game.util.ConstantUtil;
import com.qs.log.game.util.MyExportUtils;
import com.qs.pub.pay.model.PayLog;
import com.qs.pub.pay.model.WeixinMsg;
import com.qs.pub.pay.service.IPayLogService;
import com.qs.pub.pay.service.IWeinxinMsgService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * //@Author:zun.wei, @Date:2017/4/5 11:20
 *  跑得快控制器
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/runfast/")
public class RunfastController extends PayBaseController {
	

	Logger log = Logger.getLogger(RunfastController.class);

    @Resource
	private ITaxesInviteWeekService taxesInviteWeekService;
    @Resource
   	private ITaxesInviteWeekSendService taxesInviteWeekSendService;
    
    @Resource
    private IMemberAgentService memberAgentService;
    
	@Autowired
	private IWeinxinMsgService weinxinMsgService;
    
	@Autowired
	private IPayLogService payLog;
    
	@Autowired
	private RedisTemplate<String,List<Integer>> redisTemplate;
	
    
    
    @Value("${paodekuai.appid}")
    private  String appid;
    
    @Value("${paodekuai.mchid}")
    private  String mchid;
    
    @Value("${paodekuai.key}")
    private  String apikey;
    
    @Value("${paodekuai.certfile}")
    private  String certfile;

   
    /**
     * 代理商周信息统计入口
     */
    @RequestMapping(value = "agentWeekInfoStaUi.html", method = RequestMethod.GET)
    public String agentWeekInfoStaUi(Model model, HttpServletRequest request) {
        try {
            Map<String, List<String>> date = BusinessDateUtil.getAgentInfoDateTime();
            String json = JSON.toJSONString(date);
            List<String> keys = new ArrayList<String>();
            Set<String> keySet = date.keySet();
            Iterator<String> ki = keySet.iterator();
            while (ki.hasNext()) {
                String key = ki.next();
                keys.add(key.substring(1));
            }
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("years", keys);
            model.addAttribute("jsonDate", json);
            model.addAttribute("lastMonday", BusinessDateUtil.getLastWeekMonday());
            model.addAttribute("lastSunday", BusinessDateUtil.getLastWeekSunday());
            model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Run_Fast);
            return "/WEB-INF/view/web/agent_weekStaInfo_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 代理商活动返利列表
     */
    @RequestMapping("agentActivitySta.html")
    @ResponseBody
    public Object agentActivitySta(String gridPager, HttpServletResponse response) throws Exception {
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
		Map<String,Object> map = super.getAgentActivitySta(gridPager, response,
				ConstantUtil.GameTypeConstant.Run_Fast, taxesInviteWeekSendService);
        return map;
    }
    
    
    
    /**
     * 跳转到代理商活动返利列表
     */
    @RequestMapping(value = "agentActivityStaUi.html", method = RequestMethod.GET)
    public String agentActivityStaUi(Model model, HttpServletRequest request) {
        try {
            Map<String, List<String>> date = BusinessDateUtil.getAgentInfoDateTime();
            String json = JSON.toJSONString(date);
            List<String> keys = new ArrayList<String>();
            Set<String> keySet = date.keySet();
            Iterator<String> ki = keySet.iterator();
            while (ki.hasNext()) {
                String key = ki.next();
                keys.add(key.substring(1));
            }
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("years", keys);
            model.addAttribute("jsonDate", json);
            model.addAttribute("lastMonday", BusinessDateUtil.getLastWeekMonday());
            model.addAttribute("lastSunday", BusinessDateUtil.getLastWeekSunday());
            model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Run_Fast);
            return "/WEB-INF/view/web/agent_activity_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 代理商周信息统计
     */
    @RequestMapping("agentWeekInfoSta.html")
    @ResponseBody
    public Object agentWeekInfoSta(String gridPager, HttpServletResponse response) throws Exception {
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
		//Map<String,Object> map = super.getAgentWeekInfoSta(gridPager, response,ConstantUtil.GameTypeConstant.Run_Fast, taxesInviteWeekService);
	       Map<String, Object> parameters = null;
	        // 映射Pager对象
	        Pager pager = JSON.parseObject(gridPager, Pager.class);
	        // 判断是否包含自定义参数
	        parameters = pager.getParameters();
	        if (parameters.size() < 0) {
	            //parameters.put("mid", null);
	            return null;//如果没有mid传过来则不执行查询。
	        }
	        String date = parameters.get("searchDate") + "";
	        if ("null".equals(date) || StringUtils.isBlank(date)) {
	            parameters.put("searchDate", BusinessDateUtil.getLastWeekSunday());
	        }
	        parameters.put("dbTable", ConstantUtil.GameTypeConstant.Run_Fast + ".memberagents");
	        if(pager.getIsExport()){//判断是否是导出操作
	            if(pager.getExportAllData()){
	                //3.1、导出全部数据
	                List<Map<String,Object>> list = taxesInviteWeekService.getPaoDeKuaiWeekPayInfoByDate(parameters);
	                MyExportUtils.exportAll(response, pager, list);
	                return null;
	            }else{
	                //3.2、导出当前页数据
	                MyExportUtils.export(response, pager);
	                return null;
	            }
	        }
	        // 设置分页，page里面包含了分页信息
	        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
	        List<Map<String,Object>> list = taxesInviteWeekService.getPaoDeKuaiWeekPayInfoByDate(parameters);
	        return getReturnPage(pager, page, list);
	        
    }


    /**
     * 确认支付
     * @param openid
     * @param date
     * @param mid
     * @param request
     * @return
     * @throws WeixinException
     */
    @ResponseBody
    @RequestMapping(value = "confirmPay.html")
    public Object confirmPay(String openid, Date date, Integer mid, HttpServletRequest request){
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
	    WeixinPayProxy PAY = null;
        WeixinPayAccount ACCOUNT = null;
        ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
        PAY = new WeixinPayProxy(ACCOUNT);
        Map<String, Object> map = new HashMap<String, Object>();
     	if(ConstantUtil.NoPay.RUNFAST_MID.contains(mid+"")){
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "不需要返利的代理商");
			return map;
		}
		try {
			 //map = super.weixinPay(request, mid, date, PAY, taxesInviteWeekService, memberAgentService, payLog,ConstantUtil.GameType.Run_Fast);
			
			 TaxesInviteWeek tdwRecord = new TaxesInviteWeek();
		        tdwRecord.setMid(mid);
		        tdwRecord.setDate(date);
		        if (null == date) {
		            map.put(CommonContants.SUCCESS, Boolean.FALSE);
		            map.put(CommonContants.MESSAGE, "日期参数为空");
		            return map;
		        }
		        if (mid<=0) {
		            map.put(CommonContants.SUCCESS, Boolean.FALSE);
		            map.put(CommonContants.MESSAGE, "mid小于0");
		            return map;
		        }
		        TaxesInviteWeek qRecord = taxesInviteWeekService.findTaxesDirectlyWeekByCondition(tdwRecord);
		        if (null == qRecord) {
		            map.put(CommonContants.SUCCESS, Boolean.FALSE);
		            map.put(CommonContants.MESSAGE, "上周结算数据不存在");
		            return map;
		        }
		        if (qRecord.getIsaward() != 1) {
		            map.put(CommonContants.SUCCESS, Boolean.FALSE);
		            map.put(CommonContants.MESSAGE, "已经支付");
		            return map;
		        }

		        if (qRecord.getRebatetotal().intValue() < 1) {
		            map.put(CommonContants.SUCCESS, Boolean.FALSE);
		            map.put(CommonContants.MESSAGE, "支付金额小于1元");
		            return map;
		        }
		        MemberAgents agent = memberAgentService.findByMid(mid);

		        if (null == agent) {
		            map.put(CommonContants.SUCCESS, Boolean.FALSE);
		            map.put(CommonContants.MESSAGE, "不是代理商");
		            return map;
		        }

		        if(StringUtils.isBlank(openid)){
		            map.put(CommonContants.SUCCESS, Boolean.FALSE);
		            map.put(CommonContants.MESSAGE, "openid为空");
		            return map;
		        }
		        String ip ="121.196.224.140";
		  
		        String tranNo = PayUtil.getTransferNo();
		        //保存日志、默认失败的
		        int logId= this.savePayLog(qRecord, agent, ip, payLog,ConstantUtil.GameType.Run_Fast,tranNo,"",0);
		        
		        CorpPayment payment = new CorpPayment(tranNo, openid,
		                CorpPaymentCheckNameType.NO_CHECK, "推广奖励", qRecord.getRebatetotal().doubleValue(), ip);


		        CorpPaymentResult result = PAY.sendCorpPayment(payment);
		        System.out.println("getReturnCode=============::"+result.getReturnCode());
		   
		        int c = 0;
		        PayLog updateLog = new PayLog();
		        updateLog.setId(logId);
		        
		        if (null != result && "SUCCESS".equals(result.getReturnCode())) {
		        	TaxesInviteWeek updateRecord = new TaxesInviteWeek();
		            updateRecord.setMid(qRecord.getMid());
		            updateRecord.setDate(qRecord.getDate());
		            c = taxesInviteWeekService.updateIsawardByCondition(updateRecord);
		            
		            updateLog.setStatus(1);
		            updateLog.setcModifyTime(new Date());
		        
		             
		        }else{
		        	 updateLog.setStatus(0);
		        	 updateLog.setExtend3(result.getReturnMsg());
		             updateLog.setcModifyTime(new Date());
		        }
		        
		        payLog.updateByPrimaryKeySelective(updateLog);
		        
		        map.put(CommonContants.SUCCESS, Boolean.TRUE);
		        map.put(CommonContants.MESSAGE, "支付成功");
		        return map;
		        
		        
		} catch (WeixinException e) {
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, e.getMessage());
			this.saveWeinMsg(mid,weinxinMsgService,ConstantUtil.GameType.Run_Fast, e);
			e.printStackTrace();
		}catch (Exception e) {   
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "支付失败");
			e.printStackTrace();
        }
	    return map;
    }
    
    
    /**
     * 活动支付
     * @param openid
     * @param date
     * @param mid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "activityPay.html")
    public Object activityPay(String openid, Date date, Integer mid, HttpServletRequest request){
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
	    WeixinPayProxy PAY = null;
        WeixinPayAccount ACCOUNT = null;
        ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
        PAY = new WeixinPayProxy(ACCOUNT);
        Map<String, Object> map = new HashMap<String, Object>();
     	if(ConstantUtil.NoPay.RUNFAST_MID.contains(mid+"")){
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "不需要返利的代理商");
		    return map;
		}
		try {
			map = super.activityWeixinPay(request, mid, date, PAY, taxesInviteWeekSendService, memberAgentService, payLog,ConstantUtil.GameType.Run_Fast);
		} catch (WeixinException e) {
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, e.getMessage());
			this.saveWeinMsg(mid,weinxinMsgService,ConstantUtil.GameType.Run_Fast, e);
			e.printStackTrace();
		}catch (Exception e) {   
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "支付失败");
			e.printStackTrace();
        }
	    return map;
    }

	/**
	 * 代理商历史结算发放列表
	 * //@Author:zun.wei, @Date:2017/4/7 13:56
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "agentWeekInfoStaHistoryUi.html", method = RequestMethod.GET)
	public String agentWeekInfoStaHistoryUi(Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Run_Fast);
			return "/WEB-INF/view/web/agent_weekStaHistoryInfo_list";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}


	/**
	 * 代理商历史结算发放列表
	 * //@Author:zun.wei, @Date:2017/4/7 13:56
	 * @param gridPager
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("agentWeekInfoStaHistory.html")
	@ResponseBody
	public Object agentWeekInfoStaHistory(String gridPager) throws Exception {
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
		return super.getAgentWeekInfoStaHistory(gridPager,taxesInviteWeekService);
	}

	/**
	 *  代理商结算发放明细入口
	 *  //@Author:zun.wei, @Date:2017/4/7 15:02
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "agentWeekInfoStaHistoryDetailUi.html", method = RequestMethod.GET)
	public String agentWeekInfoStaHistoryDetailUi(Model model,String date,String stadate, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("date", date);
			model.addAttribute("stadate", stadate);
			model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Run_Fast);
			return "/WEB-INF/view/web/agent_weekStaInfoHistoryDetail_list";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 *  代理商结算发放明细
	 *  //@Author:zun.wei, @Date:2017/4/7 15:02
	 * @param gridPager
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("agentWeekInfoStaHistoryDetail.html")
	@ResponseBody
	public Object agentWeekInfoStaHistoryDetail(String gridPager,HttpServletResponse response) throws Exception {
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
		return super.getWeekPayHistoryDetailInfoByDate(gridPager, response,
				ConstantUtil.GameTypeConstant.Run_Fast, taxesInviteWeekService);
	}

	  /**
     *  代理商自定义支付
     */
    @RequestMapping("getAgentByMid.html")
    @ResponseBody
    public Object getAgentByMid(int mid) throws Exception {
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);

		MemberAgents agent=memberAgentService.findByMid(mid);

		Map<String ,Object > map=new HashMap<String,Object>();

		if(null!=agent){
			map.put(CommonContants.SUCCESS, Boolean.TRUE);
			map.put(CommonContants.DATA, agent);
		}else{
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "数据不存在");
		}

        return map;
    }

    /**
     * 完成代理商自定义支付
     * @param mid
     * @param request
     * @return
     * @throws WeixinException
     */
    @ResponseBody
    @RequestMapping(value = "saveSimplePay.html")
    public Object saveSimplePay(int mid,BigDecimal money,String msg, HttpServletRequest request){
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
	    //自定义支付
        WeixinPayProxy PAY = null;
        WeixinPayAccount ACCOUNT = null;
        ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
        PAY = new WeixinPayProxy(ACCOUNT);
        Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = super.saveSimplePay(request,mid, money, msg, PAY,  taxesInviteWeekService, memberAgentService, payLog,ConstantUtil.GameType.Run_Fast);
		} catch (WeixinException e) {
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, e.getMessage());
			this.saveWeinMsg(mid,weinxinMsgService,ConstantUtil.GameType.Run_Fast, e);
			e.printStackTrace();
		}catch (Exception e) {   
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "支付失败");
			e.printStackTrace();
        }
	    return map;
    }



	/**
	 *  //@Author:zun.wei, @Date:2017/4/7 17:20
	 *  导出详情excel
	 * @param gridPager
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportDetailExcel.html")
	@ResponseBody
	public void exportDetailExcel(String gridPager,HttpServletResponse response,String date,String stadate) throws Exception {
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
		super.DetailExcelExport(stadate, ConstantUtil.GameTypeConstant.Run_Fast,date, response, taxesInviteWeekService);
	}
	
	/**
	 * 批量支付(0到500元)
	 * @param request
	 * @return
	 * @throws WeixinException
	 */
    @ResponseBody
    //@RequestMapping(value = "batchPay.html")
    public Object batchPay(HttpServletRequest request,String searchDate){
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);


		Map<String, Object> parameters = new HashMap<String, Object>();

		if (StringUtils.isBlank(searchDate)) {
			parameters.put("searchDate", BusinessDateUtil.getLastWeekSunday());
		}else{
			parameters.put("searchDate",searchDate);
		}
		parameters.put("dbTable", ConstantUtil.GameTypeConstant.Run_Fast + ".memberagents");
		
		List<Integer> failureLIst=redisTemplate.opsForValue().get(ConstantUtil.FAILURE_MID_LIST+ ConstantUtil.GameTypeConstant.Run_Fast);
		failureLIst=failureLIst=null!=failureLIst&&failureLIst.size()>0?failureLIst:null;
		parameters.put("failureList",failureLIst); 
		
		
	     WeixinPayProxy PAY = null;
	     WeixinPayAccount ACCOUNT = null;
	     ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
	     PAY = new WeixinPayProxy(ACCOUNT);
	     Map<String, Object> map = new HashMap<String, Object>();
		List<TaxesInviteWeek>  listInvite=taxesInviteWeekService.getWeekPayListByCondition(parameters);
		if(null!=listInvite&&listInvite.size()>0){
			for(TaxesInviteWeek record:listInvite){
				int mid=record.getMid();
				Date date=record.getDate();
				if(record.getRebatetotal().intValue()>500){
					continue;
				}
				if(ConstantUtil.NoPay.RUNFAST_MID.contains(mid+"")){
					this.saveFailureMidListCache(ConstantUtil.GameTypeConstant.Run_Fast, mid);
					continue;
				}
				try {
					Map<String,Object> paymap = super.weixinPay(request,mid, date, PAY, taxesInviteWeekService,
							memberAgentService, payLog,ConstantUtil.GameType.Run_Fast);
				} catch (WeixinException e) {
					this.saveFailureMidListCache(ConstantUtil.GameTypeConstant.Run_Fast, mid);
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.MESSAGE, e.getMessage());
					log.error("mid==========::"+mid);
					this.saveWeinMsg(mid,weinxinMsgService,ConstantUtil.GameType.Run_Fast, e);
					e.printStackTrace();
				}catch (Exception e) {   
					this.saveFailureMidListCache(ConstantUtil.GameTypeConstant.Run_Fast, mid);
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.MESSAGE, "支付失败");
					e.printStackTrace();
		        }
			}
		}
		
		map.put(CommonContants.SUCCESS, Boolean.TRUE);
		 map.put(CommonContants.MESSAGE, "支付成功");
		 
		return map;
    }
    
    private void saveFailureMidListCache(String gameType, Integer mid){
		List<Integer> failureLIst=redisTemplate.opsForValue().get(ConstantUtil.FAILURE_MID_LIST+gameType);
		if(null==failureLIst){
			failureLIst=new ArrayList<Integer>();
		}else{
			failureLIst.add(mid);
		}
		redisTemplate.opsForValue().set(ConstantUtil.FAILURE_MID_LIST+gameType, failureLIst,1,TimeUnit.DAYS);
	}
    
    
    /**
	 * 活动批量支付(0到500元)
	 * @param request
	 * @return
	 * @throws WeixinException
	 */
    @ResponseBody
    @RequestMapping(value = "activityBatchPay.html")
    public Object activityBatchPay(HttpServletRequest request,String searchDate){
		DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);


		Map<String, Object> parameters = new HashMap<String, Object>();

		if (StringUtils.isBlank(searchDate)) {
			parameters.put("searchDate", BusinessDateUtil.getLastWeekSunday());
		}else{
			parameters.put("searchDate",searchDate);
		}
		parameters.put("dbTable", ConstantUtil.GameTypeConstant.Run_Fast + ".memberagents");
		
	     WeixinPayProxy PAY = null;
	     WeixinPayAccount ACCOUNT = null;
	     ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
	     PAY = new WeixinPayProxy(ACCOUNT);
	     Map<String, Object> map = new HashMap<String, Object>();
		List<TaxesInviteWeekSend>  listInvite=taxesInviteWeekSendService.getWeekPayListByCondition(parameters);
		if(null!=listInvite&&listInvite.size()>0){
			for(TaxesInviteWeekSend record:listInvite){
				int mid=record.getMid();
				Date date=record.getDate();
				if(record.getRebatetotal().intValue()>500){
					continue;
				}
				if(ConstantUtil.NoPay.RUNFAST_MID.contains(mid+"")){
					continue;
				}
				try {
					Map<String,Object> paymap = super.activityWeixinPay(request,mid, date, PAY, taxesInviteWeekSendService,
							memberAgentService, payLog,ConstantUtil.GameType.Run_Fast);
				} catch (WeixinException e) {
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.MESSAGE, e.getMessage());
					log.error("mid==========::"+mid);
					this.saveWeinMsg(mid,weinxinMsgService,ConstantUtil.GameType.Run_Fast, e);
					e.printStackTrace();
				}catch (Exception e) {   
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.MESSAGE, "支付失败");
					e.printStackTrace();
		        }
			}
		}
		
		map.put(CommonContants.SUCCESS, Boolean.TRUE);
		 map.put(CommonContants.MESSAGE, "支付成功");
		 
		return map;
    }




	

}
