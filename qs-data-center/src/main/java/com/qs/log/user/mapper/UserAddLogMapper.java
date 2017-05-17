package com.qs.log.user.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.user.model.UserAddLog;

public interface UserAddLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddLog record);

    int insertSelective(UserAddLog record);

    UserAddLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddLog record);

    int updateByPrimaryKey(UserAddLog record);
}