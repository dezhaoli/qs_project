package com.qs.log.game.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.qs.log.game.mapper.GameRecordShareMapper;
import com.qs.log.game.mapper.GameRecordSubMapper;
import com.qs.log.game.mapper.RoomRecordMapper;
import com.qs.log.game.model.GameRecordSub;
import com.qs.log.game.model.GoldLog;
import com.qs.log.game.model.RoomRecord;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.GameRecordService;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.service.MemberService;
import com.qs.log.game.model.GameRecord;
import com.qs.log.game.model.GameRecordShare;
import com.qs.common.constant.CacheConstan;
import com.qs.common.util.CommonUtils;
import com.qs.log.game.mapper.GameRecordMapper;
import com.qs.log.game.mapper.GoldLogMapper;
import com.qs.log.game.mapper.TaxesInviteMapper;
import com.qs.log.game.mapper.PlayerRecordMapper;


@Service("gameRecordService")
public class GameRecordServiceImpl implements GameRecordService {
	
	@Autowired
	private RoomRecordMapper roomRecordMapper;
	@Autowired
	private GameRecordShareMapper gameRecordShareMapper;
	@Autowired
	private GameRecordSubMapper gameRecordSubMapper;
	@Autowired
	private GameRecordMapper gameRecordMapper;
	@Autowired
	private GoldLogMapper goldLogMapper;
	@Autowired
	private TaxesInviteMapper taxesInviteMapper;
	@Autowired
	private PlayerRecordMapper playerRecordMapper;
	@Autowired
	private GameRecordService gameRecordService;
	  
    @Value("${game.gametype}")
    private int gameType;
	
	
	
			   
	@Autowired
	private MemberService memberService;
	
	
	@Override
	public int addGameRecordShare(GameRecordShare gameRecordShare) {
		return gameRecordShareMapper.insert(gameRecordShare);
	}

	
	@Override
	public RoomRecord getPaiJuData(RoomRecord roomRecord) {
		return roomRecordMapper.getPaiJuData(roomRecord);
	}

	@Override
	public Map<String, Object> getShareGameRecord(Integer sid) {
		 Map<String, Object> shareMap= gameRecordShareMapper.getShareGameRecord(sid);
		 if(null!=shareMap){
		 Integer player1=(Integer)shareMap.get("player1");
		 Integer player2=(Integer)shareMap.get("player2");
		 Integer player3=(Integer)shareMap.get("player3");
		 Integer player4=(Integer)shareMap.get("player4");
		 
		 Memberfides mUser1= memberService.findMemberfidesById(player1);
		 Memberfides mUser2= memberService.findMemberfidesById(player2);
		 Memberfides mUser3= memberService.findMemberfidesById(player3);
		 Memberfides mUser4= memberService.findMemberfidesById(player4);
		 List<Object> storeJsona=new ArrayList<Object>();
		 if(null!=mUser1){
			 List<Object> aList=new ArrayList<Object>();
			 aList.add(mUser1.getMid());
			 aList.add(mUser1.getName());
			 aList.add(0);
			 aList.add(mUser1.getSex());
			 aList.add(mUser1.getIcon());
		     storeJsona.add(aList);
		 }
		 if(null!=mUser2){
			 List<Object> aList=new ArrayList<Object>();
			 aList.add(mUser2.getMid());
			 aList.add(mUser2.getName());
			 aList.add(0);
			 aList.add(mUser2.getSex());
			 aList.add(mUser2.getIcon());
		     storeJsona.add(aList);
		 }
		 if(null!=mUser3){
			 List<Object> aList=new ArrayList<Object>();
			 aList.add(mUser3.getMid());
			 aList.add(mUser3.getName());
			 aList.add(0);
			 aList.add(mUser3.getSex());
			 aList.add(mUser3.getIcon());
		     storeJsona.add(aList);
		 }
		 
		 if(null!=mUser4){
			 List<Object> aList=new ArrayList<Object>();
			 aList.add(mUser4.getMid());
			 aList.add(mUser4.getName());
			 aList.add(0);
			 aList.add(mUser4.getSex());
			 aList.add(mUser4.getIcon());
		     storeJsona.add(aList);
		 }
		 
		 shareMap.put("user",storeJsona);
		 }
		 
				 
		 return shareMap;
	}

	@Override
	public List<Object> getHonorDetail(Integer mid, String uid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<GameRecordSub> gameRecordSubList=gameRecordSubMapper.getHonorDetail(uid);
		 List<Object> jsonList=new ArrayList<Object>();
		 
		if(null!=gameRecordSubList&&gameRecordSubList.size()>0){
			for(GameRecordSub gameRecord:gameRecordSubList){
				 List<Object> subList=new ArrayList<Object>();
				 List<Object> arrList=new ArrayList<Object>();
				String[] dataGame = StringUtils.split(gameRecord.getDataGame(), ';');
				String[] aInfo0 = StringUtils.split(dataGame[0], ',');
				String[] aInfo1 = StringUtils.split(dataGame[1], ',');
				subList.add(aInfo0);
				subList.add(aInfo1);
				if(dataGame.length>=3){
					String[] aInfo2=StringUtils.split(dataGame[2], ',');
					subList.add(aInfo2);
				}
				if(dataGame.length>=4){
					String[] aInfo3=StringUtils.split(dataGame[3], ',');
					subList.add(aInfo3);
				}
				
				arrList.add(gameRecord.getWinner());
				arrList.add(gameRecord.getNumOfGames());
				arrList.add(gameRecord.getDate());
				arrList.add(subList);
				arrList.add(gameRecord.getUuid());
				arrList.add(gameRecord.getId());	
				jsonList.add(arrList);
			}
		}
		return jsonList;
	}


