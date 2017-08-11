package com.qs.mainku.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.mainku.game.model.BaseParam;

public interface BaseParamMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseParam record);

    int insertSelective(BaseParam record);

    BaseParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseParam record);

    int updateByPrimaryKey(BaseParam record);
    
    /**
     * 通过编码查询参数设置
     * @param code
     * @return
     */
    BaseParam getBaseParamByCode(String code);
}