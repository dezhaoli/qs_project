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
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Column;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.pay.PayUtil;
import com.qs.common.util.DateUtil;
import com.qs.log.game.model.TaxesDirectlyWeek;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import com.qs.log.game.service.ITaxesInviteWeekService;
import com.qs.log.game.util.BusinessDateUtil;
import com.qs.log.game.util.MyExportUtils;
import com.qs.webside.pay.service.IPayLogService;
import com.qs.webside.sys.model.UserEntity;
import com.qs.webside.pay.model.PayLog;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *  //@Author:zun.wei, @Date:2017/4/7 9:49
 *  支付基本控制器
 * Created by zun.wei on 2017/4/7.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
public class PayBaseController extends BaseController {


    /**
     *  微信企业支付
     * @param mid
     * @param date
     * @param request
     * @param appid
     * @param apikey
     * @param mchid
     * @param certificateKey
     * @param certfile
     * @param taxesDirectlyWeekService
     * @param memberAgentService
     * @param payLog
     * @return
     * @throws WeixinException
     */
    protected Map<String, Object> weixinPay(Integer mid, Date date, HttpServletRequest request, String appid
            , String apikey, String mchid, String certificateKey, String certfile
            ,ITaxesDirectlyWeekService taxesDirectlyWeekService,IMemberAgentService memberAgentService,IPayLogService payLog,int gameType) throws WeixinException {

        TaxesDirectlyWeek tdwRecord = new TaxesDirectlyWeek();
        Map<String, Object> map = new HashMap<String, Object>();
        tdwRecord.setMid(mid);
        tdwRecord.setDate(date);
        TaxesDirectlyWeek qRecord = taxesDirectlyWeekService.findTaxesDirectlyWeekByCondition(tdwRecord);
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

        if (qRecord.getRebatetotal() < 1) {
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

        if(StringUtils.isBlank(agent.getOpenid())){
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.MESSAGE, "openid为空");
            return map;
        }
        String ip = PayUtil.getLocalIp(request);
        if (null != ip && ip.contains("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        String tranNo = PayUtil.getTransferNo();
        CorpPayment payment = new CorpPayment(tranNo, agent.getOpenid(),
                CorpPaymentCheckNameType.NO_CHECK, "推广费用", qRecord.getRebatetotal(), ip);

        WeixinPayProxy PAY = null;
        WeixinPayAccount ACCOUNT = null;

        ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
        PAY = new WeixinPayProxy(ACCOUNT);
        //设置预支付日志
        int payLogId = this.savePayLog(qRecord, agent, ip, payLog,gameType);

        if (payLogId <= 0) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.MESSAGE, "重复支付");
            return map;
        }


        CorpPaymentResult result = PAY.sendCorpPayment(payment);
        System.out.println("getReturnCode=============::"+result.getReturnCode());
        int c = 0;
        if (null != result && "SUCCESS".equals(result.getReturnCode())) {
            TaxesDirectlyWeek updateRecord = new TaxesDirectlyWeek();
            updateRecord.setMid(qRecord.getMid());
            updateRecord.setDate(qRecord.getDate());
            c = taxesDirectlyWeekService.updateIsawardByCondition(updateRecord);
            //日志
            PayLog log = new PayLog();
            log.setId(payLogId);
            log.setExtend2(tranNo);
            //支付成功日志状态
            log.setStatus(1);
            int logrecord = payLog.updateByPrimaryKeySelective(log);
        }
        map.put(CommonContants.SUCCESS, Boolean.TRUE);
        map.put(CommonContants.MESSAGE, "支付成功");
        return map;
    }
    
    
    
