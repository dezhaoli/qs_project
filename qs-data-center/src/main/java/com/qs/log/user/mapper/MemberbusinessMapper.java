package com.qs.log.user.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.user.model.Memberbusiness;

public interface MemberbusinessMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Memberbusiness record);

    int insertSelective(Memberbusiness record);

    Memberbusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberbusiness record);

    int updateByPrimaryKey(Memberbusiness record);
}