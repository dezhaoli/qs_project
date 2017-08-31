package com.qs.warehouse.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.warehouse.model.DouniuGameRecord;

public interface DouniuGameRecordMapper extends IBaseMapper {
  
    int insertSelective(DouniuGameRecord record);

   
}