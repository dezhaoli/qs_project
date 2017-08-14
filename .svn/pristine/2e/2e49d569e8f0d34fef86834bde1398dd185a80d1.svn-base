package com.qs.mainku.game.service;

import com.qs.mainku.game.model.Members;

/**
 * Created by zun.wei on 2017/8/14 16:28.
 * Description:
 */
public interface IMemberService {

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
