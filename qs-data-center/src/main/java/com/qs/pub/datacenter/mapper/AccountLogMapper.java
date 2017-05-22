package com.qs.pub.datacenter.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.datacenter.model.AccountLog;

public interface AccountLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountLog record);

    int insertSelective(AccountLog record);

    AccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountLog record);

    int updateByPrimaryKey(AccountLog record);
}