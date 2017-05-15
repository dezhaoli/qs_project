package com.qs.pub.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.sys.model.LoginInfoEntity;


public interface LoginInfoService {
	public int log(LoginInfoEntity loginInfo);
	
	public List<LoginInfoEntity> queryListByPage(Map<String, Object> parameter);
}
