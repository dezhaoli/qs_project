package com.qs.webside.sys.service.game.service.impl;

import com.qs.webside.game.mapper.ApkRecordMapper;
import com.qs.webside.game.model.ApkRecord;
import com.qs.webside.sys.service.game.service.IApkRecordService;
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
public class ApkRecordServiceImpl implements IApkRecordService{

    @Resource
    private ApkRecordMapper apkRecordMapper;

    @Override
    public List<ApkRecord> queryListByPage(Map<String, Object> parameters) {
        return apkRecordMapper.queryListByPage(parameters);
    }

    @Override
    public int addAppVersion(ApkRecord apkRecord) {
        return apkRecordMapper.insertSelective(apkRecord);
    }

}
