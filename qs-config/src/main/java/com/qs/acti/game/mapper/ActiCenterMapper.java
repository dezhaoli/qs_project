package com.qs.acti.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.acti.game.model.ActiCenter;

public interface ActiCenterMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiCenter record);

    int insertSelective(ActiCenter record);

    ActiCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiCenter record);

    int updateByPrimaryKey(ActiCenter record);
}