package com.qs.log.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.GoldLog;

public interface GoldLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoldLog record);

    int insertSelective(GoldLog record);

    GoldLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoldLog record);

    int updateByPrimaryKey(GoldLog record);
    

    /**
     * 根据mid获取用户金币来源
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getUserGoldOriginPageByMid(Map<String,Object> parameters);
    
    /**
     * 根据金币商务发金币功能
     * @param record
     * @return
     * @author:zyy
     * @time:2017年4月27日
     */
    int updateGoldFromParam(Map<String,Object> parma);
}