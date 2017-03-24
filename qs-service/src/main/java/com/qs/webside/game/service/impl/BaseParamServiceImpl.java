package com.qs.webside.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.game.mapper.BaseParamMapper;
import com.qs.webside.game.model.BaseParam;
import com.qs.webside.game.service.IBaseParamService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/1.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
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
	public BaseParam findBaseParamByCode(String code) {
		return baseParamMapper.findBaseParamByCode(code);
	}
	
	
	@Override
	@Cacheable(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},key="#root.methodName")
	public List<BaseParam> selectAllList() {
		return baseParamMapper.selectAllList();
	}

	@Override
	@CacheEvict(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},allEntries=true)
	public int updateBatch(List<BaseParam> list) {
		for (BaseParam baseParam : list) {
			baseParamMapper.update(baseParam);
		}
		return 1;
	}


}
