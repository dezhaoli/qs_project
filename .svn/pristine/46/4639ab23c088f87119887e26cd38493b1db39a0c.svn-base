package com.qs.webside.game.service;

import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.qs.webside.game.model.MobileVersion;
import com.qs.webside.game.model.Memberinvite;
import com.qs.common.constant.CacheConstan;
import com.qs.webside.game.model.BaseParam;

public interface GameService {
   
	/**
	 * 最新的app更新版本
	 * @return
	 */
	public MobileVersion findLatestMobileVersion(Map<String, Object> map,String cacheKey);
	/**
	 * 验证邀请码是否存在
	 * @param code
	 * @return
	 */
	public Memberinvite findByInviteCode(String code);

	/**
	 * 通过编码查询参数设置
	 * @param code
	 * @return
	 */
	public BaseParam getBaseParamByCode(String code);
	/**
	 * 取得参数值
	 * @param code
	 * @return
	 */
	public String getBaseParamValueByCode(String code);
	
	/***
	 * 绑定邀请者
	 * @param mid
	 * @param inviteMid
	 * @return
	 */
	public int saveInviteBind(int mid,int inviteMid);
	/**
	 * 更新金币
	 * @param mid
	 * @param gold
	 * @param gmType
	 * @return
	 */
	public Map<String, Object>  updateGold(int mid,int gold,int gmType);
	

	

 
}