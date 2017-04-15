package com.qs.webside.pay.model;
import java.io.Serializable;
import java.util.Date;

public class WeixinMsg implements Serializable{
	
	
	private String code;
	private String desc;
	private String id;
	private int  mid;
	private String  type; //错误类型
	private int  gameType;
	private String content;//内容
	
	private Date createTime;//创建时间

	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
	

	

}
