package com.qs.pub.sys.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.common.base.baseservice.impl.AbstractService;
import com.qs.pub.sys.mapper.LoginInfoMapper;
import com.qs.pub.sys.model.LoginInfoEntity;
import com.qs.pub.sys.service.LoginInfoService;

@Service("loginInfoService")
public class LoginInfoServiceImpl extends AbstractService<LoginInfoEntity, Long> implements LoginInfoService{

	@Autowired
	private LoginInfoMapper loginInfoMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loginInfoMapper);
	}

	@Override
	public int log(LoginInfoEntity loginInfo) {
		return loginInfoMapper.insert(loginInfo);
	}
	
}
