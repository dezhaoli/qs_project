package com.qs.pub.game.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qs.common.constant.CacheConstan;
import com.qs.pub.game.mapper.DictMapper;
import com.qs.pub.game.model.Dict;
import com.qs.pub.game.service.IDictService;


@Service
public class DictServiceImpl 
		implements IDictService {

	@Autowired
	private DictMapper dictMapper;


	@Override
	public Dict findByName(String name) {
		return null;
	}

	@Override
	public Dict findById(Integer id) {
		return dictMapper.selectByPrimaryKey(id);
	}

	@Override
	@CacheEvict(value={CacheConstan.DICT_CACHE_STORE_NAME},allEntries=true)
	public int update(Dict record) {
		return dictMapper.updateByPrimaryKey(record);
	}

	@Override
	@CacheEvict(value={CacheConstan.DICT_CACHE_STORE_NAME},allEntries=true)
	public int deleteBatchById(List<Integer> ids) {
		return 0;
	}

	@Override
	@CacheEvict(value={CacheConstan.DICT_CACHE_STORE_NAME},allEntries=true)
	public int deleteById(Integer id) {
		return dictMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Dict> queryListByPage(Map<String, Object> parameter) {
		return dictMapper.queryListAll(parameter);
	}

	@Override
	@CacheEvict(value={CacheConstan.DICT_CACHE_STORE_NAME},allEntries=true)
	public int insert(Dict record) {
		return dictMapper.insert(record);
	}

	@Override
	@Cacheable(value={CacheConstan.DICT_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public List<Dict> findDictList(String code) {
		return dictMapper.findList(code);
	}


	@Override
	@CacheEvict(value={CacheConstan.DICT_CACHE_STORE_NAME},allEntries=true)
	public int updateByName(Dict record) {
		return dictMapper.updateByName(record);
	}
	@Override
	@CacheEvict(value={CacheConstan.DICT_CACHE_STORE_NAME},allEntries=true)
	public List<Dict> selectByName() {
		return dictMapper.selectByName();
	}

	@Override
	public int updateStatus(Dict record) {
		return dictMapper.updateStatus(record);
	}

}