	@Override
	public List<Object> queryGameRecordListByPage(Map<String, Object> parameter) {
		List<GameRecord> gameRecordList= gameRecordMapper.queryListByPage(parameter);
		Map<Integer, Object> userMap = new HashMap<Integer, Object>();
		List<Object> rsultAllUser=new ArrayList<Object>();
		if(null!=gameRecordList){
			for(GameRecord gameRecord:gameRecordList){
			  if(CommonUtils.checkIntegerNull(gameRecord.getPlayer1())>0){
					 Memberfides mUser1= memberService.findMemberfidesById(gameRecord.getPlayer1());
					 userMap.put(gameRecord.getPlayer1(), mUser1);
				}
			   if(CommonUtils.checkIntegerNull(gameRecord.getPlayer2())>0){
					 Memberfides mUser2= memberService.findMemberfidesById(gameRecord.getPlayer2());
					 userMap.put(gameRecord.getPlayer2(), mUser2);
				}
			   if(CommonUtils.checkIntegerNull(gameRecord.getPlayer3())>0){
					 Memberfides mUser3= memberService.findMemberfidesById(gameRecord.getPlayer3());
					 userMap.put(gameRecord.getPlayer3(), mUser3);
				}
			   if(CommonUtils.checkIntegerNull(gameRecord.getPlayer4())>0){
					 Memberfides mUser4= memberService.findMemberfidesById(gameRecord.getPlayer4());
					 userMap.put(gameRecord.getPlayer4(), mUser4);
				}
	
				 
			}
		}
		
		if(null!=gameRecordList){
			for(GameRecord gameRecord:gameRecordList){
				List<Object> allUser=new ArrayList<Object>();
			   if(CommonUtils.checkIntegerNull(gameRecord.getPlayer1())>0){
					List<Object> playerUser1=new ArrayList<Object>();
					playerUser1.add(gameRecord.getPlayer1());
					Memberfides user=(Memberfides)userMap.get(gameRecord.getPlayer1());
					playerUser1.add(user.getName());
					String[] dataGame = StringUtils.split(gameRecord.getDataGame(), ';');
					playerUser1.add(dataGame[0]);
					playerUser1.add(user.getSex());
					playerUser1.add(user.getIcon());
					allUser.add(playerUser1);
			  }
		
			    if(CommonUtils.checkIntegerNull(gameRecord.getPlayer2())>0){
					List<Object> playerUser2=new ArrayList<Object>();
					playerUser2.add(gameRecord.getPlayer2());
					Memberfides user=(Memberfides)userMap.get(gameRecord.getPlayer2());
					playerUser2.add(user.getName());
					String[] dataGame = StringUtils.split(gameRecord.getDataGame(), ';');
					playerUser2.add(dataGame[1]);
					playerUser2.add(user.getSex());
					playerUser2.add(user.getIcon());
					allUser.add(playerUser2);
			  }
			    if(CommonUtils.checkIntegerNull(gameRecord.getPlayer3())>0){
					List<Object> playerUser3=new ArrayList<Object>();
					playerUser3.add(gameRecord.getPlayer3());
					Memberfides user=(Memberfides)userMap.get(gameRecord.getPlayer3());
					playerUser3.add(user.getName());
					String[] dataGame = StringUtils.split(gameRecord.getDataGame(), ';');
					playerUser3.add(dataGame[2]);
					playerUser3.add(user.getSex());
					playerUser3.add(user.getIcon());
					allUser.add(playerUser3);
			  }
			    
			    
			    if(CommonUtils.checkIntegerNull(gameRecord.getPlayer4())>0){
					List<Object> playerUser4=new ArrayList<Object>();
					playerUser4.add(gameRecord.getPlayer4());
					Memberfides user=(Memberfides)userMap.get(gameRecord.getPlayer4());
					playerUser4.add(user.getName());
					String[] dataGame = StringUtils.split(gameRecord.getDataGame(), ';');
					playerUser4.add(dataGame[3]);
					playerUser4.add(user.getSex());
					playerUser4.add(user.getIcon());
					allUser.add(playerUser4);
			  }
			    
			    List<Object> pList=new ArrayList<Object>();
			    pList.add(gameRecord.getRoomid());
			    pList.add(gameRecord.getNum());
			    pList.add(gameRecord.getDate());
			    pList.add(allUser);
			    pList.add(gameRecord.getUid());
			    
			    //当游戏开心跑胡子时候需要type字段
			    if ( gameType ==17) {
			    	pList.add(gameRecord.getType());
			    }
			    pList.add(gameRecord.getMaster());
			    pList.add(gameRecord.getClubId());
			    pList.add(null);
			    rsultAllUser.add(pList);
			    
				
			}
			
		}
		
		return rsultAllUser;
	}


	@Override
	public int saveGoldLog(int mid, long gold, long nowgold, byte goldLogType, byte action) {
		GoldLog record=new GoldLog();
		record.setMid(mid);
		record.setType(goldLogType);
		record.setGold(gold);
		record.setNowgold(nowgold);
		record.setAction(action);
		record.setGametype((byte)gameType);
		record.setRemark("");
		record.setFormid(0);
		int c=goldLogMapper.insertSelective(record);
		return c;
	}


	@Override
	public int addOrUpdateTaxesInvite(TaxesInvite record) {
		
		return taxesInviteMapper.insertOrUpdateTaxesInvite(record);
	}


	@Override
	@Cacheable(value={CacheConstan.GAME_RECORD_CACHE_NAME},key="#root.methodName+':'+#root.args[0]")
	public int getPlayCount(int mid, byte gameType) {
		return playerRecordMapper.getPlayCount(mid, gameType);
	}



}
