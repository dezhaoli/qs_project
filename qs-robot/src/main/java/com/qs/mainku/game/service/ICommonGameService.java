package com.qs.mainku.game.service;

import com.qs.mainku.game.model.Commongame;

/**
 * Created by zun.wei on 2017/6/29 11:26.
 * Description:金币表业务层接口
 */
public interface ICommonGameService {

    int deleteByPrimaryKey(Integer mid);

    int insert(Commongame record);

    int insertSelective(Commongame record);

    Commongame selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Commongame record);

    int updateByPrimaryKey(Commongame record);

}
