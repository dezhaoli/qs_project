package com.qs.pub.game.mapper;

import java.util.List;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.Area;

public interface AreaMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    List<Area> selectByAreaPrimaryKey(Area record);
}