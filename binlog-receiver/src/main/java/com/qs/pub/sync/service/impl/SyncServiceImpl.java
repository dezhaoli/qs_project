package com.qs.pub.sync.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.common.ip2region.DbSearcher;
import com.qs.common.util.DateUtil;
import com.qs.datasource.DataSourceSwitch;
import com.qs.pub.sync.common.SyncLogTool;
import com.qs.pub.sync.service.ISyncMemberfidesService;
import com.qs.pub.sync.service.ISyncMemberagentsService;
import com.qs.pub.sync.service.ISysnMemberbusinessService;
import com.qs.pub.sync.service.LogErrorService;
import com.qs.pub.sync.service.LogSuccessService;
import com.qs.pub.sync.service.SyncService;
import com.qs.sync.model.DouniuGameRecord;
import com.qs.sync.model.DouniuGameRecordSub;
import com.qs.sync.model.GameRecord;
import com.qs.sync.model.GameRecordSub;
import com.qs.sync.model.GoldLog;
import com.qs.sync.model.LogError;
import com.qs.sync.model.LogSuccess;
import com.qs.sync.model.MajiangGameRecord;
import com.qs.sync.model.MajiangGameRecordSub;
import com.qs.sync.model.PlayerRecord;
import com.qs.sync.model.RoomRecord;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncGameRule;
import com.qs.sync.model.SyncMemberagents;
import com.qs.sync.model.SyncMemberbusiness;
import com.qs.sync.model.SyncMemberfides;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncPlaying;
import com.qs.sync.model.SyncUserKeep;
import com.qs.sync.model.SyncUserLoginLog;
import com.qs.warehouse.service.IDouniuGameRecordService;
import com.qs.warehouse.service.IDouniuGameRecordSubService;
import com.qs.warehouse.service.IGameRecordService;
import com.qs.warehouse.service.IGameRecordSubService;
import com.qs.warehouse.service.IGoldLogService;
import com.qs.warehouse.service.IMajiangGameRecordService;
import com.qs.warehouse.service.IMajiangGameRecordSubService;
import com.qs.warehouse.service.IPlayerRecordService;
import com.qs.warehouse.service.IRoomRecordService;

/**
 * 同步数据服务实现
 */
@Service
public class SyncServiceImpl implements SyncService {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);


	
	
	@Autowired
	private ISysnMemberbusinessService syncMemberbusiness;
	
	@Autowired
	private ISyncMemberagentsService syncMemberagentsService;
	
	@Autowired
	private ISyncMemberfidesService syncMemberFidesService;
	
	
	
	
	 

	@Resource
	private IGameRecordService gameRecordService;
	@Resource
	private IGameRecordSubService gameRecordSubService;
	@Resource
	private IGoldLogService goldLogService;
	@Resource
	private IPlayerRecordService playerRecordService;
	@Resource
	private IRoomRecordService roomRecordService;
	@Resource
	private IDouniuGameRecordService douniuGameRecordService;
	@Resource
	private IDouniuGameRecordSubService douniuGameRecordSubService;
	@Resource
	private IMajiangGameRecordService majiangGameRecordService;
	@Resource
	private IMajiangGameRecordSubService majiangGameRecordSubService;
	
	
	
	

	



	/**
	 * 获取上层调用方法信息(即调用数据同步的方法,便于错误定位)
	 * @return
	 */
	protected static String getCallerMethod(){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		return ste.getClassName() + "." + ste.getMethodName() + "(line" + ste.getLineNumber() + ")";
	}
	
	
	@Override
	public void addMemberbusiness(SyncMemberbusiness myMessage)
	{
		syncMemberbusiness.insert(myMessage);
	}
	@Override
	public void addMemberagents(SyncMemberagents myMessage)
	{
		syncMemberagentsService.insert(myMessage);
	}
	@Override
	public void addMemberFides(SyncMemberfides myMessage)
	{
		syncMemberFidesService.insert(myMessage);
	}
	
	
	
	
	@Override
	public int addGameRecord(GameRecord gameRecord)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		gameRecord.setDbName(gameRecord.getDatabaseName());
		return gameRecordService.insertSelective(gameRecord);
	}
	@Override
	public int addGameRecordSub(GameRecordSub gameRecordSub)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		gameRecordSub.setDbName(gameRecordSub.getDatabaseName());
		return gameRecordSubService.insertSelective(gameRecordSub);
	}
	@Override
	public int addGoldLog(GoldLog goldLog)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		goldLog.setDbName(goldLog.getDatabaseName());
		return goldLogService.insertSelective(goldLog);
		 
	}
	@Override
	public int addPlayerRecord(PlayerRecord playerRecord)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		playerRecord.setDbName(playerRecord.getDatabaseName());
		return playerRecordService.insertSelective(playerRecord);
		
	}
	@Override
	public int addRoomRecord(RoomRecord roomRecord)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		roomRecord.setDbName(roomRecord.getDatabaseName());
		return roomRecordService.insertSelective(roomRecord);
		
	}
	@Override
	public int addDouniuGameRecord(DouniuGameRecord douniuGameRecord)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		douniuGameRecord.setDbName(douniuGameRecord.getDatabaseName());
		return douniuGameRecordService.insertSelective(douniuGameRecord);
		
	}
	@Override
	public int addDouniuGameRecordSub(DouniuGameRecordSub douniuGameRecordSub)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		douniuGameRecordSub.setDbName(douniuGameRecordSub.getDatabaseName());
		return douniuGameRecordSubService.insertSelective(douniuGameRecordSub);
		
	}
	@Override
	public int addMajiangGameRecord(MajiangGameRecord majiangGameRecord)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		majiangGameRecord.setDbName(majiangGameRecord.getDatabaseName());
		return majiangGameRecordService.insertSelective(majiangGameRecord);
		
	}
	@Override
	public int addMajiangGameRecordSub(
			MajiangGameRecordSub majiangGameRecordSub)
	{
		DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
		majiangGameRecordSub.setDbName(majiangGameRecordSub.getDatabaseName());
		return majiangGameRecordSubService.insertSelective(majiangGameRecordSub);
		
	}
	
	
	
	
 

}
