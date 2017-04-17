package com.qs.webside.agent.model;

import java.io.Serializable;

public class AgentRebatescale implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6176025503626209868L;
	//firstLessscale firstMiddlescale firstMorescale secondScale thirdScale 
	private Integer id;

    private Integer mid;

    private Integer firstLessscale;

    private Integer firstMiddlescale;

    private Integer firstMorescale;

    private Integer secondScale;

    private Integer thirdScale;

    private Integer gametype;

    private Integer PMktime;
    
    private String mktime;

    
    public Integer getPMktime() {
		return PMktime;
	}

	public void setPMktime(Integer pMktime) {
		PMktime = pMktime;
	}

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

   

    public Integer getFirstLessscale() {
		return firstLessscale;
	}

	public void setFirstLessscale(Integer firstLessscale) {
		this.firstLessscale = firstLessscale;
	}

	public Integer getFirstMiddlescale() {
		return firstMiddlescale;
	}

	public void setFirstMiddlescale(Integer firstMiddlescale) {
		this.firstMiddlescale = firstMiddlescale;
	}

	public Integer getFirstMorescale() {
		return firstMorescale;
	}

	public void setFirstMorescale(Integer firstMorescale) {
		this.firstMorescale = firstMorescale;
	}

	public Integer getSecondScale() {
		return secondScale;
	}

	public void setSecondScale(Integer secondScale) {
		this.secondScale = secondScale;
	}

	public Integer getThirdScale() {
		return thirdScale;
	}

	public void setThirdScale(Integer thirdScale) {
		this.thirdScale = thirdScale;
	}

	public Integer getGametype() {
		return gametype;
	}

	public void setGametype(Integer gametype) {
		this.gametype = gametype;
	}

	public String getMktime() {
        return mktime;
    }

    public void setMktime(String mktime) {
        this.mktime = mktime;
    }
}