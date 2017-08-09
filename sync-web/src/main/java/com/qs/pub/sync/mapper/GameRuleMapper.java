package com.qs.pub.sync.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncGameRule;

public interface GameRuleMapper extends IBaseMapper {
	int insert(SyncGameRule record);
}
