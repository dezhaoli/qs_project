package com.qs.mainku.game.mapper;


import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.mainku.game.model.Members;

public interface MembersMapper extends IBaseMapper<Members,Integer> {
    int deleteByPrimaryKey(Integer mid);

    int insert(Members record);

    int insertSelective(Members record);

    Members selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Members record);

    int updateByPrimaryKey(Members record);
    
    /**
     * 根据微信openid查询平台ID与游戏ID对应表
     * @param sitemid
     * @return
     */
    Members findMembersBySitemid(String sitemid);
}