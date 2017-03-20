package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.Memberinvite;

public interface MemberinviteMapper extends IBaseMapper<Memberinvite,Integer> {
  
    /**
     * 验证邀请码是否存在
     * @param code
     * @return
     */
    Memberinvite findByInviteCode(String code);

}