package com.qs.log.user.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.user.model.Memberagents;
import com.qs.log.user.model.Memberbusiness;

public interface MemberagentsMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Memberagents record);

    int insertSelective(Memberagents record);

    Memberagents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberagents record);

    int updateByPrimaryKey(Memberagents record);

	List<Memberbusiness> selectByMid(Map<String, Object> parameters);
}