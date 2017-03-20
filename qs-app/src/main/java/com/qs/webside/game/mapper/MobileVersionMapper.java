package com.qs.webside.game.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.MobileVersion;

public interface MobileVersionMapper extends IBaseMapper<MobileVersion,Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileVersion record);

    int insertSelective(MobileVersion record);

    MobileVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileVersion record);

    int updateByPrimaryKey(MobileVersion record);
    
    /**
     * 最新的游戏版本
     * @return
     */
    MobileVersion findLatestMobileVersion(Map<String, Object> map);
}