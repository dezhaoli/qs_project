package com.qs.pub.game.model;

public class CommonAgents {
    private String sitemid;

    private String info;

    private String loginPhone;

    private String loginPasswd;

    private String loginSalt;

    private Integer loginBindtime;

    private Byte loginIsbind;

    public String getSitemid() {
        return sitemid;
    }

    public void setSitemid(String sitemid) {
        this.sitemid = sitemid == null ? null : sitemid.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone == null ? null : loginPhone.trim();
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd == null ? null : loginPasswd.trim();
    }

    public String getLoginSalt() {
        return loginSalt;
    }

    public void setLoginSalt(String loginSalt) {
        this.loginSalt = loginSalt == null ? null : loginSalt.trim();
    }

    public Integer getLoginBindtime() {
        return loginBindtime;
    }

    public void setLoginBindtime(Integer loginBindtime) {
        this.loginBindtime = loginBindtime;
    }

    public Byte getLoginIsbind() {
        return loginIsbind;
    }

    public void setLoginIsbind(Byte loginIsbind) {
        this.loginIsbind = loginIsbind;
    }
}