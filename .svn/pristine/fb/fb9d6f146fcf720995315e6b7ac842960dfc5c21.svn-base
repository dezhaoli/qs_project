package com.qznet.commons.upload.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.qznet.commons.upload.util.MyFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qznet.commons.upload.util.FileUploadUtil;
import com.qznet.commons.upload.util.ResConfigurer;
import com.qznet.commons.upload.util.UploadMessage;

@Controller
public class HomeController {

	@Autowired
	private Gson gson;

	@RequestMapping(value = "upload.action", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UploadMessage upload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "type", required = false) String type,
			 HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Authentication");

		UploadMessage message =null;
		try {
			message= FileUploadUtil.saveFile(file.getBytes(), file.getOriginalFilename());
			//MyFileUploadUtil.saveFile(file.getBytes(), file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
//System.out.println("message = " + message);
        return message;
	}

	@RequestMapping(value = "headImgUpload.action", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UploadMessage uploadHeadImg(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "userType", required = false) String userType
			) {
		UploadMessage message = new UploadMessage();
		try {
			message =  FileUploadUtil.saveFile(file.getBytes(), file.getOriginalFilename());
			if(message!=null){
				String appUrl = ResConfigurer.getContextProperty("appUrl").toString();
				RestTemplate rest= new RestTemplate() ;
				String tmsg =rest.getForObject(appUrl+"headImgUpload.action?token={token}&userType={userType}&path={path}", String.class, token, userType,message.getPath());
				UploadMessage temp=gson.fromJson(tmsg, UploadMessage.class);
				message.setState(temp.getState());
				message.setMessage(temp.getMessage());
//				message.setState(Integer.parseInt(state));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}



	@RequestMapping(value = "goUpload.html")
	public String toUpload(Model model,@RequestParam(value = "type", required = false,defaultValue="0") int type){
		model.addAttribute("type", type);
		return "upload";
	}
}
