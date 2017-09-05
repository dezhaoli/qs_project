package com.qs.webside.game.mapper;

import java.util.List;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.ClubMember;

public interface ClubMemberMapper extends IBaseMapper {
    int insert(ClubMember record);

    int insertSelective(ClubMember record);
    
    /**
     * 获取当前用户所对应的俱乐部列表
     * @param clubMember
     * @return
     * @author:zyy
     * @time:2017年8月31日
     */
    List<ClubMember> getByPrimaryKeyList (int mid);
    
    /**
     * 退出俱乐部成员
     * @param record
     * @return
     * @author:zyy
     * @time:2017年9月1日
     */
    int deleteByPrimaryKey(ClubMember record);
    
    /**
     * 获取当前用户是否存在
     * @param clubMember
     * @return
     * @author:zyy
     * @time:2017年9月1日
     */
    ClubMember getClubMemberInfo(ClubMember clubMember) ;
}