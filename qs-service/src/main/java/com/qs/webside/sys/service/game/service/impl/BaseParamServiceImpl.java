package com.qs.webside.sys.service.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.game.mapper.BaseParamMapper;
import com.qs.webside.game.model.BaseParam;
import com.qs.webside.sys.service.game.service.IBaseParamService;
import org.springframework.cache.annotation.CacheEvict;
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
    public List<BaseParam> queryBaseParamListByPage(Map<String, Object> parameters) {
        return baseParamMapper.queryInExampleVersion(parameters);
    }

    @Override
    @CacheEvict(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},allEntries=true)
    public int updateBaseParam(BaseParam baseParam) {
     
        return baseParamMapper.updateByPrimaryKeySelective(baseParam);
    }

	@Override
	public BaseParam findBaseParamByCode(String code) {
		return baseParamMapper.findBaseParamByCode(code);
	}

}
