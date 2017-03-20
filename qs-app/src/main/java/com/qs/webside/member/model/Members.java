package com.qs.webside.member.model;

import java.io.Serializable;

public class Members implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3250911473891764434L;

	private Integer mid;

    private String sitemid;

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
}