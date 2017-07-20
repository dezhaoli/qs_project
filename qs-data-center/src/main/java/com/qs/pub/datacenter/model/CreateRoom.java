package com.qs.pub.datacenter.model;

import java.util.Date;

public class CreateRoom {
    private Integer id;

    private Integer mid;

    private Integer appId;
    private String appName;
    private String playId;
    private String playName;
    private String realname;
    private String userGroupName;
    private String businessName;
    private String name;
    private String userName;
    private Integer gold;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUserGroupName()
	{
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName)
	{
		this.userGroupName = userGroupName;
	}

	public String getBusinessName()
	{
		return businessName;
	}

	public void setBusinessName(String businessName)
	{
		this.businessName = businessName;
	}

	public Integer getGold()
	{
		return gold;
	}

	public void setGold(Integer gold)
	{
		this.gold = gold;
	}

	private Date createRoomTime;
    
    private Integer totals;
    
    

    public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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