package com.qs.pub.act.model;

import java.util.Date;

public class ActGiveGold extends BaseRequest{
    private Integer id;

    private Integer mid;

    private Integer gold;

    private Integer type;

    private Date giveTime;

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

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getGiveTime() {
        return giveTime;
    }

    public void setGiveTime(Date giveTime) {
        this.giveTime = giveTime;
    }

    @Override
    public String toString() {
        return "ActGiveGold{" +
                "id=" + id +
                ", mid=" + mid +
                ", gold=" + gold +
                ", type=" + type +
                ", giveTime=" + giveTime +
                '}';
    }

}