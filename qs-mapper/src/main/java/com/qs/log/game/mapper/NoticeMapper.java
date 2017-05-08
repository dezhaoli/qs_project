package com.qs.log.game.mapper;

import java.util.List;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.Notice;

public interface NoticeMapper extends IBaseMapper<Notice,Object> {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);
    
    List<Notice> selectByTitle(String title);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}