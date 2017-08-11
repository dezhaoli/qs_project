package com.qs.webside.activity.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.Constants;
import com.qs.webside.activity.model.ActiAward;
import com.qs.webside.activity.model.ActiAwardRecord;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.service.IActiAwardRecordService;
import com.qs.webside.activity.service.IActiAwardService;
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
 * Created by zun.wei on 2017/6/12 9:32.
 * Description:
 */
@Controller
@RequestMapping(value = "/api/actiAward/")
public class ActiAwardController extends BaseController {

    @Resource
    private IActiAwardService actiAwardService;

    /**
     * @Author:zun.wei , @Date:2017/6/23 15:15
     * @Description:获取商品列表信息
     * @param baseRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("getCommodityList.do")
    public Object getCommodityList(BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("mid", token.getMid());
        List<Map<String,Object>> actiAwardList = actiAwardService.queryListByPage(parameters);
        if (actiAwardList != null && actiAwardList.size() > 0) {
            return this.getReturnData(actiAwardList, AppConstants.Result.SUCCESS);
        } else {
            return this.getReturnData(actiAwardList, AppConstants.Result.FAILURE);
        }
    }


    /*@ResponseBody
    @RequestMapping("getCommodityList.do")
    public Object getCommodityList(BaseRequest baseRequest) {
        List<ActiAward> actiAwardList = actiAwardService.queryListByPage(new HashMap<>());
        if (actiAwardList == null) {
            return this.getReturnData(actiAwardList, AppConstants.Result.FAILURE);
        } else {
            AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
            ActiIntegral actiIntegral = actiIntegralService.selectByMid(token.getMid());
            if (actiIntegral == null) {
                for (ActiAward actiAward : actiAwardList) {
                    actiAward.setState(Constants.Award.LACK);
                }
                return this.getReturnData(actiAwardList, AppConstants.Result.SUCCESS);
            }
            for (ActiAward acta : actiAwardList) {
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("actiType", acta.getType());
                parameterMap.put("awardId", acta.getId());
                long hashExAwards = actiAwardRecordService.checkAwardRecordSumByActiType(parameterMap);//已兑换奖品个数
                int count = actiAwardRecordService.countAwardNumber(new ActiAwardRecord(acta.getId(), token.getMid()));
                if (hashExAwards < acta.getAwardNum()) {
                    if (acta.getIntegral() <= actiIntegral.getNowIntegral()) {
                        if (acta.getReceiveNum() == null) {
                            acta.setReceiveNum(0);
                        }
                        if (acta.getReceiveNum() > count) {
                            acta.setState(Constants.Award.CONVERT);
                        } else {
                            acta.setState(Constants.Award.CONVERTED);
                        }
                    } else {
                        acta.setState(Constants.Award.LACK);
                    }
                } else {
                    acta.setState(Constants.Award.CONVERTED);
                }
            }

            return this.getReturnData(actiAwardList, AppConstants.Result.SUCCESS);
        }
    }*/


}
