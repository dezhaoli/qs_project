package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.MemberPayMent;

import java.util.List;
import java.util.Map;

public interface MemberPayMentMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(MemberPayMent record);

    int insertSelective(MemberPayMent record);

    MemberPayMent selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(MemberPayMent record);

    int updateByPrimaryKey(MemberPayMent record);

    /**
     * 根据mid和时间段查询
     * @param parameters
     * @return
     */
    List<MemberPayMent> queryListByMidDate(Map<String, Object> parameters);
    
    /**
     * 查询个人充值
     * @param parameters（date）
     * @return
     * @author:zyy
     * @time:2017年3月31日
     */
    List<Map<String, Object>>  queryListByMidInfo(Map<String, Object> parameters);

    /**
     * //@Author:zun.wei, @Date:2017/4/17 18:53
     * 根据日期查询充值记录
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryChangeByDate(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/5/3 16:53
     * @Description:游戏后台充值查询，查询当天总充值金额
     * @return
     */
    Float queryChangeNowDay();

    /**
     * @Author:zun.wei , @Date:2017/5/5 18:11
     * @Description:根据时间获取充值总额.
     * @param parameters
     * @return
     */
    Float queryChangeCountByDate(Map<String, Object> parameters);

    /**
     * //@Author:zun.wei, @Date:2017/4/18 11:07
     * 订单查询
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryOrderCountByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/5/3 17:31
     * @Description:游戏后台，订单统计查询当天总充值金额
     * @return
     */
    Float queryOrderNowDayPayCount();

    /**
     * //@Author:zun.wei, @Date:2017/4/18 14:57
     * 订单详情
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryOrderDetailByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/5/5 17:49
     * @Description:根据时间获取总订单总额
     * @param parameters
     * @return
     */
    Float queryOrderPayCountByDate(Map<String, Object> parameters);
    
    /**
     * 根据mid 获取当前用户总充值分等级 
     * 用于代理商俱乐部拉去用户成员控制
     * 代理商自身充值达到1000元，俱乐部等级1级
	 * 代理商自身充值达到2000元，俱乐部等级2级
	 * 代理商自身充值达到5000元，俱乐部等级3级
	 * 代理商自身充值达到10000元，俱乐部等级4级
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年8月28日
     */
    Integer getByMidGrade(Integer mid);

}