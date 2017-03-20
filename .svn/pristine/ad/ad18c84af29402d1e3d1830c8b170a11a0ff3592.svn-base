package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.GoldLogMapper;
import com.qs.log.game.model.GoldLog;
import com.qs.log.game.service.IGoldLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/9.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class GoldLogServiceImpl implements IGoldLogService{

    @Resource
    private GoldLogMapper goldLogMapper;

    @Override
    public int insert(GoldLog record) {
        return goldLogMapper.insert(record);
    }

    @Override
    public int insertSelective(GoldLog record) {
        return goldLogMapper.insertSelective(record);
    }

    @Override
    public List<GoldLog> queryListByPage(Map<String, Object> parameters) {
        return goldLogMapper.queryListByPage(parameters);
    }

}
