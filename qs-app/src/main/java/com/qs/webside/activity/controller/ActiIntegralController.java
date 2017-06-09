package com.qs.webside.activity.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.service.IActiIntegralService;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        ActiIntegral actiIntegral = actiIntegralService.selectByMid(token.getMid());
        List<Map<String,Object>> actiIntegralList = actiIntegralService.queryListByPage(new HashMap<>());
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("userIntegral", actiIntegral);
        returnMap.put("actiIntegralList", actiIntegralList);
        if (actiIntegral == null || actiIntegralList == null) {
            return this.getReturnData(returnMap, AppConstants.Result.FAILURE);
        } else {
            return this.getReturnData(returnMap, AppConstants.Result.SUCCESS);
        }
    }


}
