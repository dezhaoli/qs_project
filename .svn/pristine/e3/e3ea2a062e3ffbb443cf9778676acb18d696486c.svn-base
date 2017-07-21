package com.qs.pub.sys.service;



import java.util.List;
import java.util.Map;

import com.qs.pub.sys.model.BaseParam;

/**
 * Created by zun.wei on 2017/3/1.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IBaseParamService {

    int updateBaseParam(BaseParam baseParam);
    
    /**
     * 通过编码查询参数
     * @param code
     * @return
     */
    BaseParam findBaseParamByCode(String code);
    /**
     * 
     * @标题: getBaseParamValueByCode 
     * @描述: 通过编码查询参数值
     *
     * @参数信息
     *    @param code
     *    @return
     *
     * @返回类型 String
     * @开发者 qs
     * @可能抛出异常
     */
    String getBaseParamValueByCode(String code);
    
    List<BaseParam> selectAllList();
    //批量更新
  	int updateBatch(List<BaseParam> list);

	List<BaseParam> queryListByPage(Map<String, Object> parameter);

	int insert(BaseParam baseParam);

	Integer updateStatus(BaseParam baseParam);

	BaseParam findById(Integer id);

}
