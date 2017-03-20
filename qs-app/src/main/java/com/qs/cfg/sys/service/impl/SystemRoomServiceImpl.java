package com.qs.cfg.sys.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qs.cfg.sys.mapper.SystemRoomMapper;
import com.qs.cfg.sys.model.SystemRoom;
import com.qs.cfg.sys.service.SystemRoomService;
import com.qs.common.util.FileUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Service("systemRoomService")
public class SystemRoomServiceImpl 
		implements SystemRoomService {

	@Autowired
	private SystemRoomMapper systemRoomMapper;
	
	private String cfgDir;

	
	@Override
	public SystemRoom findById(Integer id) {
		return systemRoomMapper.selectByPrimaryKey(id);
	}

	@Override
	public int createRoomJson() {
		Map<String, Object> parameter=new HashMap<String, Object>();
		List<SystemRoom> roomList=systemRoomMapper.queryListByPage(parameter);
		
		 JSONObject root=new JSONObject();
		 JSONObject room=new JSONObject();
		
		 JSONArray jsona = new JSONArray();
		 if(null!=roomList){
		 for(SystemRoom systemRoom:roomList){
			 JSONObject chilJson=new JSONObject();
			 JSONObject chilJson_type=new JSONObject();
			 JSONObject chilJson_item=new JSONObject();
			 JSONObject chilJson_item_att=new JSONObject();
			 chilJson_item_att.put("begin", systemRoom.getBegin());
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
			 chilJson_item_att.put("rid",systemRoom.getId());
			 
			 chilJson_item.put("@attributes",chilJson_item_att);
			 chilJson.put("item",chilJson_item);  
			 chilJson_type.put("type",systemRoom.getType());
			 chilJson.put("@attributes",chilJson_type);
			 jsona.add(chilJson);
		 }
		}
		 
		 room.put("room", jsona);
		 root.put("content", room);
		 //创建文件room.data
		 FileUtils.createFile(cfgDir+"room.data",root.toJSONString());
		 System.out.println(root.toJSONString());
		return 1;
	}

	@Override
	public int createRoomXml() {
		Map<String, Object> parameter=new HashMap<String, Object>();
		List<SystemRoom> roomList=systemRoomMapper.queryListByPage(parameter);
       //创建一个xml文档                                                                                  
       Document doc = DocumentHelper.createDocument();                                                    
		// 向xml文件中添加注释
		// doc.addComment("这里是注释");
		// 创建一个名为students的节点，因为是第一个创建，所以是根节点,再通过doc创建一个则会报错。
		Element root = doc.addElement("system");
		root.addAttribute("date","");
		// 在root节点下创建一个名为room的节点
		Element memcachecfgEle = root.addElement("memcachecfg");
		Element matchEle = root.addElement("match");
		
		Element roomCfgEle = root.addElement("systemroomcfg");
		if (null != roomList) {
			for (SystemRoom systemRoom : roomList) {
				Element itemEle = roomCfgEle.addElement("item");
				itemEle.addAttribute("id", String.valueOf(systemRoom.getId()));
				itemEle.addAttribute("ju", String.valueOf(systemRoom.getJu()));
				itemEle.addAttribute("cost",String.valueOf(systemRoom.getCost()));
				itemEle.addAttribute("begin",String.valueOf(systemRoom.getBegin()));
				itemEle.addAttribute("end",String.valueOf(systemRoom.getEnd()));
				itemEle.addAttribute("type",systemRoom.getType());
				itemEle.addAttribute("name",systemRoom.getName());
				itemEle.addAttribute("serverid",String.valueOf(systemRoom.getServerid()));
				itemEle.addAttribute("bettime",String.valueOf(systemRoom.getBettime()));
				/*itemEle.addAttribute("taxation",String.valueOf(systemRoom.getTaxation()));
				itemEle.addAttribute("taxation",String.valueOf(systemRoom.getTaxation()));
				itemEle.addAttribute("taxes_mode",String.valueOf(systemRoom.getTaxesMode()));
				itemEle.addAttribute("sb",String.valueOf(systemRoom.getSb()));
				itemEle.addAttribute("min",String.valueOf(systemRoom.getMin()));
				itemEle.addAttribute("max",String.valueOf(systemRoom.getMax()));*/
				itemEle.addAttribute("status",String.valueOf(systemRoom.getStatus()));
				
			}
		}

		// 用于格式化xml内容和设置头部标签
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置xml文档的编码为utf-8
		format.setEncoding("utf-8");
		Writer out;
		try {
			// 创建一个输出流对象
			out = new FileWriter(cfgDir+"c_config.xml");
			// 创建一个dom4j创建xml的对象
			XMLWriter writer = new XMLWriter(out, format);
			// 调用write方法将doc文档写到指定路径
			writer.write(doc);
			writer.close();
			System.out.print("生成XML文件成功");
		} catch (IOException e) {
			System.out.print("生成XML文件失败");
			e.printStackTrace();
		}

		return 1;
	}

	

}
