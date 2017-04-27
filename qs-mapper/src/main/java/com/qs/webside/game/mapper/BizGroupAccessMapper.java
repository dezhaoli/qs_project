package com.qs.webside.game.mapper;

import org.apache.ibatis.annotations.Param;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.BizGroupAccess;

public interface BizGroupAccessMapper extends IBaseMapper {
    int insert(BizGroupAccess record);

    int insertSelective(BizGroupAccess record);
    
    /**
     * 根据ID查询该用户的代理商权限
     * @param id
     * @return String一个人结果集以" ," 结束
     * @author:zyy
     * @time:2017年4月27日
     */
    String selectCountAcids(@Param("id")Integer id);
}