package com.qs.pub.sys.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.PayWhiteList;

public interface PayWhiteListMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayWhiteList record);

    int insertSelective(PayWhiteList record);

    PayWhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayWhiteList record);

    int updateByPrimaryKey(PayWhiteList record);
    /**
     * 根据不同参数查PayWhiteList对象信息
     * @param param
     * @return  list<PayWhiteList>
     * @author:zyy
     * @time:2017年4月14日
     */
    List<PayWhiteList> selectPayWhiteListAll(Map<String,Object> param);
}