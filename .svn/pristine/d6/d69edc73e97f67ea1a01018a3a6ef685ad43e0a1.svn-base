/*
 * 文件名：PlayingController.java	 
 * 时     间：下午7:56:09
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.constant.Constant;
import com.qs.pub.datacenter.model.Playing;
import com.qs.pub.datacenter.model.UserLoginLog;
import com.qs.pub.datacenter.service.IPlayingService;
import com.qs.pub.sys.model.UserEntity;

/** 
 * @ClassName: RegionPlayingController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月23日 下午7:56:09 
 */
@Controller
@RequestMapping("/regionPlaying/")
public class RegionPlayingController extends BaseController
{
	@Resource
	private IPlayingService playService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	/**
	 * 
	 * @标题: toRegionPlayListtUi 
	 * @描述:  跳转到区域在线在玩主页面
	 *
	 * @参数信息
	 *    @param pag
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toRegionPlayListtUi.html")
	public String toRegionPlayListtUi(){
		
		return "/WEB-INF/view/loginfo/region_playing_list";
	}
	/**
	 * 
	 * @标题: regionPlayList 
	 * @描述:  区域在线在玩数据查询接口
	 *
	 * @参数信息
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("regionPlayList.html")
	@ResponseBody
	public Object regionPlayList(String gridPager){
		ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
		
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),
				pager.getPageSize());
		parameters.put("gameCode", gameCode);
		
		List<Playing> list = playService.queryListByRegion(parameters);
		
		return getReturnPage(pager, page, list);
	}
	
}
