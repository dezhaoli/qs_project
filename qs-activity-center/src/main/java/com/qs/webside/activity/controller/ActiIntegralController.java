package com.qs.webside.activity.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.service.IActiIntegralService;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by zun.wei on 2017/6/9 10:19.
 * Description:活动中心积分表
 */
@Controller
@RequestMapping(value = "/api/actiIntegral/")
public class ActiIntegralController extends BaseController {

    @Resource
    private IActiIntegralService actiIntegralService;


    /**
     * @Author:zun.wei , @Date:2017/6/9 13:51
     * @Description:获取积分排行榜
     * @return
     */
    @ResponseBody
    @RequestMapping("rankingList.do")
    public Object getActiIntegralRankingList(BaseRequest baseRequest) {
        List<Map<String,Object>> actiIntegralList = actiIntegralService.queryListByPage(new HashMap<>());
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("actiIntegralList", actiIntegralList);
        if (actiIntegralList == null) {
            return this.getReturnData(returnMap, AppConstants.Result.FAILURE);
        } else {
            return this.getReturnData(returnMap, AppConstants.Result.SUCCESS);
        }
    }


    /**
     * @Author:zun.wei , @Date:2017/6/12 10:05
     * @Description:根据用户mid获取对应的积分对象
     * @param baseRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("getRankingByUserMid.do")
    public Object getRankingByUserMid(BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        ActiIntegral actiIntegral = actiIntegralService.selectByMid(token.getMid());
        if (actiIntegral == null) {
            return this.getReturnData(actiIntegral, AppConstants.Result.FAILURE);
        } else {
            return this.getReturnData(actiIntegral, AppConstants.Result.SUCCESS);
        }
    }

    /**
     * @Author:zun.wei , @Date:2017/7/7 13:33
     * @Description:分享链接送积分
     * @param baseRequest 积分请求
     * @return
     */
    @RequestMapping("sendIntegralByShare.do")
    @ResponseBody
    public Object sendIntegralByShare(BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Object map = actiIntegralService.sendIntegralByShare(token.getMid());
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/7/10 16:02
     * @Description:消耗房卡送积分(即：积分福利活动,actiTYpe = 10)
     * @param baseRequest
     * @return
     */
    @RequestMapping("useGoldToSendIntegral.do")
    @ResponseBody
    public Object useGoldToSendIntegral(BaseRequest baseRequest, HttpServletRequest request) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        String type = request.getParameter("cfgType");
        int cfgType = -1;
        if (StringUtils.isNotBlank(type)) cfgType = Integer.parseInt(type);
        Map<String, Object> map = actiIntegralService.useGoldToSendIntegral(token.getMid(),cfgType);
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/7/10 16:22
     * @Description:检查用户房卡消耗情况，消耗房卡送积分(即：积分福利活动,actiTYpe = 10)
     * @param baseRequest
     * @return
     */
    @RequestMapping("checkUseGoldToSendIntegral.do")
    @ResponseBody
    public Object checkUseGoldToSendIntegral(BaseRequest baseRequest) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Map<String, Object> map = actiIntegralService.checkUseGoldToSendIntegral(token.getMid());
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

}
