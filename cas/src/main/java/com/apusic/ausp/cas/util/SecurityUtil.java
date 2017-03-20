package com.apusic.ausp.cas.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public  class SecurityUtil {
	
	 public static final String PASSWORD_SALT = "ausp";
	 
	/**
	 * 加密密码
	 * @param pasword
	 * @param salt
	 * @return
	 */
	public static String encodePassword(String password,String salt){
		String password_cipherText = 
				new SimpleHash("md5",password,ByteSource.Util.bytes(salt),2).toHex();
		return password_cipherText;
	}
	
	/**
	 * 是否是有效的密码
	 * @param encPass
	 * @param rawPass
	 * @param salt
	 * @return
	 */
    public static boolean isPasswordValid(String encPass, String rawPass, String salt) {
        String pass1 = "" + encPass;
        String pass2 = encodePassword(rawPass, salt);

        return pass1.equals(pass2);
    }
	
	public static void main(String[] args) {
		System.out.println("ddd==="+encodePassword("13510508616123456","ausp"));
	}
}
