package com.qs.pub.game.service;

import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.model.MemberBusinessDto;
import com.qs.webside.member.model.MemberFides;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 *
 * 商务管理
 * Created by zun.wei on 2017/3/6.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IBusinessService {

    /**
     * 代理商列表
     * @param parameter
     * @return
     */
    List<Map<String,Object>> queryListMapByPage(Map<String, Object> parameter,String dbName);

    /**
     * 添加代理
     * @param memberBusinessDto 包装代理商信息的Dto对象。
     * @return 影响的条数
     */
    int add(MemberBusinessDto memberBusinessDto);

    /**
     * 根据手机号码查询代理商是否绑定，添加的时候校验，不允许重复
     * @param phone
     * @return
     */
    MemberBusiness loadByPhone(String phone);

    /**
     *  根据邮箱查询代理商是否绑定，添加的时候校验，不允许重复
     * @param email
     * @return
     */
    MemberBusiness loadByEmail(String email);

    /**
     * 根据id查找代理商对象
     * @param id
     * @return
     */
    MemberBusiness selectById(Integer id);

    /**
     * 更新手机号码
     * @param id
     * @param phone
     * @return
     */
    int udpatePhoneById(Integer id,String phone,Integer company,String email,String name);

    /**
     * 重置密码
     * @param agentId
     * @param password
     * @return
     */
    int updatePassWordById(Integer agentId, String password,String confirmPwd);

    /**
     *  检查游戏是否存在
     * @param gameId 游戏id
     * @return
     */
    boolean loadGameExistByGameId(Integer gameId);

    /**
     * 商务后台，执行代理商授权
     * @param userId mId
     * @param gameId gameId
     * @return
     */
    Map<String,Object> saveAuthorization(Integer userId, Integer gameId,MemberBusiness memberBusiness,
                                         Integer gameType,String goldhost,int goldport);

    /**
     * 根据mid查询用户是否存在
     * @param mid
     * @return
     */
    boolean loadCheckMemberByMid(Integer mid);

    /**
     * 根据mid查找人员信息
     * @param mid
     * @return
     */
    MemberFides loadMemberByMid(Integer mid);

    /**
     * 根据游戏id和用户mid查询用户信息。
     * @param userId
     * @param gameId
     * @return
     */
    Map<String,Object> queryUserInfo(Integer userId, Integer gameId, MemberBusiness memberBusiness);

    /**
     * 根据代理商所属id查询以及代理名下的所有代理商条数。
     * @param belongid
     * @return Integer total = map.get("total");
     */
    Map<String, Object> queryFirstAgentCountByBelongId(Integer belongid);

    /**
     * 获取商务专员绑定的一级代理商充值列表
     * @param belongid
     * @return
     */
    List<Map<String, Object>> queryFirstAgentByBelongIdPage(Map<String,Object> belongid);

    /**
     * 根据mid获取代理商详情
     * @param mid
     * @return
     */
    Map<String, Object> getAgentDetailInfoByMid(Integer mid,String dbName);


    /**
     * 查询子级代理商
     * @param buzIdFriId 商务id，和一级代理id
     * @return
     */
    List<Map<String, Object>> queryChildrenAgent(Map<String, Object> buzIdFriId);

    /**
     *  获取商务id下面的一级代理商所有子代理商总条数
     * @param firstMidBelongId
     * @return
     */
    Integer getChildrenAgentsCount(Map<String, Object> firstMidBelongId);

    /**
     * 根据手机或者邮箱获取代理商用户
     * @param phoneOrMail 手机或者邮箱
     * @return
     */
    MemberBusiness findByPhoneOrEmail(String phoneOrMail);

    /**
     *  获取6位邀请码
     * @return 邀请码
     */
    String getInviteCode();

    /**
     *  根据用户id获取用户、（检查是否为代理商）、和所属商务的信息
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getUserAgentAndBuziInfoByUserMid(Map<String, Object> parameters);
    
    /**
     * 金牌代理商获取
     * @return
     * @author:zyy
     * @time:2017年4月27日
     */
    List<Map<String,Object>> getGoldAgentList(Map<String,Object> param);

    /**
     * @Author:zun.wei , @Date:2017/4/28 9:55
     * @Description:根据商务专员ID获取本公司其它商务
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getCompanyBiz(Map<String, Object> parameters);

}
