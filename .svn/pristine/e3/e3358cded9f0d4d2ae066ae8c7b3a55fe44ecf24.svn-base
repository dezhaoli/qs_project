package com.qs.webside.member.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.AppConstants.MemcacheKeyPrefix;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.*;
import com.qs.log.game.model.GoldLog;
import com.qs.log.game.model.PlayerRecord;
import com.qs.log.game.service.IBusiGoldLogService;
import com.qs.log.game.service.IGoldLogService;
import com.qs.log.game.service.IPlayerRecordService;
import com.qs.pub.game.model.CommonAgents;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.model.MemberInvite;
import com.qs.pub.game.service.IBusinessService;
import com.qs.pub.game.service.ICommonAgentService;
import com.qs.pub.game.service.ICommonAgentsRelationService;
import com.qs.pub.game.service.IMemberInviteService;
import com.qs.webside.agent.service.IAgentClubGroupService;
import com.qs.webside.agent.service.IAgentClubMemberService;
import com.qs.webside.agent.service.IAgentMidsServcie;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.game.model.BaseParam;
import com.qs.webside.game.model.ClubMember;
import com.qs.webside.game.model.ClubMids;
import com.qs.webside.game.service.IBaseParamService;
import com.qs.webside.game.service.IClubMemberService;
import com.qs.webside.game.service.IClubMidsService;
import com.qs.webside.game.service.IClubService;
import com.qs.webside.member.model.AgentMids;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.Members;
import com.qs.webside.member.service.IMemberFidesService;
import com.qs.webside.member.service.IMembersServcie;

import jodd.util.StringUtil;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 人员信息控制器，所有关于人员信息的，如金币，积分，平台ID等都在这里。
 *
 * Created by zun.wei on 2017/3/9.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/member/")
public class MembersController extends BaseController{
	
	Logger log = Logger.getLogger(MembersController.class);

    @Resource
    private IMemberFidesService memberFidesService;

    @Resource
    private IPlayerRecordService playerRecordService;

    @Resource
    private IGoldLogService goldLogService;

    @Resource
    private IBusinessService businessService;

    @Resource
    private IMemberAgentService memberAgentService;

    @Resource
    private IMemberInviteService memberInviteService;

    @Resource
    private IBusiGoldLogService busiGoldLogService;

    @Autowired
    private IAgentMidsServcie agentMidsServcie;

    @Resource
    private ICommonAgentsRelationService commonAgentsRelationService ;

    @Resource
    private IAgentClubGroupService agentClubGroupService;

    @Resource
    private IAgentClubMemberService agentClubMemberService;

    @Resource
    private IBaseParamService baseParamService;

    @Autowired
    private IMembersServcie membersServcie;

    @Resource
    private ICommonAgentService commonAgentService;
    
    @Autowired
    private IClubService clubService ;
    
    @Autowired
    private IClubMidsService clubMidsService ;
    
    @Autowired
    private IClubMemberService clubMemberService;
    
    
//    @Autowired
//	private MemcachedClient memcachedClient;

    @Value("${game.goldhost}")
    private String goldhost;

    @Value("${game.goldport}")
    private int goldport;

    @Value("${game.gametype}")
    private String gameType;
    
    @Value("${game.gameCode}")
    private String gameCode;

    /**
     * 查看用户资料入口
     * @return
     */
    @RequestMapping(value = "userInfo/editUI.html", method = RequestMethod.GET)
    public String showUserInfo(Model model, String id, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        model.addAttribute("id", id);
        Map<String, Object> midOrsitemid = new HashMap<String, Object>();
        midOrsitemid.put("mid", id);
        Map<String, Object> map = memberFidesService.queryUserInfoByMidAndSiteMid(midOrsitemid);
        Object o = map.get("belongid");
        if (o != null) {
            int belongId = Integer.valueOf(o + "");
            MemberBusiness business = businessService.selectById(belongId);
            if (business != null) map.put("businessName",business.getName());
        }
        if (!StringUtil.isBlank(id)) {
            int agentId = Integer.valueOf(id);
            MemberAgents memberAgents = memberAgentService.selectByMid(agentId);
            map.put("memberAgents", memberAgents);
        }
        AgentMids agentMids = agentMidsServcie.selectByMid(Integer.valueOf(id));//待开房
        map.put("agentMids", agentMids);
        model.addAttribute("memberFides", map);
        return "WEB-INF/view/web/user/member_userInfo_show";
    }

