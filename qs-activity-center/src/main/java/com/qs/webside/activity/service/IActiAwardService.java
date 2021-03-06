package com.qs.webside.activity.service;

import com.qs.webside.activity.model.ActiAward;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/8 13:27.
 * Description:活动中心奖品表
 */
public interface IActiAwardService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiAward record);

    int insertSelective(ActiAward record);

    ActiAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAward record);

    int updateByPrimaryKey(ActiAward record);

    List<Map<String,Object>> queryListByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/6/13 20:12
     * @Description:根据商品id和活动时间控制查询商品
     * @param id
     * @return
     */
    ActiAward selectByIdLimitByActiTime(Integer id);

    /**
     * @Author:zun.wei , @Date:2017/6/28 11:47
     * @Description: 删除对应商品id的缓存
     * @param id 商品id
     */
    void deleteAwardStockCache(Integer id);

    /**
     * @Author:zun.wei , @Date:2017/6/27 19:59
     * @Description:根据较少的数量和商品id更新库存
     * @param parameters
     * @return
     */
    int updateStockByReduceAndId(Map<String, Object> parameters);
    
    
    /**
     * 根据某种活动类型查询活动所有奖品
     * @param type
     * @return
     * @author:zyy
     * @time:2017年9月18日
     */
    List<ActiAward> selectByTypeKey(Integer type);

}
