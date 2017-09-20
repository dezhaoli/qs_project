package com.qs.pub.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * bbs项目中针对各种实体类对象与Map之间的转换、String的各种操作、路径检查纠正等，不建议写入其它代码
 * 
 * hjp 2013-12-2 
 */
public class MyUtil {
	
	/**
	 * 实体类转换为map，但是实体类中的8种基本类型的变量不建议转换成Key:value，请单独map.put(),
	 * 或将基本类型换成其包装类型
	 */
	public static <T> Map<String, Object> castMap(T entity)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] field = entity.getClass().getDeclaredFields();
		for (Field field2 : field) {
			String fieldName = field2.getName();
			Method m = entity.getClass().getDeclaredMethod(
					"get" + initcap(fieldName));
			if (m != null) {
				Object o = m.invoke(entity);
				if (o != null && m.getReturnType() != boolean.class) {
					map.put(fieldName, o);
				}
			}
		}
		return map;
	}
	
	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {
		if(str == null) return null;
		if(str == "") return "";
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	

}