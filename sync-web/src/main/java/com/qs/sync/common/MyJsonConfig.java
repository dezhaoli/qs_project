package com.qs.sync.common;


import net.sf.json.JSONNull;
import net.sf.json.JsonConfig;

import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

/**
 * Integer,String类型转化json默认值为null
 * @author moyousheng
 *
 */
public class MyJsonConfig {

	private static JsonConfig jsonConfig = new JsonConfig();

	public static JsonConfig getJsonConfig() {

		if(jsonConfig==null){
			jsonConfig = new JsonConfig();
			jsonConfig.registerDefaultValueProcessor(String.class,
					new DefaultValueProcessor() {
						public Object getDefaultValue(Class arg0) {
							if (JSONUtils.isString(arg0)) {
								return null;
							}
							return JSONNull.getInstance();
						}
					});

			jsonConfig.registerDefaultValueProcessor(Integer.class,
					new DefaultValueProcessor() {
						public Object getDefaultValue(Class arg0) {
							if (Integer.class.isInstance(arg0)) {
								return null;
							}
							return JSONNull.getInstance();
						}
					});
			
			return jsonConfig;
			
		}
		return jsonConfig;
	}

	static {
		jsonConfig.registerDefaultValueProcessor(String.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class arg0) {
						if (JSONUtils.isString(arg0)) {
							return null;
						}
						return JSONNull.getInstance();
					}
				});

		jsonConfig.registerDefaultValueProcessor(Integer.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class arg0) {
						if (Integer.class.isInstance(arg0)) {
							return null;
						}
						return JSONNull.getInstance();
					}
				});
	}

}