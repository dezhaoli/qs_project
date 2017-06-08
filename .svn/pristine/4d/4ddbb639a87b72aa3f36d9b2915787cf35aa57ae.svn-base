package com.qs.webside.activity.service.impl;

import com.qs.webside.activity.model.ActiAward;
import com.qs.webside.activity.service.IActiAwardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/8 13:28.
 * Description:
 */
@Service
public class ActiAwardServiceImpl implements IActiAwardService {

    @Resource
    private IActiAwardService actiAwardService;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiAwardService.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiAward record) {
        return actiAwardService.insert(record);
    }

    @Override
    public int insertSelective(ActiAward record) {
        return actiAwardService.insertSelective(record);
    }

    @Override
    public ActiAward selectByPrimaryKey(Integer id) {
        return actiAwardService.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiAward record) {
        return actiAwardService.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiAward record) {
        return actiAwardService.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> queryListByPage(Map<String, Object> parameters) {
        return actiAwardService.queryListByPage(parameters);
    }


}
