package com.qznet.commons.upload.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CORSInterceptor implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
  	      setCORSHeader(response);

		 return true;
	    	    
    }
 
	private void setCORSHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的域都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
	}


    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception { 
		 
	 }
  

    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
        // TODO Auto-generated method stub  
    }  
    
}
