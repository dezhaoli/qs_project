package com.qs.log.game.service;

import com.qs.log.game.model.RoomRecord;
import com.qs.log.game.model.TaxesInvite;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qs.log.game.model.GameRecordShare;
import com.qs.log.game.model.GameRecord;

public interface GameRecordService {
	
	
	/**
	 * 玩家玩的局数
	 * @param mid
	 * @param gameType
	 * @return
	 */
	int getPlayCount(int mid,byte gameType);
   
	/**
	 * 获取牌局回放数据
	 * @return
	 */
	public RoomRecord getPaiJuData(RoomRecord roomRecord);
	/**
	 * 获取牌局回放数据
	 * @param sid
	 * @return
	 */
	public Map<String, Object> getShareGameRecord(Integer sid);
	
	/**
	 * 获取详细战绩
	 * @param mid
	 * @param uid
	 * @return
	 */
	public List<Object> getHonorDetail(Integer mid,String uid);
	
	/**
	 * 分享牌局
	 * @param gameRecordShare
	 * @return
	 */
	public int addGameRecordShare(GameRecordShare gameRecordShare);
	
	/**
	 * 获取我的战绩
	 * @param parameter
	 * @return
	 */
	public List<Object> queryGameRecordListByPage(Map<String, Object> parameter);
	
	/**
	 * 金币记录
	 * @param mid
	 * @param gold
	 * @param nowgold
	 * @param type
	 * @param action
	 */
	public int  saveGoldLog(int mid,long gold,long nowgold,byte goldLogType,byte action);
	
	/**
	 * 新增邀请者奖励(数据不存在就更新操作)
	 * @param record
	 * @return
	 */
	public int addOrUpdateTaxesInvite(TaxesInvite record);
	 
	
	
	
	
 
}