package com.qs.webside.api.model;

/**
 * 分享表
 * @author moys
 *
 */
public class GameRecordShareRequest extends BaseRequest{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -6310674477221273120L;
	private Integer id;
	private Integer recordid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}
	@Override
	public String toString() {
		return "GameRecordShareRequest [id=" + id + ", recordid=" + recordid + ", getSesskey()=" + getSesskey() + "]";
	}
   
	
	
	
	
	
	
}
