/*
 * 文件名：CompanyServiceImpl.java	 
 * 时     间：上午10:16:05
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.common.exception.ServiceException;
import com.qs.pub.sys.mapper.CompanyMapper;
import com.qs.pub.sys.mapper.GroupCompanyMapper;
import com.qs.pub.sys.model.Company;
import com.qs.pub.sys.model.GroupCompany;
import com.qs.pub.sys.service.CompanyService;

/** 
 * @ClassName: CompanyServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月15日 上午10:16:05 
 */
@Service
public class CompanyServiceImpl implements CompanyService
{
	@Resource
	private CompanyMapper companyMapper;
	@Resource
	private GroupCompanyMapper groupCompanyMapper;

	@Override
	public List<Company> queryListByPage(Map<String, Object> parameters)
	{
		return companyMapper.queryListByPage(parameters);
	}

	@Override
	public int insert(Company companyEntity)
	{
		return companyMapper.insert(companyEntity);
	}

	@Override
	public Company findById(int id)
	{
		// TODO Auto-generated method stub
		return companyMapper.findById(id);
	}

	@Override
	public int update(Company companyEntity)
	{
		// TODO Auto-generated method stub
		return companyMapper.updateByPrimaryKey(companyEntity);
	}

	@Override
	public Company findByName(String companyName)
	{
		// TODO Auto-generated method stub
		return companyMapper.findByName(companyName);
	}

	@Override
	public boolean addRolePermBatch(int companyId, List<Integer> list)
	{
		boolean flag = false;
		try {
			int permCount = groupCompanyMapper.selectByCompanyId(companyId);
			boolean delFlag = true;
			if (permCount > 0) {
				int delResult = groupCompanyMapper.deleteByCompanyId(companyId);
				if (permCount != delResult) {
					delFlag = false;
				}
			}

			if (delFlag) {
				if (list.size() > 0) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("companyId", companyId);
					parameter.put("resourceIds", list);
					int addResult = groupCompanyMapper.addCompanyBatch(parameter);
					if (addResult == list.size()) {
						flag = true;
					}
				} else {
					flag = true;
				}
			}
			return flag;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<GroupCompany> queryBusinessList(Map<String, Object> parameter)
	{
		return groupCompanyMapper.queryGroupList(parameter);
	}
}
