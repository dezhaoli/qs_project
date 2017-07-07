package com.qs.webside.activity.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.activity.mapper.ActiCenterMapper;
import com.qs.webside.activity.model.ActiCenter;
import com.qs.webside.activity.service.IActiCenterService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    public int deleteByPrimaryKey(Integer id) {
        return actiCenterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiCenter record) {
        return actiCenterMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiCenter record) {
        return actiCenterMapper.insertSelective(record);
    }

    @Override
    public ActiCenter selectByPrimaryKey(Integer id) {
        return actiCenterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiCenter record) {
        return actiCenterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiCenter record) {
        return actiCenterMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiCenter> queryListByPage(Map<String, Object> parameter) {
        return actiCenterMapper.queryListByPage(parameter);
    }

    @Override
    @Cacheable(value = {CacheConstan.ACTIVITY_CENTER_CACHE_NAME}, key = "#root.methodName")
    public List<Map<String,Object>> queryListActivityByStatus() {
        return actiCenterMapper.queryListActivityByStatus();
    }

    @Override
    @Cacheable(value = {CacheConstan.ACTIVITY_CENTER_TYPE_CACHE_NAME},key = "#root.methodName + ':'+#root.args[0]")
    public Map<String, Object> queryListActivityByStatusAndType(Integer type) {
        return actiCenterMapper.queryListActivityByStatusAndType(type);
    }

}
