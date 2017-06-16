package com.qs.pub.sys.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.UserGroup;

public interface UserGroupMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);

	int selectByUserId(Integer userId);

	int deleteByUserId(Integer userId);

	int addUserGroupBatch(Map<String, Object> parameter);
}