package com.qs.mainku.game.service.impl;

import com.qs.mainku.game.mapper.CommongameMapper;
import com.qs.mainku.game.model.Commongame;
import com.qs.mainku.game.service.ICommonGameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/6/29 11:27.
 * Description:金币表业务层接口实现类
 */
@Service
public class CommonGameServiceImpl implements ICommonGameService {

    @Resource
    private CommongameMapper commongameMapper;


    @Override
    public int deleteByPrimaryKey(Integer mid) {
        return commongameMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public int insert(Commongame record) {
        return commongameMapper.insert(record);
    }

    @Override
    public int insertSelective(Commongame record) {
        return commongameMapper.insertSelective(record);
    }

    @Override
    public Commongame selectByPrimaryKey(Integer mid) {
        return commongameMapper.selectByPrimaryKey(mid);
    }

    @Override
    public int updateByPrimaryKeySelective(Commongame record) {
        return commongameMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Commongame record) {
        return commongameMapper.updateByPrimaryKey(record);
    }

}
