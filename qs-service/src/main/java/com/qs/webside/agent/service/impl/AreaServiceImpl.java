package com.qs.webside.agent.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.game.mapper.AreaMapper;
import com.qs.pub.game.model.Area;
import com.qs.webside.agent.service.IAreaService;

@Service
public class AreaServiceImpl implements IAreaService {
  
	@Resource
	private	AreaMapper areaMapper;

	@Override
	public int deleteByPrimaryKey(Integer aid) {
		return areaMapper.deleteByPrimaryKey(aid);
	}

	@Override
	public int insert(Area record) {
		return areaMapper.insert(record);
	}

	@Override
	public int insertSelective(Area record) {
		return areaMapper.insertSelective(record);
	}

	@Override
	public Area selectByPrimaryKey(Integer aid) {
		return areaMapper.selectByPrimaryKey(aid);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		return areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Area record) {
		return areaMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Area> selectByAreaPrimaryKey(Area record) {
		return areaMapper.selectByAreaPrimaryKey(record);
	}

	@Override
	public Area selectAreaNameByArea(String areaName) {
		return areaMapper.selectAreaNameByArea(areaName);
	}

	@Override
	public Map<String, Object> selectAreaInfo(Map<String,Object> parma) {
		Object aid=parma.get("aid");
		Area area=areaMapper.selectByPrimaryKey(Integer.valueOf(aid.toString()));
		if(area !=null){
			parma.put("level", area.getLevel());
		}else {
			parma.put("level", 0);
		}
		return areaMapper.selectAreaInfo(parma);
	}

	@Override
	public List<Area> selectBusGetAgentArea(String id) {
		return areaMapper.selectBusGetAgentArea(id);
	}

}
