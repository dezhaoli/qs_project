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
        if (record != null) {
            int type = actiTimeControlMapper.checkActiTypeIsExist(record.getType());
            if (type == 0) {
                return actiTimeControlMapper.insert(record);
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int insertSelective(ActiTimeControl record) {
        if (record != null) {
            int type = actiTimeControlMapper.checkActiTypeIsExist(record.getType());
            if (type == 0) {
                return actiTimeControlMapper.insertSelective(record);
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public ActiTimeControl selectByPrimaryKey(Integer id) {
        return actiTimeControlMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int updateByPrimaryKeySelective(ActiTimeControl record) {
        if (record != null) {
            int type = actiTimeControlMapper.checkActiTypeIsExist(record.getType());
            if (type == 0) {
                return actiTimeControlMapper.updateByPrimaryKeySelective(record);
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    @CacheEvict(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, allEntries = true)
    public int updateByPrimaryKey(ActiTimeControl record) {
        if (record != null) {
            int type = actiTimeControlMapper.checkActiTypeIsExist(record.getType());
            if (type == 0) {
                return actiTimeControlMapper.updateByPrimaryKey(record);
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public List<ActiTimeControl> queryListByPage(Map<String, Object> parameter) {
        return actiTimeControlMapper.queryListByPage(parameter);
    }

    @Override
    @Cacheable(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME}, key = "#root.methodName")
    public ActiTimeControl getActTimeControlLimit01(Integer type) {
        return actiTimeControlMapper.getActTimeControlLimit01(type);
    }

}
