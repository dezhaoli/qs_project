package com.qs.datasource.game.util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.qs.webside.member.model.MemberAgents;

@Component
public class DateSourcesUtil {

	Logger log = Logger.getLogger(DateSourcesUtil.class);  
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 获取当前用户的mid
	 * @return
	 */
	public static Integer getAgentMid(){
		Integer mid=0;
		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
		if (memberAgents != null) {
			mid = memberAgents.getMid();
		}
		return mid;
	}
	
	public  void setkey(String key,String value){
		redisTemplate.opsForValue().set(key,value,2*6,TimeUnit.DAYS);

	}

	public String getKey(String key){
		String value=
				(String)redisTemplate.opsForValue().get(key);
		return value; 
	}
	
	/**
	 *  读写类型，游戏类型，指定表
	 * @param gameType 游戏类型 默认取主表
	 * @param tableType 对应表名
	 * @return 唯一数据源
	 * @author:zyy
	 * @time:2017年4月21日
	 */
	public String getDateBaseName(String RWType,int gameType,String DBType){
		String name="";
		
		log.debug("into getDateBaseName select param ……………………"+RWType+":"+gameType+":"+DBType);
         //读数据源
		if (RWType.equals(ConstantUtil.ReadWrite.READ)) {

			switch (gameType) {
			case 6:
				name=ConstantUtil.GameNameGDMJ.GD_MAJIANG_READ;
				if (ConstantUtil.TypeCode.CFG.equals(DBType)){
					name=ConstantUtil.GameNameGDMJ.GD_MAJIANG_CFG_READ;
				}
				if (ConstantUtil.TypeCode.LOG.equals(DBType)){
					name=ConstantUtil.GameNameGDMJ.GD_MAJIANG_LOG_READ;
				}
				break;
			case 5:
				name=ConstantUtil.GameNameSCMJ.SC_MAJIANG_READ;
				if (ConstantUtil.TypeCode.CFG.equals(DBType)){
					name=ConstantUtil.GameNameSCMJ.SC_MAJIANG_CFG_READ;
				}
				if (ConstantUtil.TypeCode.LOG.equals(DBType)){
					name=ConstantUtil.GameNameSCMJ.SC_MAJIANG_LOG_READ;
				}
				break;
			default:
				log.debug("into getDateBaseName name::NULL ……………………"+gameType);
				break;
			}

		}else if (RWType.equals(ConstantUtil.ReadWrite.Write)){
           //写数据源
			switch (gameType) {
			case 6:
				name=ConstantUtil.GameNameGDMJ.GD_MAJIANG_WRITE;
				if (ConstantUtil.TypeCode.CFG.equals(DBType)){
					name=ConstantUtil.GameNameGDMJ.GD_MAJIANG_CFG_WRITE;
				}
				if (ConstantUtil.TypeCode.LOG.equals(DBType)){
					name=ConstantUtil.GameNameGDMJ.GD_MAJIANG_LOG_WRITE;
				}
				break;
			case 5:
				name=ConstantUtil.GameNameSCMJ.SC_MAJIANG_WRITE;
				if (ConstantUtil.TypeCode.CFG.equals(DBType)){
					name=ConstantUtil.GameNameSCMJ.SC_MAJIANG_CFG_WRITE;
				}
				if (ConstantUtil.TypeCode.LOG.equals(DBType)){
					name=ConstantUtil.GameNameSCMJ.SC_MAJIANG_LOG_WRITE;
				}
				break;
			default:
				log.debug("into getDateBaseName name::NULL ……………………"+gameType);
				break;
			}

			
		}else {
			log.debug("into getDateBaseName RWType::NULL ……………………"+gameType);
		}
		return name;
	}
}
