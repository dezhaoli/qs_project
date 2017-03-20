package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.Apkrecord;

public interface ApkrecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Apkrecord record);

    int insertSelective(Apkrecord record);

    Apkrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apkrecord record);

    int updateByPrimaryKey(Apkrecord record);
}