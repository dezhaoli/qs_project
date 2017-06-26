package com.qs.common.util;

import me.hao0.common.security.MD5;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zun.wei on 2017/6/26 15:54.
 * Description:
 */
public class SignUtils {

    Logger logger = Logger.getLogger(SignUtils.class);

    public static final String SIGN ="sign";

    public static final String SIGN_KEY = "D9%J@#$A$%#@JA&&635";


    /**
     * 过滤签名参数(升序，排出空值，sign)
     * @param params 待校验参数
     * @return 过滤后的参数
     */
    public Map<String, String> filterSignParams(final Map<String, ?> params) {
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
    public String doSign(final Map<String, String> params) {
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
    private static boolean isChineseChar(String str) {
        boolean temp = false;
        Matcher m = Pattern.compile("[\u4e00-\u9fa5]").matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

}
