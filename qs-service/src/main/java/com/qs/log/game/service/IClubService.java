package com.qs.log.game.service;

import com.qs.log.game.model.Club;

public interface IClubService {
	int deleteByPrimaryKey(Integer clubid);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(Integer clubid);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);
}
