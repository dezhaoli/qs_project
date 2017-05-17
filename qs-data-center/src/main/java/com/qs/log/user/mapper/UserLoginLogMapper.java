package com.qs.log.user.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.user.model.UserLoginLog;

public interface UserLoginLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
    
}