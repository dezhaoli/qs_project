package com.qs.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	// 长日期格式
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static final Date getNowDate() {
			return new Date(System.currentTimeMillis());
      }
	/**
	 * 将日期格式的字符串转换为长整型
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static long convert2long(String date) {
		try {
			String format = DateUtil.TIME_FORMAT;
			if (StringUtils.isNotBlank(date)) {
				SimpleDateFormat sf = new SimpleDateFormat(format);
				return sf.parse(date).getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}
	
	public static Integer convertToInt(String date) {
		String dateStr="";
		try {
			String format = DateUtil.TIME_FORMAT;
			if (StringUtils.isNotBlank(date)) {
				SimpleDateFormat sf = new SimpleDateFormat(format);
				long l= sf.parse(date).getTime();
				dateStr=String.valueOf(l);
				if(null!=dateStr&&dateStr.length()>=10){
					dateStr=dateStr.substring(0,10);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int dateNum=0;
		if(null!=dateStr){
			dateNum=Integer.parseInt(dateStr);
		}
		
		return dateNum;
	}
	
	/**
	 * 把当前的时间转int
	 * @return
	 */
	public static Integer currentTimeToInt(){
		String currentTime=String.valueOf(System.currentTimeMillis());
		String time=currentTime.substring(0,10);
		return Integer.parseInt(time);
	}
	
	

	
	 /** 
	  * 将长整型数字转换为日期格式的字符串 
	  *  
	  * @param time 
	  * @param format 
	  * @return 
	  */  
	 public static String convert2String(long time) {  
	   String format = DateUtil.TIME_FORMAT;
	   if(time>0l) {  
	   if (StringUtils.isBlank(format))  
	    format =  DateUtil.TIME_FORMAT;  
	   SimpleDateFormat sf = new SimpleDateFormat(format);  
	   Date date = new Date(time);  
	   return sf.format(date);  
	  }  
	  return "";  
	 } 
	 

	public static void main(String[] args) throws Exception {

		System.out.println(DateUtil.convert2long("2017-02-13 00:00"));
		System.out.println(DateUtil.convert2String(1486742400));

	}

}
