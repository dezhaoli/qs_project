package com.qs.sync.model;

import java.util.Date;

import com.qs.sync.model.SyncObject;


public class SyncPlaying extends SyncObject{
    /** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = -4955792482509877440L;

	private Integer id;

    private Integer mid;

    private Integer appId;
    private String appName;
    

    private Date gameStartTime;

    private Date gameStopTime;
    
    private String playId;
    private String playName;
    
    private Integer totals;
    
    //终端类型
    private String terminalType; 
    //渠道id
    private String channelId;
    //应用版本号
    private String appVersion;
    
    //ip地址
    private String ip;
    
    
    

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

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getAppName()
	{
		return appName;
	}

	public void setAppName(String appName)
	{
		this.appName = appName;
	}

	public Integer getTotals()
	{
		return totals;
	}

	public void setTotals(Integer totals)
	{
		this.totals = totals;
	}

	public String getPlayId()
	{
		return playId;
	}

	public void setPlayId(String playId)
	{
		this.playId = playId;
	}

	public String getPlayName()
	{
		return playName;
	}

	public void setPlayName(String playName)
	{
		this.playName = playName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Date getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(Date gameStartTime) {
        this.gameStartTime = new Date();
    }

    public Date getGameStopTime() {
        return gameStopTime;
    }

    public void setGameStopTime(Date gameStopTime) {
        this.gameStopTime = new Date();
    }
}