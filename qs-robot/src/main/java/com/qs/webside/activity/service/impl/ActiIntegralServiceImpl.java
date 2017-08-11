package com.qs.webside.activity.service.impl;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.MemcachedUtil;
import com.qs.mainku.game.model.BaseParam;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.service.IBaseParamService;
import com.qs.mainku.game.service.IMemberFidesService;
import com.qs.webside.activity.mapper.ActiIntegralMapper;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.model.ActiSendIntegral;
import com.qs.webside.activity.service.IActiCenterService;
import com.qs.webside.activity.service.IActiIntegralCfgService;
import com.qs.webside.activity.service.IActiIntegralService;
import com.qs.webside.activity.service.IActiSendIntegralService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by zun.wei on 2017/6/8 19:10.
 * Description:活动中心积分表
 */
@Service
public class ActiIntegralServiceImpl implements IActiIntegralService {

    private Log log = LogFactory.getLog(ActiIntegralServiceImpl.class);

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

    @Resource
    private IBaseParamService baseParamService;

    @Resource
    private MemcachedClient memcachedClient;

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
    public Map<String, Object> useGoldToSendIntegral(int mid,int cfgType) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        //String memcachedUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.MEMCACHED_IP);
        //memcachedUrl = StringUtils.isBlank(memcachedUrl) ? "" : memcachedUrl;
        String memcachedKey = "ds" + simpleDateFormat.format(new Date()) + "|" + mid + "";
        //Object o = MemcachedUtil.getMemcached(memcachedUrl, memcachedKey);
        log.debug("useGoldToSendIntegral memcached key is ---------------::" + memcachedKey);
        Object o = memcachedClient.get(memcachedKey);
        log.debug("useGoldToSendIntegral memcached value is ---------------::" + o);
        int nowGold = o == null ? 0 : Integer.parseInt(o + "");
        log.debug("useGoldToSendIntegral memcached gold is ---------------::" + nowGold);
        nowGold = nowGold > 0 ? nowGold : 0;
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("actiType", 10);
        List<Map<String, Object>> cfgs = actiIntegralCfgService.queryListByActiTypeLimitByDate(map);//缓存
        if (cfgs != null && cfgs.size() > 0) {
            Map<String, Object> cfg1 = cfgs.get(0);
            long closeTime = (long) cfg1.get("closeTime");
            long nowTime = new Date().getTime() / 1000;
            if (closeTime < nowTime) {
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -4);
                result.put(CommonContants.MESSAGE, "活动已过期！");
                return result;
            }
            for (int i = 0; i < cfgs.size(); i++) {
                if ((int)cfgs.get(i).get("id") == cfgType) {
                    int gold = (int) cfgs.get(i).get("gold");
                    if (nowGold < gold) {
                        result.put(CommonContants.RESULT, Boolean.FALSE);
                        result.put(CommonContants.ERROR, -2);
                        result.put(CommonContants.MESSAGE, "当前所消耗的房卡不足!");
                        return result;
                    }
                    ValueOperations<String, Integer> v = redisTemplate.opsForValue();
                    Integer receive = v.get("i" + simpleDateFormat.format(new Date())
                            + ":" + mid + ":" + cfgs.get(i).get("id"));
                    if (receive == null) receive = 0;
                    if (receive == 0) {//表示未领取过
                        ActiIntegral actiIntegral = new ActiIntegral();
                        actiIntegral.setMid(mid);
                        actiIntegral.setNowIntegral(Long.parseLong(cfgs.get(i).get("integral") + ""));
                        int updateResult = actiIntegralMapper.insertOrUpate(actiIntegral);
                        if (updateResult > 0) {//更新积分成功
                            ValueOperations valueOper = redisTemplate.opsForValue();
                            valueOper.set("i" + simpleDateFormat.format(new Date())//设置成已领取
                                    + ":" + mid + ":" + cfgs.get(i).get("id"), 1, 2, TimeUnit.DAYS);
                            result.put(CommonContants.RESULT, Boolean.TRUE);
                            result.put(CommonContants.DATA, cfgs.get(i).get("integral"));
                            result.put(CommonContants.MESSAGE, "成功兑换" + cfgs.get(i).get("integral") + "积分!");
                        } else {
                            log.debug("Exchange points error ===>:: udpate user integral fail ！");
                            result.put(CommonContants.RESULT, Boolean.FALSE);
                            result.put(CommonContants.DATA, -6);
                            result.put(CommonContants.MESSAGE, "更新用户积分失败!");
                        }
                        return result;
                    } else {
                        result.put(CommonContants.RESULT, Boolean.FALSE);
                        result.put(CommonContants.ERROR, -3);
                        result.put(CommonContants.MESSAGE, "当天已经领取过该奖励了!");
                        return result;
                    }
                }
            }
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.DATA, -5);
            result.put(CommonContants.MESSAGE, "不存在的配置!");
            return result;
        } else {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "消耗房卡送积分未配置,或活动已过期！");
            return result;
        }
    }

    @Override
    public Map<String, Object> checkUseGoldToSendIntegral(int mid) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        //String memcachedUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.MEMCACHED_IP);
        //memcachedUrl = StringUtils.isBlank(memcachedUrl) ? "" : memcachedUrl;
        String memcachedKey = "ds" + simpleDateFormat.format(new Date()) + "|" + mid + "";
        log.debug("checkUseGoldToSendIntegral memcached key is ---------------::" + memcachedKey);
        //Object o = MemcachedUtil.getMemcached(memcachedUrl, memcachedKey);
        Object o = memcachedClient.get(memcachedKey);
        log.debug("checkUseGoldToSendIntegral memcached value is ---------------::" + o);
        int nowGold = o == null ? 0 : Integer.parseInt(o + "");
        log.debug("checkUseGoldToSendIntegral memcached gold is ---------------::" + nowGold);
        nowGold = nowGold > 0 ? nowGold : 0;
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("actiType", 10);
        List<Map<String, Object>> cfgs = actiIntegralCfgService.queryListByActiTypeLimitByDate(map);//缓存
        if (cfgs != null && cfgs.size() > 0) {
            Map<String, Object> cfg1 = cfgs.get(0);
            long closeTime = (long) cfg1.get("closeTime");
            long nowTime = new Date().getTime() / 1000;
            if (closeTime < nowTime) {
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -2);
                result.put(CommonContants.MESSAGE, "活动已过期！");
                return result;
            }
            List<Map<String, Object>> items = new ArrayList<>();
            for (int i = 0; i < cfgs.size(); i++) {
                Map<String, Object> receives = new HashMap<>();
                ValueOperations<String, Integer> valueOper = redisTemplate.opsForValue();
                Integer receive = valueOper.get("i" + simpleDateFormat.format(new Date())
                        + ":" + mid + ":" + cfgs.get(i).get("id"));
                if (receive == null) receive = 0;//0 表示未领取,1 表示已领取;
                receives.put("cfgType", cfgs.get(i).get("id"));//配置类型
                receives.put("status", receive);//兑换状态
                receives.put("nowGold", nowGold);//当前所消耗的房卡
                receives.put("gold", cfgs.get(i).get("gold"));//所需消耗的房卡
                receives.put("integral", cfgs.get(i).get("integral"));//奖励的积分
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
