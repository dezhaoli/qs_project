package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.Commongame;

public interface CommongameMapper extends IBaseMapper<Commongame,Integer> {
    int deleteByPrimaryKey(Integer mid);

    int insert(Commongame record);

    int insertSelective(Commongame record);

    Commongame selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Commongame record);

    int updateByPrimaryKey(Commongame record);
}