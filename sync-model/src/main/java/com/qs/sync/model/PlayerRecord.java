package com.qs.sync.model;


public class PlayerRecord extends SyncObject{
    /** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = -5632232467297925079L;

	private Long id;

    private Integer mid;

    private Integer room;

    private Integer seat;

    private String uuid;

    private Integer timestamp;

    private Byte type;

    private Integer clubId;
    
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

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

	@Override
	public String toString() {
		return "PlayerRecord [id=" + id + ", mid=" + mid + ", room=" + room + ", seat=" + seat + ", uuid=" + uuid
				+ ", timestamp=" + timestamp + ", type=" + type + ", clubId=" + clubId + ", dbName=" + dbName + "]";
	}
    
}