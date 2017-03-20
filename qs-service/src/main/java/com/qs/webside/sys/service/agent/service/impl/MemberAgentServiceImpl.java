package com.qs.webside.sys.service.agent.service.impl;

import com.qs.common.util.ID;
import com.qs.webside.member.mapper.MemberAgentsMapper;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.sys.service.agent.service.IMemberAgentService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MemberAgentServiceImpl implements IMemberAgentService {

    @Resource
    private MemberAgentsMapper memberAgentsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberAgentsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MemberAgents record) {
        return memberAgentsMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberAgents record) {
        return memberAgentsMapper.insertSelective(record);
    }

    @Override
    public MemberAgents selectByPrimaryKey(Integer id) {
        return memberAgentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberAgents record) {
        return memberAgentsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberAgents record) {
        return memberAgentsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MemberAgents> queryListByPage(Map<String, Object> parameters) {
        return memberAgentsMapper.queryListByPage(parameters);
    }

    @Override
    public int editScale(Integer id, Integer mid, Byte scale, String remark) {
        MemberAgents memberAgents = new MemberAgents();
        memberAgents.setId(id);
        memberAgents.setMid(mid);
        memberAgents.setRemark(remark);
        memberAgents.setScale(scale);
        return memberAgentsMapper.updateByPrimaryKeySelective(memberAgents);
    }

    @Override
    public int resetPwd(Integer id) {
        MemberAgents memberAgents = new MemberAgents();
        /*int max = 99999999;
        int min = 10000000;
        Random random = new Random();
        int randomNum = random.nextInt(max)%(max-min+1) + min;*/
        String randomNum = "66668888";
        String uuId = ID.generateUUID();
        String passwordCryto = new Md5Hash(randomNum,uuId,2).toBase64();
        //memberAgents.setSystempasswd(randomNum + "");//明文密码
        memberAgents.setId(id);
        memberAgents.setPasswd(passwordCryto);//加密的密码
        memberAgents.setSalt(uuId);//盐值
        return memberAgentsMapper.updateByPrimaryKeySelective(memberAgents);
    }



}
