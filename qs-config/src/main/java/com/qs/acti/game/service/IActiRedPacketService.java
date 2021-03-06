package com.qs.acti.game.service;

import com.qs.acti.game.model.ActiIntegralCfg;
import com.qs.acti.game.model.ActiRedPacketCfg;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/9/19 13:41.
 * Description:活动中心红包接口
 */
public interface IActiRedPacketService {

    List<ActiRedPacketCfg> queryListByPage(Map<String, Object> parameters);

    int deleteByPrimaryKey(Integer id);

    int insert(ActiRedPacketCfg record);

    int insertSelective(ActiRedPacketCfg record);

    ActiRedPacketCfg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiRedPacketCfg record);

    int updateByPrimaryKey(ActiRedPacketCfg record);

    /**
     * @Author:zun.wei , @Date:2017/9/22 18:17
     * @Description:根据活动类型获取该活动类型下的红包库存
     * @param actiType
     * @return
     */
    Integer queryStoreByActiType(Integer actiType);

}
