package com.qs.pub.sys.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.GameGroup;
import com.qs.pub.sys.model.Group;

public interface GameGroupMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameGroup record);

    int insertSelective(GameGroup record);

    GameGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameGroup record);

    int updateByPrimaryKey(GameGroup record);

    GameGroup findByName(String groupName);

	List<GameGroup> queryUserGroupList(Map<String, Object> parameter);

	List<GameGroup> queryListGroupPrivilege(Map<String, Object> parameters);
	//查找游戏中间表中游戏分组的所有游戏id
	int selectByGameGroupId(String id);
	//删除游戏分组中间表中的所有游戏id
	int deleteByGameGroupId(String id);
	//添加游戏分组中间表中的所有游戏id和分组id
	int addGameGroupMiddle(Map<String, Object> parameter);
	//查看所有的分组
	List<GameGroup> findAll();
	
	List<GameGroup> findById(Long id);
}