package com.qs.webside.sys.service.agent.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.common.constant.CommonContants;
import com.qs.webside.member.mapper.MemberFidesMapper;
import com.qs.webside.member.mapper.MemberPayMentMapper;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.MemberPayMent;
import com.qs.webside.sys.service.agent.service.IMemberAgentService;
import com.qs.webside.sys.service.agent.service.IMemberPayMentService;

@Service
public class MemberPayMentServiceImpl implements IMemberPayMentService {

	@Resource
	private MemberPayMentMapper memberPayMentMapper;
	
	@Resource
	private IMemberAgentService memberAgentService;
	
	@Resource
	private MemberFidesMapper memberFidesMapper;
	@Override
	public int deleteByPrimaryKey(Integer pid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MemberPayMent record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(MemberPayMent record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberPayMent selectByPrimaryKey(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MemberPayMent record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MemberPayMent record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据type 类型来查询1,2,3级团队
	 * 明细
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberPayMent> queryListByPage(Map<String, Object> parameter) {
		List<MemberPayMent> resultList=null;
		String mids="";
		if (parameter !=null){
			
			//一级团队
			if (parameter.get("type").equals(CommonContants.ONE_TEMPE)){
				resultList=memberPayMentMapper.queryListByPage(parameter);
				 if (resultList !=null) {
					 
					 for (MemberPayMent memberPayMent : resultList) {
						 Object fMid=memberPayMent.getFmid();
						 MemberFides memberFides=memberFidesMapper.selectByPrimaryKey(Integer.valueOf(fMid.toString()));
						 memberPayMent.setName(memberFides.getName());
					 }
				 }
			}
			
			//二级团队
			if (parameter.get("type").equals(CommonContants.TOW_TEMPE)){
				
				if(parameter.containsKey("mid")){
					Object mid=parameter.get("mid");
					
					List< MemberAgents> mbgList=memberAgentService.getMemberAgentsInfoByParentId(mid.toString());
					if (mbgList !=null) {
						
						for (MemberAgents memberAgents : mbgList) {
							mids=memberAgents.getMid().toString()+",";
						}
					}
					if (!"".equals(mids)){
						mids=mids.substring(0, mids.length()-1);
						parameter.put("mid", mids);
						resultList=memberPayMentMapper.queryListByPage(parameter);
						for (MemberPayMent memberPayMent : resultList) {
							Object fMid=memberPayMent.getFmid();
							MemberFides memberFides=memberFidesMapper.selectByPrimaryKey(Integer.valueOf(fMid.toString()));
							if (memberFides != null){
								
								memberPayMent.setName(memberFides.getName());
							}
						}
					}
					
				}
			}
			//三级团队
			if (parameter.get("type").equals(CommonContants.HTREE_TEMPE)){
				resultList=memberPayMentMapper.queryListByPage(parameter);
				 if (resultList !=null) {
					 
					 for (MemberPayMent memberPayMent : resultList) {
						 Object fMid=memberPayMent.getFmid();
						 MemberFides memberFides=memberFidesMapper.selectByPrimaryKey(Integer.valueOf(fMid.toString()));
						 memberPayMent.setName(memberFides.getName());
					 }
				 }
			}
			
		}
		return resultList;
	}

}
