package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.IpaddressUseLog;

public interface IpaddressUseLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IpaddressUseLog record);

    int insertSelective(IpaddressUseLog record);

    IpaddressUseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpaddressUseLog record);

    int updateByPrimaryKey(IpaddressUseLog record);
}