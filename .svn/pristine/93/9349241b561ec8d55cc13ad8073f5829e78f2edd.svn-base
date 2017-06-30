package com.qs.pub.sync.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.SyncUserKeep;

public interface SyncUserKeepMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SyncUserKeep record);

    int insertSelective(SyncUserKeep record);

    SyncUserKeep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SyncUserKeep record);

    int updateByPrimaryKey(SyncUserKeep record);

	SyncUserKeep selectByUserId(Map<String, Object> map);

	int update(SyncUserKeep syncUserKeep);
}