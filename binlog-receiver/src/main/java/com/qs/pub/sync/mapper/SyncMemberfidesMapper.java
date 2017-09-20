package com.qs.pub.sync.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.SyncMemberfides;

public interface SyncMemberfidesMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(SyncMemberfides record);

    int insertSelective(SyncMemberfides record);

    SyncMemberfides selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(SyncMemberfides record);

    int updateByPrimaryKey(SyncMemberfides record);
}