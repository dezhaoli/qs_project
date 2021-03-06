package com.qs.acti.game.mapper;

import com.qs.acti.game.model.ActiRedPacketCfg;
import com.qs.common.base.basemapper.IBaseMapper;

public interface ActiRedPacketCfgMapper extends IBaseMapper {
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