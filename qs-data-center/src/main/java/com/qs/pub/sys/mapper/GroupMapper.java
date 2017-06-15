package com.qs.pub.sys.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.Group;

public interface GroupMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

	Group findByName(String groupName);
}