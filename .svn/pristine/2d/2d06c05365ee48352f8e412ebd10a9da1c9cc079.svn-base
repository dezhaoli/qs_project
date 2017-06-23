package com.qs.webside.activity.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.webside.activity.model.ActiAward;
import com.qs.webside.activity.model.ActiAwardAddress;
import com.qs.webside.activity.model.ActiAwardRecord;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.service.IActiAwardAddressService;
import com.qs.webside.activity.service.IActiAwardRecordService;
import com.qs.webside.activity.service.IActiAwardService;
import com.qs.webside.activity.service.IActiIntegralService;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.game.service.GameService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/7 15:21.
 * Description:活动中心奖品兑换记录表
 */
@Controller
@RequestMapping(value = "/api/actiAwardRecord/")
public class ActiAwardRecordController extends BaseController {

    @Resource
    private IActiAwardRecordService actiAwardRecordService;

    @Resource
    private IActiAwardService actiAwardService;

    @Resource
    private IActiIntegralService actiIntegralService;

    @Resource
    private IActiAwardAddressService actiAwardAddressService;

    @Resource
    private GameService gameService;

    /**
     * @param request
     * @param baseRequest
     * @return
     * @Author:zun.wei , @Date:2017/6/8 13:59
     * @Description:活动中心积分兑换奖品接口
     */
    @RequestMapping("exchangePrizes.do")
    @ResponseBody
    public Object addExchangePrizes(HttpServletRequest request, BaseRequest baseRequest) {
        int id = Integer.valueOf(request.getParameter("id"));
        int number = Integer.valueOf(request.getParameter("number"));//兑换个数默认1
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        int mid = token.getMid();
        ActiAward actiAward = actiAwardService.selectByIdLimitByActiTime(id);//奖品(如果活动时间截止，就查询不到商品)
        if (actiAward == null) return this.getReturnData(null, AppConstants.Result.FAILURE);
        if (!"1".equals(actiAward.getRemark())) {//如果不是兑换房卡的商品
            ActiAwardAddress actiAwardAddress = actiAwardAddressService.selectByMidKey(mid);//收获地址
            if (actiAwardAddress == null) return this.getReturnData(null, AppConstants.Result.FAILURE_1002);
        }
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("actiType", actiAward.getType());
        parameterMap.put("awardId", actiAward.getId());
        long hashExAwards = actiAwardRecordService.checkAwardRecordSumByActiType(parameterMap);//已兑换奖品个数
        long awards = actiAward.getAwardNum();//奖品总数
        if (number < 1) return this.getReturnData(null,  AppConstants.Result.FAILURE_1003);
        return verifyPrizeQuantity(number, mid, actiAward, hashExAwards, awards);
    }

    /**
     * 获取当前用户已兑换列表信息
     *
     * @param model
     * @param request
     * @param baseRequest
     * @return
     * @author:zyy
     * @time:2017年6月7日
     */
    @ResponseBody
    @RequestMapping("getConvertedInfo.do")
    public Object getActivityCenterData(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        List<Map<String, Object>> actiAwardRecord = actiAwardRecordService.selectByMidKey(token.getMid());
        return this.getReturnData(actiAwardRecord, AppConstants.Result.SUCCESS);
    }

    /**
     * @param number       兑换个数
     * @param mid          兑换人mid
     * @param actiAward    奖品对象
     * @param hashExAwards 已经兑换的奖品个数
     * @param awards       奖品总数
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:56
     * @Description:验证奖品数量
     */
    private Object verifyPrizeQuantity(int number, int mid, ActiAward actiAward, long hashExAwards, long awards) {
        if (verifyUserReceNum(mid, actiAward)) return this.getReturnData(null,  AppConstants.Result.FAILURE_1004);
        if ((awards - hashExAwards) >= number) {//判断奖品数量是否充足
            ActiIntegral actiIntegral = actiIntegralService.selectByMid(mid);
            return verifyIntegralObject(number, mid, actiAward, actiIntegral);
        } else {//奖品不足
            return this.getReturnData(null,  AppConstants.Result.FAILURE_1005);
        }
    }

    /**
     * @Author:zun.wei , @Date:2017/6/23 10:55
     * @Description: 验证用户可领取次数是否达到上限
     * @param mid 用户mid
     * @param actiAward 奖品对象
     * @return
     */
    private boolean verifyUserReceNum(int mid, ActiAward actiAward) {
        Integer limitNum = actiAward.getLimitNum();//是否打开限制领取，0表示不限制，1表示限制
        if (limitNum == null) limitNum = 0;
        if (limitNum != 0) {
            Integer receiveNum = actiAward.getReceiveNum();//可领取次数
            if (receiveNum == null) receiveNum = 0;
            int hasReceNum = actiAwardRecordService.countAwardNumber
                    (new ActiAwardRecord(actiAward.getId(), mid));//用户已兑换个数
            if (receiveNum <= hasReceNum) {//用户已兑换大于等于可领取次数时
                return true;
            }
        }
        return false;
    }

