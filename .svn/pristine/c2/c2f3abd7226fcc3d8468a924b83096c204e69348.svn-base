package com.qs.webside.sys.service.agent.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.member.model.MemberPayMent;

public interface IMemberPayMentService {
	int deleteByPrimaryKey(Integer pid);

    int insert(MemberPayMent record);

    int insertSelective(MemberPayMent record);

    MemberPayMent selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(MemberPayMent record);

    int updateByPrimaryKey(MemberPayMent record);
    
    public List<MemberPayMent> queryListByPage(Map<String, Object> parameter);
}
