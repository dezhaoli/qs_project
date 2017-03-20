package com.qs.webside.api.model;

/**
 * 获取详细战绩
 * @author moys
 *
 */
public class HonorDetailRequest extends BaseRequest{
   
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "HonorDetailRequest [ids=" + ids + ", getSesskey()=" + getSesskey() + "]";
	}
	

	
	
	
	
}
