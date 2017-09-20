package com.qs.sync.model;

import java.util.Date;

public class GoldLog extends SyncObject {
    /** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 1579016419216325386L;

	private Long id;

    private Integer mid;

    private Byte type;

    private Long gold;

    private Long nowgold;

    private Byte action;

    private Date date;

    private String remark;

    private Byte gametype;

    private Integer formid;
    
 

	//数据库名字，动态插入
    private String dbName;

    public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getNowgold() {
        return nowgold;
    }

    public void setNowgold(Long nowgold) {
        this.nowgold = nowgold;
    }

    public Byte getAction() {
        return action;
    }

    public void setAction(Byte action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getGametype() {
        return gametype;
    }

    public void setGametype(Byte gametype) {
        this.gametype = gametype;
    }

    public Integer getFormid() {
        return formid;
    }

    public void setFormid(Integer formid) {
        this.formid = formid;
    }
    @Override
 	public String toString() {
 		return "GoldLog [id=" + id + ", mid=" + mid + ", type=" + type + ", gold=" + gold + ", nowgold=" + nowgold
 				+ ", action=" + action + ", date=" + date + ", remark=" + remark + ", gametype=" + gametype
 				+ ", formid=" + formid + ", dbName=" + dbName + "]";
 	}
}