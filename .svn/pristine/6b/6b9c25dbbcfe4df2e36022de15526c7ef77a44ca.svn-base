package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.MemberWhiteList;

public interface MemberWhiteListMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberWhiteList record);

    int insertSelective(MemberWhiteList record);

    MemberWhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberWhiteList record);

    int updateByPrimaryKey(MemberWhiteList record);

    MemberWhiteList selectByMid(Integer id);

    int updateTakeEffectById(Integer id);

    int updateTakeEffectActById(Integer id);

    /**
     * @Author:zun.wei , @Date:2017/7/7 10:28
     * @Description:生效或失效全部
     * @param type 0表示失效，1表示生效
     * @return
     */
    int updateTakeEffectAll(int type);

}