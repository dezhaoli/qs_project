package com.qs.webside.agent.service;

import com.qs.pub.game.model.CommonAgentsInfo;

public interface ICommonAgentsInfoService {

	int deleteByPrimaryKey(String sitemid);

    int insert(CommonAgentsInfo record);

    int insertSelective(CommonAgentsInfo record);

    CommonAgentsInfo selectByPrimaryKey(String sitemid);

    int updateByPrimaryKeySelective(CommonAgentsInfo record);

    int updateByPrimaryKey(CommonAgentsInfo record);
}
