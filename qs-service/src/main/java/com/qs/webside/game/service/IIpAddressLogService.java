package com.qs.webside.game.service;

import com.qs.webside.game.model.IpaddressLog;

import java.util.List;
import java.util.Map;

/**
 *  服务器入口IP修改记录表
 * Created by zun.wei on 2017/2/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IIpAddressLogService {

    int deleteByPrimaryKey(Integer id);

    int insert(IpaddressLog record);

    int insertSelective(IpaddressLog record);

    IpaddressLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpaddressLog record);

    int updateByPrimaryKey(IpaddressLog record);

    public List<IpaddressLog> queryListByPage(Map<String, Object> parameter);

}
