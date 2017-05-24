package com.qs.cfg.acti.service;

/**
 * Created by zun.wei on 2017/5/23 16:03.
 * Description:代理商商城
 */
public interface IAgentStoreSerivce {

    /**
     * 通过商城id查询金币
     * @param money
     * @return
     */
    Integer getGoldByPayMoney(Integer money);

    /**
     * 创建商城Json
     * @return
     */
    String createStoreJson();

}
