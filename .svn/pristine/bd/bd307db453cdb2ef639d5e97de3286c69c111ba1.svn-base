package com.apusic.ausp.cas.authentication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.springframework.stereotype.Component;

import com.apusic.ausp.cas.model.User;

/**
 * 自定义的返回给客户端相关信息
 */
@Component(value="attributeRepository")
public class UserStubPersonAttributeDao extends StubPersonAttributeDao {
	@Resource
	private UserDaoJdbc userDaoJdbc;
	
	@Override
	public IPersonAttributes getPerson(String uid) {
		Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();
		try {
			User user = userDaoJdbc.getByUsername(uid);
			attributes.put("userId", Collections.singletonList((Object)user.getUserId()));
			attributes.put("phone", Collections.singletonList((Object)user.getPhone()));
			attributes.put("email", Collections.singletonList((Object)user.getEmail()));
			if(!StringUtils.isBlank(user.getRealName())){
			    attributes.put("realName", Collections.singletonList((Object)URLEncoder.encode(user.getRealName(), "UTF-8")));
			}else{
				 attributes.put("realName",Collections.singletonList((Object)user.getRealName()));
			}
		    //保存日志
			//userDaoJdbc.saveLoginLogByThread(user);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new AttributeNamedPersonImpl(attributes);
	}
}