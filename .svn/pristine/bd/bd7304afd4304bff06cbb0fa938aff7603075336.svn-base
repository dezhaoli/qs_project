package com.qs.pub.sys.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.common.Common;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.pub.sys.model.BaseParam;
import com.qs.pub.sys.service.IBaseParamService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/1.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/baseparam/")
public class BaseParamController {

    @Resource
    private IBaseParamService baseParamService;
   
    /**
     * 正在审核版本
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getInCheckVersion.html",method = RequestMethod.POST)
    public Object getInCheckVersion() {
        Map<String, Object> parameters = new HashMap<>();
        
        parameters.put("code", AppConstants.BaseParam.ANDROID_VERSION_CODE);
		BaseParam azBaseParam=baseParamService.findBaseParamByCode(AppConstants.BaseParam.ANDROID_VERSION_CODE);
        Map<String, Object> parameters1 = new HashMap<>();
        parameters1.put("code", AppConstants.BaseParam.IOS_VERSION_CODE);
		BaseParam iosBaseParam=baseParamService.findBaseParamByCode(AppConstants.BaseParam.IOS_VERSION_CODE);
        Map<String, Object> data = new HashMap<>();
        if(null!=azBaseParam){
          data.put("android",azBaseParam);
          data.put("successAndroid", true);
        }
        if(null!=iosBaseParam){
          data.put("ios",iosBaseParam);
          data.put("successIos", true);
        }
        return data;
    }
    /**
     * 正在审核版本修改
     * @param versionValue
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateInCheckVersion.html",method = RequestMethod.POST)
    public Object update(String versionValue,Integer id) {
        Map<String, Object> data = new HashMap<>();    
        BaseParam baseParam=new BaseParam();
        baseParam.setId(id);
        baseParam.setValue(versionValue);
        int result = baseParamService.updateBaseParam(baseParam);
        if (result > 0) {
            return getInCheckVersion();
        }
        return data;
    }
    
    /**
     * 更新页面
     * @param model
     * @param request
     * @return
     */
/*	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request){
		try
		{
			String code=AppConstants.BaseParam.CONFIG_VERSION_CODE;
			BaseParam configBaseParam=baseParamService.findBaseParamByCode(code);
			PageUtil page = new PageUtil(request);
			model.addAttribute("baseParam",configBaseParam);
			return Common.BACKGROUND_PATH + "/web/mobile/baseparam_form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		
		
	}*/
	  /**
	   * 更新基本参数
	   * @param versionValue
	   * @param id
	   * @return
	   */
	   @ResponseBody
	    @RequestMapping(value = "/update.html",method = RequestMethod.POST)
	    public Object updateBaseParam(String versionValue,Integer id) {
	        Map<String, Object> map = new HashMap<>();    
	        BaseParam baseParam=new BaseParam();
	        baseParam.setId(id);
	        baseParam.setValue(versionValue);
	        
	        int result = baseParamService.updateBaseParam(baseParam);
	    	if(result>0){
				map.put(CommonContants.SUCCESS, Boolean.TRUE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.UPDATE_SUCCESS);
			}else
			{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.UPDATE_FAILURE);
			}
	        return map;
	   }
	   /**
	    * 更新基本参数
	    * @param versionValue
	    * @param id
	    * @return
	    */
	   @ResponseBody
	   @RequestMapping(value = "/baseparamEdit.html",method = RequestMethod.POST)
	   public Object updateBaseParamTwo(String versionValue,BaseParam baseParam) {
		   Map<String, Object> map = new HashMap<>();    
		   baseParam.setModifyTime(new Date(System.currentTimeMillis()));
		   
		   int result = baseParamService.updateBaseParam(baseParam);
		   
		   if(result>0){
			   map.put(CommonContants.SUCCESS, Boolean.TRUE);
			   map.put(CommonContants.DATA, null);
			   map.put(CommonContants.MESSAGE, CommonContants.UPDATE_SUCCESS);
		   }else
		   {
			   map.put(CommonContants.SUCCESS, Boolean.FALSE);
			   map.put(CommonContants.DATA, null);
			   map.put(CommonContants.MESSAGE, CommonContants.UPDATE_FAILURE);
		   }
		   return map;
	   }
	   
		@RequestMapping("baseparam.html")
		public String baseparam(Model model, HttpServletRequest request, String id) {
			try
			{
				List<BaseParam> list = baseParamService.selectAllList();
				model.addAttribute("list", list);
				return Common.BACKGROUND_PATH + "/web/param/baseparam_view";
			}catch(Exception e)
			{
				throw new SystemException(e);
			}
		}
		/**
		 * 参数设置页面跳转
		 * @param model
		 * @param request
		 * @param id
		 * @return
		 */
		@RequestMapping("baseparamTwo.html")
		public String baseparamTwo(Model model, HttpServletRequest request, String id) {
				return Common.BACKGROUND_PATH + "/web/param/baseparam_two_list";
		}
		/**
		 * 参数设置修改页面跳转
		 * @param model
		 * @param request
		 * @param id
		 * @return
		 */
		@RequestMapping("baseparamEditUI.html")
		public String baseparamTwoEditUI(Model model, HttpServletRequest request, Integer id) {
			BaseParam baseParam = baseParamService.findById(id);
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("record", baseParam);
			return Common.BACKGROUND_PATH + "/web/param/baseparam_two_edit";
		}
		
		/**
		 * 参数设置数据列表
		 * @param gridPager
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("baseparamList.html")
		@ResponseBody
		public Object list(String gridPager) throws Exception{
			Map<String,Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			
			// 设置分页，page里面包含了分页信息
			Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "id");
		
			List<BaseParam> list = baseParamService.queryListByPage(parameters);
			parameters.clear();
			parameters.put("isSuccess", Boolean.TRUE);
			parameters.put("nowPage", pager.getNowPage());
			parameters.put("pageSize", pager.getPageSize());
			parameters.put("pageCount", page.getPages());
			parameters.put("recordCount", page.getTotal());
			parameters.put("startRecord", page.getStartRow());
			//列表展示数据
			parameters.put("exhibitDatas", list);
			return parameters;
		}
		/**
		 * 跳转至添加页面
		 * @param model
		 * @param request
		 * @param id
		 * @return
		 */
		@RequestMapping("baseparamUI.html")
		public String baseparamUI(Model model, HttpServletRequest request, String id) {
			try
			{
				List<BaseParam> list = baseParamService.selectAllList();
				model.addAttribute("list", list);
				return Common.BACKGROUND_PATH + "/web/param/baseparam_two_add";
			}catch(Exception e)
			{
				throw new SystemException(e);
			}
		}
		
		/**
		 * 添加数据
		 * @param baseParam
		 * @return
		 */
		@RequestMapping("add.html")
		@ResponseBody
		public Object add(BaseParam baseParam)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			try
			{
				//UserEntity userEntity = ShiroAuthenticationManager.getUserEntity();
				baseParam.setCreateTime(new Date(System.currentTimeMillis()));
				baseParam.setStatus("1");
				int result = baseParamService.insert(baseParam);
				if(result>0){
					map.put(CommonContants.SUCCESS, Boolean.TRUE);
					map.put(CommonContants.DATA, null);
					map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
				}else
				{
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.DATA, null);
					map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
				}
			}catch(Exception e)
			{
				throw new AjaxException(e);
			}
			return map;
		}
		/**
		 * 更新启用状态
		 * @param baseParam
		 * @return
		 */
		@RequestMapping("updateStatus.html")
		@ResponseBody
		public Integer updateStatus(BaseParam baseParam)
		{
			 
			return baseParamService.updateStatus(baseParam);
		}
		
		@RequestMapping("editUI.html")
		public String editUI(Model model, HttpServletRequest request, String id) {
			try
			{
				List<BaseParam> list = baseParamService.selectAllList();
				model.addAttribute("list", list);
				return Common.BACKGROUND_PATH + "/web/param/baseparam_form";
			}catch(Exception e)
			{
				throw new SystemException(e);
			}
		}
		@RequestMapping(value="updateBatch.html", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Object updateBatch(@RequestBody List<BaseParam> list) {
			  Map<String, Object> map = new HashMap<>();    
			  int result=baseParamService.updateBatch(list);
			 if(result>0){
				map.put(CommonContants.SUCCESS, Boolean.TRUE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.UPDATE_SUCCESS);
			}else{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.UPDATE_FAILURE);
			}
			 return map;
		}




}
