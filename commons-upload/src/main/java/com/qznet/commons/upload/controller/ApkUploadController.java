package com.qznet.commons.upload.controller;

import com.qznet.commons.upload.util.FileUploadUtil;
import com.qznet.commons.upload.util.UploadMessage;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zun.wei on 2017/2/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
public class ApkUploadController {

    @RequestMapping(value = "/apk/upload.action", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UploadMessage upload(@RequestParam("file") MultipartFile file,
                                @RequestParam(value = "type", required = false) String type,
                                HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authentication");

        UploadMessage message =null;
        try {
            if (!checkApkFile(file.getOriginalFilename())) {
                UploadMessage uploadMessage = new UploadMessage();
                uploadMessage.setErrorType(true);
                return uploadMessage;
            }
            message= FileUploadUtil.saveFile(file,file.getBytes(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
   // @RequestMapping(value = "/img/upload.action", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UploadMessage uploadImg(@RequestParam("file") MultipartFile file,
                                @RequestParam(value = "type", required = false) String type,
                                HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authentication");

        UploadMessage message =null;
        try {
            /*if (!checkApkFile(file.getOriginalFilename())) {
                UploadMessage uploadMessage = new UploadMessage();
                uploadMessage.setErrorType(true);
                return uploadMessage;
            }*/
            message= FileUploadUtil.saveFileImg(file,file.getBytes(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
    
    private  boolean checkApkFile(String fileName){
        boolean flag = false;
        //String suffixList="xls,xlsx,jpg,gif,png,ico,bmp,jpeg";
        String suffixList="apk";
        //获取文件后缀
        String suffix=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());

        if(suffixList.contains(suffix.trim().toLowerCase())){
            flag=true;
        }
        return flag;
    }

}
