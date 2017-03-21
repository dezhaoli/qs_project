package com.qs.webside.agent.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.exception.AgentemException;
import com.qs.pub.game.model.Area;
import com.qs.webside.agent.model.UserAgentRequest;
import com.qs.webside.member.mapper.MemberInviteMapper;
import com.qs.webside.sys.service.agent.service.IMemberAreaService;

@Controller
@RequestMapping(value = "/user")
public class UserDataController {

	@Autowired
	private IMemberAreaService memberAreaService;
	
	@Autowired
	private MemberInviteMapper memberInviteMapper;
	/**
	 * 用户填写信息弹窗
	 */
	@RequestMapping(value = "adduser.html")
	public String updataUser(Model model,HttpServletRequest req,String areaid,String level,String id) {
		
		try {
			
		
		model.addAttribute("areaid", areaid);
		model.addAttribute("aid",id);
		
		Area area=memberAreaService.selectByPrimaryKey(Integer.valueOf(areaid));
		String aid="";
		
		if (area !=null ){
			aid=area.getCode().toString().substring(0, 2);
		}
		
	
		area.setCode(Integer.valueOf(aid));
		area.setLevel(Byte.valueOf(level));
		List<Area> areaList=memberAreaService.selectByAreaPrimaryKey(area);
		
		model.addAttribute("areaList",areaList);
		} catch (Exception e) {
			throw new AgentemException(e);
		}
		return "WEB-INF/view/web/agent/adduser";
	}
	
	/**
	 * 获取地市
	 * @param model
	 * @param req
	 * @param areaid
	 * @param level
	 */
	@ResponseBody
	@RequestMapping(value = "area.html",method = RequestMethod.POST)
	public List<Area>  selectArea(Model model,HttpServletRequest req,String code,String level){
		List<Area> areaList=null;
		
		try {
			
			Area area=new Area();
			String id="";
			
			if (  "1".equals(level)){
				
				id=code.substring(0, 2);
			}
			if ( "2".equals(level)){
				
				id=code.substring(0, 4);
			}
			area.setCode(Integer.valueOf(id));
			area.setLevel(Byte.valueOf(level));
			areaList=memberAreaService.selectByAreaPrimaryKey(area);
			
		} catch (Exception e) {
			throw new AgentemException(e);
		}
		return areaList;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateUserInfo.html",method = RequestMethod.POST)
	public String updateUserInfo(Model model,HttpServletRequest req,UserAgentRequest UserAgentRequst){
		String result="success";
		try {
		} catch (Exception e) {
			result="error";
			throw new AgentemException(e);
		}
		return result;
	}
}
