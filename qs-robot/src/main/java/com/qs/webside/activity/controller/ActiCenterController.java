package com.qs.webside.activity.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.util.DateUtil;
import com.qs.mainku.game.service.IBaseParamService;
import com.qs.webside.activity.service.IActiCenterService;
import com.qs.webside.activity.service.IActiSendGoldService;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zun.wei on 2017/5/31 14:18.
 * Description:活动中心控制器
 */
@Controller
@RequestMapping(value = "/api/activity/")
public class ActiCenterController extends BaseController {


    @Value("${game.gametype}")
    private int gameType;

    //@Value("${app.interfaceSendGold.url}")
    //private String sendGoldUrl;

    @Resource
    private IBaseParamService baseParamService;

    @Resource
    private IActiCenterService actiCenterService;

    @Resource
    private IActiSendGoldService actiSendGoldService;

    /**
     * @Author:zun.wei , @Date:2017/6/1 14:44
     * @Description:获取启用状态下的活动中心列表
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("getActivityCenterData.do")
    @ResponseBody
    public Object getActivityCenterData(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        List<Map<String,Object>> actiCenter = actiCenterService.queryListActivityByStatus();
        return this.getReturnData(actiCenter, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:19
     * @Description:查询用户是否评论过
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("checkUserIsComment.do")
    @ResponseBody
    public Object checkUserIsComment(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Object map = actiSendGoldService.checkUserIsComment(token.getMid());
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:20
     * @Description:根据评论发送金币
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("sendGoldByComment.do")
    @ResponseBody
    public Object sendGoldByComment(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        String sendGoldUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.APP_SEND_GOLD_INTERFACE_URL);
        Object map = actiSendGoldService.sendGoldByComment(token.getMid(),gameType,baseRequest.getSesskey(),sendGoldUrl);
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:20
     * @Description:根据分享链接发放金币
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("sendGoldByShare.do")
    @ResponseBody
    public Object sendGoldByShare(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        String sendGoldUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.APP_SEND_GOLD_INTERFACE_URL);
        Object map = actiSendGoldService.sendGoldByShare(token.getMid(),gameType,baseRequest.getSesskey(),sendGoldUrl);
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/7/12 18:08
     * @Description:活动倒计时剩余天数
     * @param request
     * @return
     */
    @RequestMapping("countdownToActivity.do")
    @ResponseBody
    public Object countdownToActivity(HttpServletRequest request) throws ParseException {
        String type = request.getParameter("actiType");
        int actiType = -1;
        if (StringUtils.isNotBlank(type)) actiType = Integer.parseInt(type);
        Map<String, Object> map = actiCenterService.queryListActivityByStatusAndType(actiType);
        Map<String, Object> result = new HashMap<>();
        if (map != null && map.size() > 0) {
            long endTime = (long) map.get("endTime") * 1000;
            long nowTime = new Date().getTime();
            int days = daysBetween(new Date(nowTime), new Date(endTime));
            result.put("time", days >= 0 ? days : 0);
            return this.getReturnData(result, AppConstants.Result.SUCCESS);
        }
        result.put("time", 0);
        return this.getReturnData(result, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/7/12 19:20
     * @Description:获取两个日期之间相差的天数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 两个时间相差的天数
     * @throws ParseException
     */
    private int daysBetween(Date startTime, Date endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        startTime = sdf.parse(sdf.format(startTime));
        endTime = sdf.parse(sdf.format(endTime));
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endTime);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

}
