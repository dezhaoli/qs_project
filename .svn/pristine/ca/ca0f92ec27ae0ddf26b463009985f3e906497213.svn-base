package com.qznet.commons.upload.util;

public class UploadMessage {
	private int state;
	private String url;
	private String path;
	private String message;
	private long size;
	private String originalFilename;
	private boolean errorType;

	public boolean isErrorType() {
		return errorType;
	}

	public void setErrorType(boolean errorType) {
		this.errorType = errorType;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public UploadMessage() {
	}

	public UploadMessage(String path, String url,int state, String message) {
		super();
		this.path = path;
		this.url = url;
		this.state=state;
		this.message = message;
	}
	public UploadMessage(String path, String url) {
		super();
		this.path = path;
		this.url = url;
		this.state=200;
		this.message = "上传成功";
	}

	public UploadMessage(String path, String url,long size) {
		super();
		this.path = path;
		this.url = url;
		this.size = size;
		this.state=200;
		this.message = "上传成功";
	}

	public UploadMessage(String path, String url,String originalFilename,long size) {
		super();
		this.path = path;
		this.url = url;
		this.size = size;
		this.originalFilename = originalFilename;
		this.state=200;
		this.message = "上传成功";
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "UploadMessage{" +
				"state=" + state +
				", url='" + url + '\'' +
				", path='" + path + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
