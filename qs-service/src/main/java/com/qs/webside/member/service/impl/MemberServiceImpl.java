package com.qs.webside.member.service.impl;


import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.AppConstants.MemcacheKeyPrefix;
import com.qs.common.util.MemcachedUtil;
import com.qs.webside.game.model.BaseParam;
import com.qs.webside.game.service.IBaseParamService;
import com.qs.webside.member.mapper.CommongameMapper;
import com.qs.webside.member.model.Commongame;
import com.qs.webside.member.service.IMemberService;



@Service("memberService")
public class MemberServiceImpl implements IMemberService {
	
    Logger log = Logger.getLogger(MemberServiceImpl.class);  

	
	@Autowired
	private CommongameMapper commongameMapper;
	
	@Resource
    private IBaseParamService baseParamService;


	@Override
	public Commongame findCommongameById(Integer id) {
		return commongameMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateCommongame(Commongame record) {
		BaseParam iosBaseParam=baseParamService.findBaseParamByCode(AppConstants.BaseParam.MEMCACHED_IP);
		if (iosBaseParam !=null ){
			try {
				MemcachedUtil.deleteMemcached(iosBaseParam.getValue(), MemcacheKeyPrefix.TMGMCOM+record.getMid());
			} catch (IOException e) {
				log.debug("into insertOpenRoom  MemcachedUtil IOException: "+e);
				e.printStackTrace();
			}
		}else {
			log.debug("into insertOpenRoom BaseParam is:: null ");
		}
		return commongameMapper.updateByPrimaryKeySelective(record);
	}

}
