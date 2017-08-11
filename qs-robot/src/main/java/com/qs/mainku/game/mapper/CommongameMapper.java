package com.qs.mainku.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.mainku.game.model.Commongame;

/**
 * @Author:zun.wei , @Date:2017/6/29 11:19
 * @Description:金币表
 */
public interface CommongameMapper extends IBaseMapper<Commongame,Integer> {

    int deleteByPrimaryKey(Integer mid);

    int insert(Commongame record);

    int insertSelective(Commongame record);

    Commongame selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Commongame record);

    int updateByPrimaryKey(Commongame record);

}