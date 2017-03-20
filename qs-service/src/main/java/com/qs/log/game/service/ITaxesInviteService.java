package com.qs.log.game.service;

import com.qs.log.game.model.TaxesInvite;

import java.util.Map;

/**
 *
 * Created by zun.wei on 2017/3/18.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface ITaxesInviteService {


    int insert(TaxesInvite record);

    int insertSelective(TaxesInvite record);

    /**
     * 根据mid获取支付总数，和邀请总数
     * @param mid
     * @return
     */
    Map<String, Object> getPayAndInviteTotalByMid(Integer mid);

    /**
     * 根据parentId获取支付总数，和邀请总数
     * @param parentId
     * @return
     */
    Map<String, Object> getPayAndInviteTotalByParentId(Integer parentId);

    /**
     * 根据parent_id获取支付总数，和邀请总数
     * @param agentParentId
     * @return
     */
    Map<String, Object> getPayAndInviteTotalByAgentParentId(Integer agentParentId);


}
