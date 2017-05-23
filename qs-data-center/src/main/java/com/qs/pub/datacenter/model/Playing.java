package com.qs.pub.datacenter.model;

import java.util.Date;

public class Playing {
    private Integer id;

    private Integer mid;

    private Integer appId;

    private Date gameStartTime;

    private Date gameStopTime;
    
    private String playId;
    private String playName;
    

    

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
        this.gameStartTime = gameStartTime;
    }

    public Date getGameStopTime() {
        return gameStopTime;
    }

    public void setGameStopTime(Date gameStopTime) {
        this.gameStopTime = gameStopTime;
    }
}