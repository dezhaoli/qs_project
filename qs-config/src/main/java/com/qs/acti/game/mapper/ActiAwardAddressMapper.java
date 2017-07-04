package com.qs.acti.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.acti.game.model.ActiAwardAddress;

public interface ActiAwardAddressMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardAddress record);

    int insertSelective(ActiAwardAddress record);

    ActiAwardAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardAddress record);

    int updateByPrimaryKey(ActiAwardAddress record);

    /**
     * @Author:zun.wei , @Date:2017/6/9 16:04
     * @Description:根据mid获取活动中心地址对象
     * @param mid
     * @return
     */
    ActiAwardAddress selectByMid(Integer mid);

}