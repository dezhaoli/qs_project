package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.AppGame;

public interface AppGameMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer gid);

    int insert(AppGame record);

    int insertSelective(AppGame record);

    AppGame selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(AppGame record);

    int updateByPrimaryKey(AppGame record);
}