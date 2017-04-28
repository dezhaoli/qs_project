package com.qs.webside.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.IAppGameService;
import com.qs.pub.game.service.IBusinessService;
import com.qs.webside.util.BusinessDataSourceUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/4/28 9:39.
 * Description:查看分公司商务
 */
@Controller
@RequestMapping(value = "/business/")
public class ViewBranchBuzController extends BaseController {

    @Resource
    private IBusinessService businessService;
    @Resource
    private IAppGameService appGameService;
    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;

    /**
     * @Author:zun.wei , @Date:2017/4/28 10:39
     * @Description:查看分公司商务入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "branchBusinessUi.html",method = RequestMethod.GET)
    public String branchBusinessUi(Model model, HttpServletRequest request) {
        String gameName = appGameService.getGameName(businessDataSourceUtil.getGameType());
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        model.addAttribute("gameName", gameName);
        return "/WEB-INF/view/web/business/business_showCompanyBuzi_list";
    }

    /**
     * @Author:zun.wei , @Date:2017/4/28 10:39
     * @Description:查看分公司商务
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("branchBusiness.html")
    @ResponseBody
    public Object branchBusiness(String gridPager) throws Exception {
        businessDataSourceUtil.clearAllDynamicDataSource();
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }
        //String gameCode = appGameService.getGameCode(businessDataSourceUtil.getGameType());
        MemberBusiness memberBusiness = (MemberBusiness) SecurityUtils.getSubject().getPrincipal();
        if (memberBusiness != null) {
            parameters.put("companyId", memberBusiness.getId());
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String, Object>> list = businessService.getCompanyBiz(parameters);
        return getReturnPage(pager, page, list);
    }


}
