package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.BizUserGroup;

public interface BizUserGroupMapper extends IBaseMapper {
    int insert(BizUserGroup record);

    int insertSelective(BizUserGroup record);
}