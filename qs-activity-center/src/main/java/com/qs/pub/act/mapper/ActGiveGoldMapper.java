package com.qs.pub.act.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.act.model.ActGiveGold;

public interface ActGiveGoldMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActGiveGold record);

    int insertSelective(ActGiveGold record);

    ActGiveGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActGiveGold record);

    int updateByPrimaryKey(ActGiveGold record);
}