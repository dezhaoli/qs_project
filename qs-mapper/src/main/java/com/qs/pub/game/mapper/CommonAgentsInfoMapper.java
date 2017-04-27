package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.CommonAgentsInfo;

import java.util.List;
import java.util.Map;

/**
 * 代理商信息表
 */
public interface CommonAgentsInfoMapper extends IBaseMapper {
    int deleteByPrimaryKey(String sitemid);

    int insert(CommonAgentsInfo record);

    int insertSelective(CommonAgentsInfo record);

    CommonAgentsInfo selectByPrimaryKey(String sitemid);

    int updateByPrimaryKeySelective(CommonAgentsInfo record);

    int updateByPrimaryKey(CommonAgentsInfo record);

    /**
     * 获取商务专员绑定的一级代理商充值列表
     * @param belongid
     * @return
     */
    List<Map<String, Object>> queryFirstAgentByBelongIdPage(Map<String,Object> belongid);

}