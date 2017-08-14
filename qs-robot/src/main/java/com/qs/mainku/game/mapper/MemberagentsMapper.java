package com.qs.mainku.game.mapper;


import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.mainku.game.model.Memberagents;

public interface MemberagentsMapper extends IBaseMapper<Memberagents,Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(Memberagents record);

    int insertSelective(Memberagents record);

    Memberagents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberagents record);

    int updateByPrimaryKey(Memberagents record);
    /**
     * 通过mid查询代理商
     * @param mid
     * @return
     */
    Memberagents findMemberagentsByMid(Integer mid);

    /**
     * @Author:zun.wei , @Date:2017/8/14 10:57
     * @Description:检查代理商是否存在
     * @param mid 代理商mid
     * @return
     */
    int checkAgentIsExist(Integer mid);

}