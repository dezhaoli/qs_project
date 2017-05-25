package com.qs.cfg.acti.mapper;

import com.qs.cfg.acti.model.ActiTimeControl;
import com.qs.common.base.basemapper.IBaseMapper;

public interface ActiTimeControlMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiTimeControl record);

    int insertSelective(ActiTimeControl record);

    ActiTimeControl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiTimeControl record);

    int updateByPrimaryKey(ActiTimeControl record);

    /**
     * @Author:zun.wei , @Date:2017/5/25 16:43
     * @Description:查询最新的一条活动数据
     * @return
     */
    ActiTimeControl getActTimeControlLimit01();

}