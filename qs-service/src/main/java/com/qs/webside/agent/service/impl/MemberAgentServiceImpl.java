package com.qs.webside.agent.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.DateUtil;
import com.qs.common.util.ID;
import com.qs.log.game.service.ITaxesInviteService;
import com.qs.pub.game.model.CommonAgents;
import com.qs.pub.game.model.CommonAgentsRelation;
import com.qs.pub.game.model.MemberInvite;
import com.qs.pub.game.service.IBusinessService;
import com.qs.pub.game.service.ICommonAgentService;
import com.qs.pub.game.service.ICommonAgentsRelationService;
import com.qs.pub.game.service.IMemberInviteService;
import com.qs.webside.agent.model.AgentClubGroup;
import com.qs.webside.agent.model.AgentClubMember;
import com.qs.webside.agent.service.IAgentClubGroupService;
import com.qs.webside.agent.service.IAgentClubMemberService;
import com.qs.webside.agent.service.IAgentMidsServcie;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.member.mapper.MemberAgentsMapper;
import com.qs.webside.member.mapper.MemberFidesMapper;
import com.qs.webside.member.model.AgentMids;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.Members;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MemberAgentServiceImpl implements IMemberAgentService {

	
    @Resource
    private MemberAgentsMapper memberAgentsMapper;

    @Autowired
    private ITaxesInviteService taxesInviteService;
    

	@Autowired
	private IAgentMidsServcie agentMidsServcie;
	
	@Autowired
	private IBusinessService businessService;
	
	@Resource
    private ICommonAgentService commonAgentService;
    
	@Resource
    private IMemberInviteService memberInviteService;
	
	@Resource
	private MemberFidesMapper memberFidesMapper;
	
	@Resource
	private ICommonAgentsRelationService commonAgentsRelationService ;

	@Autowired
	private IAgentClubGroupService agentClubGroupService ;
	
	@Autowired
	private IAgentClubMemberService  agentClubMemberService ;
	
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberAgentsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MemberAgents record) {
        return memberAgentsMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberAgents record) {
        return memberAgentsMapper.insertSelective(record);
    }

    @Override
    public MemberAgents selectByPrimaryKey(Integer id) {
        return memberAgentsMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public int updateByPrimaryKeySelective(MemberAgents record) {
        return memberAgentsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberAgents record) {
        return memberAgentsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MemberAgents> queryListByPage(Map<String, Object> parameters) {
        return memberAgentsMapper.queryListByPage(parameters);
    }

    @Override
    public int editScale(Integer id, Integer mid, Byte scale, String remark) {
        MemberAgents memberAgents = new MemberAgents();
        memberAgents.setId(id);
        memberAgents.setMid(mid);
        memberAgents.setRemark(remark);
        memberAgents.setScale(scale);
        return memberAgentsMapper.updateByPrimaryKeySelective(memberAgents);
    }

    @Override
    public int resetPwd(Integer id) {
		String randomNum = "66668888";
		String uuId = ID.generateUUID();
		String passwordCryto = new Md5Hash(randomNum,uuId,2).toBase64();
       int result = 0;
		MemberAgents memberAgents = memberAgentsMapper.selectByPrimaryKey(id);
		if (memberAgents != null) {
			CommonAgents commonAgents = new CommonAgents();
			commonAgents.setSitemid(memberAgents.getSitemid());
			commonAgents.setLoginSalt(uuId);
			commonAgents.setLoginPasswd(passwordCryto);
			result = commonAgentService.updateSelectiveByIfphoneIsExsit(commonAgents);
		}
		return result;
	}

	@Override
	public MemberAgents getMemberAgentsInfoBySitemid(String sitemid) {
		return memberAgentsMapper.getMemberAgentsInfoBySitemid(sitemid);
	}


	@Override
	public Map<String,Object> getTaxesInviteMapper(Map<String, Object> param) {
		
		return taxesInviteService.getPayTempInviteCountByTime(param);
	}

	@Override
	public List<MemberAgents> getMemberAgentsInfoByParentId(String parentId) {
		return memberAgentsMapper.getMemberAgentsInfoByParentId(parentId);
	}
    @Override
    public MemberAgents selectByMid(Integer mid) {
        return memberAgentsMapper.selectByMid(mid);
    }

    @Override
    public Map<String, Object> getAgentInfoAndBizInfoByMid(Integer mid) {
        return memberAgentsMapper.getAgentInfoAndBizInfoByMid(mid);
    }

	@Override
    public Map<String, Object> queryFirstAgentCountByBelongId(Integer belongid) {
        return memberAgentsMapper.queryFirstAgentCountByBelongId(belongid);
    }

    /*@Override
    public List<Map<String, Object>> queryFirstAgentByBelongIdPage(Map<String, Object> belongid) {
        return commonAgentInfoService.queryFirstAgentByBelongIdPage(belongid);
    }*/

    @Override
    public Map<String, Object> getAgentBusinessInfoByMid(Map<String,Object> parameters) {
        return memberAgentsMapper.getAgentBusinessInfoByMid(parameters);
    }

    @Override
    public String getAgentRealNameByMid(Integer mid) {
        return memberAgentsMapper.getAgentRealNameByMid(mid);
    }

    @Override
    public Integer getChildrenAgentsCount(Map<String, Object> firstMidBelongId) {
        return memberAgentsMapper.getChildrenAgentsCount(firstMidBelongId);
    }

    /*@Override
    public List<Map<String, Object>> getChildrenAgentsList(Map<String, Object> firstMidBelongId) {
        return memberAgentsMapper.getChildrenAgentsList(firstMidBelongId);
    }*/

    @Override
    public MemberAgents findByBelongidAndMid(Map<String, Object> parameters) {
        return memberAgentsMapper.findByBelongidAndMid(parameters);
    }

    /*@Override
    public Map<String, Object> getAgentAndBusizInfoByMid(Map<String,Object> mid) {
        return memberAgentsMapper.getAgentAndBusizInfoByMid(mid);
    }*/

    @Override
    public String getFirstAgentCode() {
        return memberAgentsMapper.getFirstAgentCode();
    }

    @Override
	public List<MemberAgents> selectVipDirectlyInfo(Map<String, Object> paramters) {
		List<MemberAgents> mbList=new ArrayList<MemberAgents> ();
		if (!paramters.containsKey("type"))paramters.put("type", CommonContants.TYPE_VIP);
		
		mbList=memberAgentsMapper.selectVipDirectlyInfo(paramters);
		
		if (mbList !=null) {
			for (MemberAgents memberAgents : mbList) {
				
				AgentMids agentMids= agentMidsServcie.selectByMid(memberAgents.getMid());
				if (agentMids !=null) memberAgents.setOpenRoom("1"); else memberAgents.setOpenRoom("2");
				
//				MemberFides memberFides=memberFidesMapper.selectByPrimaryKey(memberAgents.getMid());
//				memberAgents.setRealname(memberFides.getName());
			}
		}
		return mbList;
	}

    /**
     * 代理商开通代理商业务插入信息
     * @param parma
     * @return
     * @author:zyy
     * @time:2017年3月29日
     */
	@Override
//	@CacheEvict(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},key="'getClubInfoList:'+#parma.agentId")
	public Map<String, Object> updateEmpowerAgentSubmit(Map<String, Object> parma) {
		
		Map<String, Object> res=new HashMap<String,Object>();
		MemberInvite memberinvite=new MemberInvite();
		MemberAgents memberAgents=new MemberAgents();
		MemberAgents mbg = (MemberAgents) parma.get("agents");
		MemberFides mbf =(MemberFides) parma.get("memberFides"); //被开通开通代理商基本信息
		Members member= (Members) parma.get("member");

		String invitecode=null;
		
		String agentPasswd = "123456";
		String uuId = ID.generateUUID();
		String passwordCryto = new Md5Hash(agentPasswd,uuId,2).toBase64();
		Integer alevel=Integer.valueOf(mbg.getAlevel().toString())+1;
		String code="";
		Object gameCode=parma.get("gameCode");


			invitecode=memberInviteService.getInviteCode(member.getSitemid());
			if (StringUtils.isBlank(invitecode)) {
				invitecode=businessService.getInviteCode();//邀请码获取
				//邀请信息
				memberinvite.setMid(mbf.getMid());
				memberinvite.setInvitecode(invitecode);//
				memberinvite.setInviteurl("http://lwdownload.jiaheyx.com/?via=" + invitecode);
				memberinvite.setSitemid(member.getSitemid());
				memberInviteService.insertSelective(memberinvite);
			}
			
			// mid,passwd,salt,mktime,parent_id,alevel,belongid,firstmid,company,sitemid
			memberAgents.setMid(mbf.getMid());
			memberAgents.setPasswd(passwordCryto);  
			memberAgents.setSalt(uuId);
			memberAgents.setMktime(DateUtil.currentTimeToInt().toString());
			memberAgents.setParentId(mbg.getMid());
			memberAgents.setAlevel(alevel.byteValue());
			memberAgents.setBelongid(mbg.getBelongid());
			memberAgents.setFirstmid(mbg.getFirstmid());
			memberAgents.setCompany(mbg.getCompany());
			memberAgents.setSitemid(member.getSitemid());
			memberAgents.setOpenid(mbf.getIdentity());
			memberAgents.setAreaid(0);
			memberAgents.setGlevel(Byte.valueOf("0"));
			
			code=this.getNextAgentCode(mbg);
			memberAgents.setCode(code);
			
			//更新common表信息
			//维护共用代理商表
			String siteMid = member.getSitemid();
			CommonAgents commonAgents = commonAgentService.selectByPrimaryKey(siteMid);
			if (commonAgents != null) {
				String info = commonAgents.getInfo();
				Map<String, Object> infoMap = JSON.parseObject(info, Map.class);
				infoMap.put(gameCode.toString(), mbf.getMid());
				String result = JSON.toJSONString(infoMap);
				CommonAgents commonAgents1 = new CommonAgents();
				commonAgents1.setSitemid(siteMid);
				commonAgents1.setInfo(result);
				commonAgentService.updateByPrimaryKeySelective(commonAgents1);
			} else {
				Map<String, Object> infoMap = new HashMap<>();
				infoMap.put(gameCode.toString(), mbf.getMid());
				String result = JSON.toJSONString(infoMap);
				CommonAgents commonAgents1 = new CommonAgents();
				commonAgents1.setSitemid(siteMid);
				commonAgents1.setInfo(result);
				commonAgentService.insertSelective(commonAgents1);
			}

			//int valueGameType=(int)redisTemplate.opsForValue().get("key");
			//common_agents_relation更新
			CommonAgentsRelation commonAR=new CommonAgentsRelation();
			commonAR.setMid(mbf.getMid());
			Object gameType=parma.get("gameType");
			commonAR.setGameType(Integer.parseInt(gameType.toString()));
			commonAR.setSitemid(siteMid);
			commonAR.setCreateId(mbg.getId());
			commonAR.setCreateTime(DateUtil.getNowDate());
			
			AgentClubGroup  agentClubGroup=new AgentClubGroup();
			agentClubGroup.setCmid(Integer.valueOf(parma.get("agentId").toString()));
			agentClubGroup.setName(mbf.getName());
			agentClubGroup.setCreateTime(DateUtil.getNowDate());
			
			AgentClubMember agentClubMember=new AgentClubMember();
			agentClubMember.setMid(Integer.valueOf(parma.get("agentId").toString()));
			agentClubMember.setCmid(Integer.valueOf(parma.get("agentId").toString()));
			agentClubMember.setCreateTime(DateUtil.getNowDate());
			
			agentClubGroupService.insertSelective(agentClubGroup);//当前俱乐部
			agentClubMemberService.insertSelective(agentClubMember);//当前俱乐部成员
			if (Integer.parseInt(gameType.toString())>=20 ||Integer.parseInt(gameType.toString())==17){
				//删除代开房数据 , 取消免费开房
				agentMidsServcie.deleteByMid(mbf.getMid());
			}
			this.insertSelective(memberAgents);
			commonAgentsRelationService.insertSelective(commonAR);
		 return res;
	}
	   
	/**
	 * 取得代理商编码
	 * @param agent
	 * @return
	 */
		@Override
	   public String getNextAgentCode(MemberAgents agent){
		    int parentId=agent.getMid();
	        String code = null;
	        String maxCode =memberAgentsMapper.getMaxAgentsCodeBymMid(parentId);
	        if (parentId>0) {
	        	//二级以及以下代理商
	            if (StringUtils.isNotBlank(maxCode)) {
	                String parentCode = maxCode.substring(0, maxCode.length() - 5);
	                String suffixCode = StringUtils.right(maxCode, 5);
	                String suffixCodeInc = Integer.toString(Integer.parseInt("1".concat(suffixCode)) + 1);
	                suffixCodeInc = suffixCodeInc.substring(1);
	                code = parentCode.concat(suffixCodeInc);
	            } else {
	                code = agent.getCode().concat("00001");
	            }
	        } else {
	        	//一级
	            if (StringUtils.isNotBlank(maxCode)) {
	                code = Integer.toString(Integer.parseInt(maxCode) + 1);
	            } else {
	                code = "10001";
	            }
	        }
	        return code;
	  }
	   
	   /**
	    * 取得一级代理商编码
	    * @return
	    */
       @Override
	   public String getFistLevelAgentCode(){
	        String code = null;
	        int parentId=0;
	        String maxCode =memberAgentsMapper.getMaxAgentsCodeBymMid(parentId);
	    	//一级
            if (StringUtils.isNotBlank(maxCode)) {
                code = Integer.toString(Integer.parseInt(maxCode) + 1);
            } else {
                code = "10001";
            }
	        return code;
	    }

	@Override
	public List<Map<String, Object>> getBusTOAgentCountInfo(Map<String, Object> param) {
		int count=0;
		List<Map<String, Object>> list=memberAgentsMapper.getBusTOAgentCountInfo(param);
		for (Map<String, Object> map : list) {
			map.put("count", count);
		}
				return list;
	}

	@Override
	public int updateByMid(MemberAgents record) {
		return memberAgentsMapper.updateByMid(record);
	}

	@Override
	public Integer getIsAgentEmpower(Map<String, Object> parma) {
		return memberAgentsMapper.getIsAgentEmpower(parma);
	}

	@Override
	public int getCountAgentsByParentId(int parentId) {
		return memberAgentsMapper.getCountAgentsByParentId(parentId);
	}

	@Override
	public List<Map<String, Object>> getVipPokelist(Map<String, Object> param) {
		return memberAgentsMapper.getVipPokelist(param);
	}

	@Override
	public List<Map<String, Object>> getVipOpenRoomlist(Map<String, Object> param) {
		List<Map<String, Object>> retuntList=memberAgentsMapper.getVipOpenRoomlist(param);
		if (retuntList.size()==1){
			for (Map<String, Object> map : retuntList) {
				Object kaifang= map.get("kaifang");
				Object payGold= map.get("payGold");
				if (kaifang.toString().equals("0")){
					map.put("kaifang", null);
				}
				
				if (payGold.toString().equals("0")){
					map.put("payGold", null);
				}
			}
		}
		return retuntList;
	}

}
