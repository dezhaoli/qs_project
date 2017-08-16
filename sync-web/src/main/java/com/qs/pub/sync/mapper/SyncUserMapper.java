package com.qs.pub.sync.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sync.model.SyncUser;

public interface SyncUserMapper extends IBaseMapper{
	int insert(SyncUser record);
}
