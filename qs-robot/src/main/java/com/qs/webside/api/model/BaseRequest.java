package com.qs.webside.api.model;

import java.io.Serializable;

public class BaseRequest implements Serializable{

	private static final long serialVersionUID = -1687932235358838733L;
	
	private String sesskey;

	public String getSesskey() {
		return sesskey;
	}

	public void setSesskey(String sesskey) {
		this.sesskey = sesskey;
	}

	@Override
	public String toString() {
		return "BaseRequest [sesskey=" + sesskey + "]";
	}
	
	

}
