import java.io.File;
import java.io.FileFilter;
import java.math.BigInteger;

/*
 * 文件名：Test.java	 
 * 时     间：下午2:38:05
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
/**
 * @ClassName: Test
 * @描述: (这里用一句话描述这个类的作用)
 * @author qs
 * @date 2017年5月31日 下午2:38:05
 */
public class Test
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
		/*String str = decryptPassword("2be98afc86aa7f2e4cb79ce10be96aacc");
		System.out.println(str);
		System.out.println(encryptPassword("dev"));*/
		
		File file = new File("D:\\dev\\dataintegration_gezila.com");
		File[] files = file.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				return pathname.getName().endsWith(".ktr");
			}
		});
		for(File f:files){
			String path = f.getAbsolutePath();
		}
	}
	
	
}
