package com.qs.pub.sys.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.BusinessGroup;

public interface BusinessGroupMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessGroup record);

    int insertSelective(BusinessGroup record);

    BusinessGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessGroup record);

    int updateByPrimaryKey(BusinessGroup record);

	int selectByGroupId(int id);

	int deleteByGroupId(int id);

	int addBusinessBatch(Map<String, Object> parameter);

	List<BusinessGroup> queryBusinessList(Map<String, Object> parameter);

	List<BusinessGroup> queryLeaderList(Map<String, Object> parameter);

	List<Integer> queryBusinessIdListByGroupId(Integer groupId);
}