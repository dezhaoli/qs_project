package com.qs.log.game.model;

import java.io.Serializable;
import java.util.Date;

public class TaxesDirectlyDay implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TaxesDirectlyDay() {
		super();
	}
	
	public TaxesDirectlyDay(Integer mid, Date date, Short bindpeople, Integer paycount, Integer playtimes) {
		super();
		this.mid = mid;
		this.date = date;
		this.bindpeople = bindpeople;
		this.paycount = paycount;
		this.playtimes = playtimes;
	}

	private Integer mid;

    private Date date;

    private Short bindpeople;

    private Integer paycount;

    private Integer playtimes;

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

    public Short getBindpeople() {
        return bindpeople;
    }

    public void setBindpeople(Short bindpeople) {
        this.bindpeople = bindpeople;
    }

    public Integer getPaycount() {
        return paycount;
    }

    public void setPaycount(Integer paycount) {
        this.paycount = paycount;
    }

    public Integer getPlaytimes() {
        return playtimes;
    }

    public void setPlaytimes(Integer playtimes) {
        this.playtimes = playtimes;
    }
}