package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiCenter;

import java.util.List;
import java.util.Map;

public interface ActiCenterMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiCenter record);

    int insertSelective(ActiCenter record);

    ActiCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiCenter record);

    int updateByPrimaryKey(ActiCenter record);

    /**
     * @Author:zun.wei , @Date:2017/6/1 14:19
     * @Description:拉取启用状态下的活动中心对象
     * @return
     */
    List<Map<String,Object>> queryListActivityByStatus();

}