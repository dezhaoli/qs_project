package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiRedPacketCfg;

import java.util.List;
import java.util.Map;

public interface ActiRedPacketCfgMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiRedPacketCfg record);

    int insertSelective(ActiRedPacketCfg record);

    ActiRedPacketCfg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiRedPacketCfg record);

    int updateByPrimaryKey(ActiRedPacketCfg record);

    /**
     * @Author:zun.wei , @Date:2017/9/19 14:41
     * @Description:根据活动类型获取红包配置列表
     * @param actiType
     * @return
     */
    List<ActiRedPacketCfg> queryListByActiType(Integer actiType);

    /**
     * @Author:zun.wei , @Date:2017/9/19 15:11
     * @Description:更新红包配置库存
     * @param parameters
     * @return
     */
    int updateStockByReduceAndId(Map<String, Object> parameters);


}