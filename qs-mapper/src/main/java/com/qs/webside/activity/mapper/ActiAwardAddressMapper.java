package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiAwardAddress;

public interface ActiAwardAddressMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardAddress record);

    int insertSelective(ActiAwardAddress record);

    ActiAwardAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardAddress record);

    int updateByPrimaryKey(ActiAwardAddress record);
}