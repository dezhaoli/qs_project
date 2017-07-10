package com.qs.webside.activity.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.service.IMemberFidesService;
import com.qs.webside.activity.mapper.ActiIntegralMapper;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.model.ActiSendIntegral;
import com.qs.webside.activity.service.IActiCenterService;
import com.qs.webside.activity.service.IActiIntegralCfgService;
import com.qs.webside.activity.service.IActiIntegralService;
import com.qs.webside.activity.service.IActiSendIntegralService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by zun.wei on 2017/6/8 19:10.
 * Description:活动中心积分表
 */
@Service
public class ActiIntegralServiceImpl implements IActiIntegralService {

    @Resource
    private ActiIntegralMapper actiIntegralMapper;

    @Resource
    private IMemberFidesService memberFidesService;

    @Resource
    private IActiSendIntegralService actiSendIntegralService;

    @Resource
    private IActiCenterService actiCenterService;

    @Resource
    private IActiIntegralCfgService actiIntegralCfgService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiIntegralMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiIntegral record) {
        return actiIntegralMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiIntegral record) {
        return actiIntegralMapper.insertSelective(record);
    }

    @Override
    public ActiIntegral selectByPrimaryKey(Integer id) {
        return actiIntegralMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiIntegral record) {
        return actiIntegralMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiIntegral record) {
        return actiIntegralMapper.updateByPrimaryKey(record);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/30 9:24
     * @Description:缓存积分排行榜，定时30分钟失效。
     * @param parameters
     * @return
     */
    @Override
    @Cacheable(value = {CacheConstan.INTEGRAL_RANKING_CACHE_NAME},key="#root.methodName+'SortList:'")
    public List<Map<String,Object>> queryListByPage(Map<String, Object> parameters) {
        List<Map<String, Object>> actiIntegralList = actiIntegralMapper.queryListByPage(parameters);
        List<Map<String, Object>> returnList = new ArrayList<>();
        if (actiIntegralList != null && actiIntegralList.size() > 0) {
            for (Map<String, Object> actiIntegral : actiIntegralList) {
                MemberFides memberFides = memberFidesService.selectByPrimaryKey(Integer.parseInt(actiIntegral.get("mid") + ""));
                if (memberFides != null) {
                    actiIntegral.put("name", memberFides.getName());
                    actiIntegral.put("icon", memberFides.getIcon());
                }
                returnList.add(actiIntegral);
            }
        }
        return returnList;
    }

    @Override
    public ActiIntegral selectByMid(Integer mid) {
        return actiIntegralMapper.selectByMid(mid);
    }

    @Override
    public Object sendIntegralByShare(int mid) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> actiCenter = actiCenterService.queryListActivityByStatusAndType(8);//分享链接送积分类型为8
        if (actiCenter != null) {
            int startTime = 0;
            int endTime = 0;
            if (actiCenter.get("startTime") != null) startTime = Integer.parseInt(actiCenter.get("startTime") + "");
            if (actiCenter.get("endTime") != null) endTime = Integer.parseInt(actiCenter.get("endTime") + "");
            int nowTime = new Long(((new Date().getTime()) / 1000)).intValue();
            if (startTime <= nowTime && nowTime <= endTime) {
                int reward = (int) actiCenter.get("reward");//奖励的积分
                if (reward == 0) reward = 10;
                ActiSendIntegral actiSendIntegral = new ActiSendIntegral();
                actiSendIntegral.setMid(mid);
                actiSendIntegral.setSendDate(new Date());
                actiSendIntegral.setSendTime(new Date());
                actiSendIntegral.setType(8);//分享链接送积分
                actiSendIntegral.setIntegral(reward);//分享链接送的积分数量:10
                int insertResult = actiSendIntegralService.insertSelective(actiSendIntegral);
                if (insertResult > 0) {
                    ActiIntegral actiIntegral = new ActiIntegral();
                    actiIntegral.setNowIntegral((long) reward);
                    actiIntegral.setMid(mid);
                    int inserIntegralResult = insertOrUpate(actiIntegral);
                    if (inserIntegralResult > 0) {
                        result.put(CommonContants.RESULT, Boolean.TRUE);
                        result.put(CommonContants.DATA,reward);
                        result.put(CommonContants.MESSAGE, "成功获得积分！");
                    } else {
                        result.put(CommonContants.RESULT, Boolean.FALSE);
                        result.put(CommonContants.ERROR, -4);
                        result.put(CommonContants.MESSAGE, "系统异常获取积分失败！");
                    }
                } else {
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -1);
                    result.put(CommonContants.MESSAGE, "该用户当天已经分享过了！");
                }
            } else {
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -2);
                result.put(CommonContants.MESSAGE, "活动时间未开始或者已结束！");
            }
        } else {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -3);
            result.put(CommonContants.MESSAGE, "活动类型不存在！");
        }
        return result;
    }

    @Override
    public int insertOrUpate(ActiIntegral record) {
        return actiIntegralMapper.insertOrUpate(record);
    }

    @Override
    public Object useGoldToSendIntegral(int mid,int integralType) {
        //TODO 1.获取当前时间消耗的房卡数量。
        int nowGold = 0;
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> cfgs = actiIntegralCfgService.queryListByActiTypeLimitByDate(10);//缓存
        if (cfgs != null && cfgs.size() > 0) {
            Map<String, Object> cfg1 = cfgs.get(0);
            long closeTime = (long) cfg1.get("closeTime");
            long nowTime = new Date().getTime() / 1000;
            if (closeTime < nowTime) {
                cfgs = actiIntegralCfgService.queryListByActiTypeLimitByDate2(10);//非缓存，清缓存
                if (cfgs == null || cfgs.size() < 1) {
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -1);
                    result.put(CommonContants.MESSAGE, "消耗房卡送积分未配置,或活动已过期！");
                    return result;
                }
            }
            for (int i = 0; i < cfgs.size(); i++) {
                if ((int)cfgs.get(i).get("id") == integralType) {
                    int gold = (int) cfgs.get(i).get("gold");
                    if (nowGold < gold) {
                        result.put(CommonContants.RESULT, Boolean.FALSE);
                        result.put(CommonContants.ERROR, -2);
                        result.put(CommonContants.MESSAGE, "当前所消耗的房卡不足!");
                        return result;
                    }
                    ValueOperations valueOper = redisTemplate.opsForValue();
                    valueOper.set("i" + simpleDateFormat.format(new Date())//设置成已领取
                            + ":" + mid + ":" + cfgs.get(i).get("id"),1,2, TimeUnit.DAYS);
                    break;
                }
            }
            result.put(CommonContants.RESULT, Boolean.TRUE);
            result.put(CommonContants.DATA, 1);
            result.put(CommonContants.MESSAGE, "兑换成功!");
            return result;
        } else {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "消耗房卡送积分未配置,或活动已过期！");
            return result;
        }
    }

    @Override
    public Object checkUseGoldToSendIntegral(int mid) {
        //TODO 1.获取当前时间消耗的房卡数量。
        int nowGold = 0;
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> cfgs = actiIntegralCfgService.queryListByActiTypeLimitByDate(10);//缓存
        if (cfgs != null && cfgs.size() > 0) {
            Map<String, Object> cfg1 = cfgs.get(0);
            long closeTime = (long) cfg1.get("closeTime");
            long nowTime = new Date().getTime() / 1000;
            if (closeTime < nowTime) {
                cfgs = actiIntegralCfgService.queryListByActiTypeLimitByDate2(10);//非缓存，清缓存
                if (cfgs == null || cfgs.size() < 1) {
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -1);
                    result.put(CommonContants.MESSAGE, "消耗房卡送积分未配置,或活动已过期！");
                    return result;
                }
            }
            List<Map<String, Object>> items = new ArrayList<>();
            Map<String, Object> receives = new HashMap<>();
            for (int i = 0; i < cfgs.size(); i++) {
                ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
                String receive = valueOper.get("i" + simpleDateFormat.format(new Date())
                        + ":" + mid + ":" + cfgs.get(i).get("id"));
                if (receive == null) receive = "0";//0 表示未领取,1 表示已领取;
                receives.put("integralType" + i, cfgs.get(i).get("id"));
                receives.put("status_" + i, receive);
                receives.put("nowGold_" + i, nowGold);
                receives.put("gold_" + i, cfgs.get(i).get("gold"));
                receives.put("integral_" + i, cfgs.get(i).get("integral"));
                items.add(receives);
            }
            result.put(CommonContants.RESULT, Boolean.TRUE);
            result.put(CommonContants.DATA, items);
            result.put(CommonContants.MESSAGE, "拉取成功！");
            return result;
        } else {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "消耗房卡送积分未配置,或活动已过期！");
            return result;
        }
    }


}
