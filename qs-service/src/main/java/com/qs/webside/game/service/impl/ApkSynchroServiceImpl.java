package com.qs.webside.game.service.impl;

import com.qs.webside.game.mapper.ApkSynchroMapper;
import com.qs.webside.game.model.ApkSynchro;
import com.qs.webside.game.model.ApkSynchroWithBLOBs;
import com.qs.webside.game.service.IApkSynchroService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class ApkSynchroServiceImpl implements IApkSynchroService {

    @Resource
    private ApkSynchroMapper apkSynchroMapper;

    @Override
    public int deleteById(Integer id) {
        return apkSynchroMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int add(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.insert(record);
    }

    @Override
    public int addSelective(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.insertSelective(record);
    }

    @Override
    public ApkSynchroWithBLOBs selectById(Integer id) {
        return apkSynchroMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ApkSynchroWithBLOBs> queryListByPage(Map<String, Object> parameters) {
        return apkSynchroMapper.queryListByPage(parameters);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return apkSynchroMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.insert(record);
    }

    @Override
    public int insertSelective(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.insertSelective(record);
    }

    @Override
    public ApkSynchroWithBLOBs selectByPrimaryKey(Integer id) {
        return apkSynchroMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ApkSynchroWithBLOBs record) {
        return apkSynchroMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(ApkSynchro record) {
        return apkSynchroMapper.updateByPrimaryKey(record);
    }

}
