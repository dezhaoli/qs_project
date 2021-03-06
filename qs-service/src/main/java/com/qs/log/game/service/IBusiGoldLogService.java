package com.qs.log.game.service;

import com.qs.log.game.model.BusiGoldLog;

import java.util.Map;

public interface IBusiGoldLogService {

    int insert(BusiGoldLog record);

    int insertSelective(BusiGoldLog record);

    /**
     * 添加金币处理
     *
     * @param id
     * @param monery
     * @param cause
     * @return
     * @author:zyy
     * @time:2017年4月27日
     */
    public Map<String, Object> goldAdd(int id, int monery, String cause, String goldHost, int goldPort, int gameType);


    /**
     * 根据实际获取当前时间的总金币充值额度
     *
     * @param param
     * @return double
     * @author:zyy
     * @time:2017年4月27日
     */
    Integer selectCountNowGold(Map<String, Object> param);

    /**
     * 获取商务专员为玩家添加的当日金币总数
     *
     * @param param 时间，mid,id
     * @return double
     * @author:zyy
     * @time:2017年4月27日
     */
    Integer getGameUserGoldCount(Map<String, Object> param);

    /**
     *  发送请求给c++，发送金币
     * @param mid 用户id
     * @param gold 金币数量
     * @param goldLogType 添加的金币类型，后台添加？商务添加？
     * @param goldHost 发送url地址
     * @param goldPort 端口号
     * @param gameType 游戏类型
     * @return
     */
    Map<String, Object> updateGold(int mid, int gold, int goldLogType, String goldHost, int goldPort
            , int gameType,String remark);

}
