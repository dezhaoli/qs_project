/*
 * 文件名：TaxesInviteDayServiceImpl.java	 
 * 时     间：下午8:51:16
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.TaxesInviteDayMapper;
import com.qs.log.game.model.TaxesInviteDay;
import com.qs.log.game.service.ITaxesInviteDayService;

/** 
 * @ClassName: TaxesInviteDayServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月2日 下午8:51:16 
 */
@Service
public class TaxesInviteDayServiceImpl implements ITaxesInviteDayService
{
	@Resource
	private TaxesInviteDayMapper taxesInviteDayMapper;
	
	@Override
	public List<TaxesInviteDay> queryBusinessListByPage(Map<String, Object> parma) {
		return taxesInviteDayMapper.queryBusinessListByPage(parma);
	}

	@Override
	public List<TaxesInviteDay> queryAgentListByPage(
			Map<String, Object> parameters)
	{
		return taxesInviteDayMapper.queryAgentListByPage(parameters);
	}
}
