package com.qs.webside.sys.service.game.service;

import com.qs.webside.game.model.ApkSynchroWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IApkSynchroService {

    int deleteById(Integer id);

    int add(ApkSynchroWithBLOBs record);

    int addSelective(ApkSynchroWithBLOBs record);

    ApkSynchroWithBLOBs selectById(Integer id);

    int updateByIdSelective(ApkSynchroWithBLOBs record);

    int updateById(ApkSynchroWithBLOBs record);

    List<ApkSynchroWithBLOBs> queryListByPage(Map<String, Object> parameters);

}
