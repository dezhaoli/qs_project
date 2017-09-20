package com.qs.warehouse.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.MajiangGameRecordSub;

public interface MajiangGameRecordSubMapper extends IBaseMapper {
   

    int insertSelective(MajiangGameRecordSub record);

   
}