package com.qs.pub.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.common.exception.ServiceException;
import com.qs.pub.sys.model.ResourceEntity;
import com.qs.pub.sys.model.RoleEntity;
import com.qs.pub.sys.service.ResourceService;
import com.qs.pub.sys.service.RoleResourceService;
import com.qs.pub.sys.service.RoleService;

@Service("roleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService{

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Override
	public boolean insertRoleAndResource(ResourceEntity resourceEntity) {
		try
		{
			//1、添加资源
			resourceService.insert(resourceEntity);
			//2、超级管理员直接赋予该权限
			RoleEntity role = roleService.findByName("超级管理员");
			roleService.addRolePerm(role.getId(), resourceEntity.getId());
			return true;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

}
