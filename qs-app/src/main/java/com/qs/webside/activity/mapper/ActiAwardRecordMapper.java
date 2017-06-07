package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiAwardRecord;

public interface ActiAwardRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardRecord record);

    int insertSelective(ActiAwardRecord record);

    ActiAwardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardRecord record);

    int updateByPrimaryKey(ActiAwardRecord record);
}