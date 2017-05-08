/*
 * 文件名：IGameNoticeService.java	 
 * 时     间：下午6:23:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.webside.game.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.game.model.Notice;

/** 
 * @ClassName: IGameNoticeService 
 * @描述: 游戏公告 业务层 
 * @author wangzhen
 * @date 2017年5月3日 下午6:23:53 
 */
public interface INoticeService
{
	int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    public List<Notice> queryListByPage(Map<String, Object> parameter);

	List<Notice> selectByTitle(String title);
}
