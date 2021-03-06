package com.qs.acti.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.acti.game.model.ActiSendGold;

import java.util.Map;

public interface ActiSendGoldMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiSendGold record);

    int insertSelective(ActiSendGold record);

    ActiSendGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiSendGold record);

    int updateByPrimaryKey(ActiSendGold record);

    /**
     * @Author:zun.wei , @Date:2017/6/14 13:47
     * @Description:获取条件筛选内的发送的金币总数
     * @param parameters
     * @return
     */
    int querySumSendGoldByCondition(Map<String, Object> parameters);

}