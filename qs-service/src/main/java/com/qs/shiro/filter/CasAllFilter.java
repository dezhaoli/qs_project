package com.qs.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public class CasAllFilter extends AdviceFilter{
	
	public static final String casLogin = "cas";
	
	 @Value("${cas.server.url}")
	 private String casServer;
	 
	 @Value("${app.service.url}")
	 private String appServer;
	
	 
	 @Override
	protected String getName() {
		return "cas";
	}
	 
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		if(SecurityUtils.getSubject().isAuthenticated()){
			return true;
		}else{
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpresponse=(HttpServletResponse)response;
			String servletPath = httpRequest.getServletPath();
			
			httpresponse.sendRedirect(casServer+"/login?service="+appServer+"/login/index.do");
			return false;
		}

		
	}

}
