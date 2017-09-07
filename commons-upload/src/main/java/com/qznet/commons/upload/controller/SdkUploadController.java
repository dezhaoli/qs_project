package com.qznet.commons.upload.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.ListFolderRequest;
import com.qcloud.cos.request.StatFolderRequest;
import com.qcloud.cos.request.UpdateFolderRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.qcloud.cos.sign.Sign;
import com.qznet.commons.upload.util.ResConfigurer;
import com.qznet.commons.upload.util.SdkFileUploadUtil;
import com.qznet.commons.upload.util.UploadMessage;

/**
 * 
 * Created by zsf @date 创建时间：2017年9月5日 下午2:39:15 Description: 腾讯云，对象存储sdk实现文件上传
 */
@Controller
public class SdkUploadController {
	// https://console.qcloud.com/cos
	private String appId = ResConfigurer.getContextProperty("sdk.appId").toString();;// 用户相关信息

	private String secretId = ResConfigurer.getContextProperty("sdk.secretId").toString();;// 用户相关信息

	private String secretKey = ResConfigurer.getContextProperty("sdk.secretKey").toString();;// 用户相关信息

	private String region = ResConfigurer.getContextProperty("sdk.region").toString();;// 地域

	private String bucketName = ResConfigurer.getContextProperty("sdk.bucketName").toString();;// 存储桶
	
	private String realmName = ResConfigurer.getContextProperty("sdk.realmName").toString();;// 域名

	/**
	 * 
	 * @author zsf @date 创建时间：2017年9月5日 下午2:43:17
	 * @Description: 处理图片上传到腾讯云
	 * @param 图片的二进制，请求
	 * @return图片对象
	 */
	@RequestMapping(value = "/img/upload.action", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UploadMessage uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "type", required = false) String type, HttpServletRequest request,
			HttpServletResponse response) {
		ClientConfig clientConfig = new ClientConfig(); // 初始化客户端配置
		clientConfig.setRegion(region); // 设置bucket所在的区域，比如广州(gz), 天津(tj)
		Credentials cred = new Credentials(Long.parseLong(appId), secretId, secretKey); // 初始化秘钥信息
		COSClient cosClient = new COSClient(clientConfig, cred); // 初始化cosClient

		
		String fileName = file.getOriginalFilename(); // 獲取文件名
		String folder = new SimpleDateFormat("yyyyMMdd").format(new Date()); // 创建文件目录（按照年月日分文件夹）
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".")); // 获取文件后缀
		String path = request.getSession().getServletContext().getRealPath("/"); // 当前路径
		// 生成新的文件名
		if (fileName == null || "".equals(fileName)) {
			UUID uuid = UUID.randomUUID(); // 使用UUID策略生成图片
			fileName = uuid.toString().replace("-", "").toUpperCase();
		}
		File dir = new File(path + "iamges"); // 文件夹
		File dirs = new File(path + "iamges" + File.separator + folder);
		String tempfileName = folder + UUID.randomUUID() + fileSuffix; // 临时文件名
		File newfile = new File(path + "iamges" + File.separator + folder + File.separator + tempfileName);
		try {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!dirs.exists()) {
			dirs.mkdirs();
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

			long expired = System.currentTimeMillis() / 1000 + 600; // 给账号授权
																	// 授权时间
			String cosFolderPath = "/upload/img/" + folder + "/"; // 1. 生成目录,
																	// 目录名为当前时间
			String signStr = Sign.getPeriodEffectiveSign(bucketName, cosFolderPath, cred, expired);
			CreateFolderRequest createFolderRequest = new CreateFolderRequest(bucketName, cosFolderPath);// 账号授权
																											// 授权创建目录
			String createFolderRet = cosClient.createFolder(createFolderRequest);
			// 1. 上传文件(将本地文件上传到COS)
			// 将本地的local_file上传到bucket下的根分区下,并命名为cosFilePath 默认不覆盖, 如果cos上已有文件,
			// 则返回错误
			String cosFilePath = cosFolderPath + tempfileName;
			String localFilePath1 = path + "iamges" + File.separator + folder + File.separator + tempfileName;
			createFolderRequest = new CreateFolderRequest(bucketName, cosFilePath); // 账号授权
																					// 授权上传文件，文件目录
			UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosFilePath, localFilePath1);
			uploadFileRequest.setEnableShaDigest(false);
			String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
			String url=realmName+cosFilePath;
			return new UploadMessage(tempfileName.replace("\\", "/"), url, file.getOriginalFilename(), file.getSize());
		} catch (IOException e) {
			e.getStackTrace();
		} catch (AbstractCosException e) {
			e.getStackTrace();
		} finally {
			// 关闭释放资源
			cosClient.shutdown();
			SdkFileUploadUtil.delDir(dir);
		}
		return null;
	}

}
