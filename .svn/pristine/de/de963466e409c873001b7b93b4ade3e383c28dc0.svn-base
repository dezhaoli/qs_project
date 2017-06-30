package com.qs.webside.activity.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.activity.mapper.ActiAwardMapper;
import com.qs.webside.activity.model.ActiAward;
import com.qs.webside.activity.service.IActiAwardService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/8 13:28.
 * Description:活动中心奖品表
 */
@Service
public class ActiAwardServiceImpl implements IActiAwardService {

    @Resource
    private ActiAwardMapper actiAwardMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiAwardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiAward record) {
        return actiAwardMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiAward record) {
        return actiAwardMapper.insertSelective(record);
    }

    @Override
    public ActiAward selectByPrimaryKey(Integer id) {
        return actiAwardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiAward record) {
        return actiAwardMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiAward record) {
        return actiAwardMapper.updateByPrimaryKey(record);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/30 9:11
     * @Description:   缓存商品列表，根据不同的mid进行缓存
     * @param parameters
     * @return
     */
    @Override
    @Cacheable(value = {CacheConstan.ACTI_AWARD_LIST_CACHE_NAME},key="#root.methodName+'AwardList:'+#parameters.get('mid')")
    public List<Map<String,Object>> queryListByPage(Map<String, Object> parameters) {
        return actiAwardMapper.queryListByPage(parameters);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/30 9:12
     * @Description:缓存商品库存，根据不同的商品id进行缓存
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = {CacheConstan.ACTI_AWARD_STOCK_CACHE_NAME},key="#root.methodName+'AwardStock:'+#root.args[0]")
    public ActiAward selectByIdLimitByActiTime(Integer id) {
        return actiAwardMapper.selectByIdLimitByActiTime(id);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/30 9:13
     * @Description: 1.清除指定id的商品的库存的缓存；2.清除整个商品列表的缓存
     * @param id 商品id
     */
    @Override
    @Caching(evict = {@CacheEvict(value = CacheConstan.ACTI_AWARD_STOCK_CACHE_NAME, key = "'selectByIdLimitByActiTimeAwardStock:'+#root.args[0]")
            , @CacheEvict(value = CacheConstan.ACTI_AWARD_LIST_CACHE_NAME, allEntries = true)})
    public void deleteAwardStockCache(Integer id) {

    }

    @Override
    public int updateStockByReduceAndId(Map<String, Object> parameters) {
        return actiAwardMapper.updateStockByReduceAndId(parameters);
    }


}
