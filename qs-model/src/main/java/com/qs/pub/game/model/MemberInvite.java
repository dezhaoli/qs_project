package com.qs.pub.game.model;

public class MemberInvite {
    private Integer id;

    private Integer mid;

    private String sitemid;

    private String invitecode;

    private String inviteurl;

    private Byte status;

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

    public String getSitemid() {
        return sitemid;
    }

    public void setSitemid(String sitemid) {
        this.sitemid = sitemid == null ? null : sitemid.trim();
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode == null ? null : invitecode.trim();
    }

    public String getInviteurl() {
        return inviteurl;
    }

    public void setInviteurl(String inviteurl) {
        this.inviteurl = inviteurl == null ? null : inviteurl.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}