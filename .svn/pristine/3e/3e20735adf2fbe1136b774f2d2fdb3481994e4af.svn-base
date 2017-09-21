package com.qs.mainku.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.mainku.game.mapper.MemberFidesMapper;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.service.IMemberFidesService;
import com.qs.webside.member.model.Memberfides;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/27 11:05.
 * Description:人员基本表
 */
@Service
public class MemberFidesServiceImpl implements IMemberFidesService {

    @Resource
    private MemberFidesMapper memberFidesMapper;

    @Override
    public int deleteByPrimaryKey(Integer mid) {
        return memberFidesMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public int insert(MemberFides record) {
        return memberFidesMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberFides record) {
        return memberFidesMapper.insertSelective(record);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/30 10:03
     * @Description:根据mid缓存人员基本信息
     * @param mid
     * @return
     */
    @Override
    @Cacheable(value={CacheConstan.ACTI_MEMBERFIDES_INTEGRAL_RANKING_CACHE_NAME},key="#root.methodName+':'+#root.args[0]")
    public MemberFides selectByPrimaryKey(Integer mid) {
        return memberFidesMapper.selectByPrimaryKey(mid);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberFides record) {
        return memberFidesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberFides record) {
        return memberFidesMapper.updateByPrimaryKey(record);
    }

    @Override
    public MemberFides selectByMemberMid(Integer mid) {
        return memberFidesMapper.selectByMemberMid(mid);
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
    public List<MemberFides> queryListByNameAndMidAndSiteMid(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByNameAndMidAndSiteMid(parameters);
    }

    @Override
    public MemberFides queryListByMid(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByMid(parameters);
    }

    @Override
    public Integer selectAengtCountByInvite(Integer mid) {
        return memberFidesMapper.selectAengtCountByInvite(mid);
    }

    @Override
    public List<Map<String, Object>> queryUserAndGoldListByPage(Map<String, Object> parameters) {
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
    @Cacheable(value={CacheConstan.MEMBERFIDES_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
    public Memberfides findMemberfidesById(Integer mid) {
        //com.qs.webside.member.model.Memberfides
        MemberFides memberFides = memberFidesMapper.selectByPrimaryKey(mid);
        if (memberFides == null) return null;
        Memberfides memberfides = new Memberfides();
        memberfides.setIcon(memberFides.getIcon());
        memberfides.setMid(memberFides.getMid());
        memberfides.setName(memberFides.getName());
        memberfides.setSex(memberFides.getSex());
        memberfides.setBtd(memberFides.getBtd());
        memberfides.setCity(memberFides.getCity());
        memberfides.setGp(memberFides.getGp());
        memberfides.setInvite(memberFides.getInvite());
        memberfides.setSta(memberFides.getSta());
        memberfides.setMtime(memberFides.getMtime());
        memberfides.setEmail(memberFides.getEmail());
        memberfides.setBindtime(memberFides.getBindtime());
        memberfides.setTel(memberFides.getTel());
        memberfides.setRealname(memberFides.getRealname());
        memberfides.setIdentity(memberFides.getIdentity());
        memberfides.setYellowvip(memberFides.getYellowvip());
        memberfides.setIsyearvip(memberFides.getIsyearvip());
        memberfides.setPasswd(memberFides.getPasswd());
        memberfides.setSalt(memberFides.getSalt());
        memberfides.setLgtm(memberFides.getLgtm());
        memberfides.setLxlg(memberFides.getLxlg());
        memberfides.setQq(memberFides.getQq());
        memberfides.setAddress(memberFides.getAddress());
        memberfides.setRegip(memberFides.getRegip());
        memberfides.setActiveAssets(memberFides.getActiveAssets());
        return memberfides;
    }

    @Override
    public List<MemberFides> queryListByPage(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByPage(parameters);
    }

}
