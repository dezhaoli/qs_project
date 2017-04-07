package com.qs.log.game.service;

import com.qs.log.game.model.TaxesInviteWeek;

import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/7 13:40
 *  周邀请表
 * Created by zun.wei on 2017/4/7.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface ITaxesInviteWeekService {

    int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);

    /**
     * //@Author:zun.wei, @Date:2017/4/7 12:30
     * 代理商历史结算
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getHistoryAgentsRebateList(Map<String, Object> parameters);

}