    /**
     * 根据mid或者sitemid获取用户信息
     * @param model
     * @param mid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userInfo/edit.html", method = RequestMethod.POST)
    public Object showUserInfo(Model model,Integer mid,String sitemid) {
        Map<String, Object> midOrsitemid = new HashMap<String, Object>();
        midOrsitemid.put("mid", mid);
        midOrsitemid.put("sitemid", sitemid);
        Map<String, Object> map = memberFidesService.queryUserInfoByMidAndSiteMid(midOrsitemid);
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        return map;
    }

    /**
     * 用户管理入口
     * @param model
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "managementUi.html", method = RequestMethod.GET)
    public String userManagementUi(Model model, String id, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", id);
            return "WEB-INF/view/web/user/member_management_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 用户管理ajax查询
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("management.html")
    @ResponseBody
    public Object userManagement(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        parameters.put("dbName", gameCode);
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<MemberFides> list = memberFidesService.queryListByUserName(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * 用户名查询点击进入查看用户信息入口
     * @param model
     * @param mid
     * @param request
     * @return
     */
    @RequestMapping(value = "showUserInfoUi.html", method = RequestMethod.GET)
    public String showUserInfoUiByMid(Model model, Integer mid,HttpServletRequest request) {
        try {
            Map<String, Object> midOrsitemid = new HashMap<String, Object>();
            midOrsitemid.put("mid", mid);
            Map<String, Object> map = memberFidesService.queryUserInfoByMidAndSiteMid(midOrsitemid);
            Object o = map.get("belongid");
            if (o != null) {
                int belongId = Integer.valueOf(o + "");
                MemberBusiness business = businessService.selectById(belongId);
                if (business != null) map.put("businessName",business.getName());
            }
            MemberAgents memberAgents = memberAgentService.selectByMid(mid);
            map.put("memberAgents", memberAgents);
            AgentMids agentMids = agentMidsServcie.selectByMid(mid);//待开房
            map.put("agentMids", agentMids);
            model.addAttribute("memberFides", map);
            return "WEB-INF/view/web/user/member_userNameSearch_userInfo";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 代理商查看用户信息入口
     * @param model
     * @param mid
     * @param request
     * @return
     */
    @RequestMapping(value = "agent/showUserInfoUi.html", method = RequestMethod.GET)
    public String agentShowUserInfoByMid(Model model, Integer mid,HttpServletRequest request) {
        try {
            Map<String, Object> map = memberFidesService.queryUserInfoByMid(mid);
            PageUtil page = new PageUtil(request);
            model.addAttribute("memberFides", map);
            //PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", mid);
            return "WEB-INF/view/web/user/agent_userInfo_show";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 牌局记录入口
     * @param model
     * @param mid
     * @param request
     * @return
     */
    @RequestMapping(value = "agent/cardRecord/listUi.html", method = RequestMethod.GET)
    public String agentListUiCardRecordByMid(Model model, Integer mid,HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", mid);
            return "WEB-INF/view/web/user/agent_cardRecord_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 牌局记录列表
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/cardRecord/list.html")
    @ResponseBody
    public Object agentListCardRecordByMid(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("roomId", null);//房间id
            parameters.put("cardNum", null);//牌局编号
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<PlayerRecord> list = playerRecordService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * 金币来源入口
     * @param model
     * @param mid
     * @param request
     * @return
     */
    @RequestMapping(value = "agent/goldOrigin/listUi.html", method = RequestMethod.GET)
    public String agentListUiGoldOriginByMid(Model model, Integer mid,HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", mid);
            return "WEB-INF/view/web/user/agent_goldOrigin_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 金币来源
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/goldOrigin/list.html")
    @ResponseBody
    public Object agentListGoldOriginByMid(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("date", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<GoldLog> list = goldLogService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }



    /**
     *  修改邀请码入口
     *  //@Author:zun.wei, @Date:2017/4/1 14:08
     * @param model
     * @param mid
     * @param request
     * @return
     */
    @RequestMapping(value = "agent/editUserInviteCode/editUi.html", method = RequestMethod.GET)
    public String editUserInviteCodeUi(Model model, Integer mid,HttpServletRequest request) {
        try {
        	Map<String,Object> param=new HashMap<>();
        	Map<String,Object> agentAndInviteInfo=new HashMap<>();
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            
            param.put("dbName", gameCode);
            Members members=membersServcie.selectByPrimaryKey(mid);
            if (members==null){
            	model.addAttribute(CommonContants.ERROR, "发生错误：用户信息不存在！");
            	
            }else {
            	param.put("sitemid", members.getSitemid());
                model.addAttribute("sitemid", members.getSitemid());
            	agentAndInviteInfo= memberInviteService.getAgentAndInviteInfo(param);
            }
            
            //a.id, a.mid, a.invitecode, a.inviteurl, a.status,b.realname
            if (agentAndInviteInfo != null) {
                //model.addAttribute("mid", agentAndInviteInfo.get("mid"));
                model.addAttribute("mid", mid);
                model.addAttribute("realname", agentAndInviteInfo.get("realname"));
                model.addAttribute("inviteCode", agentAndInviteInfo.get("invitecode"));
            } else {
                //@Author:zun.wei, @Date:2017/4/1 10:20  mid在代理商邀请表找不到！
                model.addAttribute(CommonContants.ERROR, "发生错误：代理商邀请信息不存在！");
            }
            return "WEB-INF/view/web/user/member_editInviteCode_from";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 修改邀请码
     * //@Author:zun.wei, @Date:2017/4/1 14:08
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/editUserInviteCode/edit.html")
    @ResponseBody
    public Object editUserInviteCode(String editInviteCode,Integer mid,String sitemid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        int count = memberInviteService.queryCountByInviteCode(editInviteCode);
        if (count > 0) {//说明邀请码已经被使用了
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":邀请码已经被使用了!");
        } else {//邀请码没有被使用
            //Map<String, Object> parameters = new HashMap<String, Object>();
            MemberInvite memberInvite = new MemberInvite();
            String inviteUrl = "http://lwdownload.jiaheyx.com/?via=" + editInviteCode;
            Members members = membersServcie.selectByPrimaryKey(mid);
            if (members != null) {
                memberInvite.setSitemid(members.getSitemid());
            }
            memberInvite.setMid(mid);
            memberInvite.setInvitecode(editInviteCode);
            memberInvite.setInviteurl(inviteUrl);
            int result = memberInviteService.updateInviteCodeUrlByMidCode(memberInvite);
            if (result > 0) {//更新成功
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
            } else {//更新失败
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":更新失败!");
            }
        }
        return map;
    }


    /**
     * //@Author:zun.wei, @Date:2017/4/25 16:21
     * 取消或绑定邀请人入口
     * @param mid
     * @param model
     * @return
     */
    @RequestMapping(value = "agent/cancelOrBindInviterUi/editUi.html")
    public String updateCancelOrBindInviterUi(Integer mid,Model model) {
        Map<String, Object> memberFide = memberFidesService.queryUserInfoByMid(mid);
        if (memberFide != null && !StringUtils.isBlank(memberFide.get("belongid") + "")
                && !"null".equals(memberFide.get("belongid") + "")) {
            MemberBusiness memberBusiness = businessService.selectById(Integer.parseInt(memberFide.get("belongid") + ""));
            if (memberBusiness != null) {
                model.addAttribute("bName",memberBusiness.getName());
            }
        }
        if (memberFide == null) {
            model.addAttribute(CommonContants.ERROR, "用户不存在！");
        }
        model.addAttribute("memberFide", memberFide);
        return "WEB-INF/view/web/user/member_cancelOrBindInvite_form";
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/25 16:24
     * 绑定邀请人
     * @param bingdingId
     * @param mid
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/bindInviter/edit.html")
    @ResponseBody
    public Object saveBindInviter(Integer bingdingId,Integer mid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        MemberAgents memberAgents = memberAgentService.selectByMid(bingdingId);
        if (memberAgents == null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":要绑定的代理商不存在！");
            return map;
        }
        MemberFides memberFides = memberFidesService.selectByMemberMid(mid);
        if (memberFides == null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":用户不存在！");
            return map;
        }
        if (memberFides.getInvite() != 0) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE
                    + ":该用户已经绑定代理商！代理商mid:" + memberFides.getInvite());
            return map;
        }
        MemberAgents isFirstAgent = memberAgentService.selectByMid(mid);
        if (isFirstAgent != null) {//一级代理商
            Short belongId = isFirstAgent.getBelongid();
            if (belongId != null && !StringUtils.isBlank(belongId + "")) {//一级代理商
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE
                        + ":该用户为一级代理商无法绑定到其他代理商！");
                return map;
            }
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE
                    + ":该用户为代理商不允许取消邀请人！");//如果允许，则取消之后，他邀请的人归属给谁？
            return map;
        }
        memberFides.setInvite(bingdingId);
        memberFides.setBindtime(DateUtil.currentTimeToInt());
        int result = memberFidesService.updateByPrimaryKeySelective(memberFides);
        
        //绑定邀请人、发消息给服务器
        SendMsgToCServer.sendMsgToCServer(mid, 6, bingdingId, goldhost, goldport);
        if (result > 0) {
            map.put(CommonContants.SUCCESS, Boolean.TRUE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE +"");
        }
        return map;
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/25 16:24
     * 取消解绑邀请人
     * @param mid
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/cancelInviter/edit.html")
    @ResponseBody
    public Object updateCancelInviter(Integer mid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        MemberFides memberFides = memberFidesService.selectByMemberMid(mid);
        if (memberFides == null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":用户不存在");
            return map;
        }
        MemberAgents isAgent = memberAgentService.selectByMid(mid);
        if (isAgent != null) {
            memberAgentService.deleteByPrimaryKey(isAgent.getId());
        }
        memberFides.setInvite(0);
     
        int result = memberFidesService.updateByPrimaryKeySelective(memberFides);
        
        BaseParam baseParam = baseParamService.findBaseParamByCode(AppConstants.BaseParam.MEMCACHED_IP);
        //清除memcache待开房记录
        if (baseParam != null && StringUtils.isNotBlank(baseParam.getValue())) {
            if (!MemcachedUtil.isBlankMemcached(baseParam.getValue(), MemcacheKeyPrefix.OPEN_SESSION_KEY + mid)) {
                MemcachedUtil.deleteMemcached(baseParam.getValue(), MemcacheKeyPrefix.OPEN_SESSION_KEY + mid);
                //删除待开房
                agentMidsServcie.deleteByMid(mid);
            }
        } else {
            agentMidsServcie.deleteByMid(mid);//删除待开房
        }
        //取消绑定、发消息给服务器
        SendMsgToCServer.sendMsgToCServer(mid, 6, 0, goldhost, goldport);
        if (result > 0) {
            map.put(CommonContants.SUCCESS, Boolean.TRUE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE +" ");
        }
        return map;
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/25 16:22
     * 取消代理入口
     * @param mid
     * @return
     */
    @RequestMapping(value = "agent/cancelAgentUi/editUi.html")
    public String updateCancelAgentUi(Integer mid,Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        MemberAgents memberAgents = memberAgentService.selectByMid(mid);
        if (memberAgents != null) {
            MemberBusiness memberBusiness = businessService.selectById(memberAgents.getBelongid().intValue());
            model.addAttribute("memberBusiness", memberBusiness);
            model.addAttribute("memberAgents", memberAgents);
        } else {
            model.addAttribute(CommonContants.ERROR, "该用户不是代理商！");
        }
        return "WEB-INF/view/web/user/member_cancelAgent_form";
    }

    /**
     * 取消代理
     * @param mid
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/cancelAgent/edit.html")
    @ResponseBody
    public Object updateCancelAgent(Integer mid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        MemberFides memberFides = memberFidesService.selectByMemberMid(mid);
        if (memberFides == null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":取消用户不存在！");
            return map;
        }
        MemberAgents isAgent = memberAgentService.selectByMid(mid);
        if (isAgent == null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":取消用户不是代理商！");
            return map;
        }
        int childAgentsCount = memberAgentService.getCountAgentsByParentId(isAgent.getMid());
        if (childAgentsCount > 0) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE
                    + ":所取消的代理商还有下级代理商存在不能删除！");
            return map;
        }
        /*agentMidsServcie.deleteByMid(mbf.getMid());
        memberInviteService.insertSelective(memberinvite);
        commonAgentsRelationService.insertSelective(commonAR);*/
        List<MemberFides> memberFidesList = memberFidesService.selectAengtInviteInfo(isAgent.getMid());
        if (memberFidesList != null && memberFidesList.size() > 0) {
            for (MemberFides fides : memberFidesList) {
                fides.setInvite(0);
                int result = memberFidesService.updateByPrimaryKeySelective(fides);
                BaseParam baseParam = baseParamService.findBaseParamByCode(AppConstants.BaseParam.MEMCACHED_IP);
                //清除memcache待开房记录
                if (baseParam !=null ) {
                    if (!MemcachedUtil.isBlankMemcached(baseParam.getValue(), MemcacheKeyPrefix.OPEN_SESSION_KEY + fides.getMid())) {
                        MemcachedUtil.deleteMemcached(baseParam.getValue(), MemcacheKeyPrefix.OPEN_SESSION_KEY + fides.getMid());
                        //删除待开房
                        agentMidsServcie.deleteByMid(fides.getMid());
                    }
                }
                //取消绑定、发消息给服务器
                SendMsgToCServer.sendMsgToCServer(fides.getMid(), 6, 0, goldhost, goldport);
            }
        }
        agentMidsServcie.deleteByAgentMid(isAgent.getMid());
        CommonAgents commonAgents = commonAgentService.selectByPrimaryKey(isAgent.getSitemid());//删除公共表
        if (commonAgents != null) {
            String info = commonAgents.getInfo();
            Map<String, Object> infoMap = JSON.parseObject(info, Map.class);
            if (infoMap != null && infoMap.size() > 0) {
                if (infoMap.size() == 1) {//如果是单款游戏的代理商，就直接把邀请表的邀请码删除
                    commonAgentService.deleteByPrimaryKey(isAgent.getSitemid());
                    memberInviteService.deleteBySiteId(isAgent.getSitemid());//删除邀请表
                } else {//如果是多款游戏的代理商,邀请表的记录不能删除。
                    infoMap.remove(gameCode);
                    String result = JSON.toJSONString(infoMap);
                    CommonAgents commonAgents1 = new CommonAgents();
                    commonAgents1.setSitemid(isAgent.getSitemid());
                    commonAgents1.setInfo(result);
                    commonAgentService.updateByPrimaryKeySelective(commonAgents1);
                }
            }
        }
        int deleteCommonAgentRelation = commonAgentsRelationService.deleteByMidAndGameType(isAgent.getMid(), gameType);
        logger.debug("delete common agent realation table mid ::", isAgent.getMid());
        logger.debug("delete common agent realation table gameType ::", gameType);
        logger.debug("delete common agent realation table delete result item ::", deleteCommonAgentRelation);
        agentClubGroupService.deleteByMid(isAgent.getMid());//删除俱乐部
        agentClubMemberService.deleteByCmid(isAgent.getMid());//删除俱乐部成员表
        int result = memberAgentService.deleteByPrimaryKey(isAgent.getId());
        boolean clubFlag= this.romveClubMemcache(mid);
        log.debug("romveClubMemcache clear club member all >>>>>>>>>>>>>>>>>>>!"+clubFlag);
        if (!clubFlag){
        	map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE +":俱乐部清楚失败！");
        }
        if (result > 0) {
            //发送消息通知C++通知客户端，会员状态身份改变
            if (Integer.parseInt(gameType) >= 20) {
                SendMsgToCServer.sendMsgToCServer(isAgent.getMid(), 5, 0, goldhost, goldport);
            }
            map.put(CommonContants.SUCCESS, Boolean.TRUE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS + ":成功取消代理！");
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE +":取消代理失败！");
        }
        return map;
    }

    /**
     * 移除俱乐部清空表或缓存
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年9月7日
     */
    public boolean romveClubMemcache(int mid){
    	List<ClubMember> clubMemberLists=clubMemberService.getByPrimaryClubidList(mid);
    	List<ClubMids> clubMidsLists =clubMidsService.getMidByPrimaryClubidList(mid);
    		BaseParam baseParam = baseParamService.findBaseParamByCode(AppConstants.BaseParam.MEMCACHED_IP);
    		try {
    		if (clubMemberLists !=null ){
		    	for (ClubMember clubMember : clubMemberLists) {
		    		String clubs = (String) MemcachedUtil.getMemcached(baseParam.getValue(), AppConstants.MemcacheKeyPrefix.CLUBS+ clubMember.getMid());
						//String clubs=memcachedClient.get(AppConstants.MemcacheKeyPrefix.CLUBS+ clubMember.getClubid());
						if (clubs !=null ){
							clubs=clubs.replace(mid+",", "");
						}
						if (clubs.equals("") ||clubs !=null ){
							 MemcachedUtil.setMemcached(baseParam.getValue(), AppConstants.MemcacheKeyPrefix.CLUBS+ clubMember.getMid(), clubs, 0);
							//memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBS+ clubMember.getClubid(),0,clubs);
						} else {
							 MemcachedUtil.deleteMemcached(baseParam.getValue(),  AppConstants.MemcacheKeyPrefix.CLUBS+ clubMember.getMid());
							//memcachedClient.delete(AppConstants.MemcacheKeyPrefix.CLUBS+ clubMember.getClubid());
						}
				}
    		}
    		
    		if (clubMidsLists !=null ){
		    	for (ClubMids clubMids : clubMidsLists) {
		    		String clubAuth=(String) MemcachedUtil.getMemcached(baseParam.getValue(),AppConstants.MemcacheKeyPrefix.CLUBSAUTH+ clubMids.getMid());
		    		//String clubAuth=memcachedClient.get(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+ mid);
		    		if (clubAuth !=null ){
		    			clubAuth=clubAuth.replace(mid+",", "");
					}
					if (clubAuth.equals("") ||clubAuth !=null ){
						MemcachedUtil.setMemcached(baseParam.getValue(), AppConstants.MemcacheKeyPrefix.CLUBSAUTH+ clubMids.getMid(), clubAuth, 0);
						//memcachedClient.set(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+ mid,0,clubAuth);
					}else {
						MemcachedUtil.deleteMemcached(baseParam.getValue(), AppConstants.MemcacheKeyPrefix.CLUBSAUTH+  clubMids.getMid());
						//memcachedClient.delete(AppConstants.MemcacheKeyPrefix.CLUBSAUTH+ mid);
					}
				}
    		}
    		} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
    	clubMemberService.deleteByPrimaryClubId(mid);
    	clubService.deleteByPrimaryKey(mid);
    	clubMidsService.deleteByPrimaryClubid(mid);
    	return true;
    }

    /**
     * @Author:zun.wei , @Date:2017/4/28 15:37
     * @Description:游戏后台添加金币入口
     * @param mid
     * @param model
     * @return
     */
    @RequestMapping(value = "agent/backgroundAddGoldUi/editUi.html")
    public String addBackgroundAddGoldUi(Integer mid,Model model) {
        MemberFides memberFides = memberFidesService.selectByMemberMid(mid);
        if (memberFides != null) {
            MemberAgents memberAgents = memberAgentService.selectByMid(memberFides.getMid());
            if (memberAgents != null) {
                MemberBusiness memberBusiness = businessService.selectById(memberAgents.getBelongid().intValue());
                if (memberBusiness != null) model.addAttribute("memberBusiness", memberBusiness);
                model.addAttribute("memberAgents", memberAgents);
            }
            model.addAttribute("memberFides", memberFides);
        } else {
            model.addAttribute(CommonContants.ERROR, "用户不存在！");
        }
        return "WEB-INF/view/web/user/member_gameBackGroAddGold_form";
    }


    /**
     * @Author:zun.wei , @Date:2017/4/28 15:37
     * @Description:游戏后台添加金币
     * @param mid
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/backgroundAddGold/edit.html")
    @ResponseBody
    public Object addBackgroundAddGold(Integer mid,Integer goldCount,String remark) throws Exception {
        if (goldCount == null) goldCount = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        MemberFides memberFides = memberFidesService.selectByMemberMid(mid);
        if (memberFides == null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":要添加金币的用户不存在！");
            return map;
        }
        if (goldCount == 0) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":请填写要增/减金币！");
            return map;
        }
        if (StringUtils.isBlank(remark)) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":请填写备注！");
            return map;
        }
        map = busiGoldLogService //c++发送金币
                .updateGold(mid, goldCount, AppConstants.GoldLogSourceType.GACKGROUND_ADD_GOLD,
                        goldhost, goldport, Integer.valueOf(gameType), remark);
        Boolean flag = (Boolean)map.get(CommonContants.SUCCESS);
        if (!flag) return map;
        map.put(CommonContants.SUCCESS, true);
        map.put(CommonContants.MESSAGE, "添加金币成功!");
        return map;
    }

    /**
     * @param mid
     * @param model
     * @return
     * @Author:zun.wei , @Date:2017/6/26 10:04
     * @Description:取消代理商会员绑定入口
     */
    @RequestMapping(value = "agent/cancelAgentMembershipBinding/editUi.html")
    public String cancelAgentMembershipBinding(Integer mid, HttpServletRequest request,Model model) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        model.addAttribute("mid", mid);
        return "WEB-INF/view/web/user/agent_cancelMemberBinding_list";
    }

    /**
     * @Author:zun.wei , @Date:2017/6/26 10:37
     * @Description:取消代理商会员绑定
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("agent/cancelAgentMembershipBinding/list.html")
    @ResponseBody
    public Object cancelAgentMembershipBinding(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("memberMid", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = memberFidesService.selectAgentBindingUserList(parameters);
        return getReturnPage(pager, page, list);
    }

}
