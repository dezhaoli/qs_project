/*
 * 文件名：CompanyServiceImpl.java	 
 * 时     间：上午10:16:05
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.sys.mapper.CompanyMapper;
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
}
