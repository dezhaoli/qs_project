package com.qs.common.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.sun.tools.classfile.Annotation.element_value;


public  class ContextUtil extends ServletRequestUtils{
	
	 /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(ContextUtil.class);
    
	/**
	 * 取得当前的用户信息
	 * @return
	 *//*
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
	
	
	public static String getCurrentOrgCode(){
	    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    String orgCode="";
	    String accessToken=request.getHeader(BaseConstants.AccessToken.ACCESSTOKEN);
	    AccessToken tokenObject=getAccessTokenInfo(accessToken);
	    if(null!=tokenObject){
	      orgCode=tokenObject.getOrgCode();
	    }
	    if(StringUtils.isBlank(orgCode)){
		    Map userSessionMap=ContextUtil.getCurrentUserMap();
			if(null!=userSessionMap){
				orgCode=(String)userSessionMap.get(BaseConstants.User.ORGCODE);
	   	    }
	  	}
	    return orgCode;
	}

	*//**
	 * 获取当前用户id
	 * @return
	 *//*
	public static String getCurrentUserId(){
	    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    String userId=request.getHeader(BaseConstants.AccessToken.OPENID);
	  	if(StringUtils.isBlank(userId)){
    		Map userSessionMap=ContextUtil.getCurrentUserMap();
    		if(null!=userSessionMap){
       	     userId=(String)userSessionMap.get(BaseConstants.User.USERID);
       	    }
    	}
	    return userId;
	}
	*//**
	 * 根据accessToken获取相关用户信息
	 * @param accessToken
	 * @return
	 *//*
	public static AccessToken getAccessTokenInfo(String accessToken){
	    AccessToken accessTokenInfo = new AccessToken();
	    if(null==accessToken||"".equals(accessToken)){
	        return accessTokenInfo;
	    }
	
	    String[] infoArray = accessToken.split("-");
	    for(int i = 0;i < infoArray.length;i++){
	        if(i == 0){
	            accessTokenInfo.setAccessToken(infoArray[i]);
	        }else if(i == 1){
	            accessTokenInfo.setUserId(infoArray[i]);
	        }else if(i == 2){
	            accessTokenInfo.setOrgCode(infoArray[i]);
	        }
	    }
	    return accessTokenInfo;
	}*/
	
	/**
	 * 根据类型获取对象实例，该类型应该由spring加载。
	 * @param clazz bean类型
	 * @return
	 */
	public static <T> T getBeanByType(Class<T> clazz){
		try{
			ApplicationContext ctx = ContextLoader
					.getCurrentWebApplicationContext();
			return ctx.getBean(clazz);
		} catch (Exception e){
			try{
				return RequestContextUtils.getWebApplicationContext(
						getServletRequestAttributes().getRequest()).getBean(
						clazz);
			} catch (Exception e1){
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	private static ServletRequestAttributes getServletRequestAttributes(){
		return (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
	}
	
	
	public static HttpServletRequest getRequest(){
		return getServletRequestAttributes().getRequest();
	}
	
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
	
	

	

}
