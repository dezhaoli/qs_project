package com.qs.log.game.model;

import java.io.Serializable;
import java.util.Date;

public class Mails implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3293289964294262620L;

	private Integer id;

    private String title;

    private Byte target;

    private Date mktime;

    private Date expired;

    private Byte important;

    private String fujian;

    private String gametype;

    private String mids;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Byte getTarget() {
        return target;
    }

    public void setTarget(Byte target) {
        this.target = target;
    }

    public Date getMktime() {
        return mktime;
    }

    public void setMktime(Date mktime) {
        this.mktime = mktime;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Byte getImportant() {
        return important;
    }

    public void setImportant(Byte important) {
        this.important = important;
    }

    public String getFujian() {
        return fujian;
    }

    public void setFujian(String fujian) {
        this.fujian = fujian == null ? null : fujian.trim();
    }

    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype == null ? null : gametype.trim();
    }

    public String getMids() {
        return mids;
    }

    public void setMids(String mids) {
        this.mids = mids == null ? null : mids.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}