package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.ClubMidsMapper;
import com.qs.log.game.model.ClubMids;
import com.qs.log.game.service.IClubMidsService;

@Service
public class ClubMidsServiceImpl implements IClubMidsService {

	@Resource
	private ClubMidsMapper clubMidsMapper;
	@Override
	public int insert(ClubMids record) {
		
		return clubMidsMapper.insert(record);
	}

	@Override
	public int insertSelective(ClubMids record) {
		return clubMidsMapper.insertSelective(record);
	}

	@Override
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

}
