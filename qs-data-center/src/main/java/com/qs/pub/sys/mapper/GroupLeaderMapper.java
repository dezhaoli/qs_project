package com.qs.pub.sys.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.GroupLeader;

public interface GroupLeaderMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupLeader record);

    int insertSelective(GroupLeader record);

    GroupLeader selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupLeader record);

    int updateByPrimaryKey(GroupLeader record);

	int selectByGroupId(Integer id);

	int deleteByGroupId(Integer id);

	int addBusinessBatch(Map<String, Object> parameter);
}