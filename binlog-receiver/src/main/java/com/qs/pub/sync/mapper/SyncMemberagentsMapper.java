package com.qs.pub.sync.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.SyncMemberagents;

public interface SyncMemberagentsMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SyncMemberagents record);

    int insertSelective(SyncMemberagents record);

    SyncMemberagents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SyncMemberagents record);

    int updateByPrimaryKey(SyncMemberagents record);
}