package com.qs.webside.activity.service;

import com.qs.webside.activity.model.ActiAwardAddress;

public interface IActiAwardAddressService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardAddress record);

    int insertSelective(ActiAwardAddress record);

    ActiAwardAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardAddress record);

    int updateByPrimaryKey(ActiAwardAddress record);

    /**
     * 根据mid 获取用户地址信息
     *
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年6月7日
     */
    ActiAwardAddress selectObjectByMidKey(Integer mid);
}
