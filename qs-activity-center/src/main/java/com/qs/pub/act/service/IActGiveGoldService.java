package com.qs.pub.act.service;

import com.qs.pub.act.model.ActGiveGold;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/18 15:28.
 * Description:活动中心，活动送金币表接口
 */
public interface IActGiveGoldService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActGiveGold record);

    int insertSelective(ActGiveGold record);

    ActGiveGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActGiveGold record);

    int updateByPrimaryKey(ActGiveGold record);

    List queryListByPage(Map<String, Object> parameter);
}
