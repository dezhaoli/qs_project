package com.qs.webside.member.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.model.Memberpayment;
import com.qs.webside.member.model.Members;

import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import com.qs.webside.member.model.Commongame;
import com.qs.webside.member.model.Game;
import com.qs.webside.member.model.Memberagents;
import com.qs.webside.member.model.Memberbusiness;

public interface PaymentService {
	/**
	 * 插入订单
	 * @param record
	 * @return
	 */
	public int insertMemberpayment(Memberpayment record);
	
	/**
	 * 查询单个订单
	 * @param id
	 * @return
	 */
	public Memberpayment findMemberpaymentById(Integer id);
	/**
	 * 完成订单支付
	 * @param pid
	 * @param pstatus
	 * @return
	 */
	 int updateFinishMemberpayment(int pid,byte pstatus,String ptransno);
	 /**
	  * 增加代理邀请奖励
	  * @param mid
	  * @param money
	  * @return
	  */
	 public boolean addAgentInviteCharge(int mid,float money);
	 /**
	  * 增加代理邀请成员数
	  * @param mid
	  * @param money
	  * @return
	  */
	 public boolean addAgentInviteMembers(int mid);
}


