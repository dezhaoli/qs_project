package com.qs.webside.agent.model;

import java.io.Serializable;

public class AdminAgentPublish implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String imgName;

    private Byte showtype;

    private Integer Istime;

    private Integer Ietime;

    private Integer Imktime;

    private String stime;

    private String etime;

    private String mktime;

    private Integer type;

    private String body;

    private String name;
    
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public Byte getShowtype() {
        return showtype;
    }

    public void setShowtype(Byte showtype) {
        this.showtype = showtype;
    }

	public Integer getIstime() {
		return Istime;
	}

	public void setIstime(Integer istime) {
		Istime = istime;
	}

	public Integer getIetime() {
		return Ietime;
	}

	public void setIetime(Integer ietime) {
		Ietime = ietime;
	}

	public Integer getImktime() {
		return Imktime;
	}

	public void setImktime(Integer imktime) {
		Imktime = imktime;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getMktime() {
		return mktime;
	}

	public void setMktime(String mktime) {
		this.mktime = mktime;
	}

   
}