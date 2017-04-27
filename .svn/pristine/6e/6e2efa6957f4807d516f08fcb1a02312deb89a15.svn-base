package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.PlayerPayDay;

import java.util.List;
import java.util.Map;

public interface PlayerPayDayMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlayerPayDay record);

    int insertSelective(PlayerPayDay record);

    PlayerPayDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlayerPayDay record);

    int updateByPrimaryKey(PlayerPayDay record);

    /**
     * @Author:zun.wei , @Date:2017/4/27 15:21
     * @description:根据公司获取所有商务充值数据
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryBusiPayByCompany(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/4/27 20:08
     * @Description:根据游戏获取充值数据
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryChangeDataCountByGame(Map<String, Object> parameters);

}