package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.Memberagents;
import com.qs.webside.member.model.Memberbusiness;

public interface MemberbusinessMapper extends IBaseMapper<Memberbusiness,Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(Memberbusiness record);

    int insertSelective(Memberbusiness record);

    Memberbusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberbusiness record);

    int updateByPrimaryKey(Memberbusiness record);
    
    /**
     * 通过mid查询商务
     * @param mid
     * @return
     */
    Memberbusiness findMemberbusinessByMid(Integer mid);
}