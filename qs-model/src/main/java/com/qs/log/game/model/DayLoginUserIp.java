/*
 * 文件名：LoginUserIp.java	 
 * 时     间：下午4:31:47
 * 作     者：wangzhen       
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.model;

import java.io.Serializable;

/**
 * @ClassName: LoginUserIp
 * @描述: 登陆ip统计bean
 * @author wangzhen
 * @date 2017年5月2日 下午4:31:47
 */
public class DayLoginUserIp implements Serializable
{
	private static final long serialVersionUID = -8749536612124829961L;
	private String ip;// ip
	private Integer mid;// 用户
	private Integer time;
	
	public String getIp()
	{
		return ip;
	}
	
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
	public Integer getMid()
	{
		return mid;
	}
	
	public void setMid(Integer mid)
	{
		this.mid = mid;
	}
	
	public Integer getTime()
	{
		return time;
	}
	
	public void setTime(Integer time)
	{
		this.time = time;
	}

	@Override
	public String toString()
	{
		return "DayLoginUserIp [ip=" + ip + ", mid=" + mid + ", time=" + time
				+ "]";
	}
}
