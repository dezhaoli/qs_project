package com.qs.webside.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.sys.model.LogInfoEntity;

public interface LogInfoService {

	public int log(LogInfoEntity logInfo);
	
	public List<LogInfoEntity> queryListByPage(Map<String, Object> parameter);
}
