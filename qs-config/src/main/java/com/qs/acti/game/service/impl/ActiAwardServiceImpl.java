package com.qs.acti.game.service.impl;

import com.qs.acti.game.service.IActiAwardProService;
import com.qs.acti.game.service.IActiAwardService;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.Constants;

import com.qs.acti.game.mapper.ActiAwardMapper;
import com.qs.acti.game.model.ActiAward;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/5 17:28.
 * Description:活动中心奖品表
 */
@Service
public class ActiAwardServiceImpl implements IActiAwardService {

    @Resource
    private ActiAwardMapper actiAwardMapper;

    @Resource
    private IActiAwardProService actiAwardProService;

    @Override
    @Caching(evict = { @CacheEvict(value = CacheConstan.ACTI_AWARD_STOCK_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_AWARD_LIST_CACHE_NAME,allEntries = true)})
    public int deleteByPrimaryKey(Integer id) {
        actiAwardProService.deleteByAwardId(id);
        return actiAwardMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = CacheConstan.ACTI_AWARD_STOCK_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_AWARD_LIST_CACHE_NAME,allEntries = true)})
    public int insert(ActiAward record) {
        return actiAwardMapper.insert(record);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = CacheConstan.ACTI_AWARD_STOCK_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_AWARD_LIST_CACHE_NAME,allEntries = true)})
    public int insertSelective(ActiAward record) {
        return actiAwardMapper.insertSelective(record);
    }

    @Override
    public ActiAward selectByPrimaryKey(Integer id) {
        return actiAwardMapper.selectByPrimaryKey(id);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = CacheConstan.ACTI_AWARD_STOCK_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_AWARD_LIST_CACHE_NAME,allEntries = true)})
    public int updateByPrimaryKeySelective(ActiAward record) {
        return actiAwardMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = CacheConstan.ACTI_AWARD_STOCK_CACHE_NAME, allEntries = true),
            @CacheEvict(value = CacheConstan.ACTI_AWARD_LIST_CACHE_NAME,allEntries = true)})
    public int updateByPrimaryKey(ActiAward record) {
        return actiAwardMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> queryListByPage(Map<String, Object> parameter) {
        return (List<Map<String, Object>>) actiAwardMapper.queryListByPage(parameter);
    }

}
