package com.qs.cfg.acti.service.impl;

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
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qs.cfg.acti.mapper.StoreMapper;
import com.qs.cfg.acti.model.Store;
import com.qs.cfg.acti.service.StoreService;
import com.qs.cfg.sys.model.SystemRoom;
import com.qs.common.constant.CacheConstan;
import com.qs.common.util.FileUtils;


@Service("storeService")
public class StoreServiceImpl 
		implements StoreService {

	@Autowired
	private StoreMapper storeMapper;
	
	@Value("${cfg.dir}")
	private String cfgDir;

	@Override
	public Store findByName(String name) {
		return null;
	}

	@Override
	public Store findById(Integer id) {
		return storeMapper.selectByPrimaryKey(id);
	}

	@Override
	 @CacheEvict(value={CacheConstan.STORE_CACHE_STORE_NAME},allEntries=true)
	public int update(Store record) {
		return storeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	 @CacheEvict(value={CacheConstan.STORE_CACHE_STORE_NAME},allEntries=true)
	public int deleteBatchById(List<Integer> ids) {
		return 0;
	}

	@Override
	 @CacheEvict(value={CacheConstan.STORE_CACHE_STORE_NAME},allEntries=true)
	public int deleteById(Integer id) {
		return storeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Store> queryListByPage(Map<String, Object> parameter) {
		return storeMapper.queryListByPage(parameter);
	}

	@Override
    @CacheEvict(value={CacheConstan.STORE_CACHE_STORE_NAME},allEntries=true)
	public int insert(Store record) {
		return storeMapper.insert(record);
	}

	@Override
	public int createStoreJson() {
		Map<String, Object> parameter=new HashMap<String, Object>();
		List<Store> storeList=storeMapper.queryListByPage(parameter);
		
		 JSONObject root=new JSONObject();
		 JSONObject contentJson=new JSONObject();
		 JSONObject itemJson=new JSONObject();
		 JSONArray storeJsonArr = new JSONArray();
		 if(null!=storeList){
		 for(Store store:storeList){
			 JSONObject chilJson=new JSONObject();
			 JSONObject chilJson_type=new JSONObject();
			 JSONObject chilJson_item=new JSONObject();
			 JSONObject chilJson_item_att=new JSONObject();
			 chilJson_item_att.put("money",store.getMoney());
			 chilJson_item_att.put("gold",store.getGold());
			 chilJson_item_att.put("song",store.getSong());
			 chilJson_item_att.put("img",store.getImg());
			 chilJson_item_att.put("starttime",store.getStarttimeStr());
			 chilJson_item_att.put("endtime",store.getEndtimeStr());	
			 chilJson_item_att.put("productId",store.getProductId());		 
			 chilJson_item.put("@attributes",chilJson_item_att);
			// chilJson.put("item",chilJson_item);  
			 //chilJson_type.put("type",systemRoom.getType());
			 //chilJson.put("@attributes",chilJson_type);
			 storeJsonArr.add(chilJson_item);
		 }
		}
		 itemJson.put("item",storeJsonArr);
		 contentJson.put("store",itemJson);
		 root.put("content",contentJson);
		 //创建文件room.data
		 FileUtils.createFile(cfgDir+"common.data",root.toJSONString());
		 System.out.println(root.toJSONString());
		return 1;
	}

	@Override
	public int createStoreXml() {
		Map<String, Object> parameter=new HashMap<String, Object>();
		List<Store> storeList=storeMapper.queryListByPage(parameter);
       //创建一个xml文档                                                                                  
       Document doc = DocumentHelper.createDocument();                                                    
		// 向xml文件中添加注释
		// doc.addComment("这里是注释");
		// 创建一个名为students的节点，因为是第一个创建，所以是根节点,再通过doc创建一个则会报错。
		Element root = doc.addElement("content");
		//root.addAttribute("date","");
		//Element memcachecfgEle = root.addElement("memcachecfg");
		//Element matchEle = root.addElement("match");
		
		Element storeCfgEle = root.addElement("store");
		if (null != storeList) {
			for (Store store : storeList) {
				Element itemEle = storeCfgEle.addElement("item");
				itemEle.addAttribute("money", String.valueOf(store.getMoney()));
				itemEle.addAttribute("gold", String.valueOf(store.getGold()));
				itemEle.addAttribute("song",String.valueOf(store.getSong()));
				itemEle.addAttribute("img",store.getImg());
				itemEle.addAttribute("starttime",String.valueOf(store.getStarttimeStr()));
				itemEle.addAttribute("endtime",String.valueOf(store.getEndtimeStr()));
				itemEle.addAttribute("productId",String.valueOf(store.getProductId()));
				
			}
		}

		// 用于格式化xml内容和设置头部标签
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置xml文档的编码为utf-8
		format.setEncoding("utf-8");
		Writer out;
		try {
			// 创建一个输出流对象
			out = new FileWriter(cfgDir+"common.xml");
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
