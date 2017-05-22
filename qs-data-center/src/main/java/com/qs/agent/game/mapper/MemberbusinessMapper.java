package com.qs.agent.game.mapper;

import com.qs.agent.game.model.Memberbusiness;
import com.qs.common.base.basemapper.IBaseMapper;

public interface MemberbusinessMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Memberbusiness record);

    int insertSelective(Memberbusiness record);

    Memberbusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberbusiness record);

    int updateByPrimaryKey(Memberbusiness record);
}