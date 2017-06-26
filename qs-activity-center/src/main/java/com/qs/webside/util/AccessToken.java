package com.qs.webside.util;

import java.io.Serializable;

public class AccessToken implements Serializable{

	private static final long serialVersionUID = -7460586938668666167L;
	
	private Integer   mid;
	private Integer   gb;
	private Integer   gameType;

	public Integer getMid() {
		return mid;
	}


	public void setMid(Integer mid) {
		this.mid = mid;
	}


	public Integer getGb() {
		return gb;
	}


	public void setGb(Integer gb) {
		this.gb = gb;
	}


	public Integer getGameType() {
		return gameType;
	}


	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}
	
	
	  
	  
	  
   
	

}
