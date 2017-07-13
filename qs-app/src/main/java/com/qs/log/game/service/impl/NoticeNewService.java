package com.qs.log.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.log.game.mapper.NoticeNewMapper;
import com.qs.log.game.model.NoticeNew;
import com.qs.log.game.service.INoticeNewService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/7/13 13:39.
 * Description:新游戏公告业务层
 */
@Service
public class NoticeNewService implements INoticeNewService {


    @Resource
    private NoticeNewMapper noticeNewMapper;


    @Override
    @Cacheable(value = CacheConstan.NEW_NOTICE_PUSH_TYPE_CACHE_NAME,key = "#root.methodName+'PushType:'+#parameters.get('pushType')")
    public List<NoticeNew> queryListByPushType(Map<String, Object> parameters) {
        return noticeNewMapper.queryListByPushType(parameters);
    }

}
