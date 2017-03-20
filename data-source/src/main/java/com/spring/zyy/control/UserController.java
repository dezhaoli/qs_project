package com.spring.zyy.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.zyy.datasources.DataSourceInstances;
import com.spring.zyy.datasources.DataSourceSwitch;
import com.spring.zyy.pojo.User;
import com.spring.zyy.service.impl.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {
  private static final Logger log=Logger.getLogger(UserController.class);
	@Resource
	private IUserService userService;
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		log.info("into toIndex…… start!");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		log.info("into toIndex…… end!");
		return "index";
	}
	
	
	@RequestMapping("/data")
	public String testDataSources(HttpServletRequest request,Model model){
		log.info("into toIndex…… start!");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = null;
				user=this.userService.getUserById(userId);
		model.addAttribute("user", user);
		
		
		log.info("test1这是一个测试,获取默认数据连接data1:"+user.toString()); 
			user=this.userService.getUserById(userId);
			
	        DataSourceSwitch.setDataSourceType(DataSourceInstances.IN);  
	        user=this.userService.getUserById(userId);
	     log.info("test2这是一个测试,获取数据连接data1:"+user.toString());  
	     
	        DataSourceSwitch.setDataSourceType(DataSourceInstances.OUT);  
	        user=this.userService.getUserById(userId);
	      log.info("test3这是一个测试,获取数据连接data2:"+user.toString());  
		
		
		log.info("into toIndex…… end!");
		return "index";
	}
}