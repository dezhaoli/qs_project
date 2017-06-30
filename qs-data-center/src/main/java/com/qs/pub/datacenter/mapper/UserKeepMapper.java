package com.qs.pub.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.datacenter.model.UserKeep;

public interface UserKeepMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserKeep record);

    int insertSelective(UserKeep record);

    UserKeep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserKeep record);

    int updateByPrimaryKey(UserKeep record);

	List<UserKeep> queryUserKeepList(Map<String, Object> parameters);
}