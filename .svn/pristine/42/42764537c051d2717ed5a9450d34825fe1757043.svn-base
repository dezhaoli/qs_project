package com.qs.pub.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.datacenter.model.UserAddLog;

public interface UserAddLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddLog record);

    int insertSelective(UserAddLog record);

    UserAddLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddLog record);

    int updateByPrimaryKey(UserAddLog record);

	List<UserAddLog> queryCount(Map<String, Object> parameter);
}