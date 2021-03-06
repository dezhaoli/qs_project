package com.qs.pub.game.service.impl;

import com.qs.pub.game.mapper.AppGameMapper;
import com.qs.pub.game.model.AppGame;
import com.qs.pub.game.service.IAppGameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  游戏表
 * Created by zun.wei on 2017/3/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class AppGameServiceImpl implements IAppGameService {

    @Resource
    private AppGameMapper appGameMapper;

    @Override
    public int deleteByPrimaryKey(Byte gid) {
        return appGameMapper.deleteByPrimaryKey(gid);
    }

    @Override
    public int insert(AppGame record) {
        return appGameMapper.insert(record);
    }

    @Override
    public int insertSelective(AppGame record) {
        return appGameMapper.insertSelective(record);
    }

    @Override
    public AppGame selectByPrimaryKey(Integer gid) {
        return appGameMapper.selectByPrimaryKey(gid);
    }

    @Override
    public int updateByPrimaryKeySelective(AppGame record) {
        return appGameMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppGame record) {
        return appGameMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AppGame> queryListAll() {
        return appGameMapper.queryListAll();
    }

    @Override
    public String getGameCode(Integer gameType) {
        AppGame appGame = appGameMapper.selectByPrimaryKey(gameType);
        if (appGame != null) {
            return appGame.getGameCode();
        }
        return "";
    }

    @Override
    public String getGameName(Integer gameType) {
        AppGame appGame = appGameMapper.selectByPrimaryKey(gameType);
        if (appGame != null) {
            return appGame.getGname();
        }
        return "";
    }

}
