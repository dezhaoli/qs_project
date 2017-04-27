package com.qs.webside.member.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.webside.member.mapper.CommongameMapper;
import com.qs.webside.member.model.Commongame;
import com.qs.webside.member.service.IMemberService;



@Service("memberService")
public class MemberServiceImpl implements IMemberService {
	
    Logger log = Logger.getLogger(MemberServiceImpl.class);  

	
	@Autowired
	private CommongameMapper commongameMapper;


	@Override
	public Commongame findCommongameById(Integer id) {
		return commongameMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateCommongame(Commongame record) {
		return commongameMapper.updateByPrimaryKeySelective(record);
	}

}
