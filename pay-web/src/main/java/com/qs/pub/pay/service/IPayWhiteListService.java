package com.qs.pub.pay.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.pay.model.PayWhiteList;

public interface IPayWhiteListService {
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