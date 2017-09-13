
package com.qs.pub.datacenter.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.qs.pub.datacenter.mapper.PlayingRoomMapper;
import com.qs.pub.datacenter.model.PlayingRoom;
import com.qs.pub.datacenter.service.IRoomPlayingService;

/**
 * 
 * Created by zsf @date 创建时间：2017年9月7日 下午2:46:44
 * Description: 用戶每日參與房間統計
 */
@Service
public class PlayingRoomServiceImpl implements IRoomPlayingService
{
	@Resource
	private PlayingRoomMapper playingRoomMapper;



	@Override
	public Long queryRoomCountTotal(Map<String, Object> parameters) {
		
		return playingRoomMapper.queryRoomCountTotal(parameters);
	}

	@Override
	public List<PlayingRoom> queryListBymid(Map<String, Object> parameters) {
		
		return playingRoomMapper.queryListBymid(parameters);
	}

	
}
