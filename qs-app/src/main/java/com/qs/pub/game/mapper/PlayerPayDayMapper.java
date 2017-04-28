package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.PlayerPayDay;

public interface PlayerPayDayMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlayerPayDay record);

    int insertSelective(PlayerPayDay record);

    PlayerPayDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlayerPayDay record);

    int updateByPrimaryKey(PlayerPayDay record);
    
    
    /**]
     * 
     * @标题: insertOrUpdateBizCharge 
     * @描述:增加商务收入 
     *
     * @参数信息
     *    @param record
     *
     * @返回类型 void
     * @开发者 QS
     * @可能抛出异常
     */
    int insertOrUpdateBizCharge(PlayerPayDay record);
}