/*
 * 文件名：MemberpaymentServiceImpl.java	 
 * 时     间：下午2:21:40
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.agent.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.agent.game.mapper.MemberpaymentMapper;
import com.qs.agent.game.model.Memberpayment;
import com.qs.agent.game.service.IMemberpaymentService;

/** 
 * @ClassName: MemberpaymentServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月8日 下午2:21:40 
 */
@Service
public class MemberpaymentServiceImpl implements IMemberpaymentService
{
	@Resource
	private MemberpaymentMapper memberpaymentMapper;

	@Override
	public List<Memberpayment> queryListByPage(Map<String, Object> parameters)
	{
		return memberpaymentMapper.queryListByPage(parameters);
	}
	
}
