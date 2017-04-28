package com.qs.pub.game.model;

import java.math.BigDecimal;
import java.util.Date;

public class PlayerPayDay {
    private Integer id;

    private Date date;

    private BigDecimal paytotal;

    private Integer granttotal;

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

    public Integer getGranttotal() {
        return granttotal;
    }

    public void setGranttotal(Integer granttotal) {
        this.granttotal = granttotal;
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