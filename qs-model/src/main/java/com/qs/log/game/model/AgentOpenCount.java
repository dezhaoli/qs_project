package com.qs.log.game.model;

import java.io.Serializable;

public class AgentOpenCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5490454754040750302L;

	private int oprenRoom;//开房次数

	private int countGold;//玩牌总门卡

	private int countPlay;//一局玩牌人数
	
	public int getCountPlay() {
		return countPlay;
	}

	public void setCountPlay(int countPlay) {
		this.countPlay = countPlay;
	}

	public int getOprenRoom() {
		return oprenRoom;
	}

	public void setOprenRoom(int oprenRoom) {
		this.oprenRoom = oprenRoom;
	}

	public int getCountGold() {
		return countGold;
	}

	public void setCountGold(int countGold) {
		this.countGold = countGold;
	}
	
	
}
