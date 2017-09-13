
package com.qs.pub.datacenter.service;

import java.util.List;
import java.util.Map;


import com.qs.pub.datacenter.model.PlayingRoom;

/**
 * 
 * Created by zsf @date 创建时间：2017年9月7日 下午2:46:44
 * Description: 用戶每日參與房間統計
 */
public interface IRoomPlayingService
{


	//Long queryRegionCountTotal(Map<String, Object> parameters);



	Long queryRoomCountTotal(Map<String, Object> parameters);

	List<PlayingRoom> queryListBymid(Map<String, Object> parameters);

}
