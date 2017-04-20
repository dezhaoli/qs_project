package com.qs.pub.game.service;

import com.qs.pub.game.model.CommonAgentsRelation;

public interface ICommonAgentsRelationService {

	int deleteByPrimaryKey(Integer id);

    int insert(CommonAgentsRelation record);

    int insertSelective(CommonAgentsRelation record);

    CommonAgentsRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonAgentsRelation record);

    int updateByPrimaryKey(CommonAgentsRelation record);
}
