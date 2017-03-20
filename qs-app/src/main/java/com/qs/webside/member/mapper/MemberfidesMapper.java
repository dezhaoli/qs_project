package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.Memberfides;

public interface MemberfidesMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Memberfides record);

    int insertSelective(Memberfides record);

    Memberfides selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Memberfides record);

    int updateByPrimaryKey(Memberfides record);
}