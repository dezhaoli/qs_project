package com.qznet.commons.upload.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by zun.wei on 2017/2/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class MyFileUploadUtil {


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

}
