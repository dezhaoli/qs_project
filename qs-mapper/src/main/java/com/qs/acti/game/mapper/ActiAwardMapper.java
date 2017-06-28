package com.qs.acti.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.acti.game.model.ActiAward;

public interface ActiAwardMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAward record);

    int insertSelective(ActiAward record);

    ActiAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAward record);

    int updateByPrimaryKey(ActiAward record);

}