package com.qs.log.game.service.impl;

import java.util.ArrayList;
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
import org.springframework.stereotype.Service;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.AppConstants.MemcacheKeyPrefix;
import com.qs.log.game.mapper.ClubMapper;
import com.qs.log.game.mapper.ClubMemberMapper;
import com.qs.log.game.mapper.ClubMidsMapper;
import com.qs.log.game.model.Club;
import com.qs.log.game.model.ClubMember;
import com.qs.log.game.model.ClubMids;
import com.qs.log.game.service.IClubService;
import com.qs.pub.game.mapper.AppGameMapper;
import com.qs.pub.game.model.AppGame;
import com.qs.webside.api.controller.ShareLinkController;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

@Service
public class ClubServiceImpl implements IClubService{

	Logger log = Logger.getLogger(ClubServiceImpl.class);

	@Value("${game.gametype}")
    private int gameType;
	
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
	
	
	@Override
	@CacheEvict(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},key="#root.methodName+':'+#root.args[0]")
	public List<Map<String, Object>> getClubInfoList(String mid) {
		
		 List<Map<String, Object>>  result=new ArrayList<>();
		 Map<String, Object> param=new HashMap<>();
		 String clubs="";
		 List<ClubMember> clubMemberList=null;
		 
		try {
			clubs=memcachedClient.get(AppConstants.MemcacheKeyPrefix.CLUBS+mid);
		} catch (TimeoutException e) {
			log.debug("getClubInfoList TimeoutException…………", e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			log.debug("getClubInfoList InterruptedException…………", e);
			e.printStackTrace();
		} catch (MemcachedException e) {
			log.debug("getClubInfoList MemcachedException…………", e);
			e.printStackTrace();
		}
		
		if (("").equals(clubs) && clubs !=null) {
			clubs=clubs.substring(0, clubs.length()-1);
			param.put("clubs", clubs);
			//club=clubs.split(",");
		}else {
			
			clubMemberList=clubMemberMapper.getByPrimaryKeyList (Integer.valueOf(mid));
			if (clubMemberList.size()>0 ){
				for (ClubMember clubMember : clubMemberList) {
					clubs+=clubMember.getClubid()+",";
				}
				clubs=clubs.substring(0, clubs.length()-1);
				param.put("clubs", clubs);
			}else {
				log.debug("getClubInfoList clubs :is null …………");
				return result;
			}
		}
		AppGame appGame=  appGameMapper.selectByPrimaryKey(gameType);
		param.put("dbName", appGame.getGameCode()+".memberfides0");
		result=clubMapper.getByMidClubsInfoList(param);
		return result;
	}

	@Override
	public int deleteClubMember(ClubMember clubMember) {
		List<ClubMember> clubMemberList=null;
		int count=0;
		String clubs="";
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
			//更新memcached缓存
			if (clubs.length()>0) {
				
				try {
					memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBS+clubMember.getMid(), 0, clubs);
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
			}
		}else {
			count=AppConstants.Result.FAILURE_1002;
		}
		return count;
	
	
	}
}
