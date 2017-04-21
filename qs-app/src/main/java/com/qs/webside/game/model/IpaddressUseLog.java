package com.qs.webside.game.model;

import java.util.Date;

public class IpaddressUseLog {
    private Integer id;

    private Integer mid;

    private String ipstring;

    private String name;

    private String loginIp;

    private String type;

    private Date usetime;

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

    public String getIpstring() {
        return ipstring;
    }

    public void setIpstring(String ipstring) {
        this.ipstring = ipstring == null ? null : ipstring.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }
}