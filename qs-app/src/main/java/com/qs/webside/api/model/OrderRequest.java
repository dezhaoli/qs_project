package com.qs.webside.api.model;

public class OrderRequest extends BaseRequest {

	private static final long serialVersionUID = -3393672105061841229L;
	private String type;
	private int money;
	private String iosstring;
	private int orderid;
	private String  ver;
	
	
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getIosstring() {
		return iosstring;
	}
	public void setIosstring(String iosstring) {
		this.iosstring = iosstring;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "OrderRequest [type=" + type + ", money=" + money + ", getSesskey()=" + getSesskey() + "]";
	}
	
	
}
