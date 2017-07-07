package com.qs.webside.member.service.impl;

import com.qs.common.constant.AppConstants;
import com.qs.webside.member.mapper.MemberFidesMapper;
import com.qs.webside.member.mapper.MemberWhiteListMapper;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.MemberWhiteList;
import com.qs.webside.member.service.IMemberWhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/27.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MemberWhiteListServicerImpl implements IMemberWhiteListService {

    @Resource
    private MemberWhiteListMapper memberWhiteListMapper;
    @Resource
    private MemberFidesMapper memberFidesMapper;
    
	@Autowired
	private RedisTemplate redisTemplate;

    @Override
    public List<MemberWhiteList> queryListByPage(Map<String, Object> parameters) {
        return memberWhiteListMapper.queryListByPage(parameters);
    }

    @Override
    public int deleteById(Integer id) {
        return memberWhiteListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int add(MemberWhiteList record) {
        return memberWhiteListMapper.insert(record);
    }

    @Override
    public int addSelective(MemberWhiteList record) {
        MemberFides memberFides = memberFidesMapper.selectByMemberMid(record.getMid());
        MemberWhiteList mwl = this.selectByMid(record.getMid());
        int result = 0;
        if (null != memberFides && memberFides.getMid() > 0 && mwl == null) {
            MemberWhiteList memberWhiteList = new MemberWhiteList();
            memberWhiteList.setIcon(memberFides.getIcon());
            memberWhiteList.setName(memberFides.getName());
            memberWhiteList.setMid(memberFides.getMid());
            return memberWhiteListMapper.insertSelective(memberWhiteList);
        }
        if (mwl != null) {
            return -2;
        }
        return result;
    }
    
    @Override
    public void setMemberWhiteDeviceCache() {
	    String devicelist="";
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
            devicelist= sb.toString().substring(0, sb.toString().length() - 1);
        }
        
		String memberWhiteDeviceCacheKey=AppConstants.RedisKeyPrefix.MEMBER_WHITE_DEVICE_CACHE;
		redisTemplate.opsForValue().set(memberWhiteDeviceCacheKey, devicelist);    
}

    @Override
    public int updateTakeEffectAll(int type) {
        return memberWhiteListMapper.updateTakeEffectAll(type);
    }

    @Override
    public MemberWhiteList selectById(Integer id) {
        return memberWhiteListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(MemberWhiteList record) {
        return memberWhiteListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(MemberWhiteList record) {
        return memberWhiteListMapper.updateByPrimaryKey(record);
    }

    @Override
    public MemberWhiteList selectByMid(Integer mid) {
        return memberWhiteListMapper.selectByMid(mid);
    }

    @Override
    public int updateTakeEffectById(Integer id) {
        return memberWhiteListMapper.updateTakeEffectById(id);
    }

    @Override
    public int updateTakeEffectByAct(Integer id) {
        return memberWhiteListMapper.updateTakeEffectActById(id);
    }

}