    /**
     *  自定义支付
     * @param mid
     * @param request
     * @param appid
     * @param apikey
     * @param mchid
     * @param certificateKey
     * @param certfile
     * @param taxesDirectlyWeekService
     * @param memberAgentService
     * @param payLog
     * @return
     * @throws WeixinException
     */
    protected Map<String, Object> saveSimplePay(int mid, int money, HttpServletRequest request, String appid
            , String apikey, String mchid, String certificateKey, String certfile
            ,ITaxesDirectlyWeekService taxesDirectlyWeekService,IMemberAgentService memberAgentService,IPayLogService payLog,int gameType) throws WeixinException {

        TaxesDirectlyWeek tdwRecord = new TaxesDirectlyWeek();
        Map<String, Object> map = new HashMap<String, Object>();
        tdwRecord.setMid(mid);
       
        MemberAgents agent = memberAgentService.findByMid(mid);

        if (null == agent) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.MESSAGE, "不是代理商");
            return map;
        }

        if(StringUtils.isBlank(agent.getOpenid())){
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.MESSAGE, "openid为空");
            return map;
        }
        
        if(money<1){
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.MESSAGE, "支付金额小于1元");
            return map;
        }
        
        if(money>20){
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.MESSAGE, "支付金额大于20元");
            return map;
        }
        
        String ip = PayUtil.getLocalIp(request);
        if (null != ip && ip.contains("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        String tranNo = PayUtil.getTransferNo();
        CorpPayment payment = new CorpPayment(tranNo, agent.getOpenid(),
                CorpPaymentCheckNameType.NO_CHECK, "推广费用", money, ip);

        WeixinPayProxy PAY = null;
        WeixinPayAccount ACCOUNT = null;

        ACCOUNT = new WeixinPayAccount(appid, apikey, mchid, "", certfile);
        PAY = new WeixinPayProxy(ACCOUNT);
        //设置预支付日志

        CorpPaymentResult result = PAY.sendCorpPayment(payment);
        System.out.println("getReturnCode=============::"+result.getReturnCode());
        int c = 0;
        if (null != result && "SUCCESS".equals(result.getReturnCode())) {
            //日志
        	this.saveSimplePayLog(agent, money, ip, payLog,gameType);
        }
        map.put(CommonContants.SUCCESS, Boolean.TRUE);
        map.put(CommonContants.MESSAGE, "支付成功");
        return map;
    }

    private int saveSimplePayLog(MemberAgents agent,int money, String ip,IPayLogService payLog,int gameType) {
        PayLog log = new PayLog();
        log.setMid(agent.getMid());
        log.setAddDate(DateUtil.getNowDate());
        log.setGameType(gameType);
        log.setRebatetotal(money);
        //预支付日志状态
        log.setStatus(1);
        log.setIp(ip);
        log.setcCreateTime(new Date());
        log.setExtend1(agent.getOpenid());
        log.setExtend2("1");
        //获取登录的bean
        UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
        log.setcCreatorId(userEntity.getId().intValue());
        int c = payLog.insert(log);
        if (c > 0) {
            return log.getId();
        } else {
            return 0;
        }
    }
    

    private int savePayLog(TaxesDirectlyWeek qRecord, MemberAgents agent, String ip,IPayLogService payLog,int gameType) {
        PayLog log = new PayLog();
        log.setMid(qRecord.getMid());
        log.setAddDate(qRecord.getDate());
        log.setGameType(gameType);
        log.setRebatetotal(qRecord.getRebatetotal());
        //预支付日志状态
        log.setStatus(0);
        log.setIp(ip);
        log.setcCreateTime(new Date());
        log.setExtend1(agent.getOpenid());
        //获取登录的bean
        UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
        log.setcCreatorId(userEntity.getId().intValue());
        int c = payLog.insert(log);
        if (c > 0) {
            return log.getId();
        } else {
            return 0;
        }
    }

    /**
     *  代理商周信息统计
     * @param gridPager
     * @param response
     * @param gameType
     * @param taxesDirectlyWeekService
     * @return
     * @throws Exception
     */
    protected Map<String, Object> getAgentWeekInfoSta(String gridPager, HttpServletResponse response, String gameType
    ,ITaxesDirectlyWeekService taxesDirectlyWeekService) throws Exception {
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
        parameters.put("dbTable", gameType + ".memberagents");
        if(pager.getIsExport()){//判断是否是导出操作
            if(pager.getExportAllData()){
                //3.1、导出全部数据
                List<Map<String,Object>> list = taxesDirectlyWeekService.getWeekPayinfoByDate(parameters);
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
        List<Map<String,Object>> list = taxesDirectlyWeekService.getWeekPayinfoByDate(parameters);
        return getReturnPage(pager, page, list);
    }


    /**
     *  代理商周信息统计
     * @param gridPager
     * @param response
     * @param gameType
     * @param taxesDirectlyWeekService
     * @return
     * @throws Exception
     */
    protected Map<String, Object> getWeekPayHistoryDetailInfoByDate(String gridPager, HttpServletResponse response, String gameType
            ,ITaxesDirectlyWeekService taxesDirectlyWeekService) throws Exception {
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
        parameters.put("dbTable", gameType + ".memberagents");
        if(pager.getIsExport()){//判断是否是导出操作
            if(pager.getExportAllData()){
                //3.1、导出全部数据
                List<Map<String,Object>> list = taxesDirectlyWeekService.getWeekPayHistoryDetailInfoByDate(parameters);
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
        List<Map<String,Object>> list = taxesDirectlyWeekService.getWeekPayHistoryDetailInfoByDate(parameters);
        return getReturnPage(pager, page, list);
    }


    /**
     * 代理商历史结算发放列表
     * //@Author:zun.wei, @Date:2017/4/7 14:02
     * @param gridPager
     * @param taxesInviteWeekService
     * @return
     * @throws Exception
     */
    protected Map<String, Object> getAgentWeekInfoStaHistory(String gridPager
            ,ITaxesInviteWeekService taxesInviteWeekService) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = taxesInviteWeekService.getHistoryAgentsRebateList(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     *  详情excel导出
     * @param stadate
     * @param gameType
     * @param response
     * @param taxesDirectlyWeekService
     * @return
     * @throws Exception
     */
    protected void DetailExcelExport(String stadate,String gameType,String searchDate,
                          HttpServletResponse response,ITaxesDirectlyWeekService taxesDirectlyWeekService) throws Exception {
        Pager pager = new Pager();
        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setId("mid");
        column.setTitle("MID");
        columns.add(column);
        Column column1 = new Column();
        column1.setId("openid");
        column1.setTitle("微信openid");
        columns.add(column1);
        Column column2 = new Column();
        column2.setId("mktime");
        column2.setTitle("加入时间");
        columns.add(column2);
        Column column3 = new Column();
        column3.setId("realname");
        column3.setTitle("真实姓名");
        columns.add(column3);
        Column column4 = new Column();
        column4.setId("phone");
        column4.setTitle("联系电话");
        columns.add(column4);
        Column column5 = new Column();
        column5.setId("rebatetotal");
        column5.setTitle("本周返利金额");
        columns.add(column5);
        Column column6 = new Column();
        column6.setId("bindpeople");
        column6.setTitle("本周招募人数");
        columns.add(column6);
        Column column7 = new Column();
        column7.setId("paycount");
        column7.setTitle("首冲人数");
        columns.add(column7);
        Column column8 = new Column();
        column8.setId("isaward");
        column8.setTitle("返现状态");
        columns.add(column8);

        pager.setExportColumns(columns);//pager参数
        pager.setIsExport(true);
        pager.setExportType("EXCEL");
        pager.setExportFileName(stadate);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dbTable", gameType + ".memberagents");
        parameters.put("searchDate", searchDate);
        List<Map<String,Object>> list = taxesDirectlyWeekService.getWeekPayHistoryDetailInfoByDate(parameters);
        MyExportUtils.exportAll(response, pager, list);
    }

}
