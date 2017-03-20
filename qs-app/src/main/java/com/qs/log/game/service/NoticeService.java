package com.qs.log.game.service;

import com.qs.webside.member.model.Memberfides;

import java.util.List;
import java.util.Map;

import com.qs.log.game.model.Notice;

public interface NoticeService {
    /**
     * 获取所有公告信息
     * @param parameter
     * @return
     */
	public List<Notice>  getAllNotice(Map<String, Object> parameter);
	/**
	 * 获取用户邮件
	 * @param mid
	 * @return
	 */
	public List<Object>  getMails(int mid);
	/**
	 * 
	 * @param mid
	 * @param emailid
	 * @return
	 */
	public int  saveReadMails(int mid,int emailid,String cacheKey);
	
}