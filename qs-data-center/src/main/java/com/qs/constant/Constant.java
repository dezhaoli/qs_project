/*
 * 文件名：Constan.java	 
 * 时     间：下午7:13:00
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.constant;

import org.apache.commons.lang3.StringUtils;

/** 
 * @ClassName: Constan 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月1日 下午7:13:00 
 */
public class Constant
{
	 public static final String DATA_CENTER_GAME_TYPE = "dataCenterGameType:";
	 public static final String DATA_CENTER_GAME_CODE = "dataCenterGameCode:";
	 public static final String USER_ROLE_ID = "userRoleId";
	 
	 public static String getDataCenterBusinessGameType(String gameType){
		if(gameType != null){
			if(gameType.equals("kx_beard")){
				return "kx_beard_pub";
			}else if(gameType.equals("gd_majiang") || gameType.equals("sc_majiang")|| gameType.equals("qian_majiang")){
				return "gd_majiang_pub";
			}else if(gameType.equals("majiangjx")){
				return "majiangjx";
			}else if(gameType.equals("jx_majiang")){
				return "jx_majiang_pub";
			}else if(gameType.equals("bull") || gameType.equals("majiang")||gameType.equals("mushi") || gameType.equals("runfast")){
				return "runfast";
			}
		}
		 return gameType;
	 }
	 
	 public static String getDataCenterBusinessGameCode(String gameCode){
		 int tempGameCode=0;
			if(!StringUtils.isBlank(gameCode)){
              if(Integer.parseInt(gameCode)<10){
				   tempGameCode=Integer.parseInt(gameCode)-1;
              } else if(Integer.parseInt(gameCode)>100){
				   tempGameCode=Integer.parseInt(gameCode)-100;
              }else{
             	 tempGameCode=Integer.parseInt(gameCode);
              }
			}
			 return  tempGameCode+"";
	 }
	 
	 //数据权限管理 变量
	 public static class DataPrivilege{
		 
		 public static final String IF_ADMIN = "ifAdmin";//是否管理员
		 public static final String IF_LEADER = "ifLeader";//是否领导
		 public static final String IF_BUSINESS = "ifBusiness";//是否商务
	 }
	
	 
}
