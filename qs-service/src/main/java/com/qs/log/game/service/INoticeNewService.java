package com.qs.log.game.service;

import com.qs.log.game.model.NoticeNew;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/30 14:13.
 * Description:新公告业务层
 */
public interface INoticeNewService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(NoticeNew record,String goldhost,int goldport,int gameType);

    NoticeNew selectByPrimaryKey(Integer id);

    int updateByPrimaryKeyWithBLOBs(NoticeNew record,String goldhost,int goldport,int gameType);

    List<NoticeNew> queryListByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/7/3 10:28
     * @Description:查询定时公告列表
     * @return
     */
    List<NoticeNew> queryListByPushType();

}
