package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.RoomRecordMapper;
import com.qs.log.game.model.RoomRecord;
import com.qs.log.game.service.IRoomRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/22 16:11.
 * Description:房间记录表业务层实现类
 */
@Service
public class RoomRecordServiceImpl implements IRoomRecordService {

    @Resource
    private RoomRecordMapper roomRecordMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roomRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoomRecord record) {
        return roomRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(RoomRecord record) {
        return roomRecordMapper.insertSelective(record);
    }

    @Override
    public RoomRecord selectByPrimaryKey(Long id) {
        return roomRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RoomRecord record) {
        return roomRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoomRecord record) {
        return roomRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<RoomRecord> queryListByPage(Map<String, Object> parameter) {
        return roomRecordMapper.queryListByPage(parameter);
    }

    @Override
    public RoomRecord queryByUuid(String uuid) {
        return roomRecordMapper.queryByUuid(uuid);
    }
}
