/*
 * 文件名：DateHandle.java	 
 * 时     间：上午10:05:10
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;

/** 
 * @ClassName: DateHandle 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author wangzhen
 * @date 2017年7月14日 上午10:05:10 
 */
public class DateHandle
{
	/**
	 * 
	 * @标题: dateHandle 
	 * @描述:  传入开始年月,结束年月，返回时间区间内所有年月份 字符串集合
	 *
	 * @参数信息
	 *    @param stime 开始 年-月（2017-01）
	 *    @param etime 结束 年-月（2017-05）
	 *    @return
	 *    @throws ParseException
	 *
	 * @返回类型 JSONArray
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	public static JSONArray dateHandle(String stime,String etime) throws ParseException{
		JSONArray list = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		/*Date sDate = sdf.parse(stime);
		Date eDate = sdf.parse(etime);
		
		int s = sDate.getMonth()+1;
		int e = eDate.getMonth()+1;
		do{
			if(s<10){
				list.add("0"+s);
			}else{
				list.add(""+s);
			}
			s++;
		}while(s<=e);*/
		
		Date sDate = new SimpleDateFormat("yyyy-MM").parse(stime);// 定义起始日期
		Date eDate = new SimpleDateFormat("yyyy-MM").parse(etime);// 定义结束日期
		Calendar cd = Calendar.getInstance();// 定义日期实例
		cd.setTime(sDate);// 设置日期起始时间
		while (cd.getTime().before(eDate))
		{// 判断是否到结束日期
			String DateStr = sdf.format(cd.getTime());
			list.add(DateStr);
			cd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
		}
		String DateStr = sdf.format(cd.getTime());
		list.add(DateStr);
		
		return list;
	}
}
