package com.qs.pub.game.service.impl;

import com.qs.pub.game.mapper.PlayerPayDayMapper;
import com.qs.pub.game.model.PlayerPayDay;
import com.qs.pub.game.service.IPlayerPayDayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/4/27 14:15.
 * Description:分公司分游戏充值日表
 */
@Service
public class PlayerPayDayServiceImpl implements IPlayerPayDayService {

    @Resource
    private PlayerPayDayMapper playerPayDayMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return playerPayDayMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PlayerPayDay record) {
        return playerPayDayMapper.insert(record);
    }

    @Override
    public int insertSelective(PlayerPayDay record) {
        return playerPayDayMapper.insertSelective(record);
    }

    @Override
    public PlayerPayDay selectByPrimaryKey(Integer id) {
        return playerPayDayMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PlayerPayDay record) {
        return playerPayDayMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PlayerPayDay record) {
        return playerPayDayMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> queryBusiPayByCompany(Map<String, Object> parameters) {
        return playerPayDayMapper.queryBusiPayByCompany(parameters);
    }

    @Override
    public List<Map<String, Object>> queryChangeDataCountByGame(Map<String, Object> parameters) {
        return playerPayDayMapper.queryChangeDataCountByGame(parameters);
    }

}
