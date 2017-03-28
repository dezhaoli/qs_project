package com.qs.pub.game.model;

public class AppGame {
    private Byte gid;

    private String gname;

    public Byte getGid() {
        return gid;
    }

    public void setGid(Byte gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }
}