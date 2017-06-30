package com.qs.log.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.log.game.mapper.NoticeNewMapper;
import com.qs.log.game.model.NoticeNew;
import com.qs.log.game.service.INoticeNewService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/30 14:14.
 * Description:新公告业务层
 */
@Service
public class NoticeNewServiceImpl implements INoticeNewService {

    @Resource
    private NoticeNewMapper noticeNewMapper;


    @Override
    @CacheEvict(value={CacheConstan.NEW_NOTICE_CACHE_NAME},key = "'selectByPrimaryKey:' + #args[0]")
    public int deleteByPrimaryKey(Integer id) {
        return noticeNewMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value={CacheConstan.NEW_NOTICE_CACHE_NAME},key = "'selectByPrimaryKey:'+#record.id")
    public int insertSelective(NoticeNew record) {
        return noticeNewMapper.insertSelective(record);
    }

    @Override
    @Cacheable(value = {CacheConstan.NEW_NOTICE_CACHE_NAME},key = "#root.methodName + ':' + #args[0]")
    public NoticeNew selectByPrimaryKey(Integer id) {
        return noticeNewMapper.selectByPrimaryKey(id);
    }//TODO 结束时间之后，要清除缓存

    @Override
    @CacheEvict(value={CacheConstan.NEW_NOTICE_CACHE_NAME},key = "'selectByPrimaryKey:'+#record.id")
    public int updateByPrimaryKeyWithBLOBs(NoticeNew record) {
        return noticeNewMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public List<NoticeNew> queryListByPage(Map<String, Object> parameters) {
        return noticeNewMapper.queryListByPage(parameters);
    }

}
