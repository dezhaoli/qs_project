package com.qs.webside.game.controller;

import com.qs.cfg.acti.model.Store;
import com.qs.common.common.Common;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.model.BaseParam;
import com.qs.webside.sys.service.game.service.IBaseParamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/1.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/base/param/")
public class BaseParamController {

    @Resource
    private IBaseParamService baseParamService;
   
    /**
     * 正在审核版本
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getInCheckVersion",method = RequestMethod.POST)
    public Object getInCheckVersion() {
        Map<String, Object> parameters = new HashMap<>();
        
        parameters.put("code", AppConstants.BaseParam.ANDROID_VERSION_CODE);
        List<BaseParam> listAndroid = baseParamService.queryBaseParamListByPage(parameters);
        Map<String, Object> parameters1 = new HashMap<>();
        parameters1.put("code", AppConstants.BaseParam.IOS_VERSION_CODE);
        List<BaseParam> listIos = baseParamService.queryBaseParamListByPage(parameters1);
        Map<String, Object> data = new HashMap<>();
        if (listAndroid != null && listAndroid.size() >0) {
            data.put("android", listAndroid.get(0));
            data.put("successAndroid", true);
        }
        if (listIos != null && listIos.size() >0) {
            data.put("ios", listIos.get(0));
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
    @RequestMapping(value = "/update",method = RequestMethod.POST)
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
	@RequestMapping("editUI.html")
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
		
		
	}
	  /**
	   * 更新基本参数
	   * @param versionValue
	   * @param id
	   * @return
	   */
	   @ResponseBody
	    @RequestMapping(value = "/updateBaseParam.html",method = RequestMethod.POST)
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




}
