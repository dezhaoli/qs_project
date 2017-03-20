package com.qs.webside.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;


public class RequestUtil {

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static boolean isJsonMediaType(HttpServletRequest request) {
		String contentType = request.getContentType();
		if (StringUtils.hasText(contentType)) {
			try {
				return MediaType.parseMediaType(contentType).includes(MediaType.APPLICATION_JSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
