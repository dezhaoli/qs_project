package com.qs.agent.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.agent.game.model.Memberagents;
import com.qs.agent.game.model.Memberbusiness;
import com.qs.common.base.basemapper.IBaseMapper;

public interface MemberbusinessMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Memberbusiness record);

    int insertSelective(Memberbusiness record);

    Memberbusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberbusiness record);

    int updateByPrimaryKey(Memberbusiness record);

	List<Memberbusiness> queryMemberbusinessAddListByPage(
			Map<String, Object> parameters);

	List<Memberagents> queryMemberpaymentListByPage(
			Map<String, Object> parameters);

	List<Memberbusiness> queryUserAddListByPage(Map<String, Object> parameters);
}