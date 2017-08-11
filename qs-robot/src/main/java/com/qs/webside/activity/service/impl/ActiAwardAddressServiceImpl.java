package com.qs.webside.activity.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.activity.mapper.ActiAwardAddressMapper;
import com.qs.webside.activity.model.ActiAwardAddress;
import com.qs.webside.activity.service.IActiAwardAddressService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActiAwardAddressServiceImpl implements IActiAwardAddressService {

	@Resource
	private ActiAwardAddressMapper  actiAwardAddressMapper ;

	/**
	 * @Author:zun.wei , @Date:2017/6/30 9:16
	 * @Description:清除缓存地址，根据mid清除
	 * @return
	 */
	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME}, allEntries = true)
	public int deleteByPrimaryKey(Integer id) {
		return actiAwardAddressMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Author:zun.wei , @Date:2017/6/30 9:16
	 * @Description:清除缓存地址，根据mid清除
	 * @param record
	 * @return
	 */
	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int insert(ActiAwardAddress record) {
		return actiAwardAddressMapper.insert(record);
	}

	/**
	 * @Author:zun.wei , @Date:2017/6/30 9:16
	 * @Description:清除缓存地址，根据mid清除
	 * @param record
	 * @return
	 */
	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int insertSelective(ActiAwardAddress record) {
		return actiAwardAddressMapper.insertSelective(record);
	}

	@Override
	public ActiAwardAddress selectByPrimaryKey(Integer id) {
		return actiAwardAddressMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Author:zun.wei , @Date:2017/6/30 9:16
	 * @Description:清除缓存地址，根据mid清除
	 * @param record
	 * @return
	 */
	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int updateByPrimaryKeySelective(ActiAwardAddress record) {
		return actiAwardAddressMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @Author:zun.wei , @Date:2017/6/30 9:16
	 * @Description:清除缓存地址，根据mid清除
	 * @param record
	 * @return
	 */
	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int updateByPrimaryKey(ActiAwardAddress record) {
		return actiAwardAddressMapper.updateByPrimaryKey(record);
	}

	/**
	 * @Author:zun.wei , @Date:2017/6/30 9:16
	 * @Description:缓存地址，根据mid进行缓存
	 * @param mid
	 * @return
	 */
	@Override
	@Cacheable(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME}, key = "#root.methodName+':'+#root.args[0]")
	public ActiAwardAddress selectObjectByMidKey(Integer mid) {
		return actiAwardAddressMapper.selectByMidKey(mid);
	}

	
}
