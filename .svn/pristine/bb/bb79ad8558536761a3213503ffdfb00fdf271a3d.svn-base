package com.qs.webside.game.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.common.constant.Constants;
import com.qs.common.util.DateUtil;
import com.qs.common.util.DateUtils;
import com.qs.log.game.model.Mails;
import com.qs.log.game.service.IMailService;
import com.qs.pub.game.mapper.AppGameMapper;
import com.qs.pub.game.model.AppGame;
import com.qs.webside.game.mapper.ClubMapper;
import com.qs.webside.game.mapper.ClubMemberMapper;
import com.qs.webside.game.mapper.ClubMidsMapper;
import com.qs.webside.game.model.Club;
import com.qs.webside.game.model.ClubMember;
import com.qs.webside.game.model.ClubMids;
import com.qs.webside.game.service.IClubService;
import com.qs.webside.member.mapper.MemberagentsMapper;
import com.qs.webside.member.model.Memberagents;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.service.IMemberFideService;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

@Service
public class ClubServiceImpl implements IClubService{

	Logger log = Logger.getLogger(ClubServiceImpl.class);

	@Value("${game.gametype}")
    private int gameType;
	
	@Value("${game.goldhost}")
	private String goldHost;

	@Value("${game.goldport}")
	private int goldPort;
	
	@Resource
	private ClubMapper clubMapper;

	@Resource
	private ClubMemberMapper clubMemberMapper;
	
	@Resource
	private ClubMidsMapper clubMidsMapper;
	
	@Autowired
	private MemcachedClient memcachedClient;
	
	@Resource 
	private AppGameMapper appGameMapper;
	
