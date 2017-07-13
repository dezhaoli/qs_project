package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.NoticeNew;

import java.util.List;
import java.util.Map;

public interface NoticeNewMapper extends IBaseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(NoticeNew record);

    int insertSelective(NoticeNew record);

    NoticeNew selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeNew record);

    int updateByPrimaryKeyWithBLOBs(NoticeNew record);

    int updateByPrimaryKey(NoticeNew record);

    /**
     * @return
     * @Author:zun.wei , @Date:2017/7/3 10:28
     * @Description:根据发布类型查询启用状态下的一条最新的公告， 1.在线公告；2.定时公告；3.滚动公告。
     */
    List<NoticeNew> queryListByPushType(Map<String, Object> parameters);

}