package com.qs.log.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.MailsUser;

public interface MailsUserMapper extends IBaseMapper<MailsUser,Integer> {
    int insert(MailsUser record);

    int updateByCondition(MailsUser record);
    MailsUser findByCondition(MailsUser record);
    
    
    
}