package com.qs.mainku.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.mainku.game.mapper.BaseParamMapper;
import com.qs.mainku.game.model.BaseParam;
import com.qs.mainku.game.service.IBaseParamService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/7/5 16:16.
 * Description:基本参数控制器
 */
@Service
public class BaseParamServiceImpl implements IBaseParamService{

    @Resource
    private BaseParamMapper baseParamMapper;

    @Override
    @Cacheable(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
    public String getBaseParamValueByCode(String code) {
        BaseParam param=baseParamMapper.getBaseParamByCode(code);
        String paramValue="";
        if(null!=param){
            paramValue=param.getValue();
        }
        return paramValue;
    }

}
