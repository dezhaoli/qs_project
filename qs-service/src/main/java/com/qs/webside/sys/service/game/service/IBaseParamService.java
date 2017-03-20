package com.qs.webside.sys.service.game.service;

import com.qs.webside.game.model.BaseParam;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/1.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IBaseParamService {

    List<BaseParam> queryBaseParamListByPage(Map<String, Object> parameters);

    int updateBaseParam(BaseParam baseParam);
    
    /**
     * 通过编码查询参数
     * @param code
     * @return
     */
    BaseParam findBaseParamByCode(String code);

}
