package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.ApkRecord;

public interface ApkRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApkRecord record);

    int insertSelective(ApkRecord record);

    ApkRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApkRecord record);

    int updateByPrimaryKey(ApkRecord record);
}