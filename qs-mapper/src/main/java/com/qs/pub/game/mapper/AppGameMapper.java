package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.AppGame;

import java.util.List;

public interface AppGameMapper extends IBaseMapper {
    int deleteByPrimaryKey(Byte gid);

    int insert(AppGame record);

    int insertSelective(AppGame record);

    AppGame selectByPrimaryKey(Byte gid);

    int updateByPrimaryKeySelective(AppGame record);

    int updateByPrimaryKey(AppGame record);

    List<AppGame> queryListAll();

}