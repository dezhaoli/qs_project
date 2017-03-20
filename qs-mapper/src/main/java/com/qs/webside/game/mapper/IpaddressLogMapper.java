package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.IpaddressLog;

public interface IpaddressLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IpaddressLog record);

    int insertSelective(IpaddressLog record);

    IpaddressLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpaddressLog record);

    int updateByPrimaryKey(IpaddressLog record);
}