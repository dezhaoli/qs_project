package com.qs.sync.model;

import java.io.Serializable;
import java.util.Date;


public class SyncUserLoginLog  extends SyncObject {
    /** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 6335033716682858203L;

	private Integer id;

    private Integer mid;

    private String appId;
    private String appName;

    private String appVersion;

    private String terminalType;

    private String channelId;
    private String channelName;

    private Date loginTime;

    private Date logoutTime;
    
    private String lgtm;
    private String mtime;
    
    
    
  

	public String getMtime()
	{
		return mtime;
	}

	public void setMtime(String mtime)
	{
		this.mtime = mtime;
	}

	public String getLgtm()
	{
		return lgtm;
	}

	public void setLgtm(String lgtm)
	{
		this.lgtm = lgtm;
	}

	private String ip;
    private String region;
    private String province;
    private String city;

    private String extend1;
    private String extend2;
    private String extend3;
    private String extend4;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public String getExtend4() {
		return extend4;
	}

	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}
	

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
	{
		this.mid = mid;
	}

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	public String getAppVersion()
	{
		return appVersion;
	}

	public void setAppVersion(String appVersion)
	{
		this.appVersion = appVersion;
	}

	public String getTerminalType()
	{
		return terminalType;
	}

	public void setTerminalType(String terminalType)
	{
		this.terminalType = terminalType;
	}

	public String getChannelId()
	{
		return channelId;
	}

	public void setChannelId(String channelId)
	{
		this.channelId = channelId;
	}

	public Date getLoginTime()
	{
		return loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		
		this.loginTime = new Date();
	}

	public Date getLogoutTime()
	{
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime)
	{
		this.logoutTime = new Date();
	}

	public String getAppName()
	{
		return appName;
	}

	public void setAppName(String appName)
	{
		this.appName = appName;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}

    
}