package com.qs.log.game.service;

import java.util.List;
import java.util.Map;

import com.qs.log.game.model.ClubMember;

public interface IClubMemberService {
	int insert(ClubMember record);

	int insertSelective(ClubMember record);
	
	 /**
	  * 获取俱乐部成员    
	  * @param param
	  * @return
	  * @author:zyy
	  * @time:2017年8月28日
	  */
   List<Map<String,Object>> getClubMemberInfo (Map<String, Object> param);
   
   /**
    * 根据cmid mid 获取当前用户信息
    * @param clubMember
    * @return
    * @author:zyy
    * @time:2017年8月28日
    */
   ClubMember selectClubMember (ClubMember clubMember);
   
   /**
    * 用于判断是否邀请人数是否达上线（500）成员
    * @param parm
    * @return
    * @author:zyy
    * @time:2017年8月28日
    */
   int selectCountClubMember(int mid);
   
   
   /**
    * 删除俱乐部成员
    * @param clubMember
    * @return
    * @author:zyy
    * @time:2017年8月29日
    */
   int deleteByPrimaryKey (ClubMember clubMember);
   
   /**
    * 获取当前用户所对应的俱乐部列表
    * @param clubMember
    * @return
    * @author:zyy
    * @time:2017年8月31日
    */
   List<ClubMember> getByPrimaryKeyList (int mid);
}
