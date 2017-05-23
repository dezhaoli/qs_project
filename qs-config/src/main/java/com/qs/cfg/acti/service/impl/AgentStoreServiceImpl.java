package com.qs.cfg.acti.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qs.cfg.acti.mapper.AgentStoreMapper;
import com.qs.cfg.acti.model.AgentStore;
import com.qs.cfg.acti.model.Store;
import com.qs.cfg.acti.service.IAgentStoreSerivce;
import com.qs.common.constant.CacheConstan;
import com.qs.common.util.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/23 16:05.
 * Description:代理商商城配置
 */
@Service
public class AgentStoreServiceImpl implements IAgentStoreSerivce {

    @Value("${cfg.dir}")
    private String cfgDir;

    @Resource
    private AgentStoreMapper agentStoreMapper;

    @Override
    public List<AgentStore> queryListByPage(Map<String, Object> parameter) {
        return agentStoreMapper.queryListByPage(parameter);
    }

    @Override
    public AgentStore findByName(String name) {
        return null;
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_AGENTSTORE_NAME}, allEntries = true)
    public int insert(AgentStore record) {
        return agentStoreMapper.insert(record);
    }

    @Override
    public AgentStore findById(Integer id) {
        return agentStoreMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_AGENTSTORE_NAME}, allEntries = true)
    public int update(AgentStore record) {
        return agentStoreMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_AGENTSTORE_NAME}, allEntries = true)
    public int deleteBatchById(List<Integer> ids) {
        return 0;
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_AGENTSTORE_NAME}, allEntries = true)
    public int deleteById(Integer id) {
        return agentStoreMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int createStoreJson() {
        Map<String, Object> parameter = new HashMap<String, Object>();
        List<Store> storeList = agentStoreMapper.queryListByPage(parameter);

        JSONObject root = new JSONObject();
        JSONObject contentJson = new JSONObject();
        JSONObject itemJson = new JSONObject();
        JSONArray storeJsonArr = new JSONArray();
        if (null != storeList) {
            for (Store store : storeList) {
                JSONObject chilJson = new JSONObject();
                JSONObject chilJson_type = new JSONObject();
                JSONObject chilJson_item = new JSONObject();
                JSONObject chilJson_item_att = new JSONObject();
                chilJson_item_att.put("money", store.getMoney());
                chilJson_item_att.put("gold", store.getGold());
                chilJson_item_att.put("song", store.getSong());
                chilJson_item_att.put("img", store.getImg());
                chilJson_item_att.put("starttime", store.getStarttimeStr());
                chilJson_item_att.put("endtime", store.getEndtimeStr());
                chilJson_item_att.put("productId", store.getProductId());
                chilJson_item.put("@attributes", chilJson_item_att);
                // chilJson.put("item",chilJson_item);
                //chilJson_type.put("type",systemRoom.getType());
                //chilJson.put("@attributes",chilJson_type);
                storeJsonArr.add(chilJson_item);
            }
        }
        itemJson.put("item", storeJsonArr);
        contentJson.put("store", itemJson);
        root.put("content", contentJson);
        //创建文件room.data
        FileUtils.createFile(cfgDir + "common.dataAgent", root.toJSONString());
        System.out.println(root.toJSONString());
        return 1;
    }

    @Override
    public int createStoreXml() {
        Map<String, Object> parameter = new HashMap<String, Object>();
        List<Store> storeList = agentStoreMapper.queryListByPage(parameter);
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
                itemEle.addAttribute("song", String.valueOf(store.getSong()));
                itemEle.addAttribute("img", store.getImg());
                itemEle.addAttribute("starttime", String.valueOf(store.getStarttimeStr()));
                itemEle.addAttribute("endtime", String.valueOf(store.getEndtimeStr()));
                itemEle.addAttribute("productId", String.valueOf(store.getProductId()));

            }
        }

        // 用于格式化xml内容和设置头部标签
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置xml文档的编码为utf-8
        format.setEncoding("utf-8");
        Writer out;
        try {
            // 创建一个输出流对象
            out = new FileWriter(cfgDir + "commonAgent.xml");
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
