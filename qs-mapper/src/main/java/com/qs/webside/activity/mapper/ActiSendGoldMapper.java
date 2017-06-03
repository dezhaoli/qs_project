package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiSendGold;

import java.util.Map;

public interface ActiSendGoldMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiSendGold record);

    int insertSelective(ActiSendGold record);

    ActiSendGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiSendGold record);

    int updateByPrimaryKey(ActiSendGold record);

    /**
     * @Author:zun.wei , @Date:2017/6/2 15:00
     * @Description:测试阶段用于删除全部数据
     * @return
     */
    int deleteAll();
}