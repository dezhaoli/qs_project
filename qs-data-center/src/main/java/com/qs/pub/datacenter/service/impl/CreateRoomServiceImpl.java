/*
 * 文件名：CreateRoomServiceImpl.java	 
 * 时     间：下午5:39:34
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.datacenter.mapper.CreateRoomMapper;
import com.qs.pub.datacenter.model.CreateRoom;
import com.qs.pub.datacenter.service.ICreateRoomService;

/** 
 * @ClassName: CreateRoomServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月23日 下午5:39:34 
 */
@Service
public class CreateRoomServiceImpl implements ICreateRoomService
{
	@Resource
	private CreateRoomMapper createRoomMapper;
	@Override
	public int insert(CreateRoom createRoom)
	{
		// TODO Auto-generated method stub
		return createRoomMapper.insert(createRoom);
	}
	@Override
	public List<CreateRoom> queryPageList(Map<String, Object> parameters)
	{
		// TODO Auto-generated method stub
		return createRoomMapper.queryListByPage(parameters);
	}
	
}