	@Resource
	private MemberagentsMapper memberagentsMapper ;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private IMemberFideService memberFideService;
 	
	
	@Override
	@Cacheable(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},key="#root.methodName+':'+#root.args[0]")
	public List<Map<String, Object>> getClubInfoList(String mid) {
		
		 List<Map<String, Object>>  result=new ArrayList<>();
		 Map<String, Object> param=new HashMap<>();
		 String clubs="";
		 String clubAuth="";
		 List<ClubMember> clubMemberList=null;
		 //判断是否代理商
		 boolean flag=this.checkMidIsAgent(Integer.valueOf(mid));
		 if (flag){
			 log.info(" checkMidIsAgent is agent flag=::"+flag);
		 }
		 
			try {
				clubs=memcachedClient.get(AppConstants.MemcacheKeyPrefix.CLUBS+mid);
				if  (clubs == null ){
					clubs="";
				}
				clubAuth=memcachedClient.get(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+mid);
				if (clubAuth ==null ) {
					clubAuth="";
				}
				
			} catch (TimeoutException e) {
				log.info("getClubInfoList TimeoutException…………", e);
				e.printStackTrace();
			} catch (InterruptedException e) {
				log.info("getClubInfoList InterruptedException…………", e);
				e.printStackTrace();
			} catch (MemcachedException e) {
				log.info("getClubInfoList MemcachedException…………", e);
				e.printStackTrace();
			}
			
		if (!("").equals(clubs) && clubs !=null) {
			clubs=clubs.substring(0, clubs.length()-1);
			param.put("clubs", clubs);
			//club=clubs.split(",");
		}else {
			
			clubMemberList=clubMemberMapper.getByPrimaryKeyList (Integer.valueOf(mid));
			if (clubMemberList.size()>0 ){
				for (ClubMember clubMember : clubMemberList) {
					clubs+=clubMember.getClubid()+",";
				}
				//如果缓存为空单列表存在，重新更新memcached缓存做更新
				try {
					memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBS+mid, 0 ,clubs);
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
				clubs=clubs.substring(0, clubs.length()-1);
				param.put("clubs", clubs);
			}else {
				log.info("getClubInfoList clubs :is null …………");
				return result;
			}
		}
		result=clubMapper.getByMidClubsInfoList(param);
		for (Map<String, Object> map : result) {
			Object cmid=map.get("mid");
			String roomList="";
			if (cmid !=null) {
				
				roomList=(String) redisTemplate.opsForValue().get(Constants.Club.CLUB_ROOM_LIST+cmid.toString());
			}
			
			if (roomList ==null || roomList.equals("")) {
				map.put("roomList", Constants.Club.CLUB_ON);
			}else {
				map.put("roomList", Constants.Club.CLUB_OFF);
			}
			if (clubAuth ==null ){
				map.put("openRoom", 0);
				break;
			}
			if (clubAuth.contains(cmid.toString()) ){
				map.put("openRoom", 1);
			}else {
				map.put("openRoom", 0);
			}
		}
		return result;
	}

	/**
	 * 判断是否是代理商并授权相应乐部成员，代开房，俱乐部添加功能
	 * @param mid
	 * @param clubs
	 * @return
	 * @author:zyy
	 * @time:2017年9月4日
	 */
	public boolean checkMidIsAgent(int mid){
		Memberagents memberagents=memberagentsMapper.findMemberagentsByMid(mid);
		if (memberagents !=null){
			Club club=clubMapper.selectByPrimaryKey(mid);
			if (club ==null) {
				Club c=new Club();
				c.setClubid(mid);
				c.setOpenTime(DateUtil.currentTimeToInt());
				
				ClubMember cm=new ClubMember(); 
				cm.setMid(mid);
				cm.setClubid(mid);
				cm.setMtime(DateUtil.currentTimeToInt());
				
				ClubMids clubMids=new ClubMids();
				clubMids.setClubid(mid);
				clubMids.setMid(mid);
				
				try {
					//成员缓存
					clubMapper.insertSelective(c);//插入俱乐部表
					clubMemberMapper.insertSelective(cm);//插入成员表
					clubMidsMapper.insertSelective(clubMids); //插入代开房表
					String clubs=memcachedClient.get(AppConstants.MemcacheKeyPrefix.CLUBS+mid);
					if (clubs !=null ) {
						clubs+=mid+",";
					}else {clubs=mid+",";}
					memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBS+mid, 0 ,clubs);
					
					//代开房缓存
					String clubsAuth=memcachedClient.get(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+mid);
					if (clubsAuth !=null ){
						clubsAuth+=mid+",";
					}else{clubsAuth=mid+",";}
					memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+mid, 0 ,clubsAuth);
					
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
			}
		}else {
			return false;
		}
		return true;
	}
	@Override
	@CacheEvict(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},key="'getClubInfoList:'+#clubMember.mid")
	public int deleteClubMember(ClubMember clubMember) {
		List<ClubMember> clubMemberList=null;
		int count=0;
		String clubs="";
		String clubAuth="";
		count=clubMemberMapper.deleteByPrimaryKey(clubMember);
		if (count ==1 ){
			//删除俱乐部代开房
			ClubMids clubMids=new ClubMids();
			clubMids.setClubid(clubMember.getClubid());
			clubMids.setMid(clubMember.getMid());
			clubMidsMapper.deleteClubMidsInfo(clubMids);
			
			
			clubMemberList=clubMemberMapper.getByPrimaryKeyList (clubMember.getMid());
			if (clubMemberList.size()>0 ){
				for (ClubMember cm : clubMemberList) {
					clubs+=cm.getClubid()+",";
				}
			}
			List<ClubMids> clubMidsList=clubMidsMapper.getMidByClubMisList(clubMember.getMid());
			if (clubMidsList.size() >0) {
				for (ClubMids cm : clubMidsList) {
					clubAuth+=cm.getClubid()+",";
				}
			}
			try { 
			//更新memcached缓存CLUBS  CLUBSAUTH
				if (clubs.length()>0) {
				     memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBS+clubMember.getMid(), 0, clubs);
				 }else {
					 memcachedClient.delete(AppConstants.MemcacheKeyPrefix.CLUBS+clubMember.getMid());
				}
				
				if (clubAuth.length()>0){
					 memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+clubMember.getMid(), 0, clubAuth);
				}else {
					 memcachedClient.delete(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+clubMember.getMid());
				}
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}
		}
		
		Mails mails=new Mails();
		mails.setTitle("移除俱乐部");
		mails.setFujian(null);
		mails.setTarget(new Byte("2"));
		mails.setImportant(new Byte("0"));
		mails.setGametype(gameType+"");
		try {
			mails.setExpired(DateUtil.setLongTimeDate(new Date(),3));
		} catch (ParseException e) {
			e.printStackTrace();
			log.info("Mails set time Exception===========！"+e);
		}
		mails.setMids(clubMember.getClubid()+"");
		Memberfides mf =memberFideService.selectByPrimaryKey(clubMember.getMid());
		if (mf !=null ){
			mails.setContent("（"+mf.getName()+"）"+clubMember.getMid()+"在"+DateUtil.getNowDates()+"（时间）主动退出俱乐部。");
			
			mails.setMids(clubMember.getMid()+"");
			mailService.addSelective(mails, goldHost, goldPort);
			
			mails.setMids(clubMember.getClubid()+"");
			mailService.addSelective(mails, goldHost, goldPort);
		}else {
		/*	result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE,mid+ "用户信息不存在！");*/
			log.info(clubMember.getMid()+"user info not null::===========！");
			return AppConstants.Result.FAILURE_1002;
		}
		
		return count;
	
	
	}
}
