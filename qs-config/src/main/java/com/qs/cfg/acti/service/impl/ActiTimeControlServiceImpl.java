package com.qs.cfg.acti.service.impl;

import com.qs.cfg.acti.mapper.ActiTimeControlMapper;
import com.qs.cfg.acti.model.ActiTimeControl;
import com.qs.cfg.acti.service.IActiTimeControlService;
import com.qs.common.constant.CacheConstan;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/25 16:45.
 * Description:活动预告时间控制表
 */
@Service
public class ActiTimeControlServiceImpl implements IActiTimeControlService {

    @Resource
    private ActiTimeControlMapper actiTimeControlMapper;

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int deleteByPrimaryKey(Integer id) {
        return actiTimeControlMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int insert(ActiTimeControl record) {
        ActiTimeControl actiTimeControl = getActTimeControlLimit01();
        if (actiTimeControl == null) return actiTimeControlMapper.insert(record);
        return 0;
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int insertSelective(ActiTimeControl record) {
        ActiTimeControl actiTimeControl = getActTimeControlLimit01();
        if (actiTimeControl == null) return actiTimeControlMapper.insertSelective(record);
        return 0;
    }

    @Override
    public ActiTimeControl selectByPrimaryKey(Integer id) {
        return actiTimeControlMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int updateByPrimaryKeySelective(ActiTimeControl record) {
        return actiTimeControlMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int updateByPrimaryKey(ActiTimeControl record) {
        return actiTimeControlMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiTimeControl> queryListByPage(Map<String, Object> parameter) {
        return actiTimeControlMapper.queryListByPage(parameter);
    }

    @Override
    @Cacheable(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, key = "#root.methodName")
    public ActiTimeControl getActTimeControlLimit01() {
        return actiTimeControlMapper.getActTimeControlLimit01();
    }

}
