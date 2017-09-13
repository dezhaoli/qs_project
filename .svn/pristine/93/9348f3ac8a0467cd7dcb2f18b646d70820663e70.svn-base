package com.hzw.monitor.mysqlbinlog.web.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.hzw.monitor.mysqlbinlog.web.valid.First;

/**
 * @author gqliu
 * 2016年1月18日
 *
 */

public class MysqlBinlogBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135644170983658672L;
	
	@NotBlank(groups={First.class})
	private String ip;
	@Min(value=1,groups={First.class})
	private int port;
	@NotBlank(groups={First.class})
	private String clientID;
	@NotBlank(groups={First.class})
	private String user;
	@NotBlank(groups={First.class})
	private String password;
	@NotBlank(groups={First.class})
	private String userSchema;
	@NotBlank(groups={First.class})
	private String passwordSchema;
	
	private String filename;
	private String position;
	
	private List<RegexFilter> filters;
	
	private List<RegexSharding> shardings;
	
	private String running;//正在运行
	
	public String getRunning() {
		return running;
	}
	public void setRunning(String running) {
		this.running = running;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<RegexFilter> getFilters() {
		return filters;
	}
	public void setFilters(List<RegexFilter> filters) {
		this.filters = filters;
	}
	public String getUserSchema() {
		return userSchema;
	}
	public void setUserSchema(String userSchema) {
		this.userSchema = userSchema;
	}
	public String getPasswordSchema() {
		return passwordSchema;
	}
	public void setPasswordSchema(String passwordSchema) {
		this.passwordSchema = passwordSchema;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public List<RegexSharding> getShardings() {
		return shardings;
	}
	public void setShardings(List<RegexSharding> shardings) {
		this.shardings = shardings;
	}
	
}