    /**
     * @param number       兑换奖品个数
     * @param mid          兑换人mid
     * @param actiAward    奖品对象
     * @param actiIntegral 积分对象
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:54
     * @Description:验证积分对象是否存在
     */
    private Object verifyIntegralObject(int number, int mid, ActiAward actiAward, ActiIntegral actiIntegral) {
        if (actiIntegral != null) {
            long nowIntegral = actiIntegral.getNowIntegral();
            long needIntegral = actiAward.getIntegral();
            return verifyIntegral(number, mid, actiAward, actiIntegral, nowIntegral, needIntegral);
        } else {//当前人没有积分对象
            return this.getReturnData(null,  AppConstants.Result.FAILURE_1006);
        }
    }

    /**
     * @param number       兑换奖品个数
     * @param mid          兑换人mid
     * @param actiAward    奖品对象
     * @param actiIntegral 积分对象
     * @param nowIntegral  现存积分
     * @param needIntegral 所需积分
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:52
     * @Description: 验证积分是否充足
     */
    private Object verifyIntegral(int number, int mid, ActiAward actiAward, ActiIntegral actiIntegral, long nowIntegral, long needIntegral) {
        if (nowIntegral >= needIntegral * number) {//比较现存积分与需要的积分值
            long usedIntegral = actiIntegral.getUsedIntegral();
            nowIntegral = nowIntegral - needIntegral * number;
            usedIntegral = usedIntegral + needIntegral * number;
            actiIntegral.setNowIntegral(nowIntegral);
            actiIntegral.setUsedIntegral(usedIntegral);
            int resultAddIntegral = actiIntegralService.updateByPrimaryKey(actiIntegral);
            return updateInegral(number, mid, actiAward, resultAddIntegral);
        } else {//积分不足
            return this.getReturnData(null, AppConstants.Result.FAILURE_1007);
        }
    }

    /**
     * @param number            兑换奖品个数
     * @param mid               兑换人mid
     * @param actiAward         奖品对象
     * @param resultAddIntegral 更新积分结果影响到的条数
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:49
     * @Description:更新积分
     */
    private Object updateInegral(int number, int mid, ActiAward actiAward, int resultAddIntegral) {
        if (resultAddIntegral > 0) {//更新积分成功
            int result = actiAwardRecordService.insertSelective(preparationRecord(mid, actiAward, number));
            return addActiAwardRecord(mid, actiAward, result);
        } else {//更新积分失败
            return this.getReturnData(null, AppConstants.Result.FAILURE_1008);
        }
    }

    /**
     * @param mid       兑换人mid
     * @param actiAward 奖品
     * @param result    添加结果
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:48
     * @Description:添加到奖品兑换表
     */
    private Object addActiAwardRecord(int mid, ActiAward actiAward, int result) {
        if (result > 0) {//插入奖品兑换记录表成功
            return exchangeRoomCard(mid, actiAward, result);
        } else {//插入奖品兑换记录表失败
            return this.getReturnData(null, AppConstants.Result.FAILURE_1009);
        }
    }

    /**
     * @param mid       兑换人mid
     * @param actiAward 奖品对象
     * @param result    插入奖品兑换记录表条数
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:45
     * @Description:兑换房卡
     */
    private Object exchangeRoomCard(int mid, ActiAward actiAward, int result) {
        if ("1".equals(actiAward.getRemark()) && actiAward.getReview() == 0) {//如果兑换的是房卡类型且无需审核
            Map<String, Object> updateMap = gameService.updateGold(mid,
                    Integer.parseInt(actiAward.getDescr()),
                    AppConstants.GoldLogSourceType.INTEGRAL_SEND_GOLD);
            return sendRoomCard(result, updateMap);
        } else {
            return this.getReturnData(result, AppConstants.Result.SUCCESS);
        }
    }

    /**
     * @param result    插入奖品兑换记录表条数
     * @param updateMap 发送金币返回值map
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:43
     * @Description:发送房卡
     */
    private Object sendRoomCard(int result, Map<String, Object> updateMap) {
        if ((Boolean) updateMap.get(CommonContants.RESULT)) {
            return this.getReturnData(result, AppConstants.Result.SUCCESS);
        } else {//发放房卡失败
            return this.getReturnData(null, AppConstants.Result.FAILURE_1010);
        }
    }


    /**
     * @param mid       兑换人mid
     * @param actiAward 奖品
     * @param number    兑换个数
     * @return
     * @Author:zun.wei , @Date:2017/6/22 14:37
     * @Description:准备兑换记录对象
     */
    private ActiAwardRecord preparationRecord(int mid, ActiAward actiAward, int number) {
        ActiAwardRecord actiAwardRecord = new ActiAwardRecord();
        actiAwardRecord.setMid(mid);
        actiAwardRecord.setAwardId(actiAward.getId());
        actiAwardRecord.setName(actiAward.getName());
        actiAwardRecord.setIntegral(actiAward.getIntegral() * number);
        actiAwardRecord.setAwardNum(Long.parseLong(number + ""));
        actiAwardRecord.setIsReview(actiAward.getReview() == 0 ? 2 : 0);
        actiAwardRecord.setType(actiAward.getType());
        actiAwardRecord.setCreateTime(new Date());
        actiAwardRecord.setSendTime(actiAward.getReview() == 0 ? new Date() : null);
        return actiAwardRecord;
    }

}
