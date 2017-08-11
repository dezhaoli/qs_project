package com.qs.mainku.game.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.mainku.game.model.Commongame;
import com.qs.mainku.game.service.ICommonGameService;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/6/29 11:36.
 * Description:金币表控制层
 */
@Controller
@RequestMapping(value = "/api/activity/")
public class CommonGameController extends BaseController {

    @Resource
    private ICommonGameService commonGameService;

    /**
     * @Author:zun.wei , @Date:2017/6/29 11:42
     * @Description:从积分活动返回到客户端的时候刷新金币数量
     * @param baseRequest 基本请求
     * @return
     */
    @ResponseBody
    @RequestMapping("actiRefreshGoldStatus.do")
    public Object actiRefreshGoldStatus(BaseRequest baseRequest) {
        AccessToken token= ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Commongame commongame = commonGameService.selectByPrimaryKey(token.getMid());
        if (commongame == null) return this.getReturnData(null, AppConstants.Result.FAILURE);
        return this.getReturnData(commongame, AppConstants.Result.SUCCESS);
    }


}
