package com.qs.common.util;

import java.util.Random;

/**
 * 
 * @ClassName: RandomUtil
 * @Description: 生成随机字符串的工具类
 * @author gaogang
 * @date 2016年7月12日 下午4:25:59
 *
 */
public class RandomUtil {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 每次生成的len位数都不相同
	 * 
	 * @param param
	 * @return 定长的数字
	 */
	public static int getNotSimple(int[] param, int len) {
		Random rand = new Random();
		for (int i = param.length; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = param[index];
			param[index] = param[i - 1];
			param[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++) {
			result = result * 10 + param[i];
		}
		return result;
	}

	/**
	 * 随机生成验证码（数字+字母）
	 * @param len 邀请码长度
	 * @return
	 */
	public static String generateRandomStr(int len) {
		//字符源，可以根据需要删减
		String generateSource = "23456789abcdefghgklmnpqrstuvwxyz";//去掉1和i ，0和o
		String rtnStr = "";
		for (int i = 0; i < len; i++) {
			//循环随机获得当次字符，并移走选出的字符
			String nowStr = String.valueOf(generateSource.charAt(
					(int) Math.floor(Math.random() * generateSource.length())));
			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
		}
		return rtnStr;
	}

	/**
	 *  产生6位纯数字随机邀请码 ，没有四连号，也不能产生123456，可以123451
	 * @return 随机字符串
	 */
	public static String generateInviteCode() {
		int length = 6; //随机字符串长度
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		char cc[] = new char[6];
		while (true) {
			char c1 = NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length()));
			char c2 = NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length()));
			char c3 = NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length()));
			char c4 = NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length()));
			char c5 = NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length()));
			char c6 = NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length()));

			if (c1 == c2 && c1 == c3 && c1 == c4) {//四连号

			} else if (c2 == c3 && c2 == c4 && c2 == c5) {//四连号

			} else if (c3 == c4 && c3 == c5 && c3 == c6) {//四连号

			} else if (c1 < c2 && c2 < c3 && c3 < c4 && c4 < c5 && c5 < c6) {//123456

			} else if (c1 > c2 && c2 > c3 && c3 > c4 && c4 > c5 && c5 > c6) {//654321

			} else {
				cc[0] = c1;
				cc[1] = c2;
				cc[2] = c3;
				cc[3] = c4;
				cc[4] = c5;
				cc[5] = c6;
				sb.append(cc);
				break;
			}
		}
		return sb.toString();
	}


}
