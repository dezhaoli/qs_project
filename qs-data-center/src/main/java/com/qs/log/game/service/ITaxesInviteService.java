/*
 * 文件名：ITaxesInviteService.java	 
 * 时     间：上午9:19:27
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.service;

import java.util.List;
import java.util.Map;

import com.qs.log.game.model.TaxesInvite;

/** 
 * @ClassName: ITaxesInviteService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年7月3日 上午9:19:27 
 */
public interface ITaxesInviteService
{

	List<TaxesInvite> queryListByPage(Map<String, Object> parameters);

	List<TaxesInvite> queryListByPageOfArppu(Map<String, Object> parameters);
	
}
