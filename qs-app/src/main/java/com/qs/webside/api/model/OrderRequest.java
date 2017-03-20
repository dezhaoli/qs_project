package com.qs.webside.api.model;

public class OrderRequest extends BaseRequest {

	private static final long serialVersionUID = -3393672105061841229L;
	private String type;
	private int money;
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
