package com.qs.log.game.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.BusiGoldLog;

public interface BusiGoldLogMapper extends IBaseMapper {
	
    int insert(BusiGoldLog record);

    int insertSelective(BusiGoldLog record);
    
    /**
     * 根据实际获取商务当前时间的总金币充值额度
     * @param param
     * @return double 
     * @author:zyy
     * @time:2017年4月27日
     */
    Integer getBuisGoldCount(Map<String,Object> param);
    /**
     * 获取商务专员为玩家添加的当日金币总数
     * @param param 时间，mid,id
     * @return double 
     * @author:zyy
     * @time:2017年4月27日
     */
    Integer getGameUserGoldCount (Map<String,Object> param);
}