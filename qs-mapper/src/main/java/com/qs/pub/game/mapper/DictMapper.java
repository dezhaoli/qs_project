package com.qs.pub.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.Dict;

public interface DictMapper extends IBaseMapper<Dict, Integer> {
    List<Dict> findList(String code);

    int updateByName(Dict record);

    List<Dict> selectByName();

    int updateStatus(Dict record);

    /**
     * @Author:zun.wei , @Date:2017/6/14 9:30
     * @Description:根据父级代码和子集代码获取数据字典对象
     * @param pCodeAndcCode
     * @return
     */
    Dict findByParentCodeAndChildCodeLimit1(Map<String, Object> pCodeAndcCode);

}