package com.qs.pub.game.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.MemberInvite;

public interface MemberInviteMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberInvite record);

    int insertSelective(MemberInvite record);

    MemberInvite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberInvite record);

    int updateByPrimaryKey(MemberInvite record);
    
    /**
     *  根据邀请码查询某个邀请码是否已经被使用
     * @param inviteCode 邀请码
     * @return 返回0表示没有使用，大于0表示已经使用了。
     */
    int queryCountByInviteCode(String inviteCode);

    /**
     * 获取邀请码
     * @param parameters
     * @return
     */
    String getInviteCode(String sitemid);

    /**
     * //@Author:zun.wei, @Date:2017/4/1 9:51
     * 根据mid获取代理商信息和邀请码信息。
     * @param parameters mid
     * @return a.id, a.mid, a.invitecode, a.inviteurl, a.status,b.realname
     */
    Map<String, Object> getAgentAndInviteInfo(Map<String,Object> parameters);

    /**
     * //@Author:zun.wei, @Date:2017/4/1 11:03
     * 根据mid,inviteCode更新邀请代理商邀请码。
     * @param memberInvite  mid,inviteCode,inviteUrl
     * @return 更新的条数
     */
    int updateInviteCodeUrlByMidCode(MemberInvite memberInvite);


    /**
     * @Author:zun.wei , @Date:2017/5/3 15:31
     * @Description: 根据mid删除代理商邀请表信息
     * @param sitemid
     * @return
     */
    int deleteBySiteId(String sitemid);

    /**
     * @Author:zun.wei , @Date:2017/5/5 15:16
     * @Description:根据siteid查询邀请码对象
     * @param sitemid
     * @return
     */
    MemberInvite selectBySiteId(String sitemid);

}