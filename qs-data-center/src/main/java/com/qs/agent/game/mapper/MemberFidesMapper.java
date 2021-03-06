package com.qs.agent.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.agent.game.model.MemberFides;
import com.qs.common.base.basemapper.IBaseMapper;

public interface MemberFidesMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(MemberFides record);

    int insertSelective(MemberFides record);

    MemberFides selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MemberFides record);

    int updateByPrimaryKey(MemberFides record);

	Long queryAddUserCountTotals(Map<String, Object> parameters);

	List<MemberFides> userPayrankingList(Map<String, Object> parameters);
}