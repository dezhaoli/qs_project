package com.qznet.commons.upload.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO: DOCUMENT ME!
 * 
 * @author ys
 * @date 2015年4月10日
 */
public class FileUploadUtil {

	public static String getFileNameByFilePath(String filePath) {
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
	}

	public static String readBase64StringByInputStream(InputStream sis) throws Exception {
		byte[] writeBytes = readBytesByInputStream(sis);
		if (writeBytes != null) {
			return Base64.encodeBase64String(writeBytes);
		}
		return "";
	}

	public static String readStringByInputStream(InputStream sis) throws Exception {
		byte[] writeBytes = readBytesByInputStream(sis);
		if (writeBytes != null) {
			return new String(writeBytes, "UTF-8");
		} else {
			return "";
		}
	}

	public static byte[] readBytesByInputStream(InputStream sis) throws Exception {
		byte[] writeBytes = null;

		byte[] bytes = new byte[4 * 1024];
		int len = -1;
		ByteArrayOutputStream osOutputStream = new ByteArrayOutputStream();
		while ((len = sis.read(bytes)) != -1) {
			osOutputStream.write(bytes, 0, len);
		}
		sis.close();
		writeBytes = osOutputStream.toByteArray();
		osOutputStream.flush();
		osOutputStream.close();
		return writeBytes;
	}

	public static UploadMessage saveFile(byte[] bytes, String fileName) throws Exception {
		// String path = request.getServletContext().getRealPath("/version");//
		// 获取文件要保存的目录
		
		String rootpath = ResConfigurer.getContextProperty("path").toString();
		String realPath = ResConfigurer.getContextProperty("imgServer").toString();
		String prefix = fileName.substring(fileName.lastIndexOf("."));// 文件后缀名
		String dateStr =  DateUtil.getDateStr();
		File path = new File(rootpath);
		if (fileName == null || "".equals(fileName)) {
			UUID uuid = UUID.randomUUID(); // 使用UUID策略生成图片
			fileName = uuid.toString().replace("-", "").toUpperCase();
		}
		String tempfileName = DateUtil.getDateStr() + File.separator + UUID.randomUUID() + prefix;

		File file = new File(rootpath + File.separator + tempfileName);
		File subpath = new File(path + File.separator + dateStr);
		// byte[] bytes = Base64.decodeBase64(fileBase64);
		if (!path.exists()) {
			path.mkdirs();
		}
		if (!subpath.exists()) {
			subpath.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		InputStream is = new ByteArrayInputStream(bytes);
		byte[] readBytes = new byte[4 * 1024];
		int len = -1;
		FileOutputStream fos = new FileOutputStream(file);
		while ((len = is.read(readBytes)) != -1) {
			fos.write(readBytes, 0, len);
		}
		is.close();
		fos.close();
		return  new UploadMessage(tempfileName.replace("\\", "/"),realPath + tempfileName.replace("\\", "/")) ;
	}

	public static UploadMessage saveFile(MultipartFile multipartFile,byte[] bytes, String fileName) throws Exception {
		// String path = request.getServletContext().getRealPath("/version");//
		// 获取文件要保存的目录

		String rootpath = ResConfigurer.getContextProperty("apkPath").toString();
		String realPath = ResConfigurer.getContextProperty("apkServer").toString();
		String prefix = fileName.substring(fileName.lastIndexOf("."));// 文件后缀名
		String dateStr =  DateUtil.getDateStr();
		File path = new File(rootpath);
		if (fileName == null || "".equals(fileName)) {
			UUID uuid = UUID.randomUUID(); // 使用UUID策略生成图片
			fileName = uuid.toString().replace("-", "").toUpperCase();
		}
		String tempfileName = DateUtil.getDateStr() + File.separator + UUID.randomUUID() + prefix;

		File file = new File(rootpath + File.separator + tempfileName);
		File subpath = new File(path + File.separator + dateStr);
		// byte[] bytes = Base64.decodeBase64(fileBase64);
		if (!path.exists()) {
			path.mkdirs();
		}
		if (!subpath.exists()) {
			subpath.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		InputStream is = new ByteArrayInputStream(bytes);
		byte[] readBytes = new byte[4 * 1024];
		int len = -1;
		FileOutputStream fos = new FileOutputStream(file);
		while ((len = is.read(readBytes)) != -1) {
			fos.write(readBytes, 0, len);
		}
		is.close();
		fos.close();
		return  new UploadMessage(tempfileName.replace("\\", "/"),realPath + tempfileName.replace("\\", "/")
				,multipartFile.getOriginalFilename(),multipartFile.getSize()) ;
	}

}
