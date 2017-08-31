package com.qs.warehouse.model;

import java.util.Date;

public class MajiangGameRecordSub {
    private Integer id;

    private String uid;

    private Byte numOfGames;

    private String dataGame;

    private String winner;

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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner == null ? null : winner.trim();
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
		return "MajiangGameRecordSub [id=" + id + ", uid=" + uid + ", numOfGames=" + numOfGames + ", dataGame="
				+ dataGame + ", winner=" + winner + ", date=" + date + ", uuid=" + uuid + ", dbName=" + dbName + "]";
	}
    
}