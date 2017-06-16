package com.qs.pub.sys.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.GroupCompany;

public interface GroupCompanyMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupCompany record);

    int insertSelective(GroupCompany record);

    GroupCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupCompany record);

    int updateByPrimaryKey(GroupCompany record);

	int selectByCompanyId(int companyId);

	int deleteByCompanyId(int companyId);

	int addCompanyBatch(Map<String, Object> parameter);

	List<GroupCompany> queryGroupList(Map<String, Object> parameter);
}