package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.RoomCardCountMapper;
import com.qs.log.game.model.RoomCardCount;
import com.qs.log.game.service.IRoomCardCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/7/6 18:22.
 * Description:统计房卡总数
 */
@Service
public class RoomCardCountServiceImpl implements IRoomCardCountService{

    @Resource
    private RoomCardCountMapper roomCardCountMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return roomCardCountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoomCardCount record) {
        return roomCardCountMapper.insert(record);
    }

    @Override
    public int insertSelective(RoomCardCount record) {
        return roomCardCountMapper.insertSelective(record);
    }

    @Override
    public RoomCardCount selectByPrimaryKey(Integer id) {
        return roomCardCountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RoomCardCount record) {
        return roomCardCountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoomCardCount record) {
        return roomCardCountMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<RoomCardCount> queryListByPage(Map<String, Object> parameters) {
        return roomCardCountMapper.queryListByPage(parameters);
    }

}
