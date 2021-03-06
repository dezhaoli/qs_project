package com.qs.webside.member.service;

import com.qs.webside.member.model.Members;

/**
 * 平台ID与游戏ID对应表
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMembersServcie {

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
