package com.qs.pub.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.sys.model.ResourceEntity;

public interface ResourceService{

	/**
	 * 自定义方法
	 * 获取用户ID对应的资源信息
	 * @param userId
	 * @return
	 */
	public List<ResourceEntity> findResourcesByUserId(int userId);

	/**
	 * 自定义方法
	 * 获取用户ID对应的资源菜单信息
	 * @param userId
	 * @return
	 */
	public List<ResourceEntity> findResourcesMenuByUserId(int userId);
	
	public List<ResourceEntity> queryListByPage(Map<String, Object> parameter);
	
	public ResourceEntity findByName(String name);
	
	public ResourceEntity findById(Long id);

	public int update(ResourceEntity resourceEntity);
    
    public int deleteBatchById(List<Long> resourceIds);
    
    public List<ResourceEntity> queryResourceList(Map<String, Object> parameter);
    
    public int insert(ResourceEntity resourceEntity);
    
    public int count(Map<String, Object> parameter);
    
    public boolean deleteRoleAndResource(List<Long> resourceIds);

	/**
	 * 自定义方法
	 * 获取roleid对应的资源信息
	 * @param roleId 角色id
	 * @return
	 */
	public List<ResourceEntity> findResourcesMenuByRoleId(int roleId);


}
