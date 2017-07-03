/*
 * 文件名：TaxesInviteServiceImpl.java	 
 * 时     间：上午9:19:55
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.TaxesInviteMapper;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.ITaxesInviteService;

/** 
 * @ClassName: TaxesInviteServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年7月3日 上午9:19:55 
 */
@Service
public class TaxesInviteServiceImpl implements ITaxesInviteService
{

	@Resource
	private TaxesInviteMapper taxesInviteMapper;
	@Override
	public List<TaxesInvite> queryListByPage(Map<String, Object> parameters)
	{
		return taxesInviteMapper.queryListByPage(parameters);
	}
	@Override
	public List<TaxesInvite> queryListByPageOfArppu(
			Map<String, Object> parameters)
	{
		return taxesInviteMapper.queryListByPageOfArppu(parameters);
	}
	
}
