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
	public void addMemberbusiness(SyncMemberbusiness myMessage);
	public void addMemberagents(SyncMemberagents myMessage);
	public void addMemberFides(SyncMemberfides myMessage);
	
	
	public int addGameRecord(GameRecord gameRecord);
	public int addGameRecordSub(GameRecordSub gameRecordSub);
	public int addGoldLog(GoldLog goldLog);
	public int addPlayerRecord(PlayerRecord playerRecord);
	public int addRoomRecord(RoomRecord roomRecord);
	public int addDouniuGameRecord(DouniuGameRecord douniuGameRecord);
	public int addDouniuGameRecordSub(DouniuGameRecordSub douniuGameRecordSub);
	public int addMajiangGameRecord(MajiangGameRecord majiangGameRecord);
	public int addMajiangGameRecordSub(MajiangGameRecordSub majiangGameRecordSub);
    
   
}
