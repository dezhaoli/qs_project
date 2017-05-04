/*
 * 文件名：GameNoticeRecordController.java	 
 * 时     间：下午6:18:39
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.webside.game.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.service.IGameNoticeService;

/**
 * @ClassName: GameNoticeRecordController
 * @描述: 游戏公告控制层
 * @author wangzhen
 * @date 2017年5月3日 下午6:18:39
 */
@Controller
@RequestMapping(value = "/game/notice/")
public class GameNoticeRecordController extends BaseController
{
	@Resource
	private IGameNoticeService gameNoticeService;
	
	@RequestMapping(value = "listUi.html", method = RequestMethod.GET)
	public String listUi(Model model, String id, HttpServletRequest request)
	{
		try
		{
			return "/WEB-INF/view/web/mobile/game_notice_main";
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
	}
}
