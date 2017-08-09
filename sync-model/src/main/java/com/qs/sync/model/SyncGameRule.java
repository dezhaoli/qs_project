package com.qs.sync.model;

import java.util.Date;

/**
 * C++调用接口，主要处理统计炸弹，春天的记录
 * @author zhengshengfei
 *
 */
public class SyncGameRule extends SyncObject {
	private Integer id;				//id
	private Integer mid;			//用户id
	private Integer appId;			//游戏id
	private Date createTime;		//创建时间
	private String name;			//名称 如炸弹，春天等
	private String type;			//0是炸弹  1是春天
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = new Date();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "SyncGameRule [id=" + id + ", mid=" + mid + ", appId=" + appId + ", createTime=" + createTime + ", name="
				+ name + ", type=" + type + "]";
	}
	
}
