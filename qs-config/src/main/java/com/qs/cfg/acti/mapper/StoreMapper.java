package com.qs.cfg.acti.mapper;

import com.qs.cfg.acti.model.Store;
import com.qs.common.base.basemapper.IBaseMapper;

public interface StoreMapper extends IBaseMapper<Store,Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}