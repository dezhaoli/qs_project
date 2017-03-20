package com.qs.log.game.service;

import com.qs.log.game.model.GoldLog;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/9.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IGoldLogService {

    int insert(GoldLog record);

    int insertSelective(GoldLog record);

    List<GoldLog> queryListByPage(Map<String, Object> parameters);

}
