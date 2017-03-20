package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.Dict;

public interface DictMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}