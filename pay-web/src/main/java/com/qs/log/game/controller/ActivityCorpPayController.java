package com.qs.log.game.controller;

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
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.WeixinPayAccount;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.qs.agent.game.service.IMemberAgentService;
import com.qs.common.constant.CommonContants;
import com.qs.common.exception.SystemException;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.PageUtil;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInviteWeekSend;
import com.qs.log.game.service.ITaxesInviteWeekSendService;
import com.qs.log.game.service.ITaxesInviteWeekService;
import com.qs.log.game.util.BusinessDateUtil;
import com.qs.log.game.util.ConstantUtil;
import com.qs.pub.pay.model.WeixinMsg;
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
public class ActivityCorpPayController extends PayBaseController {
	

	Logger log = Logger.getLogger(ActivityCorpPayController.class);

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
     * 代理商活动返利列表
     */
    @RequestMapping("agentActivityCorpPayList.html")
    @ResponseBody
    public Object agentActivitySta(String gameType,String gridPager, HttpServletResponse response) throws Exception {
       	String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		
		Map<String,Object> map = super.getAgentActivitySta(gridPager, response,gameType, taxesInviteWeekSendService);
		
        return map;
    }
    
    
    
    /**
     * 跳转到代理商活动返利列表
     */
    @RequestMapping(value = "agentActivityCorpPayListUi.html", method = RequestMethod.GET)
    public String agentActivityStaUi(String gameType,Model model, HttpServletRequest request) {
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
            return "/WEB-INF/view/web/pay/agent_activity_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
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
    public Object activityPay(String gameType,String openid, Date date, Integer mid, HttpServletRequest request){
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
			map = super.activityWeixinPay(request, mid, date, PAY, taxesInviteWeekSendService, memberAgentService, payLog,Integer.parseInt(gameNo));
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
    public Object activityBatchPay(String gameType,String type,String searchDate,HttpServletRequest request){
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
		parameters.put("type",type);
		
		parameters.put("dbTable", gameType+ ".memberagents");
		
		List<Integer> failureLIst=redisTemplate.opsForValue().get(ConstantUtil.FAILURE_MID_LIST+gameType);
		failureLIst=failureLIst=null!=failureLIst&&failureLIst.size()>0?failureLIst:null;
		parameters.put("failureList",failureLIst); 
		
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
				if(CommonUtils.checkNull(ConstantUtil.getNoPayMid(gameType)).contains(mid+"")){
					this.saveFailureMidListCache(gameType, mid);
					continue;
				}
				try {
					Map<String,Object> paymap = super.activityWeixinPay(request,mid, date, PAY, taxesInviteWeekSendService,
							memberAgentService, payLog,Integer.parseInt(gameNo));
				} catch (WeixinException e) {
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
    
    
    
   
	

}
