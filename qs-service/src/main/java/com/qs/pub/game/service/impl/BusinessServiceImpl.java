package com.qs.pub.game.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.DateUtil;
import com.qs.common.util.ID;
import com.qs.common.util.RandomUtil;
import com.qs.log.game.service.ITaxesInviteService;
import com.qs.pub.game.mapper.CommonAgentsMapper;
import com.qs.pub.game.mapper.MemberBusinessMapper;
import com.qs.pub.game.model.CommonAgents;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.model.MemberBusinessDto;
import com.qs.pub.game.service.IBusinessService;
import com.qs.webside.game.mapper.ApkSynchroMapper;
import com.qs.webside.game.model.ApkSynchroWithBLOBs;
import com.qs.webside.member.mapper.*;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.MemberInvite;
import com.qs.webside.member.model.Members;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class BusinessServiceImpl implements IBusinessService {

    @Resource
    private MemberBusinessMapper memberBusinessMapper;

    @Resource
    private ApkSynchroMapper apkSynchroMapper;

    @Resource
    private MemberFidesMapper memberFidesMapper;

    @Resource
    private MemberAgentsMapper memberAgentsMapper;

    @Resource
    private MembersMapper membersMapper;

    @Resource
    private MemberInviteMapper memberInviteMapper;

    @Resource
    private CommonAgentsMapper commonAgentsMapper;

    @Resource
    private AgentMidsMapper agentMidsMapper;

    @Resource
    private ITaxesInviteService taxesInviteService;

    @Value("${game.gameCode}")
    private String gameCode;

    @Override
    public List<Map<String, Object>> queryListMapByPage(Map<String, Object> parameter) {
        return memberBusinessMapper.queryListMapByPage(parameter);
    }

    @Override
    public int add(MemberBusinessDto memberBusinessDto) {
        //TODO还需要处理account 是邮箱还是手机号  salt
        //TODO添加的时候需要验证新添加的账号是否已经存在。memberbusiness表中的手机
        if (memberBusinessDto != null && memberBusinessDto.getAccount() != null) {
            String account = memberBusinessDto.getAccount();
            String regExPhone = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
            Pattern pattern = Pattern.compile(regExPhone);
            Matcher matcher = pattern.matcher(account);
            boolean rs = matcher.find();
            String regExEmail = "\t^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}$";
            Pattern patternEmail = Pattern.compile(regExEmail);
            Matcher matcherEmail = patternEmail.matcher(account);
            boolean rsEmail = matcherEmail.find();
            if (rs) {//如果匹配为手机号码，则检查是否绑定过。
                MemberBusiness mb = loadByPhone(memberBusinessDto.getAccount());
                if (mb != null) return -100;//手机号码已绑定
                if (memberBusinessDto.getConfirmPwd()
                        .equals(memberBusinessDto.getPassWord())) {//表示未被绑定,校验密码相等
                    String uuId = ID.generateUUID();
                    String passwordCryto = new Md5Hash(memberBusinessDto.getPassWord()
                            ,uuId,2).toBase64();
                    MemberBusiness memberBusiness = new MemberBusiness();
                    memberBusiness.setCompany(memberBusinessDto.getCompany());//公司代码
                    memberBusiness.setName(memberBusinessDto.getName());//真实姓名
                    memberBusiness.setPhone(memberBusinessDto.getAccount());//手机号码
                    //memberBusiness.setEmail("sdfdsaf");
                    memberBusiness.setSalt(uuId);//盐值
                    memberBusiness.setPasswd(passwordCryto);//加密后的密码
                    memberBusiness.setRemark(memberBusinessDto.getRemark());//备注
                    memberBusiness.setMktime(DateUtil.convertToInt(new SimpleDateFormat
                            ("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())));//创建时间
                    return memberBusinessMapper.insertSelective(memberBusiness);
                }
            }
            if (rsEmail) {//如果匹配邮箱
                if (memberBusinessDto.getConfirmPwd().equals(memberBusinessDto.getPassWord())) {//校验密码相等
                    String uuId = ID.generateUUID();
                    String passwordCryto = new Md5Hash(memberBusinessDto.getPassWord()
                            ,uuId,2).toBase64();
                    MemberBusiness memberBusiness = new MemberBusiness();
                    memberBusiness.setCompany(memberBusinessDto.getCompany());//公司代码
                    memberBusiness.setName(memberBusinessDto.getName());//真实姓名
                    //memberBusiness.setPhone();//手机号码
                    memberBusiness.setEmail(memberBusinessDto.getAccount());//邮箱
                    memberBusiness.setSalt(uuId);//盐值
                    memberBusiness.setPasswd(passwordCryto);//加密后的密码
                    memberBusiness.setRemark(memberBusinessDto.getRemark());//备注
                    memberBusiness.setMktime(DateUtil.convertToInt(new SimpleDateFormat
                            ("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())));//创建时间
                    return memberBusinessMapper.insertSelective(memberBusiness);
                }
            }
            if (rs && rsEmail) {
                //两个都匹配，异常
                return 0;
            }
        }
        return 0;
    }

    @Override
    public MemberBusiness loadByPhone(String phone) {
        return memberBusinessMapper.loadByPhone(phone);
    }

    @Override
    public MemberBusiness selectById(Integer id) {
        return memberBusinessMapper.selectByPrimaryKey(id);
    }

    @Override
    public int udpatePhoneById(Integer id,String phone) {
        if (phone != null && memberBusinessMapper.loadByPhone(phone) == null) {
            MemberBusiness memberBusiness = new MemberBusiness();
            memberBusiness.setId(id);
            memberBusiness.setPhone(phone);
            return memberBusinessMapper.updateByPrimaryKeySelective(memberBusiness);
        } else if (phone != null && memberBusinessMapper.loadByPhone(phone) == null) {
            return -100;
        }
        return 0;
    }

    @Override
    public int updatePassWordById(Integer agentId, String password,String confirmPwd) {
        if (password != null && password.equals(confirmPwd)) {
            MemberBusiness memberBusiness = new MemberBusiness();
            String uuId = ID.generateUUID();
            String passwordCryto = new Md5Hash(password,uuId,2).toBase64();
            memberBusiness.setPasswd(passwordCryto);
            memberBusiness.setSalt(uuId);
            memberBusiness.setId(agentId);
            return memberBusinessMapper.updateByPrimaryKeySelective(memberBusiness);
        }
        return 0;
    }

    @Override
    public boolean loadGameExistByGameId(Integer gameId) {
        ApkSynchroWithBLOBs apkSynchroWithBLOBs = apkSynchroMapper.selectByPrimaryKey(gameId);
        return apkSynchroWithBLOBs != null;
    }

    @Override
    public Map<String, Object> saveAuthorization(Integer userId, Integer gameId,MemberBusiness memberBusiness) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!loadGameExistByGameId(gameId)) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, "游戏不存在");
            return map;
        }

        if (!loadCheckMemberByMid(userId)) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, "用户不存在");
            return map;
        }

        MemberAgents memberAgents = memberAgentsMapper.selectByMid(userId);
        if (memberAgents != null) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, "该用户已经是代理商");
            return map;
        }

        Integer mid = userId;
        Integer agentId = userId;
        Byte glevel = 0;
        Byte alevel = null;
        Integer parentId = null;
        Integer firstmid = null;
        Byte company = null;
        Short belongId = null;
        String agentPasswd = "123456";
        Integer mktime = null;
        String uuId = ID.generateUUID();
        String passwordCryto = new Md5Hash(agentPasswd,uuId,2).toBase64();
        String salt = uuId;
        String invitecode = getInviteCode();
        String inviteUrl = "http://www.jiaheyx.com/rundownload/?via=" + invitecode;

        MemberFides memberFides = loadMemberByMid(userId);
        if (memberFides != null) {
            if (memberFides.getInvite() != null) {
                Integer invite = memberFides.getInvite();
                Map<String, Object> agentInfoAndBizInfo = memberAgentsMapper.getAgentInfoAndBizInfoByMid(invite);
                if (agentInfoAndBizInfo != null) {
                    alevel = (byte) ((Integer) agentInfoAndBizInfo.get("alevel") + 1);
                    parentId = Integer.parseInt(agentInfoAndBizInfo.get("mid") + "") ;
                    firstmid = Integer.parseInt( agentInfoAndBizInfo.get("firstmid") + "");//firstmid
                    //map.put(CommonContants.DATA, agentInfoAndBizInfo);
                } else {
                    map.put(CommonContants.SUCCESS, Boolean.FALSE);
                    map.put(CommonContants.DATA, null);
                    map.put(CommonContants.MESSAGE, "推荐人不存在！");
                    return map;
                }
            } else {
                alevel = 1;
                parentId = 0;
                firstmid = mid;
            }


            company = memberBusiness.getCompany();
            belongId = Short.parseShort(memberBusiness.getId() + "");
            mktime = DateUtil.convertToInt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(System.currentTimeMillis()));

            Members members = membersMapper.selectByPrimaryKey(mid);
            if (members == null || members.getSitemid() == null || "".equals(members.getSitemid())) {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, "人员不存在！");
                return map;
            }

            //先加入邀请表
            MemberInvite memberInvite = new MemberInvite();
            memberInvite.setMid(agentId);
            memberInvite.setInvitecode(invitecode);
            memberInvite.setInviteurl(inviteUrl);
            memberInviteMapper.insertSelective(memberInvite);

            //加入代理商用户表
            MemberAgents memberAgents1 = new MemberAgents();
            memberAgents1.setMid(mid);
            memberAgents1.setPasswd(passwordCryto);
            memberAgents1.setSalt(salt);
            memberAgents1.setAlevel(alevel);
            memberAgents1.setBelongid(belongId);
            memberAgents1.setMktime(mktime + "");
            memberAgents1.setParentId(parentId);
            memberAgents1.setFirstmid(firstmid);
            memberAgents1.setGlevel(glevel);
            memberAgents1.setCompany(company);
            memberAgents1.setSitemid(members.getSitemid());
            memberAgents1.setAreaid(0);
            memberAgentsMapper.insertSelective(memberAgents1);

            //维护共用代理商表
            String siteMid = members.getSitemid();
            CommonAgents commonAgents = commonAgentsMapper.selectByPrimaryKey(siteMid);
            if (commonAgents != null) {
                String info = commonAgents.getInfo();
                Map<String, Object> infoMap = JSON.parseObject(info, Map.class);
                infoMap.put(gameCode, agentId);
                String result = JSON.toJSONString(infoMap);
                CommonAgents commonAgents1 = new CommonAgents();
                commonAgents1.setSitemid(siteMid);
                commonAgents1.setInfo(result);
                commonAgentsMapper.updateByPrimaryKeySelective(commonAgents1);
            } else {
                Map<String, Object> infoMap = new HashMap<>();
                infoMap.put(gameCode, agentId);
                String result = JSON.toJSONString(infoMap);
                CommonAgents commonAgents1 = new CommonAgents();
                commonAgents1.setSitemid(siteMid);
                commonAgents1.setInfo(result);
                commonAgentsMapper.insertSelective(commonAgents1);
            }

            //删除代开房数据 , 取消免费开房
            agentMidsMapper.deleteByMid(agentId);

            map.put(CommonContants.SUCCESS, Boolean.TRUE);

            map.put(CommonContants.DATA, null);
            //map.put(CommonContants.MESSAGE, "平台ID为空，或者人员信息为空!");
            return map;
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, "人员信息不存在！");
            return map;
        }

        //return map;
    }

    @Override
    public boolean loadCheckMemberByMid(Integer mid) {
        MemberFides memberFides = memberFidesMapper.selectByMemberMid(mid);
        return memberFides != null;
    }

    @Override
    public MemberFides loadMemberByMid(Integer mid) {
        return memberFidesMapper.selectByMemberMid(mid);
    }

    @Override
    public Map<String, Object> queryUserInfo(Integer userId, Integer gameId,MemberBusiness nowBiz) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!loadGameExistByGameId(gameId)) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            //map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, "游戏不存在");
            return map;
        }

        if (!loadCheckMemberByMid(userId)) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            //map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, "用户不存在");
            return map;
        }

        MemberAgents memberAgents = memberAgentsMapper.selectByMid(userId);
        if (memberAgents != null) {
            Map<String, Object> agentInfoAndBizInfo = memberAgentsMapper
                    .getAgentInfoAndBizInfoByMid(memberAgents.getMid());
            Map<String, Object> belongAgent = memberAgentsMapper
                    .getAgentInfoAndBizInfoByMid(Integer.parseInt(memberAgents.getBelongid() + ""));
            if (belongAgent != null) {
                agentInfoAndBizInfo.put("belongAgent", belongAgent.get("realname"));
            } else {
                agentInfoAndBizInfo.put("belongAgent", "");
            }
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, agentInfoAndBizInfo);
            map.put(CommonContants.MESSAGE, "该用户已经是代理商");
            return map;
        }


        MemberFides memberFides = loadMemberByMid(userId);
        MemberBusiness memberBusiness = null;
        MemberAgents agentInfo = null;
        MemberAgents parentAgentInfo = null;
        if (memberFides != null && memberFides.getInvite() != null) {
            parentAgentInfo = memberAgentsMapper.selectByMid(userId);
            if (parentAgentInfo != null) {
                memberBusiness = memberBusinessMapper.selectByPrimaryKey
                        (Integer.parseInt(parentAgentInfo.getBelongid() + ""));

            }
        } else if (memberFides != null && memberFides.getInvite() == null) {
            agentInfo = memberAgentsMapper.selectByPrimaryKey(userId);
            if (agentInfo != null && agentInfo.getBelongid() != null) {
                memberBusiness = memberBusinessMapper.selectByPrimaryKey
                        (Integer.parseInt(agentInfo.getBelongid() + ""));
            }
        }
        MemberInvite memberInvite = memberInviteMapper.selectByMid(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("memberInvite", memberInvite);
        data.put("memberFides", memberFides);
        data.put("memberBusiness", memberBusiness);
        data.put("parentAgentInfo", parentAgentInfo);
        map.put(CommonContants.SUCCESS, Boolean.TRUE);
        map.put(CommonContants.DATA, data);
        return map;
    }

    @Override
    public Map<String, Object> queryFirstAgentCountByBelongId(Integer belongid) {
        return memberAgentsMapper.queryFirstAgentCountByBelongId(belongid);
    }

    @Override
    public List<Map<String, Object>> queryFirstAgentByBelongIdPage(Map<String,Object> belongid) {
        return memberAgentsMapper.queryFirstAgentByBelongIdPage(belongid);
    }

    @Override
    public Map<String, Object> getAgentDetailInfoByMid(Integer mid) {
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, Object> agentBusinessInfo = memberAgentsMapper.getAgentBusinessInfoByMid(mid);

        String realNameBind = null;
        if (agentBusinessInfo != null && agentBusinessInfo.get("firstmid") != null) {
            realNameBind = memberAgentsMapper.getAgentRealNameByMid
                    (Integer.parseInt(agentBusinessInfo.get("firstmid") + ""));
        }

        String gameName = null;
        MemberFides memberFides = memberFidesMapper.selectByMemberMid(mid);
        if (memberFides != null) {
            gameName = memberFides.getName();
        }

        Map<String,Object> payAndInviteTotal = taxesInviteService.getPayAndInviteTotalByMid(mid);
        Map<String,Object> aayAndInviteTotalByParentId = taxesInviteService.getPayAndInviteTotalByParentId(mid);
        Map<String, Object> aayAndInviteTotalByAgentParentId = taxesInviteService.getPayAndInviteTotalByAgentParentId(mid);
        Integer payTotal = 0;
        Integer inviteTotal = 0;//invitetotal
        if (payAndInviteTotal != null && payAndInviteTotal.get("paytotal") != null) {
            payTotal += Integer.parseInt(payAndInviteTotal.get("paytotal") + "");
        }
        if (aayAndInviteTotalByParentId != null && aayAndInviteTotalByParentId.get("paytotal") != null) {
            payTotal += Integer.parseInt(aayAndInviteTotalByParentId.get("paytotal") + "");
        }
        if (aayAndInviteTotalByAgentParentId != null && aayAndInviteTotalByAgentParentId.get("paytotal") != null) {
            payTotal += Integer.parseInt(aayAndInviteTotalByAgentParentId.get("paytotal") + "");
        }

        if (payAndInviteTotal != null && payAndInviteTotal.get("invitetotal") != null) {
            inviteTotal += Integer.parseInt(payAndInviteTotal.get("invitetotal") + "");
        }
        if (aayAndInviteTotalByParentId != null && aayAndInviteTotalByParentId.get("invitetotal") != null) {
            inviteTotal += Integer.parseInt(aayAndInviteTotalByParentId.get("invitetotal") + "");
        }
        if (aayAndInviteTotalByAgentParentId != null && aayAndInviteTotalByAgentParentId.get("invitetotal") != null) {
            inviteTotal += Integer.parseInt(aayAndInviteTotalByAgentParentId.get("invitetotal") + "");
        }

        result.put("realNameBind", realNameBind);
        result.put("gameName", gameName);
        result.put("payTotal", payTotal);
        result.put("inviteTotal", inviteTotal);
        result.put("agentBusinessInfo", agentBusinessInfo);
        return result;
    }

    @Override
    public List<Map<String, Object>> queryChildrenAgent(Map<String, Object> buzIdFriId) {
        List<Map<String, Object>> childAgents = memberAgentsMapper.getChildrenAgentsList(buzIdFriId);
        return childAgents;
    }

    @Override
    public Integer getChildrenAgentsCount(Map<String, Object> firstMidBelongId) {
        return memberAgentsMapper.getChildrenAgentsCount(firstMidBelongId);
    }

    @Override
    public MemberBusiness findByPhoneOrEmail(String phoneOrMail) {
        return memberBusinessMapper.findByPhoneOrEmail(phoneOrMail);
    }

    @Override
    public String getInviteCode() {
        while (true) {
            String randomInviteCode = RandomUtil.generateInviteCode();
            int count = memberInviteMapper.queryCountByInviteCode(randomInviteCode);
            if (count == 0) {
                return randomInviteCode;
            }
        }
    }

    @Override
    public List<Map<String, Object>> getUserAgentAndBuziInfoByUserMid(Map<String, Object> parameters) {
        Map<String,Object> map = new HashMap<String,Object>();
        MemberFides memberFides = memberFidesMapper.queryListByMid(parameters);
        MemberBusiness business = null;
        Map<String, Object> parentAgentInfo = null;
        if (memberFides != null && memberFides.getInvite() != null) {
            parentAgentInfo = memberAgentsMapper.getAgentBusinessInfoByMid(memberFides.getInvite());
            if (parentAgentInfo != null && parentAgentInfo.get("belongid") != null) {
                business = memberBusinessMapper.selectByPrimaryKey(
                        Integer.parseInt(parentAgentInfo.get("belongid") + ""));
            }
        } else {
            if (parameters.get("mid") != null && !"".equals(parameters.get("mid"))) {
                Map<String, Object> agentInfo = memberAgentsMapper.getAgentBusinessInfoByMid(
                        Integer.parseInt(parameters.get("mid") + ""));
                if (agentInfo != null && agentInfo.get("belongid") != null) {
                    business = memberBusinessMapper.selectByPrimaryKey(
                            Integer.parseInt(agentInfo.get("belongid") + ""));
                } else {
                    business = null;
                }
            }
        }
        String inviteCode = null;
        if (parameters.get("mid") != null && !"".equals(parameters.get("mid"))) {
            inviteCode = memberInviteMapper.getInviteCode(parameters);
            if (inviteCode != null) map.put("inviteCode", inviteCode);
            if (memberFides != null) map.put("memberFides", memberFides);
            if (business != null) map.put("business", business);
            if (parentAgentInfo != null) map.put("parentAgentInfo", parentAgentInfo);
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (map.size() > 0)
        list.add(map);
        return list;
    }


}
