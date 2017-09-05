/*
 * 文件名：GroupService.java	 
 * 时     间：下午2:28:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.GameDict;
import com.qs.pub.sys.model.GameGroup;
import com.qs.pub.sys.model.Group;

/** 
 * @ClassName: GroupService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月14日 下午2:28:53 
 */
public interface GameGroupService
{

	List<GameGroup> queryListByPage(Map<String, Object> parameters);

	int insert(GameGroup groupEntity);

	int update(GameGroup groupEntity);

	GameGroup findById(Integer id);

	GameGroup findByName(String groupName);

	List<GameDict> queryDictList(Map<String, Object> parameter);

	List<GameGroup> queryListGroupPrivilege(Map<String, Object> parameters);

	//查询所有的分组
	List<GameGroup> findAll();
	//添加分配游戏权限信息
	boolean addGameGroupMiddle(String Id, List<Integer> list);

	List<GameGroup> findById(Long id);
	

	
}
