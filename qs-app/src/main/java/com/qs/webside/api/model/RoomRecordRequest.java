package com.qs.webside.api.model;

/**
 * 牌局记录
 * @author moys
 *
 */
public class RoomRecordRequest extends BaseRequest{
   
	private String uuid;
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "RoomRecordRequest [uuid=" + uuid + ", getSesskey()=" + getSesskey() + "]";
	}
	
	
	
	
}
