package com.qznet.commons.upload.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.ListFolderRequest;
import com.qcloud.cos.request.StatFolderRequest;
import com.qcloud.cos.request.UpdateFolderRequest;
import com.qcloud.cos.sign.Credentials;
import com.qcloud.cos.sign.Sign;

public class SdkFileUploadUtil {
	/**
	 * 
	 * @author zsf @date 创建时间：2017年9月5日 上午10:29:48
	 * @Description: 保存图片到本地
	 * @param 文件名
	 *            , 当前路径 ,代表当前文件的二进制
	 * @return 成功返回true 失败返回
	 * @throws IOException
	 */
	public static File saveFileToLocal(String fileName, String path, MultipartFile file) throws IOException {

		// 创建文件目录（按照年月日分文件夹）
		String folder = DateUtil.getDateStr();
		// 获取文件后缀
		String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
		// 当前路径

		// 生成新的文件名
		if (fileName == null || "".equals(fileName)) {
			UUID uuid = UUID.randomUUID(); // 使用UUID策略生成图片
			fileName = uuid.toString().replace("-", "").toUpperCase();
		}
		// 文件夹
		File dir = new File(path + "iamges" + File.separator + folder);
		// 临时文件名
		String tempfileName = folder + UUID.randomUUID() + fileSuffix;

		File newfile = new File(path + "iamges" + File.separator + folder + File.separator + tempfileName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!newfile.exists()) {
			newfile.createNewFile();
		}

		InputStream is = new ByteArrayInputStream(file.getBytes());
		byte[] readBytes = new byte[4 * 1024];
		int len = -1;
		FileOutputStream fos = new FileOutputStream(newfile);
		while ((len = is.read(readBytes)) != -1) {
			fos.write(readBytes, 0, len);
		}
		is.close();
		fos.close();
		return newfile;

	}

	/**
	 * 
	 * @author zsf @date 创建时间：2017年9月5日 上午11:25:01
	 * @Description: 递归删除目录下的所有文件及子目录下所有文件
	 * @param 将要删除的文件目录
	 * @return
	 */
	public static void delDir(File f) {
		// 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
		if (f.isDirectory()) {
			// 获取子文件/目录
			File[] subFiles = f.listFiles();
			// 遍历该目录
			for (File subFile : subFiles) {
				// 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除. 如果这是一个非空目录, 多次
				// 递归清空其内容后再删除
				delDir(subFile);
			}
		}
		// 删除空目录或文件
		f.delete();
	}

	/**
	 * 
	 * @author zsf @date 创建时间：2017年9月5日 下午4:13:07
	 * @Description: 初始化COS客户端配置
	 * @param 地域，用户相关信息
	 * @return
	 */
	public static COSClient initCosClient(String region, String appId, String secretId, String secretKey) {
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setRegion(region);
		// 初始化秘钥信息
		Credentials cred = new Credentials(Long.parseLong(appId), secretId, secretKey);
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);
		return cosClient;
	}
	



}
