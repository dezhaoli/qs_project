package com.qs.pub.pay.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.pay.model.BaseParam;

import java.util.List;
import java.util.Map;

public interface BaseParamMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseParam record);

    int insertSelective(BaseParam record);

    BaseParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseParam record);

    int updateByPrimaryKey(BaseParam record);

    List<BaseParam> selectAllList();
    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateBatch(List<BaseParam> list);
    /**
     * 通过编码查询参数
     * @param code
     * @return
     */
    BaseParam findBaseParamByCode(String code);
    /**
     * 更新基本参数
     * @param param
     * @return
     */
    int update(BaseParam param);

	List<BaseParam> selectAllList(Map<String, Object> parameter);

	Integer updateStatus(BaseParam baseParam);

    
}