package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiIntegral;

public interface ActiIntegralMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiIntegral record);

    int insertSelective(ActiIntegral record);

    ActiIntegral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiIntegral record);

    int updateByPrimaryKey(ActiIntegral record);

    /**
     * @Author:zun.wei , @Date:2017/6/8 19:38
     * @Description:根据mid查询积分对象
     * @param mid
     * @return
     */
    ActiIntegral selectByMid(Integer mid);

    /**
     * @Author:zun.wei , @Date:2017/7/7 14:21
     * @Description:不存在就插入积分对象，存在就更新。
     * @param record 积分对象
     * @return
     */
    int insertOrUpate(ActiIntegral record);

}