package com.qs.shiro.filter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * @ClassName: MyFormAuthenticationFilter
 * @Description:  基于Form表单的身份验证过滤器
 * @author moyousheng
 * @date: 2016-5-13 11:36:52
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{
	
	private static final Log log = LogFactory.getLog(MyFormAuthenticationFilter.class);
	
	/**
	 * 登录成功信息
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
		log.info("用户登录成功！token为：" + token);
		
		issueSuccessRedirect(request, response);
		return false;
	}
	
	/**
	 * 登录失败打印登录失败信息
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
            ServletRequest request, ServletResponse response) {
		log.info("用户登录失败！token为：" + token);
		e.printStackTrace();
		return false;
	}
}

