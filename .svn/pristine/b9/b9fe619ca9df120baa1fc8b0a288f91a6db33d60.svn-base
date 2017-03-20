package com.qs.cfg.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.cfg.sys.model.SystemRoom;

public interface SystemRoomService {

	public List<SystemRoom> queryListByPage(Map<String, Object> parameter);

	public SystemRoom findByName(String name);
	
	public int insert(SystemRoom record);
	
	public SystemRoom findById(Integer id);

	public int update(SystemRoom record);
    
    public int deleteBatchById(List<Integer> ids);
    
    public int deleteById(Integer id);
    /**
     * 创建房间JSon
     * @param record
     * @return
     */
	public int createRoomJson();
	
    /**
     * 创建房间Xml
     * @param record
     * @return
     */
	public int createRoomXml();
 
}