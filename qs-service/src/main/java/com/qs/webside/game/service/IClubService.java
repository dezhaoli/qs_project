package com.qs.webside.game.service;

import com.qs.webside.game.model.Club;

public interface IClubService {
	int deleteByPrimaryKey(Integer clubid);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(Integer clubid);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);
}
