package com.qs.stat.game.model;

import java.math.BigDecimal;
import java.util.Date;

public class BizInviteWeek {
    private Integer id;

    private Date date;

    private BigDecimal paytotal;

    private BigDecimal rebatetotal;

    private Integer bindpeople;

    private Byte gametype;

    private Short bizid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPaytotal() {
        return paytotal;
    }

    public void setPaytotal(BigDecimal paytotal) {
        this.paytotal = paytotal;
    }

    public BigDecimal getRebatetotal() {
        return rebatetotal;
    }

    public void setRebatetotal(BigDecimal rebatetotal) {
        this.rebatetotal = rebatetotal;
    }

    public Integer getBindpeople() {
        return bindpeople;
    }

    public void setBindpeople(Integer bindpeople) {
        this.bindpeople = bindpeople;
    }

    public Byte getGametype() {
        return gametype;
    }

    public void setGametype(Byte gametype) {
        this.gametype = gametype;
    }

    public Short getBizid() {
        return bizid;
    }

    public void setBizid(Short bizid) {
        this.bizid = bizid;
    }
}