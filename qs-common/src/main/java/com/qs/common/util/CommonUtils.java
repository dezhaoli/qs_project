package com.qs.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

/** 
 * @ClassName: MyStringUtils
 * @描述: 自定义的字符串工具类，
 *        尽可能多用{@link org.apache.commons.lang.StringUtils} or {@link org.springframework.util.StringUtils}
 * @author malp
 * @date 2015年5月19日 上午10:05:16 
 */
public class CommonUtils
{
	
	
  
	/**
	 * 
	 * @标题: getIpAddr
	 * @描述: (获取客户端IP地址)
	 * 
	 * @参数信息
	 * @param request
	 * @return
	 * 
	 * @返回类型 String
	 * @开发者 moyousheng
	 * @可能抛出异常
	 */
	public static String getIpAddr(HttpServletRequest request)
	{
		 String ip = request.getHeader("x-forwarded-for");                        
		   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		         ip = request.getHeader("Proxy-Client-IP");                       
		   }                                                                      
		   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		         ip = request.getHeader("WL-Proxy-Client-IP");                    
		   }                                                                      
		   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		         ip = request.getRemoteAddr();                                    
		   }                                                                      
		   return ip;                                                             

	}
	
	/**
	 * 取得当前cas的用户信息
	 * @return
	 */
	public static Map getCurrentUserMap(){
	   	  PrincipalCollection principals=SecurityUtils.getSubject().getPrincipals();
	      if(null!=principals){
	   	  List<Object> userList=principals.asList();
            Map userMap=(Map)userList.get(1);
		    return userMap;
          }else{
        	  return null;
          }
	}
	
	
	/**
	 * 判断对象是否为null,如果是赋空字符串
	 * @param obj
	 * @return
	 */
	public static String checkNull(Object obj) {
		if (obj == null) {
			return "";
		}
		String s;

		if (!(obj instanceof String)) {
			s = obj.toString();

		} else {
			s = (String) obj;
		}
		if (s.length() < 1) {
			return "";
		} else {
			return s.trim();
		}
	}
	
	/**
	 * 判断对象是否为null,如果是赋Integer 型的0
	 * @param obj
	 * @return
	 */
	public static Integer checkIntegerNull(Object obj){
		String s="";
		if(obj == null){
			return Integer.parseInt("0");
		}
		if (obj instanceof String) {
			s = obj.toString();
			if(s.equals("")){
				return Integer.parseInt("0");
			}

		}
		
		return Integer.parseInt(obj.toString());

	}
	
	/**
	 * 判断对象是否为null,如果是赋Integer 型的0
	 * @param obj
	 * @return
	 */
	public static Long checkLongNull(Object obj){
		String s="";
		if(obj == null){
			return Long.parseLong("0");
		}
		if (obj instanceof String) {
			s = obj.toString();
			if(s.equals("")){
				return Long.parseLong("0");
			}

		}
		
		return Long.parseLong(obj.toString());

	}

	/**
	 * 验证邮箱格式是否合法
	 * @param email
	 * @return
	 */
	public static boolean checkIsEmail(String email){
		Pattern pattern = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
	}
	
	/**
	 * 验证手机号码格式是否合法
	 * @param mobileNo
	 * @return
	 */
	public static boolean checkIsMobile(String mobileNo){
		Pattern pattern =  Pattern.compile("^[1][3,4,5,7,8][0-9]{9,10}$");
        Matcher matcher = pattern.matcher(mobileNo);
        if (matcher.matches()) {
            return true;
        }
        return false;
	}
	
	/**
	 * 生成长度为length的各个字符不重复的字符串，字符范围为26个英文字母。
	 * @param length
	 * @return
	 */
	public static String generateRandomStr(int length){
		char[] array = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
						'T','U','V','W','X','Y','Z'};
		Random rand = new Random();
		for (int i = 26; i > 0; i--) {
			int index = rand.nextInt(i);
			char tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		
		String randomStr = "";
		for(int j = 0;j < length; j ++){
			randomStr = randomStr + array[j];
		}
		return randomStr;
	}
	/**
	 * 微信性别转为游戏性别
	 * 微信普通用户性别，1为男性，2为女性
	 * 游戏性别 0男1女2未知
	 * @param sex
	 * @return
	 */
	public static byte getSex(Integer sex) {
		 byte bSex=1;
		 if(null!=sex){
			 if(sex>=1){
				 bSex=(byte)(sex-1); 
			 }
		 }else{
			 bSex=2;
		 }

		return bSex;
	}
		
	
}
