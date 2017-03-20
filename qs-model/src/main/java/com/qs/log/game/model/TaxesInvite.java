package com.qs.log.game.model;

import java.math.BigDecimal;
import java.util.Date;

public class TaxesInvite {
    private Integer mid;

    private Date date;

    private BigDecimal paytotal;

    private BigDecimal selftotal;

    private Integer invitetotal;

    private Integer parentid;

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

    public BigDecimal getPaytotal() {
        return paytotal;
    }

    public void setPaytotal(BigDecimal paytotal) {
        this.paytotal = paytotal;
    }

    public BigDecimal getSelftotal() {
        return selftotal;
    }

    public void setSelftotal(BigDecimal selftotal) {
        this.selftotal = selftotal;
    }

    public Integer getInvitetotal() {
        return invitetotal;
    }

    public void setInvitetotal(Integer invitetotal) {
        this.invitetotal = invitetotal;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}