package com.qs.shiro.realm;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;

import com.qs.common.util.CommonUtils;

/**
 * Shiro授权Realm
 * 
 * @author moyousheng
 * 
 */
public class ShiroCasRealm extends CasRealm {
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	    Map userMap=CommonUtils.getCurrentUserMap();
	    String userId=(String)userMap.get("id");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //用户所属的角色
        //authorizationInfo.setRoles(permissionService.findRolesByUserId(userId));
        //用户拥有的权限
         //authorizationInfo.setStringPermissions(permissionService.findPermissionsByUserId(userId)); 
        return authorizationInfo;
	}
	

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
	   //cas 登录
      if(token instanceof CasToken){
		
        CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        }
        String ticket = (String)casToken.getCredentials();
        if (StringUtils.isBlank(ticket)) {
            return null;
        }
        TicketValidator ticketValidator = ensureTicketValidator();
        try {
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String userName = casPrincipal.getName();
            Map map=new HashMap();
            PrincipalCollection principalCollection = new SimplePrincipalCollection(map, getName());
	        
            return new SimpleAuthenticationInfo(principalCollection, ticket);
        } catch (TicketValidationException e) { 
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
	  }else{  
		  
		    String username = (String)token.getPrincipal();  //得到用户名  
	        String password = new String((char[])token.getCredentials()); //得到密码  
	        return new SimpleAuthenticationInfo(username, password, getName());  
	  }
      
        
    }
    
    
	

}
