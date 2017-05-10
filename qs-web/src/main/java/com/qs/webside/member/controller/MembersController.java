package com.qs.webside.member.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.GoldLog;
import com.qs.log.game.model.PlayerRecord;
import com.qs.log.game.service.IBusiGoldLogService;
import com.qs.log.game.service.IGoldLogService;
import com.qs.log.game.service.IPlayerRecordService;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.model.MemberInvite;
import com.qs.pub.game.service.IBusinessService;
import com.qs.pub.game.service.ICommonAgentsRelationService;
import com.qs.pub.game.service.IMemberInviteService;
import com.qs.webside.agent.service.IAgentMidsServcie;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.Members;
import com.qs.webside.member.service.IMemberFidesService;
import com.qs.webside.member.service.IMembersServcie;

import jodd.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IMembersServcie membersServcie;

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
            map.put("businessName",business.getName());
        }
        if (!StringUtil.isBlank(id)) {
            int agentId = Integer.valueOf(id);
            MemberAgents memberAgents = memberAgentService.selectByMid(agentId);
            map.put("memberAgents", memberAgents);
        }
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
            Map<String, Object> map = memberFidesService.queryUserInfoByMid(mid);
           /* Object o = map.get("belongid");
            if (o != null) {
                int belongId = Integer.valueOf(o + "");
                MemberBusiness business = businessService.selectById(belongId);
                map.put("businessName",business.getName());
            }*/
            PageUtil page = new PageUtil(request);
            model.addAttribute("memberFides", map);
            model.addAttribute("page", page);
            model.addAttribute("id", mid);
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
            /*Object o = map.get("belongid");
            if (o != null) {
                int belongId = Integer.valueOf(o + "");
                MemberBusiness business = businessService.selectById(belongId);
                map.put("businessName",business.getName());
            }*/
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
                model.addAttribute("mid", agentAndInviteInfo.get("mid"));
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
    public String cancelOrBindInviterUi(Integer mid,Model model) {
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
    public Object bindInviter(Integer bingdingId,Integer mid) throws Exception {
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
        int result = memberFidesService.updateByPrimaryKeySelective(memberFides);
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
    public Object cancelInviter(Integer mid) throws Exception {
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
    public String cancelAgentUi(Integer mid,Model model) {
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
    public Object cancelAgent(Integer mid) throws Exception {
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
        /*agentMidsServcie.deleteByMid(mbf.getMid());
        memberInviteService.insertSelective(memberinvite);
        commonAgentsRelationService.insertSelective(commonAR);*/
        agentMidsServcie.deleteByMid(isAgent.getMid());
        memberInviteService.deleteBySiteId(isAgent.getSitemid());
        commonAgentsRelationService.deleteByMid(isAgent.getMid());
        int result = memberAgentService.deleteByPrimaryKey(isAgent.getId());
        if (result > 0) {
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
     * @Author:zun.wei , @Date:2017/4/28 15:37
     * @Description:游戏后台添加金币入口
     * @param mid
     * @param model
     * @return
     */
    @RequestMapping(value = "agent/backgroundAddGoldUi/editUi.html")
    public String backgroundAddGoldUi(Integer mid,Model model) {
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
    public Object backgroundAddGold(Integer mid,Integer goldCount,String remark) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        MemberFides memberFides = memberFidesService.selectByMemberMid(mid);
        if (memberFides == null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":要添加金币的用户不存在！");
            return map;
        }
        if (goldCount <= 0) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE + ":要添加的金币要大于0！");
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

}
