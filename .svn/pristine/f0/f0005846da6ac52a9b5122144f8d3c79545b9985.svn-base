package com.qs.webside.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qs.common.constant.AppConstants;
import com.qs.common.util.CommonUtils;

import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;


/**
 * @ClassName: AccessTokenFilter
 * @描述:token过滤器
 * @author moyousheng
 * @date 2016年7月14日 下午6:55:42
 */
public class AccessTokenFilter extends OncePerRequestFilter {


    private static final Logger logger = LoggerFactory.getLogger(AccessTokenFilter.class);

	@Autowired
	private MemcachedClient memcachedClient;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        String sesskey = (String) request.getParameter("sesskey");
        logger.debug("sesskey===::"+sesskey);
        
    	if(StringUtils.isBlank(sesskey)){
    		Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.PARA_SESSKEY_ISNULL,AppConstants.Result.FAILURE_3);
    	   this.responseMessage(response, responseMap);
    	   return;
    	}
        AccessToken token=ContextUtil.getAccessTokenInfo(sesskey);
    	if(CommonUtils.checkIntegerNull(token.getMid())==0){
    		Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.NO_MID,AppConstants.Result.FAILURE_3);
    	   this.responseMessage(response, responseMap);
    	   return ;
    	}
        
    	String tokenKey=AppConstants.MemcacheKeyPrefix.SESSKEY+token.getMid();
    	
    	String tokenValue="";
		try {
			tokenValue = memcachedClient.get(tokenKey);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
    	 logger.debug("tokenValue===::"+tokenValue);
    	if(StringUtils.isBlank(tokenValue)){
    		Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.NO_SESSKEY,AppConstants.Result.FAILURE_3);
    	   this.responseMessage(response, responseMap);
    	   return ;
    	}
	
    	 
		filterChain.doFilter(request, response);
	}
	
	
	/**
	 * app返回值
	 * @param data
	 * @param resultFlag
	 * @return
	 */
	public  Map<String, Object> getReturnData(Object data,int resultFlag){
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("svflag",resultFlag);
		//列表展示数据
		parameters.put("data", data);
		return parameters;
		
	}

    private void responseMessage(HttpServletResponse response, Map<String,Object> responseMap) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer os = null;
        try {
            os = response.getWriter();
            os.write(new ObjectMapper().writeValueAsString(responseMap));
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
	
	private void setResponseInfo(ServletResponse response,Map<String,Object> mp){
		try{
			//response.setContentType(CONTENT_TYPE);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out =response.getWriter();
			out.print(getJson(mp));
			out.flush();
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getJson(Object value) {
		String s = "";
		try {
			ObjectMapper mapper=null;
			if (mapper == null) {
				mapper = new ObjectMapper();
			}
			s = mapper.writeValueAsString(value);
		} catch (Exception e) {	
		}
		return s;
	}
	private void setCORSHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的域都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
	}

}
