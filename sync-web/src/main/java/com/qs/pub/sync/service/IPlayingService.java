/*
 * 文件名：IPlaying.java	 
 * 时     间：下午5:32:25
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sync.service;

import java.util.List;
import java.util.Map;

import com.qs.sync.model.SyncPlaying;

/** 
 * @ClassName: IPlaying 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月23日 下午5:32:25 
 */
public interface IPlayingService
{
	int insert(SyncPlaying playing);


	int insertPlayingDistinct(SyncPlaying playing);
	
	/**
	 * 
	 * @author zsf   @date 创建时间：2017年9月7日 上午10:41:31 
	 * @Description:  插入用戶當天游戲房間數
	 * @param  用戶同步對象
	 * @return
	 */
	int insertPlayingRoom(SyncPlaying playing);

	
}
