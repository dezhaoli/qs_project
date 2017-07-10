package com.qs.webside.activity.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.activity.mapper.ActiIntegralCfgMapper;
import com.qs.webside.activity.model.ActiIntegralCfg;
import com.qs.webside.activity.service.IActiIntegralCfgService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/7/10 14:05.
 * Description:活动中心积分配置表
 */
@Service
public class ActiIntegralCfgServiceImpl implements IActiIntegralCfgService {

    @Resource
    private ActiIntegralCfgMapper actiIntegralCfgMapper;


    @Override
    @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)
    public int deleteByPrimaryKey(Integer id) {
        return actiIntegralCfgMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)
    public int insert(ActiIntegralCfg record) {
        return actiIntegralCfgMapper.insert(record);
    }

    @Override
    @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)
    public int insertSelective(ActiIntegralCfg record) {
        return actiIntegralCfgMapper.insertSelective(record);
    }

    @Override
    public ActiIntegralCfg selectByPrimaryKey(Integer id) {
        return actiIntegralCfgMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)
    public int updateByPrimaryKeySelective(ActiIntegralCfg record) {
        return actiIntegralCfgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)
    public int updateByPrimaryKey(ActiIntegralCfg record) {
        return actiIntegralCfgMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiIntegralCfg> queryListByPage(Map<String, Object> parameters) {
        return actiIntegralCfgMapper.queryListByPage(parameters);
    }

    @Override
    @Cacheable(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,key = "#root.methodName+':'+root.args[0]")
    public List<Map<String,Object>> queryListByActiTypeLimitByDate(int actiType) {
        return actiIntegralCfgMapper.queryListByActiTypeLimitByDate(actiType);
    }

    @Override
    @CacheEvict(value = CacheConstan.ACTI_INTEGRAL_CFG_CACHE_NAME,allEntries = true)
    public List<Map<String, Object>> queryListByActiTypeLimitByDate2(int actiType) {
        return actiIntegralCfgMapper.queryListByActiTypeLimitByDate(actiType);
    }

}
