/*
 * 文件名：ILoginIpRecordService.java	 
 * 时     间：下午4:28:14
 * 作     者：wangzhen       
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.service;

import java.util.List;
import java.util.Map;

import com.qs.log.game.model.DayLoginUserIp;
import com.qs.log.game.model.GoldLog;

/** 
 * @ClassName: ILoginIpRecordService 
 * @描述: 登陆ip统计业务层 
 * @author wangzhen
 * @date 2017年5月2日 下午4:28:14 
 */
public interface ILoginIpCountRecordService
{
	List<DayLoginUserIp> queryListByPage(Map<String, Object> parameters);
}
