package com.qs.webside.game.service;

import com.qs.webside.game.model.BizGroupAccess;

public interface IBizGroupAccessService {
	int insert(BizGroupAccess record);

    int insertSelective(BizGroupAccess record);
    
    /**
     * 根据ID查询该用户的代理商权限
     * @param id
     * @return String一个人结果集以" ," 结束
     * @author:zyy
     * @time:2017年4月27日
     */
    String selectCountAcids(int id);
}
