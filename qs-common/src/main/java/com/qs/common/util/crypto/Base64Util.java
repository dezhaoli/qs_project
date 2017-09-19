package com.qs.common.util.crypto;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;

/**
 * base64 编码、解码util
 * 
 * @author zyy
 *
 */
public class Base64Util {
    /**
     * 将 s 进行 BASE64 编码
     *
     * @return String
     * @author zyy
     * 
     */
    public static String encode(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new sun.misc.BASE64Encoder().encode(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @return String
     * @author zyy
     * 
     */
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 
     * @return void
     * @author zyy
     * 
     */
    public static void main(String[] args) {
        System.out.println(Base64Util.encode("张三"));
        System.out.println(Base64Util.decode("5byg5LiJ"));

    }

}