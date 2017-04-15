package com.qs.webside.interceptor;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qs.common.constant.CommonContants;




public class TokenInterceptor  implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	

 

    @Override
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
    	    	
    	Date dt=new Date();
    	//用d.getHour()可以获取当前小时数。
    	int hour=dt.getHours();
    	if(hour>=1&&hour<=7){
    		Map<String, Object> responseMap=this.getReturnData();
    	   this.responseMessage(response, responseMap);
    	   return false;
    	}
    	
    	  return true;
    	

    }
    
	/**
	 * 返回值
	 * @param data
	 * @param resultFlag
	 * @return
	 */
	public  Map<String, Object> getReturnData(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put(CommonContants.SUCCESS, Boolean.FALSE);
		map.put(CommonContants.MESSAGE, "当前时间系统停止支付,请联系管理员！");
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
