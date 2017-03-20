package com.qs.webside.sys.service.game.service;

import com.qs.webside.game.model.ApkRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IApkRecordService {

    List<ApkRecord> queryListByPage(Map<String, Object> parameters);

    int addAppVersion(ApkRecord apkRecord);
}
