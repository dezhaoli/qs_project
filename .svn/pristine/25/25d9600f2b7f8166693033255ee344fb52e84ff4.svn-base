package com.qs.webside.member.service;

import com.qs.webside.member.model.MemberWhiteList;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/27.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMemberWhiteListService {

    List<MemberWhiteList> queryListByPage(Map<String, Object> parameters);

    int deleteById(Integer id);

    int add(MemberWhiteList record);

    int addSelective(MemberWhiteList record);

    MemberWhiteList selectById(Integer id);

    int updateByIdSelective(MemberWhiteList record);

    int updateById(MemberWhiteList record);

    MemberWhiteList selectByMid(Integer mid);

    int updateTakeEffectById(Integer id);

    int updateTakeEffectByAct(Integer id);
    /**
     * 
     * @标题: getMemberWhiteDevicelist 
     * @描述: 
     *
     * @参数信息
     *    @return
     *
     * @返回类型 String
     * @开发者 moys
     * @可能抛出异常
     */
    void setMemberWhiteDeviceCache();

    /**
     * @Author:zun.wei , @Date:2017/7/7 10:28
     * @Description:生效或失效全部
     * @param type 0表示失效，1表示生效
     * @return
     */
    int updateTakeEffectAll(int type);

}
