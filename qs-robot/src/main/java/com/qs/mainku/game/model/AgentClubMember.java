package com.qs.mainku.game.model;

import java.util.Date;

public class AgentClubMember {
    private Integer id;

    private Integer cmid;

    private Integer mid;

    private Date createTime;

    private Integer playCount;

    private Integer openRoom;

    private String extend1;

    private Integer extend2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmid() {
        return cmid;
    }

    public void setCmid(Integer cmid) {
        this.cmid = cmid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getOpenRoom() {
        return openRoom;
    }

    public void setOpenRoom(Integer openRoom) {
        this.openRoom = openRoom;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public Integer getExtend2() {
        return extend2;
    }

    public void setExtend2(Integer extend2) {
        this.extend2 = extend2;
    }
}