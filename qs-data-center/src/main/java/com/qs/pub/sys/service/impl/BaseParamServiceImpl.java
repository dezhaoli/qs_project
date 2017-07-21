package com.qs.pub.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qs.common.constant.CacheConstan;
import com.qs.pub.sys.mapper.BaseParamMapper;
import com.qs.pub.sys.model.BaseParam;
import com.qs.pub.sys.service.IBaseParamService;




@Service
public class BaseParamServiceImpl implements IBaseParamService {

    @Resource
    private BaseParamMapper baseParamMapper;

    @Override
    @CacheEvict(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},allEntries=true)
    public int updateBaseParam(BaseParam baseParam) {
     
        return baseParamMapper.updateByPrimaryKeySelective(baseParam);
    }

	@Override
	@Cacheable(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public BaseParam findBaseParamByCode(String code) {
		return baseParamMapper.findBaseParamByCode(code);
	}
	
	
	@Override
	@Cacheable(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public String getBaseParamValueByCode(String code) {
		BaseParam  param=baseParamMapper.findBaseParamByCode(code);
		String paramValue="";
		if(null!=param){
			paramValue=param.getValue();
		}
		return paramValue;
	}
	
	
	@Override
	public List<BaseParam> selectAllList() {
		return baseParamMapper.selectAllList();
	}
	
	@Override
	public List<BaseParam> queryListByPage(Map<String, Object> parameter) {
		return baseParamMapper.queryListAll(parameter);
	}
	@Override
	@CacheEvict(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},allEntries=true)
	public int updateBatch(List<BaseParam> list) {
		for (BaseParam baseParam : list) {
			baseParamMapper.update(baseParam);
		}
		return 1;
	}

	@Override
	@CacheEvict(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},allEntries=true)
	public int insert(BaseParam baseParam) {
		
		return baseParamMapper.insert(baseParam);
	}

	@Override
	@CacheEvict(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},allEntries=true)
	public Integer updateStatus(BaseParam baseParam) {
		return baseParamMapper.updateStatus(baseParam);
	}

	@Override
	public BaseParam findById(Integer id) {
		return baseParamMapper.selectByPrimaryKey(id);
	}


}
