package com.qs.webside.sys.service.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.game.mapper.MobileVersionMapper;
import com.qs.webside.game.model.MobileVersion;
import com.qs.webside.member.mapper.MemberFidesMapper;
import com.qs.webside.member.mapper.MemberWhiteListMapper;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.MemberWhiteList;
import com.qs.webside.sys.service.game.service.IMobileVersionService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 游戏版本实现类
 * Created by zun.wei on 2017/2/24.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MobileVersionServiceImpl implements IMobileVersionService {

    @Resource
    private MobileVersionMapper mobileVersionMapper;
    @Resource
    private MemberWhiteListMapper memberWhiteListMapper;
    @Resource
    private MemberFidesMapper memberFidesMapper;

    @Override
    @CacheEvict(value={CacheConstan.MOBLIE_VERSION_CACHE_STORE_NAME},allEntries=true)
    public int deleteById(Integer id) {
        return mobileVersionMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value={CacheConstan.MOBLIE_VERSION_CACHE_STORE_NAME},allEntries=true)
    public int add(MobileVersion record) {
        getWhiteListMember(record);
        return mobileVersionMapper.insert(record);
    }

    @Override
    @CacheEvict(value={CacheConstan.MOBLIE_VERSION_CACHE_STORE_NAME},allEntries=true)
    public int addSelective(MobileVersion record) {
        getWhiteListMember(record);
        return mobileVersionMapper.insertSelective(record);
    }

    @Override
    public MobileVersion selectById(Integer id) {
        return mobileVersionMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value={CacheConstan.MOBLIE_VERSION_CACHE_STORE_NAME},allEntries=true)
    public int updateByIdSelective(MobileVersion record) {
        getWhiteListMember(record);
        return mobileVersionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(MobileVersion record) {
        getWhiteListMember(record);
        return mobileVersionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MobileVersion> queryListByPage(Map<String, Object> parameters) {
        return mobileVersionMapper.queryListByPage(parameters);
    }

    private void getWhiteListMember(MobileVersion record) {
        if (null != record && null != record.getDevicelistTest() && "1".equals(record.getDevicelistTest())) {
            List<MemberWhiteList> list = memberWhiteListMapper.queryListByPage(new HashMap<String, Object>());
            if (list != null && list.size() > 0) {
                StringBuffer sb = new StringBuffer(" ");
                Iterator<MemberWhiteList> iterator = list.iterator();
                while (iterator.hasNext()) {
                    MemberWhiteList m = iterator.next();
                    if (1 == m.getTestType()) {
                        MemberFides memberFides = memberFidesMapper.selectByMemberMid(m.getMid());
                        if (memberFides != null && memberFides.getPasswd() != null
                                && !"".equals(memberFides.getPasswd())) {
                            sb.append(memberFides.getPasswd()).append(",");
                        }
                    }
                }
                record.setDevicelistTest(sb.toString().substring(0, sb.toString().length() - 1));
            }
        } else if (null != record && null != record.getDevicelistTest() && !"1".equals(record.getDevicelistTest())){
            record.setDevicelistTest("");
        }
    }

}
