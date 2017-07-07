package com.qs.webside.interceptor;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qs.common.constant.CommonContants;
import com.qs.common.ip2region.DbSearcher;
import com.qs.common.util.CommonUtils;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.UserService;




public class TokenInterceptor  implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	@Autowired
	private DbSearcher ipSearcher;
	
	@Autowired
	private UserService userService;

 

    @Override
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
    	    	
    	Date dt=new Date();
    	//用d.getHour()可以获取当前小时数。
    	int hour=dt.getHours();
    	if(hour>=0&&hour<=7){
    		Map<String, Object> responseMap=this.getReturnData("当前时间系统停止使用,请联系管理员！");
    	   this.responseMessage(response, responseMap);
    	   return false;
    	}
    	
		String ip =CommonUtils.getIpAddr(request);
		String region = ipSearcher.memorySearch(ip).getRegion();
		String[] regions = StringUtils.split(region, '|');
		String cityName="";
		cityName=regions[3];
		String province=regions[2];
		
		
	  String uri=CommonUtils.checkNull(request.getRequestURI());
	  logger.debug("uri==============::"+uri);
	  logger.debug("ip==============::"+ip);
	  
	  if(uri.contains("confirmPay.html")||uri.contains("saveSimplePay.html")||uri.contains("batchPay.html")||uri.endsWith("Pay.html")){
		  
	
		boolean returnFlag=false;
		//hour>=20&&hour<=24||
		if(hour>=0&&hour<=8){
    		Map<String, Object> responseMap=this.getReturnData("当前时间系统停止支付，请在工作时间支付！");
    	   this.responseMessage(response, responseMap);
    	   return false;
     }
		
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		UserEntity userInfo =userService.findById(userEntity.getId());
		
		if("深圳市".equals(cityName)||"娄底市".equals(cityName)||"江西省".equals(province)){
			returnFlag=true;
		}
		if(!returnFlag){
			Map<String, Object> responseMap=this.getReturnData("不在工作区中...,请联系管理员！");
			  this.responseMessage(response, responseMap);
	    	  return false;
		}
		

		String userIp=CommonUtils.checkNull(userInfo.getUserInfo().getAddress());
		returnFlag=false;
		if(userIp.equals(ip)){
			returnFlag=true;
		}
		
		if(!returnFlag){
			Map<String, Object> responseMap=this.getReturnData("不在白名单中...,请联系管理员！");
			 this.responseMessage(response, responseMap);
	    	 return false;
		}
		
		
	  }
		
		
    	 return true;
    	

    }
    
	/**
	 * 返回值
	 * @param data
	 * @param resultFlag
	 * @return
	 */
	public  Map<String, Object> getReturnData(String msg){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put(CommonContants.SUCCESS, Boolean.FALSE);
		map.put(CommonContants.MESSAGE, msg);
		return map;
		
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

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
