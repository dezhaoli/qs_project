package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.AppCompany;

import java.util.List;

public interface AppCompanyMapper extends IBaseMapper {
    int deleteByPrimaryKey(Byte cid);

    int insert(AppCompany record);

    int insertSelective(AppCompany record);

    AppCompany selectByPrimaryKey(Byte cid);

    int updateByPrimaryKeySelective(AppCompany record);

    int updateByPrimaryKey(AppCompany record);

    List<AppCompany> queryListAll();

}