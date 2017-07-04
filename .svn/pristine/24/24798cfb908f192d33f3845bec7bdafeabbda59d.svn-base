package com.qs.acti.game.service;

import com.qs.acti.game.model.ActiAwardAddress;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/9 15:45.
 * Description:活动中心奖品发放地址
 */
public interface IActiAwardAddressService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardAddress record);

    int insertSelective(ActiAwardAddress record);

    ActiAwardAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardAddress record);

    int updateByPrimaryKey(ActiAwardAddress record);

    List<ActiAwardAddress> queryListByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/6/9 16:04
     * @Description:根据mid获取活动中心地址对象
     * @param mid
     * @return
     */
    ActiAwardAddress selectByMid(Integer mid);

}
