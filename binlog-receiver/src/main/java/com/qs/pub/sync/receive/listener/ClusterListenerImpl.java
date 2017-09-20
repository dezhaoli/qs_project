package com.qs.pub.sync.receive.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aliyun.drc.client.message.ByteString;
import com.aliyun.drc.client.message.DataMessage;
import com.aliyun.drc.client.message.DataMessage.Record;
import com.aliyun.drc.clusterclient.ClusterListener;
import com.aliyun.drc.clusterclient.message.ClusterMessage;
import com.qs.datasource.DataSourceSwitch;
import com.qs.pub.util.Underline2Camel;
import com.qs.sync.model.DouniuGameRecord;
import com.qs.sync.model.DouniuGameRecordSub;
import com.qs.sync.model.GameRecord;
import com.qs.sync.model.GameRecordSub;
import com.qs.sync.model.GoldLog;
import com.qs.sync.model.MajiangGameRecord;
import com.qs.sync.model.MajiangGameRecordSub;
import com.qs.sync.model.PlayerRecord;
import com.qs.sync.model.RoomRecord;
import com.qs.warehouse.service.IDouniuGameRecordService;
import com.qs.warehouse.service.IDouniuGameRecordSubService;
import com.qs.warehouse.service.IGameRecordService;
import com.qs.warehouse.service.IGameRecordSubService;
import com.qs.warehouse.service.IGoldLogService;
import com.qs.warehouse.service.IMajiangGameRecordService;
import com.qs.warehouse.service.IMajiangGameRecordSubService;
import com.qs.warehouse.service.IPlayerRecordService;
import com.qs.warehouse.service.IRoomRecordService;

import net.sf.json.JSONObject;

/**
 * 
 * Created by zsf @date 创建时间：2017年8月30日 下午3:14:14
 * Description: 主要负责监听阿里云的订阅通道
 */
@Service("listener")
public class ClusterListenerImpl extends ClusterListener {
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
	
	private static  ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);

	public void notify(List<ClusterMessage> messages) {

		fixedThreadPool.execute(new Runnable() {
			public void run() {
				try {
					for (ClusterMessage message : messages) {
						//可打印数据 
//						System.out.print(message.getRecord().getDbname() + ":" + message.getRecord().getTablename()
//								+ ":" + message.getRecord().getOpt() + ":" + message.getRecord().getTimestamp() + ":"
//								+ message.getRecord());

						Record.Type type = message.getRecord().getOpt();
						DataMessage.Record.Field field;
						GameRecord gameRecord=new GameRecord();
						GameRecordSub gameRecordSub=new GameRecordSub();
						GoldLog goldLog = new GoldLog();
						PlayerRecord playerRecord=new PlayerRecord();
						RoomRecord roomRecord=new RoomRecord();
						DouniuGameRecord douniuGameRecord=new DouniuGameRecord();
						DouniuGameRecordSub douniuGameRecordSub=new DouniuGameRecordSub();
						MajiangGameRecord majiangGameRecord=new MajiangGameRecord();
						MajiangGameRecordSub majiangGameRecordSub=new MajiangGameRecordSub();
						
						if (type.equals(Record.Type.INSERT)) {
							Map<String, String> map = new HashMap<String, String>();
							List<DataMessage.Record.Field> fields = message.getRecord().getFieldList();
							for (int i = 0; i < fields.size(); i++) {
								field = fields.get(i);
								ByteString str = field.getValue();
								String es = null;
								if (str != null) {
									es = new String(str.getBytes(), "utf-8");
								}
								map.put(Underline2Camel.underline2Camel(field.getFieldname(), true), es);

							}
							JSONObject params = JSONObject.fromObject(map);
							String  tableName = message.getRecord().getTablename();//表名字
							String dbName=message.getRecord().getDbname();//数据库名字
							DataSourceSwitch.setMainDataSourceType("dataWarehouseDataSource");//动态切换数据源
							if("game_record".equals(tableName)){
								gameRecord=JSON.parseObject(params.toString(), GameRecord.class);
								gameRecord.setDbName(dbName);
								gameRecordService.insertSelective(gameRecord);
							}else if("game_record_sub".equals(tableName)){
								gameRecordSub=JSON.parseObject(params.toString(), GameRecordSub.class);
								gameRecordSub.setDbName(dbName);
								gameRecordSubService.insertSelective(gameRecordSub);
							}else if("gold_log".equals(tableName)){
								goldLog = JSON.parseObject(params.toString(), GoldLog.class);
								goldLog.setDbName(dbName);
								goldLogService.insertSelective(goldLog);	
							}else if("player_record".equals(tableName)){
								playerRecord = JSON.parseObject(params.toString(), PlayerRecord.class);
								playerRecord.setDbName(dbName);
								playerRecordService.insertSelective(playerRecord);
							}else if("room_record".equals(tableName)){
								roomRecord = JSON.parseObject(params.toString(), RoomRecord.class);
								roomRecord.setDbName(dbName);
								roomRecordService.insertSelective(roomRecord);
							}else if("douniu_game_record".equals(tableName)){
								douniuGameRecord = JSON.parseObject(params.toString(), DouniuGameRecord.class);
								douniuGameRecord.setDbName(dbName);
								douniuGameRecordService.insertSelective(douniuGameRecord);
							}else if("douniu_game_record_sub".equals(tableName)){
								douniuGameRecordSub = JSON.parseObject(params.toString(), DouniuGameRecordSub.class);
								douniuGameRecordSub.setDbName(dbName);
								douniuGameRecordSubService.insertSelective(douniuGameRecordSub);
							}else if("majiang_game_record".equals(tableName)){
								majiangGameRecord = JSON.parseObject(params.toString(), MajiangGameRecord.class);
								majiangGameRecord.setDbName(dbName);
								majiangGameRecordService.insertSelective(majiangGameRecord);
							}else if("majiang_game_record_sub".equals(tableName)){
								majiangGameRecordSub = JSON.parseObject(params.toString(), MajiangGameRecordSub.class);
								majiangGameRecordSub.setDbName(dbName);
								majiangGameRecordSubService.insertSelective(majiangGameRecordSub);
							}
							

						}
						// ackAsConsumed必须调用，返回ack的消费点，可根据消费点进行接收消息
						message.ackAsConsumed();

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void noException(Exception e) {

	}

}
