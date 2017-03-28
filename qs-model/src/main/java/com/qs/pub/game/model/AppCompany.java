package com.qs.pub.game.model;

public class AppCompany {
    private Byte cid;

    private String cname;

    public Byte getCid() {
        return cid;
    }

    public void setCid(Byte cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}