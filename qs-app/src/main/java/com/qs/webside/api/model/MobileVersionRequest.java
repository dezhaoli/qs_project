package com.qs.webside.api.model;


/**
 * 移动版本
 * @author moys
 *
 */
public class MobileVersionRequest extends BaseRequest{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8819977435377416488L;
	private int site;
	private int channel;
	private String deviceid;
	private int bigversion;
	private int version;
	public int getSite() {
		return site;
	}
	public void setSite(int site) {
		this.site = site;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public int getBigversion() {
		return bigversion;
	}
	public void setBigversion(int bigversion) {
		this.bigversion = bigversion;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "MobileVersionRequest [site=" + site + ", channel=" + channel + ", deviceid=" + deviceid
				+ ", bigversion=" + bigversion + ", version=" + version + ", getSesskey()=" + getSesskey() + "]";
	}
	
	
	
	
}
