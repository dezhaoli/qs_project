package com.qs.webside.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qs.common.constant.AppConstants;
import com.qs.common.util.DateUtil;

import me.hao0.common.security.MD5;



public class SignInterceptor implements HandlerInterceptor{

	Logger logger = Logger.getLogger(SignInterceptor.class);  
	
	public static final String SIGN_KEY = "D9%J@#$A$%#@JA&&635";
	public static final String SIGN ="sign";


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String sign = (String) request.getParameter("sign");
		String signCode = (String) request.getParameter("signCode");
		logger.debug("sign===::"+sign);
		logger.debug("signCode===::"+signCode);
		 String uri=request.getRequestURI();
		 logger.debug("uri===::"+uri);
		 
		 
		  if(StringUtils.isBlank(signCode)) {
				  Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.TITME_IS_NULL,AppConstants.Result.FAILURE_3);
		    	  this.responseMessage(response, responseMap);
		    	  return false;
			}
		 
			//默认两个小时过期
			long clientTime=Long.parseLong(signCode); 
			long serverTime=DateUtil.currentTimeToInt();
			long onehour = 7200; //两个小时的秒数
			 logger.debug("distime===::"+Math.abs(serverTime-clientTime));
			if(!(Math.abs(serverTime-clientTime)<onehour)){
				logger.debug("distime2===::"+Math.abs(serverTime-clientTime));
				  Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.TITME_IS_OVER,AppConstants.Result.FAILURE_3);
		    	  this.responseMessage(response, responseMap);
		    	  return false;
			
			}
			try {
				if (StringUtils.isBlank(sign)) {
					logger.debug("AppConstants.ResultMsg.SIGN_IS_NULL===::"+AppConstants.ResultMsg.SIGN_IS_NULL);
					Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.SIGN_IS_NULL,AppConstants.Result.FAILURE_3);
			    	 this.responseMessage(response, responseMap);
			    	 return false;
				}

	/*			String ps = getAllParameterExc(request, "sign") + "&key=" + SIGN_KEY;
				String digest = DigestUtils.md5Hex(ps).toUpperCase();*/
		        Map<String, String> dataMap = new HashMap<>();

		        Enumeration pNames=request.getParameterNames();
		        while(pNames.hasMoreElements()){
		            String name=(String)pNames.nextElement();
		            String value=request.getParameter(name);
		            logger.debug(name + "=" + value);
		            dataMap.put(name,value);
		        }
				Map<String, String> signingMap = filterSignParams(dataMap);
			    String expectSign = doSign(signingMap);
			      //  return expectSign.equals(actualSign);
			        
				logger.debug("sign===::"+sign);
				logger.debug(" expectSign===::"+expectSign);
				if (!expectSign.equals(sign)) {//签名失败
					logger.debug("AppConstants.ResultMsg.SIGN_IS_ERROR===::"+AppConstants.ResultMsg.SIGN_IS_ERROR);
					Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.SIGN_IS_ERROR,AppConstants.Result.FAILURE_3);
			    	this.responseMessage(response, responseMap);
			    	return false;
				}
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
				Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.SIGN_IS_ERROR,AppConstants.Result.FAILURE_3);
		    	this.responseMessage(response, responseMap);
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				
				e.printStackTrace();
				Map<String, Object> responseMap=this.getReturnData(AppConstants.ResultMsg.SYSTEM_ERROR,AppConstants.Result.FAILURE_3);
		    	this.responseMessage(response, responseMap);
				return false;
			}
		return true;
	}
	
	
    /**
     * 过滤签名参数(升序，排出空值，sign)
     * @param params 待校验参数
     * @return 过滤后的参数
     */
    protected Map<String, String> filterSignParams(final Map<String, ?> params) {
        Map<String, String> validParams = new TreeMap<>();

        for (Map.Entry<String, ?> param : params.entrySet()){
            if (SIGN.equals(param.getKey())
                    || param.getValue() == null
                    || "".equals(String.valueOf(param.getValue()))){
                continue;
            }
            validParams.put(param.getKey(), String.valueOf(param.getValue()));
        }

        return validParams;
    }
    
    /**
     * 请求前签名
     * @param params 参数(已经升序, 排出非空值和sign)
     * @return MD5的签名字符串(大写)
     */
    protected String doSign(final Map<String, String> params) {
        StringBuilder signing = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!StringUtils.isBlank(entry.getValue())){
                signing.append(entry.getKey()).append('=').append(this.transcoding(entry.getValue())).append("&");
            }
        }
        logger.debug("param:{}"+signing.toString());
        // append key
        signing.append("key=").append(SIGN_KEY);
        logger.debug("SIGN_KEY:{}"+signing.toString());
       
        // md5
        return MD5.generate(signing.toString(), false).toUpperCase();
    }




	/**
	 * 排序参数串 按字母排序
	 * 
	 * @param request
	 * @param excludeParameter 需要去除的参数
	 * @param setParams 需要设置进去的参数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getAllParameterExc(HttpServletRequest request, String excludeParameter) {
		Enumeration en = request.getParameterNames();
		List list = Collections.list(en);
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				String s1 = o1.toString();
				String s2 = o2.toString();
				return s1.compareTo(s2);
			}
		});
		String parameterName = null;
		StringBuilder buf = new StringBuilder();
		String valueArray[] = null;
		for (int i = 0; i < list.size(); i++) {
			parameterName = (String) list.get(i);
			if (excludeParameter.equals(parameterName))
				continue;
			valueArray = request.getParameterValues(parameterName);
			for (String value : valueArray) {
				if (buf.length() > 0)
					buf.append("&");

				buf.append(parameterName).append("=").append(transcoding(value));
			}
		}
		return buf.toString();
	}

	/**
	 * 转码
	 * 
	 * @param str
	 * @return
	 */
	private String transcoding(String str) {
		if (str == null || str.trim().length() < 1) {
			return "";
		}
		if (!isChineseChar(str)) {
			logger.debug("str===::"+str);
			try {
				return new String(str.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 检测是否包含中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChineseChar(String str) {
		boolean temp = false;
		Matcher m = Pattern.compile("[\u4e00-\u9fa5]").matcher(str);
		if (m.find()) {
			temp = true;
		}
		return temp;
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

	public static void main(String[] args) throws Exception {
		String phone = new String(Base64.encodeBase64("18503082273".getBytes()));
		System.out.println(phone);
		System.out.println(DigestUtils.md5Hex("getStockist.action?cityId=120100pageIndex=1&token=d72986764b5f47ee85927bedf0dcbe81&type=food&userType=3&key=192006250b4c09247ec02edce69f6a2d").toUpperCase());
		System.out.println(DigestUtils.md5Hex("phone=MTg1MDMwODIyNzM=&type=1&key=192006250b4c09247ec02edce69f6a2d").toUpperCase());
		System.out.println(DigestUtils.md5Hex("password=MjAyY2I5NjJhYzU5MDc1Yjk2NGIwNzE1MmQyMzRiNzA=&phone=MTg1MDMwODIyNzM=&rid=M1&validateCode=1111&key=192006250b4c09247ec02edce69f6a2d").toUpperCase());
		System.out.println(DigestUtils.md5Hex("password=MjAyY2I5NjJhYzU5MDc1Yjk2NGIwNzE1MmQyMzRiNzA=&userType=3&username=TTE=&key=192006250b4c09247ec02edce69f6a2d").toUpperCase());
		String a="channel=1000&deviceid=3986D549-E979-4379-8311-F5737C872953&gp=102&name=iphone&signCode=1489492617&site=2&sitemid=3986D549-E979-4379-8311-F5737C872953&key=DKFAJ@#$AM$%#@JA)&&635";
		String md5Str=MD5.generate(a, false).toUpperCase();
		System.out.println(md5Str);
	
	}
	

}
