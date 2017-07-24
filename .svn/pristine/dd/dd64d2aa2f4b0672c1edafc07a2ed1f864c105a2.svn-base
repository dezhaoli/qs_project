package com.qs.acti.game.service.impl;

import com.qs.acti.game.service.IActiCenterService;
import com.qs.common.constant.CacheConstan;
import com.qs.acti.game.mapper.ActiCenterMapper;
import com.qs.acti.game.model.ActiCenter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/31 15:54.
 * Description:活动中心表
 */
@Service
public class ActiCenterServiceImpl implements IActiCenterService {

    @Resource
    private ActiCenterMapper actiCenterMapper;

    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_TYPE_CACHE_NAME,allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)})
    public int deleteByPrimaryKey(Integer id) {
        return actiCenterMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_TYPE_CACHE_NAME,allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)})
    public int insert(ActiCenter record) {
        return actiCenterMapper.insert(record);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_TYPE_CACHE_NAME,allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)})
    public int insertSelective(ActiCenter record) {
        return actiCenterMapper.insertSelective(record);
    }

    @Override
    public ActiCenter selectByPrimaryKey(Integer id) {
        return actiCenterMapper.selectByPrimaryKey(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_TYPE_CACHE_NAME,allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)})
    public int updateByPrimaryKeySelective(ActiCenter record) {
        return actiCenterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTIVITY_CENTER_TYPE_CACHE_NAME,allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)})
    public int updateByPrimaryKey(ActiCenter record) {
        return actiCenterMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiCenter> queryListByPage(Map<String, Object> parameter) {
        return actiCenterMapper.queryListByPage(parameter);
    }

}
