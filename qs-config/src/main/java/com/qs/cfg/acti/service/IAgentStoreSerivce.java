package com.qs.cfg.acti.service;

import com.qs.cfg.acti.model.AgentStore;
import com.qs.cfg.acti.model.Store;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/23 16:03.
 * Description:代理商商城
 */
public interface IAgentStoreSerivce {

    List<AgentStore> queryListByPage(Map<String, Object> parameter);

    AgentStore findByName(String name);

    int insert(AgentStore record);

    AgentStore findById(Integer id);

    int update(AgentStore record);

    int deleteBatchById(List<Integer> ids);

    int deleteById(Integer id);

    /**
     * 创建商城Json
     * @return
     */
    int createStoreJson();
    /**
     * 创建商城Xml
     * @return
     */
    int createStoreXml();

}
