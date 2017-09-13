package com.qs.pub.datacenter.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayingRoom {
	private Integer id;

	private Integer mid;

	private Integer appId;

	private Integer total;

	private Date gameStartTime;
	
	//頁面展示
	private String gameStartTimeStr;


	public String getGameStartTimeStr() {
		return gameStartTimeStr;
	}

	public void setGameStartTimeStr(String gameStartTimeStr) {
		this.gameStartTimeStr = gameStartTimeStr;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Date getGameStartTime() {
		return gameStartTime;
	}

	public void setGameStartTime(Date gameStartTime) {
		this.gameStartTime = gameStartTime;
	}
}