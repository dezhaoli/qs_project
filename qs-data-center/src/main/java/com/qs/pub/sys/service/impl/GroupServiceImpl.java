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
import com.qs.pub.sys.mapper.BusinessGroupMapper;
import com.qs.pub.sys.mapper.GroupLeaderMapper;
import com.qs.pub.sys.mapper.GroupMapper;
import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.Group;
import com.qs.pub.sys.service.GroupService;

/** 
 * @ClassName: GroupServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月14日 下午2:29:23 
 */
@Service
public class GroupServiceImpl implements GroupService
{
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private BusinessGroupMapper businessGroupMapper;
	@Resource
	private GroupLeaderMapper groupLeaderMapper;
	@Override
	public List<Group> queryListByPage(Map<String, Object> parameters)
	{
		return groupMapper.queryListByPage(parameters);
	}

	@Override
	public int insert(Group groupEntity)
	{
		return groupMapper.insert(groupEntity);
	}

	@Override
	public int update(Group groupEntity)
	{
		return groupMapper.updateByPrimaryKey(groupEntity);
	}

	@Override
	public Group findById(Integer id)
	{
		return (Group) groupMapper.selectByPrimaryKey(id);
	}

	@Override
	public Group findByName(String groupName)
	{
		return groupMapper.findByName(groupName);
	}

	@Override
	public boolean addRolePermBatch(int id, List<Integer> list)
	{
		boolean flag = false;
		try {
			int permCount = businessGroupMapper.selectByGroupId(id);
			boolean delFlag = true;
			if (permCount > 0) {
				int delResult = businessGroupMapper.deleteByGroupId(id);
				if (permCount != delResult) {
					delFlag = false;
				}
			}

			if (delFlag) {
				if (list.size() > 0) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("groupId", id);
					parameter.put("resourceIds", list);
					int addResult = businessGroupMapper.addBusinessBatch(parameter);
					if (addResult == list.size()) {
						flag = true;
					}
				} else {
					flag = true;
				}
			}
			return flag;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<BusinessGroup> queryBusinessList(Map<String, Object> parameter)
	{
		return businessGroupMapper.queryBusinessList(parameter);
	}

	@Override
	public List<BusinessGroup> queryLeaderList(Map<String, Object> parameter) {
		return businessGroupMapper.queryLeaderList(parameter);
	}

	@Override
	public boolean addLeaderPermBatch(Integer id, List<Integer> list) {
		boolean flag = false;
		try {
			int permCount = groupLeaderMapper.selectByGroupId(id);
			boolean delFlag = true;
			if (permCount > 0) {
				int delResult = groupLeaderMapper.deleteByGroupId(id);
				if (permCount != delResult) {
					delFlag = false;
				}
			}

			if (delFlag) {
				if (list.size() > 0) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("groupId", id);
					parameter.put("resourceIds", list);
					int addResult = groupLeaderMapper.addBusinessBatch(parameter);
					if (addResult == list.size()) {
						flag = true;
					}
				} else {
					flag = true;
				}
			}
			return flag;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Group> queryListGroupPrivilege(Map<String, Object> parameters)
	{
		// TODO Auto-generated method stub
		return groupMapper.queryListGroupPrivilege(parameters);
	}

}
