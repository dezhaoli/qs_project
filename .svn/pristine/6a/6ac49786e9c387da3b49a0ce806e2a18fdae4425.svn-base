package com.qs.cfg.acti.service;

import java.util.List;
import java.util.Map;

import com.qs.cfg.acti.model.Store;

public interface StoreService {

	public List<Store> queryListByPage(Map<String, Object> parameter);

	public Store findByName(String name);
	
	public int insert(Store record);
	
	public Store findById(Integer id);

	public int update(Store record);
    
    public int deleteBatchById(List<Integer> ids);
    
    public int deleteById(Integer id);
    /**
     * 创建商城Json
     * @return
     */
	public int createStoreJson();
	/**
	 * 创建商城Xml
	 * @return
	 */
	public int createStoreXml();

 
}