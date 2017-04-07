package com.qs.log.game.controller;

import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.qs.agent.game.model.MemberAgents;
import com.qs.agent.game.service.IMemberAgentService;
import com.qs.common.constant.CommonContants;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import com.qs.log.game.service.ITaxesInviteWeekService;
import com.qs.log.game.util.BusinessDateUtil;
import com.qs.log.game.util.ConstantUtil;
import com.qs.log.game.util.DataSourceSwitch;
import com.qs.webside.pay.service.IPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * //@Author:zun.wei, @Date:2017/4/5 11:20
 *  斗牛控制器
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/bull/")
public class BullController extends PayBaseController {

    @Resource
    private ITaxesDirectlyWeekService taxesDirectlyWeekService;

    @Resource
    private ITaxesInviteWeekService taxesInviteWeekService;

    @Resource
    private IMemberAgentService memberAgentService;

    @Autowired
    private IPayLogService payLog;

    @Value("${bull.appid}")
    private  String appid;

    @Value("${bull.mchid}")
    private  String mchid;

    @Value("${bull.key}")
    private  String apikey;

    @Value("${bull.certfile}")
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
            model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Bull);
            return "/WEB-INF/view/web/agent_weekStaInfo_list";
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
        DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Bull_Log);
        DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Bull_Main);
        return super.getAgentWeekInfoSta(gridPager, response,
                ConstantUtil.GameTypeConstant.Bull, taxesDirectlyWeekService);
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
    public Object confirmPay(String openid, Date date, Integer mid,HttpServletRequest request) throws WeixinException {
        DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Bull_Log);
        DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Bull_Main);
        return super.weixinPay(mid, date, request, appid, apikey, mchid, "",
                certfile, taxesDirectlyWeekService, memberAgentService, payLog,ConstantUtil.GameType.Bull);
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
            model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Bull);
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
        DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Bull_Log);
        DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Bull_Main);
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
            model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Bull);
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
        DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Bull_Log);
        DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Bull_Main);
        return super.getWeekPayHistoryDetailInfoByDate(gridPager, response,
                ConstantUtil.GameTypeConstant.Bull, taxesDirectlyWeekService);
    }

    /**
     *  代理商自定义支付
     */
    @RequestMapping("getAgentByMid.html")
    @ResponseBody
    public Object getAgentByMid(int mid) throws Exception {
        DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Bull_Log);
        DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Bull_Main);

		MemberAgents agent=memberAgentService.findByMid(mid);

		Map<String ,Object > map=new HashMap<String,Object>();

		if(null!=agent){
			map.put(CommonContants.SUCCESS, Boolean.TRUE);
			map.put(CommonContants.DATA, agent);
		}else{
			map.put(CommonContants.SUCCESS, Boolean.FALSE);
			map.put(CommonContants.DATA, agent);
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
    public Object saveSimplePay(int mid,int money, HttpServletRequest request) throws WeixinException {
        DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Bull_Log);
        DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Bull_Main);
	    //自定义支付
		Map<String,Object> map = super.saveSimplePay(mid, money, request, appid, apikey, mchid, "",
				certfile, taxesDirectlyWeekService, memberAgentService, payLog,ConstantUtil.GameType.Bull);
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
        DataSourceSwitch.setLogDataSourceType(ConstantUtil.LogDataSourceConstantKey.Bull_Log);
        DataSourceSwitch.setMainDataSourceType(ConstantUtil.MainDataSourceConstantKey.Bull_Main);
        super.DetailExcelExport(stadate, ConstantUtil.GameTypeConstant.Bull,date, response, taxesDirectlyWeekService);
    }


}
