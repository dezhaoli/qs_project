/*
 * 文件名：KtrPasswordUtil.java	 
 * 时     间：下午2:50:23
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.util;

import java.math.BigInteger;

/**
 * @ClassName: KtrPasswordUtil
 * @描述: (这里用一句话描述这个类的作用)
 * @author qs
 * @date 2017年5月31日 下午2:50:23
 */
public class KtrPasswordUtil
{
	private static final int RADIX = 16;
	private static final String SEED = "0933910847463829827159347601486730416058";
	public static final String PASSWORD_ENCRYPTED_PREFIX = "Encrypted ";
	
	public static final String encryptPassword(String password)
	{
		if (password == null)
			return "";
		if (password.length() == 0)
			return "";
		
		BigInteger bi_passwd = new BigInteger(password.getBytes());
		
		BigInteger bi_r0 = new BigInteger(SEED);
		BigInteger bi_r1 = bi_r0.xor(bi_passwd);
		
		return bi_r1.toString(RADIX);
	}
	
	public static final String decryptPassword(String encrypted)
	{
		if (encrypted == null)
			return "";
		if (encrypted.length() == 0)
			return "";
		
		BigInteger bi_confuse = new BigInteger(SEED);
		
		try
		{
			BigInteger bi_r1 = new BigInteger(encrypted, RADIX);
			BigInteger bi_r0 = bi_r1.xor(bi_confuse);
			
			return new String(bi_r0.toByteArray());
		} catch (Exception e)
		{
			return "";
		}
	}
	
	public static void main(String[] args)
	{
		String str = decryptPassword("2be98afc86aa7f2e4cb79ce10be96aacc");
		System.out.println(str);
		System.out.println(encryptPassword("dev"));
	}
	
}
