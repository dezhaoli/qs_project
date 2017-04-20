package com.qs.cfg.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qs.cfg.sys.mapper.SystemRoomMapper;
import com.qs.cfg.sys.model.SystemRoom;
import com.qs.cfg.sys.service.SystemRoomService;
import com.qs.common.constant.CacheConstan;


@Service("systemRoomService")
public class SystemRoomServiceImpl 
		implements SystemRoomService {

	@Autowired
	private SystemRoomMapper systemRoomMapper;
	

	
	@Override
	public SystemRoom findById(Integer id) {
		return systemRoomMapper.selectByPrimaryKey(id);
	}

	@Override
	@Cacheable(value={CacheConstan.STORE_CACHE_STORE_NAME},key="#root.methodName")
	public String createRoomJson() {
		Map<String, Object> parameter=new HashMap<String, Object>();
		List<SystemRoom> roomList=systemRoomMapper.queryListByPage(parameter);
		
		 JSONObject root=new JSONObject();
		 JSONObject room=new JSONObject();
		 JSONObject item=new JSONObject();
		
		JSONArray itemArr = new JSONArray();
		 
		 Map<String,Object> roomMap=new HashMap<String,Object>();
		 List<SystemRoom>  myRoomList=new ArrayList<SystemRoom>();
		 if(null!=roomList){
		   for(SystemRoom systemRoom:roomList){
			   String jucost=systemRoom.getJu()+","+systemRoom.getCost();
			   JSONArray jsonArr=(JSONArray)roomMap.get(systemRoom.getType());
			   if(null==jsonArr){
				   jsonArr = new JSONArray();
				   jsonArr.add(jucost);
				    myRoomList.add(systemRoom);
			   }else{
				   jsonArr.add(jucost);
			   }
			  
			   roomMap.put(systemRoom.getType(),jsonArr);
		  }
		 }
		 
		 
		 if(null!=roomList){
		 for(SystemRoom systemRoom:myRoomList){
			 //JSONObject chilJson_type=new JSONObject();
			 JSONObject chilJson_item=new JSONObject();
			 JSONObject chilJson_item_att=new JSONObject();
			 chilJson_item_att.put("type", systemRoom.getType());
			//玩法类型
			 chilJson_item_att.put("sctype", systemRoom.getType());
			 JSONArray jucostArr=(JSONArray)roomMap.get(systemRoom.getType());
			 chilJson_item_att.put("jucost",jucostArr);
			 chilJson_item_att.put("name",systemRoom.getName());
			 chilJson_item_att.put("serverid",systemRoom.getServerid());
			 chilJson_item_att.put("bettime",systemRoom.getBettime());
			 chilJson_item_att.put("status", systemRoom.getStatus());
			 
		/*	 chilJson_item_att.put("begin", systemRoom.getBegin());
			 chilJson_item_att.put("end", systemRoom.getEnd());
			 chilJson_item_att.put("ju", systemRoom.getJu());
			 chilJson_item_att.put("sb", systemRoom.getSb());
			 chilJson_item_att.put("min", systemRoom.getMin());
			 chilJson_item_att.put("max", systemRoom.getMax());
			 chilJson_item_att.put("cost", systemRoom.getCost());
			 chilJson_item_att.put("seat","");
			 chilJson_item_att.put("name",systemRoom.getName());
			 chilJson_item_att.put("serverid",systemRoom.getServerid());
			 chilJson_item_att.put("bettime",systemRoom.getBettime());
			 chilJson_item_att.put("rid",systemRoom.getId());*/
			 chilJson_item.put("@attributes",chilJson_item_att);
	
			 itemArr.add(chilJson_item);
		 }
		}
		 
		 item.put("item", itemArr);
		 room.put("room", item);
		 root.put("content", room);
		 //创建文件room.data
		 //FileUtils.createFile(cfgDir+"room.data",root.toJSONString());
		 System.out.println(root.toJSONString());
		return root.toJSONString();
	}

	

	

}
