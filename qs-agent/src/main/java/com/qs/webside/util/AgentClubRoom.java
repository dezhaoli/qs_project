package com.qs.webside.util;

import java.io.Serializable;

public class AgentClubRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4094282023505373774L;

	private	int bigLows;

	private	int bigMid;

	private	int bigHig;

	private	int yoursLows;

	private	int yoursMid;

	private	int yoursHig;

	private	int replaceLows;

	private	int replaceMid;

	private	int replaceHig;

	public int getBigLows() {
		return bigLows;
	}

	public void setBigLows(int bigLows) {
		this.bigLows = bigLows;
	}

	public int getBigMid() {
		return bigMid;
	}

	public void setBigMid(int bigMid) {
		this.bigMid = bigMid;
	}

	public int getBigHig() {
		return bigHig;
	}

	public void setBigHig(int bigHig) {
		this.bigHig = bigHig;
	}

	public int getYoursLows() {
		return yoursLows;
	}

	public void setYoursLows(int yoursLows) {
		this.yoursLows = yoursLows;
	}

	public int getYoursMid() {
		return yoursMid;
	}

	public void setYoursMid(int yoursMid) {
		this.yoursMid = yoursMid;
	}

	public int getYoursHig() {
		return yoursHig;
	}

	public void setYoursHig(int yoursHig) {
		this.yoursHig = yoursHig;
	}

	public int getReplaceLows() {
		return replaceLows;
	}

	public void setReplaceLows(int replaceLows) {
		this.replaceLows = replaceLows;
	}

	public int getReplaceMid() {
		return replaceMid;
	}

	public void setReplaceMid(int replaceMid) {
		this.replaceMid = replaceMid;
	}

	public int getReplaceHig() {
		return replaceHig;
	}

	public void setReplaceHig(int replaceHig) {
		this.replaceHig = replaceHig;
	}

	
	
}
