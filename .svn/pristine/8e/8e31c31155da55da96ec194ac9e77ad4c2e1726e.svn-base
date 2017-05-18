package com.qs.webside.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/18 17:05.
 * Description:
 */
public abstract class ContextUtil extends ServletRequestUtils {

    private static final Log log = LogFactory.getLog(ContextUtil.class);


    /**
     * 根据accessToken获取相关用户信息
     */
    public static AccessToken getAccessTokenInfo(String sesskey){
        AccessToken accessTokenInfo = new AccessToken();
        if(StringUtils.isBlank(sesskey)){
            return accessTokenInfo;
        }
        if(StringUtils.isBlank(sesskey)){
            return accessTokenInfo;
        }
        String[] infoArray = sesskey.split("-");
        if(null!=infoArray&&infoArray.length>0){
            accessTokenInfo.setMid(Integer.parseInt(infoArray[0]));
            accessTokenInfo.setGb(Integer.parseInt(infoArray[2]));
        }
        return accessTokenInfo;
    }

    public static Map<String, Object> objectToMap(Map<String, Object> map, Object obj){
        if(obj == null){
            return map;
        }

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                if(!"serialVersionUID".equals(field.getName())){
                    map.put(field.getName(), field.get(obj));
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

}
