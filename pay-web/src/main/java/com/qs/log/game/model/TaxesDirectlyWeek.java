package com.qs.log.game.model;

import java.util.Date;

public class TaxesDirectlyWeek {
    private Integer mid;

    private Date date;

    private Integer paycount;

    private Short bindpeople;

    private Integer playtimes;

    private Integer rebatetotal;

    private Byte isaward;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPaycount() {
        return paycount;
    }

    public void setPaycount(Integer paycount) {
        this.paycount = paycount;
    }

    public Short getBindpeople() {
        return bindpeople;
    }

    public void setBindpeople(Short bindpeople) {
        this.bindpeople = bindpeople;
    }

    public Integer getPlaytimes() {
        return playtimes;
    }

    public void setPlaytimes(Integer playtimes) {
        this.playtimes = playtimes;
    }

    public Integer getRebatetotal() {
        return rebatetotal;
    }

    public void setRebatetotal(Integer rebatetotal) {
        this.rebatetotal = rebatetotal;
    }

    public Byte getIsaward() {
        return isaward;
    }

    public void setIsaward(Byte isaward) {
        this.isaward = isaward;
    }
}