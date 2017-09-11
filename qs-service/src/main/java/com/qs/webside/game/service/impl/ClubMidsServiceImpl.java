package com.qs.webside.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.game.mapper.ClubMidsMapper;
import com.qs.webside.game.model.ClubMids;
import com.qs.webside.game.service.IClubMidsService;

@Service
public class ClubMidsServiceImpl implements IClubMidsService {

	@Resource
	private ClubMidsMapper clubMidsMapper;
	@Override
	public int insert(ClubMids record) {
		
		return clubMidsMapper.insert(record);
	}

	@Override
	@CacheEvict(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},key="'getClubInfoList:'+#record.mid")
	public int insertSelective(ClubMids record) {
		return clubMidsMapper.insertSelective(record);
	}

	@Override
	@CacheEvict(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},key="'getClubInfoList:'+#record.mid")
	public int deleteByPrimaryKey(ClubMids record) {
		return clubMidsMapper.deleteByPrimaryKey(record);
	}

	@Override
	public int selectCountClubMids(Map<String, Object> parma) {
		return clubMidsMapper.selectCountClubMids(parma);
	}

	@Override
	public List<ClubMids> getMidByClubMisList(int mid) {
		return clubMidsMapper.getMidByClubMisList(mid);
	}

	@Override
	public int deleteByPrimaryClubid(int clubid) {
		return clubMidsMapper.deleteByPrimaryClubid(clubid);
	}

	@Override
	public List<ClubMids> getMidByPrimaryClubidList(int clubid) {
		return clubMidsMapper.getMidByPrimaryClubidList(clubid);
	}

	@Override
	@CacheEvict(value = {CacheConstan.NEW_INTO_CLUB_ALL_NAME}, allEntries = true)
	public int deleteClubCacheAll() {
		return 0;
	}

}
