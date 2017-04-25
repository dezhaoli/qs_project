package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.MemberBusiness;

import java.util.List;
import java.util.Map;

public interface MemberBusinessMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberBusiness record);

    int insertSelective(MemberBusiness record);

    MemberBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberBusiness record);

    int updateByPrimaryKey(MemberBusiness record);

    List<Map<String,Object>> queryListMapByPage(Map<String, Object> parameter);

    /**
     * 查询对应的手机号码是否绑定到代理商
     * @param phone
     * @return
     */
    MemberBusiness loadByPhone(String phone);

    /**
     * 查询对应的邮箱是否绑定到代理商
     * @param email
     * @return
     */
    MemberBusiness loadByEmail(String email);

    /**
     * 根据手机或者邮箱获取代理商用户
     * @param phoneOrMail 手机或者邮箱
     * @return
     */
    MemberBusiness findByPhoneOrEmail(String phoneOrMail);

    /**
     * 根据mid查询对应的代理商信息，和商务信息。
     * @param mid
     * @return
     */
    Map<String, Object> getBusInfoAndBizInfoByMid(Integer mid);

}