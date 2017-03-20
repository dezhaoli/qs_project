package com.qs.webside.util;

import org.apache.shiro.codec.Base64; 
import org.apache.shiro.codec.H64; 
import org.apache.shiro.codec.Hex; 
import org.apache.shiro.crypto.AesCipherService; 
import org.apache.shiro.crypto.SecureRandomNumberGenerator; 
import org.apache.shiro.crypto.hash.Md5Hash;

import com.qs.common.util.crypto.MD5;
import com.qs.webside.sys.model.UserEntity;

import java.security.Key; 

/**
 * 
 * @ClassName: EndecryptUtils
 * @Description: 加密工具类
 * @author gaogang
 * @date 2016年7月12日 下午4:24:19
 *
 */
public class EndecryptUtils {
	/** 
     * base64进制加密 
     * 
     * @param password 
     * @return 
     */ 
    public static String encrytBase64(String password) { 
        byte[] bytes = password.getBytes(); 
        return Base64.encodeToString(bytes); 
    } 
    /** 
     * base64进制解密 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptBase64(String cipherText) { 
        return Base64.decodeToString(cipherText); 
    } 
    /** 
     * 16进制加密 
     * 
     * @param password 
     * @return 
     */ 
    public static String encrytHex(String password) { 
        byte[] bytes = password.getBytes(); 
        return Hex.encodeToString(bytes); 
    } 
    /** 
     * 16进制解密 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptHex(String cipherText) { 
        return new String(Hex.decode(cipherText)); 
    } 
    
    public static String generateKey() 
    { 
        AesCipherService aesCipherService=new AesCipherService(); 
        Key key=aesCipherService.generateNewKey(); 
        return Base64.encodeToString(key.getEncoded()); 
    } 
    /** 
     * 对密码进行md5加密,并返回密文和salt，包含在User对象中 
     * @param username 用户名 
     * @param password 密码
     * @param hashIterations 迭代次数 
     * @return UserEntity对象，包含密文和salt 
     */ 
    public static UserEntity md5Password(String username,String password,int hashIterations){ 
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator(); 
        String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
        //组合username,两次迭代，对密码进行加密 
        //username+salt=salt
        String password_cryto = new Md5Hash(password,salt,hashIterations).toBase64(); 
        UserEntity user=new UserEntity(); 
        user.setPassword(password_cryto); 
        user.setCredentialsSalt(salt); 
        user.setUserName(username); 
        return user; 
    } 
    
    
    
    public static void main(String[] args) { 
        String password = "admin"; 
        String cipherText = encrytHex(password); 
        System.out.println(password + "hex加密之后的密文是：" + cipherText); 
        String decrptPassword=decryptHex(cipherText); 
        System.out.println(cipherText + "hex解密之后的密码是：" + decrptPassword); 
        String cipherText_base64 = encrytBase64(password); 
        System.out.println(password + "base64加密之后的密文是：" + cipherText_base64); 
        String decrptPassword_base64=decryptBase64(cipherText_base64); 
        System.out.println(cipherText_base64 + "base64解密之后的密码是：" + decrptPassword_base64); 
        String h64=  H64.encodeToString(password.getBytes()); 
        System.out.println(h64); 
        String salt="7road"; 
        String cipherText_md5= new Md5Hash(password,salt,4).toHex(); 
        System.out.println(password+"通过md5加密之后的密文是："+cipherText_md5); 
        System.out.println(generateKey()); 
        System.out.println("=========================================================="); 
        AesCipherService aesCipherService=new AesCipherService(); 
        aesCipherService.setKeySize(128); 
        Key key=aesCipherService.generateNewKey(); 
        String aes_cipherText= aesCipherService.encrypt(password.getBytes(),key.getEncoded()).toHex(); 
        System.out.println(password+" aes加密的密文是："+aes_cipherText); 
        String aes_mingwen=new String(aesCipherService.decrypt(Hex.decode(aes_cipherText),key.getEncoded()).getBytes()); 
        System.out.println(aes_cipherText+" aes解密的明文是："+aes_mingwen); 
        String username="admin@qs.com";
        String salt2="6ca87d4b7820b5fd90bd821b8af1ecbc";
        String password_cryto = new Md5Hash("123456",salt2,2).toBase64(); 
        System.out.println("password_cryto=========================================================="+password_cryto); 
    	String md5Pwd=new Md5Hash("123456",null,0).toString();
        System.out.println("md5Pwd=========================================================="+md5Pwd); 
		String mypassword=MD5.encrypt("123456");
	    System.out.println("mypassword=========================================================="+mypassword); 
    	String md5Pwd2=new Md5Hash(md5Pwd,null,0).toBase64();
    	 System.out.println("md5Pwd2=========================================================="+md5Pwd2); 
    	

        
        
    } 
}
