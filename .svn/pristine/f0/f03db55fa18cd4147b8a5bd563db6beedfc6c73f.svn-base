package com.qs.webside.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据list 对象分页工具
 * @author zyy
 *
 */
public class PagesUtil {

	/**
	  * 
	  * @param pageNo 当前页码
	  * @param pageSize 页数
	  * @param list  所有集合
	  * @return
	  * @throws Exception
	  */
	 public static  List<Object> page(int pageNo,int pageSize,List<?> list) throws Exception{
	  List<Object> result = new ArrayList<Object>();
	  if(list != null && list.size() > 0){
	   int allCount = list.size();
	   int pageCount = (allCount + pageSize-1) / pageSize;
	   if(pageNo >= pageCount){
	    pageNo = pageCount;
	   }
	   int start = (pageNo-1) * pageSize;
	   int end = pageNo * pageSize;
	   if(end >= allCount){
	    end = allCount;
	   }
	   for(int i = start; i < end; i ++){
	    result.add(list.get(i));
	   }
	  }
	  return (result != null && result.size() > 0) ? result : null;
	 }
}
