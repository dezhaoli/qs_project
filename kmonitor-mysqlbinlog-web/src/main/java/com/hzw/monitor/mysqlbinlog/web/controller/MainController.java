package com.hzw.monitor.mysqlbinlog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gqliu
 * 2016年1月18日
 *
 */
@Controller
public class MainController extends BaseController {
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/mysqlbinlog/list.do";
	}
}
