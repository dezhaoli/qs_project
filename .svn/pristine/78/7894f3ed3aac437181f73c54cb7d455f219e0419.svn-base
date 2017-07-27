/*
 * 文件名：GroupServiceImpl.java	 
 * 时     间：下午2:29:23
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.common.exception.ServiceException;
import com.qs.pub.datacenter.mapper.DictMapper;

import com.qs.pub.sys.mapper.BusinessGroupMapper;
import com.qs.pub.sys.mapper.GameDictMapper;
import com.qs.pub.sys.mapper.GameGroupMapper;
import com.qs.pub.sys.mapper.GroupLeaderMapper;
import com.qs.pub.sys.mapper.GroupMapper;
import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.GameDict;
import com.qs.pub.sys.model.GameGroup;
import com.qs.pub.sys.model.Group;
import com.qs.pub.sys.service.GameGroupService;


/** 
 * @ClassName: GroupServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月14日 下午2:29:23 
 */
@Service
public class GameGroupServiceImpl implements GameGroupService
{
	@Resource
	private GameGroupMapper gameGroupMapper;//游戏分组mapper
	@Resource
	private GameDictMapper gameDictMapper;//游戏mapper

	@Override
	public List<GameGroup> queryListByPage(Map<String, Object> parameters)
	{
		return gameGroupMapper.queryListByPage(parameters);
	}

	@Override
	public int insert(GameGroup groupEntity)
	{
		return gameGroupMapper.insert(groupEntity);
	}

	@Override
	public int update(GameGroup groupEntity)
	{
		return gameGroupMapper.updateByPrimaryKey(groupEntity);
	}

	@Override
	public GameGroup findById(Integer id)
	{
		return (GameGroup) gameGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public GameGroup findByName(String groupName)
	{
		return gameGroupMapper.findByName(groupName);
	}

	@Override
	public List<GameDict> queryDictList(Map<String, Object> parameter)
	{
		return gameDictMapper.queryDictList(parameter);
		
	}

	@Override
	public List<GameGroup> queryListGroupPrivilege(Map<String, Object> parameters)
	{
		// TODO Auto-generated method stub
		return gameGroupMapper.queryListGroupPrivilege(parameters);
	}

	@Override
	public boolean addGameGroupMiddle(String Id, List<Integer> list) {
		boolean flag = false;
		try {
			//保存的时候，先根据分组id删除所有中间表中的游戏id
			int permCount = gameGroupMapper.selectByGameGroupId(Id);
			boolean delFlag = true;
			if (permCount > 0) {
				int delResult = gameGroupMapper.deleteByGameGroupId(Id);
				if (permCount != delResult) {
					delFlag = false;
				}
			}

			if (delFlag) {
				if (list.size() > 0) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("groupId", Id);
					parameter.put("resourceIds", list);
					int addResult = gameGroupMapper.addGameGroupMiddle(parameter);
					if (addResult == list.size()) {
						flag = true;
					}
				} else {
					flag = false;
				}
			}
			return flag;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<GameGroup> findAll() {
		
		return gameGroupMapper.findAll();
	}



}
