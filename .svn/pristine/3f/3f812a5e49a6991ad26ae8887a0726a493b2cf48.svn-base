package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiAward;

import java.util.Map;

public interface ActiAwardMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAward record);

    int insertSelective(ActiAward record);

    ActiAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAward record);

    int updateByPrimaryKey(ActiAward record);

    /**
     * @Author:zun.wei , @Date:2017/6/13 20:12
     * @Description:根据商品id和活动时间控制查询商品
     * @param id
     * @return
     */
    ActiAward selectByIdLimitByActiTime(Integer id);

    /**
     * @Author:zun.wei , @Date:2017/6/27 19:59
     * @Description:根据较少的数量和商品id更新库存
     * @param parameters
     * @return
     */
    int updateStockByReduceAndId(Map<String, Object> parameters);

}