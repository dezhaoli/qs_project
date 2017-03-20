package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.Notice;

public interface NoticeMapper extends IBaseMapper<Notice,Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
}