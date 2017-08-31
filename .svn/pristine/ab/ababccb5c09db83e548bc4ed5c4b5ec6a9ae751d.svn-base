package com.qs.warehouse.model;

import java.io.Serializable;

public class RoomRecord implements Serializable {
    private Long id;

    private Integer room;

    private String uuid;

    private String info;

    private Integer timestamp;

    private Integer type;
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

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "RoomRecord [id=" + id + ", room=" + room + ", uuid=" + uuid + ", info=" + info + ", timestamp="
				+ timestamp + ", type=" + type + ", dbName=" + dbName + "]";
	}
    
}