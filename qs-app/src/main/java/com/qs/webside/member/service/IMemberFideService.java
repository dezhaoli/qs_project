package com.qs.webside.member.service;

import com.qs.webside.member.model.Memberfides;

/**
 * Created by zun.wei on 2017/7/21 10:28.
 * Description:
 */
public interface IMemberFideService {

    int deleteByPrimaryKey(Integer mid);

    int insert(Memberfides record);

    int insertSelective(Memberfides record);

    Memberfides selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Memberfides record);

    int updateByPrimaryKey(Memberfides record);

}
