package com.qs.pub.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.datacenter.model.PlayingRoom;

public interface PlayingRoomMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlayingRoom record);

    int insertSelective(PlayingRoom record);

    PlayingRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlayingRoom record);

    int updateByPrimaryKey(PlayingRoom record);
    

	Long queryRoomCountTotal(Map<String, Object> parameters);
	//根據mid查詢所有的參與房間
	List<PlayingRoom> queryListBymid(Map<String, Object> parameters);
}