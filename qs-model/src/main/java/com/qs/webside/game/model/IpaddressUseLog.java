package com.qs.webside.game.model;

public class IpaddressUseLog {
    private Integer id;

    private Integer mid;

    private Byte gametype;

    private String belonglevel;

    private String ipstring;

    private Integer usetime;

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

    public Byte getGametype() {
        return gametype;
    }

    public void setGametype(Byte gametype) {
        this.gametype = gametype;
    }

    public String getBelonglevel() {
        return belonglevel;
    }

    public void setBelonglevel(String belonglevel) {
        this.belonglevel = belonglevel == null ? null : belonglevel.trim();
    }

    public String getIpstring() {
        return ipstring;
    }

    public void setIpstring(String ipstring) {
        this.ipstring = ipstring == null ? null : ipstring.trim();
    }

    public Integer getUsetime() {
        return usetime;
    }

    public void setUsetime(Integer usetime) {
        this.usetime = usetime;
    }
}