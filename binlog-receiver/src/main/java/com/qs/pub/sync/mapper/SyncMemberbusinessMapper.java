package com.qs.pub.sync.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.SyncMemberbusiness;

public interface SyncMemberbusinessMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SyncMemberbusiness record);

    int insertSelective(SyncMemberbusiness record);

    SyncMemberbusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SyncMemberbusiness record);

    int updateByPrimaryKey(SyncMemberbusiness record);
}