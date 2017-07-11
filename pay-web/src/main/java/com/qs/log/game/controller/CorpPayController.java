package com.qs.log.game.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.WeixinPayAccount;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.qs.agent.game.model.MemberAgents;
import com.qs.agent.game.service.IMemberAgentService;
import com.qs.common.constant.CommonContants;
import com.qs.common.exception.SystemException;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.PageUtil;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInviteWeek;
import com.qs.log.game.service.ITaxesInviteWeekSendService;
import com.qs.log.game.service.ITaxesInviteWeekService;
import com.qs.log.game.util.BusinessDateUtil;
import com.qs.log.game.util.ConstantUtil;
import com.qs.pub.pay.service.IBaseParamService;
import com.qs.pub.pay.service.IPayLogService;
import com.qs.pub.pay.service.IWeinxinMsgService;
/**
 * 
 * @ClassName: CorpPayController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年4月29日 下午1:36:49
 */
@Controller
@RequestMapping(value = "/corpPay/")
public class CorpPayController extends PayBaseController {
	

	Logger log = Logger.getLogger(CorpPayController.class);

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
	private IBaseParamService baseParamService;
	
	
	@Autowired
	private RedisTemplate<String,List<Integer>> redisTemplate;

   
    /**
     * 
     * @标题: agentWeekInfoStaUi 
     * @描述: 跳转到企业支付列表
     *
     * @参数信息
     *    @param gameType
     *    @param model
     *    @param request
     *    @return
     *
     * @返回类型 String
     * @开发者 qs
     * @可能抛出异常
     */
    @RequestMapping(value = "agentCorpPayListUi.html", method = RequestMethod.GET)
    public String agentWeekInfoStaUi(String gameType,Model model, HttpServletRequest request) {
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
            model.addAttribute("gameType",gameType);
            return "/WEB-INF/view/web/pay/agent_corpPay_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }


    /**
     * 
     * @标题: agentWeekInfoSta 
     * @描述: 企业支付列表接口
     *
     * @参数信息
     *    @param gridPager
     *    @param response
     *    @return
     *    @throws Exception
     *
     * @返回类型 Object
     * @开发者 qs
     * @可能抛出异常
     */
    @RequestMapping("agentCorpPayList.html")
    @ResponseBody
    public Object agentWeekInfoSta(String gameType,String gridPager, HttpServletResponse response) throws Exception {
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		
		//DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Run_Fast_Log);
		//DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Run_Fast_Main);
		Map<String,Object> map = super.getAgentWeekInfoSta(gridPager, response,
				gameType, taxesInviteWeekService);
        return map;
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
    public Object confirmPay(String gameType,String openid, Date date, Integer mid, HttpServletRequest request){
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
    	String appid=baseParamService.getBaseParamValueByCode(gameType+".appid");
    	String apikey=baseParamService.getBaseParamValueByCode(gameType+".apikey");
    	String mchid=baseParamService.getBaseParamValueByCode(gameType+".mchid");
    	String certfile=baseParamService.getBaseParamValueByCode(gameType+".certfile");
    	String gameNo=baseParamService.getBaseParamValueByCode(gameType+".gameType");
    	
	    WeixinPayProxy PAY = null;
        WeixinPayAccount ACCOUNT = null;

    	
        ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
        PAY = new WeixinPayProxy(ACCOUNT);
        Map<String, Object> map = new HashMap<String, Object>();
    	if(CommonUtils.checkNull(ConstantUtil.getNoPayMid(gameType)).contains(mid+"")){
    		this.saveFailureMidListCache(gameType, mid);
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "不需要返利的代理商");
			return map;
		}
		try {
			map = super.weixinPay(request, mid, date, PAY, taxesInviteWeekService, memberAgentService, payLog,Integer.parseInt(gameNo));
		} catch (WeixinException e) {
			this.saveFailureMidListCache(gameType, mid);
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, e.getMessage());
			this.saveWeinMsg(mid,weinxinMsgService,Integer.parseInt(gameNo), e);
			e.printStackTrace();
		}catch (Exception e) {   
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "支付失败");
			e.printStackTrace();
        }
	    return map;
    }
    
 
	/**
	 * 
	 * @标题: agentWeekInfoStaHistoryUi 
	 * @描述: (代理商历史结算发放列表
	 *
	 * @参数信息
	 *    @param model
	 *    @param request
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 qs
	 * @可能抛出异常
	 */
	@RequestMapping(value = "agentWeekInfoStaHistoryUi.html", method = RequestMethod.GET)
	public String agentWeekInfoStaHistoryUi(String gameType,Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("gameType", gameType);
			return "/WEB-INF/view/web/pay/agent_weekStaHistoryInfo_list";
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
	public Object agentWeekInfoStaHistory(String gameType,String gridPager) throws Exception {
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);

		return super.getAgentWeekInfoStaHistory(gridPager,taxesInviteWeekService);
	}

	/**
	 * 
	 * @标题: agentWeekInfoStaHistoryDetailUi 
	 * @描述: 跳转到代理商结算发放明细界面
	 *
	 * @参数信息
	 *    @param model
	 *    @param date
	 *    @param stadate
	 *    @param request
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 qs
	 * @可能抛出异常
	 */
	@RequestMapping(value = "agentWeekInfoStaHistoryDetailUi.html", method = RequestMethod.GET)
	public String agentWeekInfoStaHistoryDetailUi(String gameType,Model model,String date,String stadate, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("date", date);
			model.addAttribute("stadate", stadate);
			model.addAttribute("gameType", gameType);
			return "/WEB-INF/view/web/pay/agent_weekStaInfoHistoryDetail_list";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 
	 * @标题: agentWeekInfoStaHistoryDetail 
	 * @描述: 代理商结算发放明细
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @param response
	 *    @return
	 *    @throws Exception
	 *
	 * @返回类型 Object
	 * @开发者 qs
	 * @可能抛出异常
	 */
	@RequestMapping("agentWeekInfoStaHistoryDetail.html")
	@ResponseBody
	public Object agentWeekInfoStaHistoryDetail(String gameType,String gridPager,HttpServletResponse response) throws Exception {
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		return super.getWeekPayHistoryDetailInfoByDate(gridPager, response,
				gameType, taxesInviteWeekService);
	}
	
	/**
     * 跳转到代理商自定义支付列表UI
     */
    @RequestMapping(value = "agentSimplePayUi.html", method = RequestMethod.GET)
    public String agentSinglePayUi(Model model, HttpServletRequest request) {
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
           // model.addAttribute("gameType", gameType);
            return "/WEB-INF/view/web/pay/agent_simplePay_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

	 /**
	  * 
	  * @标题: getAgentByMid 
	  * @描述: 自定义支付查询代理商数据接口
	  *
	  * @参数信息
	  *    @param mid
	  *    @return
	  *    @throws Exception
	  *
	  * @返回类型 Object
	  * @开发者 qs
	  * @可能抛出异常
	  */
    @RequestMapping("getAgentByMid.html")
    @ResponseBody
    public Object getAgentByMid(String gameType,int mid) throws Exception {
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);

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
     * 代理商自定义支付接口
     * @param mid
     * @param request
     * @return
     * @throws WeixinException
     */
    @ResponseBody
    @RequestMapping(value = "saveSimplePay.html")
    public Object saveSimplePay(String gameType,int mid,BigDecimal money,String msg, HttpServletRequest request){
    	
    	String appid=baseParamService.getBaseParamValueByCode(gameType+".appid");
    	String apikey=baseParamService.getBaseParamValueByCode(gameType+".apikey");
    	String mchid=baseParamService.getBaseParamValueByCode(gameType+".mchid");
    	String certfile=baseParamService.getBaseParamValueByCode(gameType+".certfile");
    	String gameNo=baseParamService.getBaseParamValueByCode(gameType+".gameType");
    	
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
	    //自定义支付
        WeixinPayProxy PAY = null;
        WeixinPayAccount ACCOUNT = null;
        ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
        PAY = new WeixinPayProxy(ACCOUNT);
        Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = super.saveSimplePay(request,mid, money, msg, PAY,  taxesInviteWeekService, memberAgentService, payLog,Integer.parseInt(gameNo));
		} catch (WeixinException e) {
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, e.getMessage());
			this.saveWeinMsg(mid,weinxinMsgService,Integer.parseInt(gameNo), e);
			e.printStackTrace();
		}catch (Exception e) {   
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.MESSAGE, "支付失败");
			e.printStackTrace();
        }
	    return map;
    }



	/**
	 * 
	 * @标题: exportDetailExcel 
	 * @描述: 导出明细EXCEL接口
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @param response
	 *    @param date
	 *    @param stadate
	 *    @throws Exception
	 *
	 * @返回类型 void
	 * @开发者 qs
	 * @可能抛出异常
	 */
	@RequestMapping("exportDetailExcel.html")
	@ResponseBody
	public void exportDetailExcel(String gameType,String gridPager,HttpServletResponse response,String date,String stadate) throws Exception {
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		super.DetailExcelExport(stadate, gameType,date, response, taxesInviteWeekService);
	}
	
	/**
	 * 批量支付(0到500元)接口
	 * @param request
	 * @return
	 * @throws WeixinException
	 */
    @ResponseBody
    @RequestMapping(value = "batchPay.html")
    public Object batchPay(String gameType,HttpServletRequest request,String searchDate){
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		String appid=baseParamService.getBaseParamValueByCode(gameType+".appid");
    	String apikey=baseParamService.getBaseParamValueByCode(gameType+".apikey");
    	String mchid=baseParamService.getBaseParamValueByCode(gameType+".mchid");
    	String certfile=baseParamService.getBaseParamValueByCode(gameType+".certfile");
    	String gameNo=baseParamService.getBaseParamValueByCode(gameType+".gameType");

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (StringUtils.isBlank(searchDate)) {
			parameters.put("searchDate", BusinessDateUtil.getLastWeekSunday());
		}else{
			parameters.put("searchDate",searchDate);
		}
		parameters.put("dbTable", gameType + ".memberagents");
		
		List<Integer> failureLIst=redisTemplate.opsForValue().get(ConstantUtil.FAILURE_MID_LIST+gameType);
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
				if(CommonUtils.checkNull(ConstantUtil.getNoPayMid(gameType)).contains(mid+"")){
					this.saveFailureMidListCache(gameType, mid);
					continue;
				}
				try {
					Map<String,Object> paymap = super.weixinPay(request,mid, date, PAY, taxesInviteWeekService,
							memberAgentService, payLog,Integer.parseInt(gameNo));
				} catch (WeixinException e) {
					this.saveFailureMidListCache(gameType, mid);
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.MESSAGE, e.getMessage());
					log.error("mid==========::"+mid);
					this.saveWeinMsg(mid,weinxinMsgService,Integer.parseInt(gameNo), e);
					e.printStackTrace();
				}catch (Exception e) {   
					this.saveFailureMidListCache(gameType, mid);
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
    
    
   
	

}
