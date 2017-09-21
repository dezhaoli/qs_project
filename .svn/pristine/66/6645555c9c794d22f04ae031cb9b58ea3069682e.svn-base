package com.qs.webside.activity.service.impl;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.Constants;
import com.qs.common.util.DateUtil;
import com.qs.webside.activity.mapper.ActiAwardRecordMapper;
import com.qs.webside.activity.mapper.ActiCenterMapper;
import com.qs.webside.activity.model.ActiAward;
import com.qs.webside.activity.model.ActiAwardRecord;
import com.qs.webside.activity.model.ActiCenter;
import com.qs.webside.activity.service.IActiAwardService;
import com.qs.webside.activity.service.IActiCenterService;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by zun.wei on 2017/5/31 15:54.
 * Description:活动中心表
 */
@Service
public class ActiCenterServiceImpl implements IActiCenterService {
	
	Logger log = Logger.getLogger(ActiCenterServiceImpl.class);  
    @Resource
    private ActiCenterMapper actiCenterMapper;
    
    @Resource
    private MemcachedClient memcachedClient;
    
    @Resource
    private ActiAwardRecordMapper actiAwardRecordMapper; 
    

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiCenterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiCenter record) {
        return actiCenterMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiCenter record) {
        return actiCenterMapper.insertSelective(record);
    }

    @Override
    public ActiCenter selectByPrimaryKey(Integer id) {
        return actiCenterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiCenter record) {
        return actiCenterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiCenter record) {
        return actiCenterMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiCenter> queryListByPage(Map<String, Object> parameter) {
        return actiCenterMapper.queryListByPage(parameter);
    }

    @Override
    @Cacheable(value = {CacheConstan.ACTIVITY_CENTER_CACHE_NAME}, key = "#root.methodName")
    public List<Map<String,Object>> queryListActivityByStatus() {
        return actiCenterMapper.queryListActivityByStatus();
    }

    @Override
    @Cacheable(value = {CacheConstan.ACTIVITY_CENTER_TYPE_CACHE_NAME},key = "#root.methodName + ':'+#root.args[0]")
    public Map<String, Object> queryListActivityByStatusAndType(Integer type) {
        return actiCenterMapper.queryListActivityByStatusAndType(type);
    }

    
    /**
     * 所用活动信息封装对象获取
     */
	@Override
	public Map<String, Object> getActivityCenterInfos(int mid,int gameType) {
		Map<String,Object> result=new HashMap<>();	
		Map<String,Object> parameters=new HashMap<>();	
		ActiCenter actiCenter= this.getActivityInfo(AppConstants.ActivityType.ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE);
		
		int lotteryNumber=Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT;
		log.debug("into getActivityCenterInfos actiCenter:NULL: IS NOT activityList");
		if (actiCenter !=null ){
			result.put("type", actiCenter.getType());
			result.put("createTime", actiCenter.getCreateTime());
			result.put("endTime", actiCenter.getCloseTime());
			result.put("allCount", Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT);
			Object object = null;
			try {
				object = memcachedClient.get(CacheConstan.NATIONAL_ACTIVITIES_FINISHROOMCOUNT+mid);
//				object=20;
			} catch (TimeoutException e) {
				log.debug("into getActivityCenterInfos Exception:::",e);
				e.printStackTrace();
			} catch (InterruptedException e) {
				log.debug("into getActivityCenterInfos Exception:::",e);
				e.printStackTrace();
			} catch (MemcachedException e) {
				log.debug("into getActivityCenterInfos Exception:::",e);
				e.printStackTrace();
			}
			log.debug("roomCount ___________________qain::"+object);
			//获取抽奖次数
			if (object !=null){
				log.debug("roomCount ___________________object::"+object);
				
				//获取当前用户当天抽奖次数
				parameters.put("mid", mid);
				parameters.put("type", AppConstants.ActivityType.ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE);
				parameters.put("date", DateUtil.getNewDate());
//				List <Map<String,Object>> actiAwardRecords=actiAwardRecordMapper.selectByParamList(parameters);
				int alreadyCount=actiAwardRecordMapper.queryRecordCount(parameters);//当天已经兑换奖品次数
				int count=0;
				if(alreadyCount >lotteryNumber){
					result.put("playCount",count );
				}else {
					if (Constants.GameType.JX_MAJIANG.equals(gameType+"")) {
						result.put("alreadyNumber", alreadyCount);
						count=Integer.valueOf(object.toString())/Constants.ActiveCenter.JX_ELEVEN_ACTIVITES_COUNT;//江西换算值积分换算剩余次数
					}else {
						count=Integer.valueOf(object.toString())/Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT;//开心换算值积分换算剩余次数
					}
					if(alreadyCount==0 || count ==0){
						result.put("playCount",count );
					}else {
						int orCount=lotteryNumber-alreadyCount;//可能获得做大次数
						if (orCount<count ){//if所有剩余次数小于获得可用次数就给当前可用次数
							result.put("playCount",orCount );
						}else {
							if (count-alreadyCount>0){
								
								result.put("playCount",count-alreadyCount );
							}else {
								result.put("playCount",count );
							}
						}
					}
				}
				/*if (Constants.GameType.JX_MAJIANG.equals(gameType+"")) {
					result.put("alreadyNumber", alreadyCount);
					count=Integer.valueOf(object.toString())/Constants.ActiveCenter.JX_ELEVEN_ACTIVITES_COUNT;//江西换算值积分换算剩余次数
				}else {
					count=Integer.valueOf(object.toString())/Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT;//开心换算值积分换算剩余次数
				}
				if (count>lotteryNumber){
					count=lotteryNumber;
				}
				if (alreadyCount<lotteryNumber ) {
					if (count+alreadyCount>lotteryNumber){
						
						result.put("playCount",count-alreadyCount );
					}else {
						result.put("playCount",count );
					}
				}else {
					result.put("playCount",0);
				}*/
			}else {
				result.put("playCount",0);
				if (Constants.GameType.JX_MAJIANG.equals(gameType+"")) {
					result.put("alreadyNumber",0);
				}
			}
			
		}
		
		return result;
	}

	@Override
    //@Cacheable(value = {CacheConstan.ACTI_AWARD_LIST_CACHE_NAME},key="#root.methodName+'AwardList:'+#parameters.get('mid')")
	public ActiCenter getActivityInfo(int type) {
		return actiCenterMapper.getActivityInfo(type);
	}
}
