package com.qs.pub.sync.service;

import com.qs.sync.model.DouniuGameRecord;
import com.qs.sync.model.DouniuGameRecordSub;
import com.qs.sync.model.GameRecord;
import com.qs.sync.model.GameRecordSub;
import com.qs.sync.model.GoldLog;
import com.qs.sync.model.MajiangGameRecord;
import com.qs.sync.model.MajiangGameRecordSub;
import com.qs.sync.model.PlayerRecord;
import com.qs.sync.model.RoomRecord;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncGameRule;
import com.qs.sync.model.SyncMemberagents;
import com.qs.sync.model.SyncMemberbusiness;
import com.qs.sync.model.SyncMemberfides;
import com.qs.sync.model.SyncPlaying;
import com.qs.sync.model.SyncUserKeep;
import com.qs.sync.model.SyncUserLoginLog;

/**
 * 数据同步服务
 */
public interface SyncService {
    /**
     * 同步在玩
     * @param syncObject
     * @return
     */
    public int syncPlaying(SyncPlaying syncPlaying);
    /**
     * 同步创建房间
     * @param syncObject
     * @return
     */
    public int addSyncCreateRoom(SyncCreateRoom syncCreateRoom);
	public int syncCreateRoomLog(SyncCreateRoom syncCreateRoom);
	public int syncPlayingLog(SyncPlaying syncPlaying);
	public int addSyncUserLoginLog(SyncUserLoginLog syncUserLoginLog);
	public int syncUserLoginLogError(SyncUserLoginLog syncUserLoginLog);
	public int addSyncUserKeep(SyncUserKeep syncUserKeep);
	/**
	 * 处理  炸弹春天的统计 
	 * @param syncGameRule
	 */
	public int addSyncGameRule(SyncGameRule syncGameRule);
	
   
}
