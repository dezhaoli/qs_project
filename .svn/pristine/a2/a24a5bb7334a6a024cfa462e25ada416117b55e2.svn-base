package com.qs.acti.game.service.impl;

import com.qs.acti.game.mapper.ActiAwardProMapper;
import com.qs.acti.game.model.ActiAwardPro;
import com.qs.acti.game.service.IActiAwardProService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/9/15 15:58.
 * Description:
 */
@Service
public class ActiAwardProServiceImpl implements IActiAwardProService{

    @Resource
    private ActiAwardProMapper actiAwardProMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiAwardProMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiAwardPro record) {
        return actiAwardProMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiAwardPro record) {
        return actiAwardProMapper.insertSelective(record);
    }

    @Override
    public ActiAwardPro selectByPrimaryKey(Integer id) {
        return actiAwardProMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiAwardPro record) {
        return actiAwardProMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiAwardPro record) {
        return actiAwardProMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiAwardPro> queryListByPage(Map<String, Object> parameters) {
        return actiAwardProMapper.queryListByPage(parameters);
    }

    @Override
    public ActiAwardPro selectByAwarId(Integer awarId) {
        return actiAwardProMapper.selectByAwarId(awarId);
    }

    @Override
    public int updateByAwardIdAndType(ActiAwardPro record) {
        return actiAwardProMapper.updateByAwardIdAndType(record);
    }

    @Override
    public int deleteByAwardId(Integer awardId) {
        return actiAwardProMapper.deleteByAwardId(awardId);
    }

}
