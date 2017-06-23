package com.qs.pub.sys.mapper;

import java.util.List;
import java.util.Map;

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

	List findByuId(Map map);

	List findByAll();

	Integer ifLeader(Map<String, Object> map);

	List selectBusiness(Map<String, Object> parameters);

	List findBusinessByGroupId(int i);

	List<Business> selectByGroupId(Map map);
}