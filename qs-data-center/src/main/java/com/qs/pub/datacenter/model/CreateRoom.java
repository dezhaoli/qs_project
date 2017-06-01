package com.qs.pub.datacenter.model;

import java.util.Date;

public class CreateRoom {
    private Integer id;

    private Integer mid;

    private Integer appId;
    private String appName;
    private String playId;
    private String playName;

    private Date createRoomTime;
    
    private Integer totals;
    
    

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


    public Date getCreateRoomTime() {
        return createRoomTime;
    }

    public void setCreateRoomTime(Date createRoomTime) {
        this.createRoomTime = new Date();
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
    
    
}