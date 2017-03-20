package com.qs.log.game.mapper;

import java.util.List;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.GameRecordSub;

public interface GameRecordSubMapper extends IBaseMapper<GameRecordSub,Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(GameRecordSub record);

    int insertSelective(GameRecordSub record);

    GameRecordSub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameRecordSub record);

    int updateByPrimaryKey(GameRecordSub record);
    
   /**
    * 获取详细战绩
    * @param uid
    * @return
    */
    List<GameRecordSub> getHonorDetail(String uid);
    
}