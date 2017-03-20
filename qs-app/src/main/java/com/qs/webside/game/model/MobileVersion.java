package com.qs.webside.game.model;

import java.io.Serializable;
import java.util.Date;

public class MobileVersion implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7168819854220772550L;

	private Integer id;

    private Byte site;

    private Integer channel;

    private Byte bigversion;

    private Short version;

    private Byte isskip;

    private String des;

    private String url;

    private String onlineversion;

    private String devicelistTest;

    private String urlTest;

    private Date lasttime;

    private String forceurl;

    private Integer gameType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getSite() {
        return site;
    }

    public void setSite(Byte site) {
        this.site = site;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Byte getBigversion() {
        return bigversion;
    }

    public void setBigversion(Byte bigversion) {
        this.bigversion = bigversion;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Byte getIsskip() {
        return isskip;
    }

    public void setIsskip(Byte isskip) {
        this.isskip = isskip;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getOnlineversion() {
        return onlineversion;
    }

    public void setOnlineversion(String onlineversion) {
        this.onlineversion = onlineversion == null ? null : onlineversion.trim();
    }

    public String getDevicelistTest() {
        return devicelistTest;
    }

    public void setDevicelistTest(String devicelistTest) {
        this.devicelistTest = devicelistTest == null ? null : devicelistTest.trim();
    }

    public String getUrlTest() {
        return urlTest;
    }

    public void setUrlTest(String urlTest) {
        this.urlTest = urlTest == null ? null : urlTest.trim();
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getForceurl() {
        return forceurl;
    }

    public void setForceurl(String forceurl) {
        this.forceurl = forceurl == null ? null : forceurl.trim();
    }

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }
}