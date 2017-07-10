package com.qs.webside.activity.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.acti.game.model.ActiIntegralCfg;
import com.qs.acti.game.service.IActiIntegralCfgService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.Constants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.Dict;
import com.qs.pub.game.service.IDictService;
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
 * Created by zun.wei on 2017/7/10 14:14.
 * Description:活动中心积分配置表
 */
@Controller
@RequestMapping(value = "/actiIntegralCfg/")
public class ActiIntegralCfgController extends BaseController {

    @Resource
    private IActiIntegralCfgService actiIntegralCfgService;

    @Resource
    private IDictService dictService;


    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        List<Dict> activityList = dictService.findDictList(Constants.Dict.ACTIVITY_ID);//活动类型选择
        model.addAttribute("activityList", JSON.toJSONString(activityList));
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/activity/acti_integralCfg_list";
    }

    @RequestMapping("list.html")
    @ResponseBody
    public Object list(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<ActiIntegralCfg> list = actiIntegralCfgService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    @RequestMapping("addUI.html")
    public String addUI(Model model, HttpServletRequest request, String id) {
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        List<Dict> activityList = dictService.findDictList(Constants.Dict.ACTIVITY_ID);//活动类型选择
        model.addAttribute("activityList", activityList);
        return "/WEB-INF/view/web/activity/acti_integralCfg_form";
    }

    @RequestMapping("editUI.html")
    public String editUI(Model model, HttpServletRequest request, Integer id) {
        ActiIntegralCfg record = actiIntegralCfgService.selectByPrimaryKey(id);
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        model.addAttribute("record", record);
        List<Dict> activityList = dictService.findDictList(Constants.Dict.ACTIVITY_ID);
        model.addAttribute("activityList", activityList);
        return "/WEB-INF/view/web/activity/acti_integralCfg_form";
    }

    @RequestMapping("add.html")
    @ResponseBody
    public Object add(ActiIntegralCfg actiIntegralCfg) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = actiIntegralCfgService.insertSelective(actiIntegralCfg);
        super.executeRequestResult(result,map);
        return map;
    }

    @RequestMapping("edit.html")
    @ResponseBody
    public Object update(ActiIntegralCfg actiIntegralCfg){
        Map<String, Object> map = new HashMap<String, Object>();
        int result = actiIntegralCfgService.updateByPrimaryKeySelective(actiIntegralCfg);
        super.executeRequestResult(result,map);
        return map;
    }

    @RequestMapping("deleteById.html")
    @ResponseBody
    public Object deleteById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = actiIntegralCfgService.deleteByPrimaryKey(id);
        super.executeDeleteRequestResult(result,map);
        return map;
    }

}
