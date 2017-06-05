package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiAward;

import java.util.List;
import java.util.Map;

public interface ActiAwardMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAward record);

    int insertSelective(ActiAward record);

    ActiAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAward record);

    int updateByPrimaryKey(ActiAward record);

}