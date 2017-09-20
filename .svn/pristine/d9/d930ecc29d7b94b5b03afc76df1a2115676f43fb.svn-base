package com.qs.sync.model;

import java.util.Date;

public class GameRecordSub extends SyncObject {
    /** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = -8211303778133812198L;

	private Long id;

    private String uid;

    private Byte numOfGames;

    private String dataGame;

    private Integer winner;

    private Date date;

    private String uuid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Byte getNumOfGames() {
        return numOfGames;
    }

    public void setNumOfGames(Byte numOfGames) {
        this.numOfGames = numOfGames;
    }

    public String getDataGame() {
        return dataGame;
    }

    public void setDataGame(String dataGame) {
        this.dataGame = dataGame == null ? null : dataGame.trim();
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

	@Override
	public String toString() {
		return "GameRecordSub [id=" + id + ", uid=" + uid + ", numOfGames=" + numOfGames + ", dataGame=" + dataGame
				+ ", winner=" + winner + ", date=" + date + ", uuid=" + uuid + ", dbName=" + dbName + "]";
	}
    
}