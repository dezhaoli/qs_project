package com.qs.log.game.model;

public class Club {
    private Integer clubid;

    private String clubCost;

    private Integer openTime;

    public Integer getClubid() {
        return clubid;
    }

    public void setClubid(Integer clubid) {
        this.clubid = clubid;
    }

    public String getClubCost() {
        return clubCost;
    }

    public void setClubCost(String clubCost) {
        this.clubCost = clubCost == null ? null : clubCost.trim();
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) {
        this.openTime = openTime;
    }
}