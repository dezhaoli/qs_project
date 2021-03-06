package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.PlayerRecordMapper;
import com.qs.log.game.model.PlayerRecord;
import com.qs.log.game.service.IPlayerRecordService;
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
public class PlayerRecordServiceImpl implements IPlayerRecordService{

    @Resource
    private PlayerRecordMapper playerRecordMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return playerRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PlayerRecord record) {
        return playerRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(PlayerRecord record) {
        return playerRecordMapper.insertSelective(record);
    }

    @Override
    public PlayerRecord selectByPrimaryKey(Long id) {
        return playerRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PlayerRecord record) {
        return playerRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PlayerRecord record) {
        return playerRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PlayerRecord> queryListByPage(Map<String, Object> parameters) {
        return playerRecordMapper.queryListByPage(parameters);
    }

    @Override
    public List<Map<String, Object>> queryCardRecordByPage(Map<String, Object> parameters) {
        return playerRecordMapper.queryCardRecordByPage(parameters);
    }

    @Override
    public List<Map<String, Object>> queryBoardStatisticsByPage(Map<String, Object> parameters) {
        return playerRecordMapper.queryBoardStatisticsByPage(parameters);
    }

    @Override
    public Integer queryBoardStatisticsByCount(Map<String, Object> parameters) {
        return playerRecordMapper.queryBoardStatisticsByCount(parameters);
    }

}
