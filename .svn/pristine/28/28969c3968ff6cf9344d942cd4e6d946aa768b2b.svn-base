package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.TaxesInvite;

import java.util.Map;

public interface TaxesInviteMapper extends IBaseMapper {

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
     *
     * @param dbNameAndAgentParentId 数据库名字和代理父级id
     * @return
     */
    Map<String, Object> getPayAndInviteTotalByAgentParentId(Map<String, Object> dbNameAndAgentParentId);


}