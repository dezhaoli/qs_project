package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.BaseParam;

import java.util.List;
import java.util.Map;

public interface BaseParamMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseParam record);

    int insertSelective(BaseParam record);

    BaseParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseParam record);

    int updateByPrimaryKey(BaseParam record);

    List<BaseParam> queryInExampleVersion(Map<String, Object> parameters);
    
    /**
     * 通过编码查询参数
     * @param code
     * @return
     */
    BaseParam findBaseParamByCode(String code);
    
}