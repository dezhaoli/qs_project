package com.qs.pub.sys.mapper;

import java.util.List;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.Business;

public interface BusinessMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

	Business findById(int id);

	List findByuId(Long id);

	List findByAll();

	Integer ifLeader(Long id);

	Integer selectBusiness(Long id);

	List findBusinessByGroupId(int i);

	List<Business> selectByGroupId(Integer groupId);
}