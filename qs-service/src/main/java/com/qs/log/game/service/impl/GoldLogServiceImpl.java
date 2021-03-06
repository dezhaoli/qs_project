package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.GoldLogMapper;
import com.qs.log.game.model.GoldLog;
import com.qs.log.game.service.IGoldLogService;
import com.qs.pub.game.service.IAppGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  用户金币流入表
 *
 * Created by zun.wei on 2goldLogMapper17/3/9.
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
    @Override
    public List<GoldLog> queryLogListByPage(Map<String, Object> parameters) {
    	return goldLogMapper.queryLogListByPage(parameters);
    }

    @Override
    public List<Map<String, Object>> getUserGoldOriginPageByMid(Map<String, Object> parameters) {
        if (parameters == null) parameters = new HashMap<String, Object>();
        return goldLogMapper.getUserGoldOriginPageByMid(parameters);
    }

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return goldLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public GoldLog selectByPrimaryKey(Integer id) {
		
		return goldLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GoldLog record) {
		
		return goldLogMapper.updateByPrimaryKeySelective( record);
	}

	@Override
	public int updateByPrimaryKey(GoldLog record) {
		
		return goldLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateGoldFromParam(Map<String, Object> parma) {
		
		return goldLogMapper.updateGoldFromParam(parma);
	}

    @Override
    public List<Map<String, Object>> queryCardCountByDate(Map<String, Object> parameters) {
        return goldLogMapper.queryCardCountByDate(parameters);
    }

}
