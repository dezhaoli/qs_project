package com.qs.pub.act.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.act.model.ActGiveGold;

import java.util.Map;

public interface ActGiveGoldMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActGiveGold record);

    int insertSelective(ActGiveGold record);

    ActGiveGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActGiveGold record);

    int updateByPrimaryKey(ActGiveGold record);

    /**
     * @Author:zun.wei , @Date:2017/5/22 11:21
     * @Description:验证当天有没有通过评论送过金币
     * @param parameters mid，new Date()
     * @return
     */
    int checkThisDayHadComment(Map<String, Object> parameters);

}