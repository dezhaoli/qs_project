package com.qs.warehouse.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.warehouse.model.DouniuGameRecordSub;

public interface DouniuGameRecordSubMapper extends IBaseMapper {
   

    int insertSelective(DouniuGameRecordSub record);

 
}