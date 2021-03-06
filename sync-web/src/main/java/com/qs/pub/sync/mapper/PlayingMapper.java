package com.qs.pub.sync.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.SyncPlaying;

public interface PlayingMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SyncPlaying record);


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