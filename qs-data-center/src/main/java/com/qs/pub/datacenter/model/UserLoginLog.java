package com.qs.pub.datacenter.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserLoginLog  implements Serializable {
    /** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 6335033716682858203L;

	private Integer id;

    private Integer mid;

    private String appId;
    private String appName;

    private String appVersion;

    private String terminalType;

    private String channelId;
    private String channelName;

    private Date loginTime;

    private Date logoutTime;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
	{
		this.mid = mid;
	}

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	public String getAppVersion()
	{
		return appVersion;
	}

	public void setAppVersion(String appVersion)
	{
		this.appVersion = appVersion;
	}

	public String getTerminalType()
	{
		return terminalType;
	}

	public void setTerminalType(String terminalType)
	{
		this.terminalType = terminalType;
	}

	public String getChannelId()
	{
		return channelId;
	}

	public void setChannelId(String channelId)
	{
		this.channelId = channelId;
	}

	public Date getLoginTime()
	{
		return loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		
		this.loginTime = new Date();
	}

	public Date getLogoutTime()
	{
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime)
	{
		this.logoutTime = new Date();
	}

	public String getAppName()
	{
		return appName;
	}

	public void setAppName(String appName)
	{
		this.appName = appName;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}

    
}