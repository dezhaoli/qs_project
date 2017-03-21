package com.qs.webside.sys.service.agent.service;

import java.util.List;

import com.qs.pub.game.model.Area;

public interface IMemberAreaService {
	int deleteByPrimaryKey(Integer aid);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    List<Area> selectByAreaPrimaryKey(Area record);
}
