package com.qs.webside.member.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.member.mapper.MemberFidesMapper;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.service.IMemberFidesService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/27.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MemberFidesServiceImpl implements IMemberFidesService {

    @Resource
    private MemberFidesMapper memberFidesMapper;


    @Override
    @CacheEvict(value={CacheConstan.MEMBERFIDES_CACHE_STORE_NAME},allEntries=true)
    public int deleteByPrimaryKey(Integer mid) {
        return memberFidesMapper.deleteByPrimaryKey(mid);
    }

    @Override
    @CacheEvict(value={CacheConstan.MEMBERFIDES_CACHE_STORE_NAME},allEntries=true)
    public int insert(MemberFides record) {
        return memberFidesMapper.insert(record);
    }

    @Override
    @CacheEvict(value={CacheConstan.MEMBERFIDES_CACHE_STORE_NAME},allEntries=true)
    public int insertSelective(MemberFides record) {
        return memberFidesMapper.insertSelective(record);
    }

    @Override
    public MemberFides selectByPrimaryKey(Integer mid) {
        return memberFidesMapper.selectByPrimaryKey(mid);
    }

    @Override
    @CacheEvict(value={CacheConstan.MEMBERFIDES_CACHE_STORE_NAME},allEntries=true)
    public int updateByPrimaryKeySelective(MemberFides record) {
        return memberFidesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @CacheEvict(value={CacheConstan.MEMBERFIDES_CACHE_STORE_NAME},allEntries=true)
    public int updateByPrimaryKey(MemberFides record) {
        return memberFidesMapper.updateByPrimaryKey(record);
    }

    @Override
    public MemberFides selectByMemberMid(Integer mid) {
        return memberFidesMapper.selectByMemberMid(mid);
    }

    @Override
    public List<MemberFides> queryListByPage(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByPage(parameters);
    }

    @Override
    public Map<String, Object> queryUserInfoByMid(Integer mid) {
        return memberFidesMapper.queryUserInfoByMid(mid);
    }

    @Override
    public Map<String, Object> queryUserInfoByMidAndSiteMid(Map<String, Object> midOrSiteMid) {
        return memberFidesMapper.queryUserInfoByMidAndSiteMid(midOrSiteMid);
    }

    @Override
    public List<MemberFides> queryListByUserName(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByNameAndMidAndSiteMid(parameters);
    }

    @Override
    public MemberFides queryListByMid(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByMid(parameters);
    }

    @Override
    public List<MemberFides> queryListByNameAndMidAndSiteMid(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByNameAndMidAndSiteMid(parameters);
    }

	@Override
	public Integer selectAengtCountByInvite(Integer mid) {
		
		return memberFidesMapper.selectAengtCountByInvite(mid);
	}

	@Override
	public List<Map<String, Object>> queryUserAndGoldListByPage(Map<String, Object> parameters){
		return memberFidesMapper.queryUserAndGoldListByPage(parameters);
	}

	@Override
	public List<MemberFides> selectAengtInviteInfo(Integer invite) {
		return memberFidesMapper.selectAengtInviteInfo(invite);
	}

    @Override
    public List<Map<String, Object>> selectAgentBindingUserList(Map<String, Object> parameters) {
        return memberFidesMapper.selectAgentBindingUserList(parameters);
    }

	@Override
	public int selectAgentClubGrade(Integer mid) {
		return memberFidesMapper.selectAgentClubGrade(mid);
	}

}
