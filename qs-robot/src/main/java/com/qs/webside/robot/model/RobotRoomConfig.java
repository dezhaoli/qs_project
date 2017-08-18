package com.qs.webside.robot.model;

public class RobotRoomConfig {
    private Integer id;

    private Integer mid;

    private Integer roomType;

    private String roomName;

    private String data;

    private String wanfa;

    private String ownuser;

    private Integer gameType;

    private String remark;

    private String ext1;

    private String ext2;

    private String ext3;

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

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getWanfa() {
        return wanfa;
    }

    public void setWanfa(String wanfa) {
        this.wanfa = wanfa == null ? null : wanfa.trim();
    }

    public String getOwnuser() {
        return ownuser;
    }

    public void setOwnuser(String ownuser) {
        this.ownuser = ownuser == null ? null : ownuser.trim();
    }

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

	@Override
	public String toString() {
		return "RobotRoomConfig [id=" + id + ", mid=" + mid + ", roomType=" + roomType + ", roomName=" + roomName
				+ ", data=" + data + ", wanfa=" + wanfa + ", ownuser=" + ownuser + ", gameType=" + gameType
				+ ", remark=" + remark + ", ext1=" + ext1 + ", ext2=" + ext2 + ", ext3=" + ext3 + "]";
	}
    
}