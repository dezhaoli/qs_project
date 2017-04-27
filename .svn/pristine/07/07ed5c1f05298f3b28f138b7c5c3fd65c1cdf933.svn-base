package com.qs.webside.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.IAppGameService;
import com.qs.pub.game.service.IPlayerPayDayService;
import com.qs.webside.util.BusinessDataSourceUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/4/27 20:13.
 * Description:游戏充值查询
 */
@Controller
@RequestMapping(value = "/business/")
public class GameChanSearController extends BaseController {

    // /business/gameChangeSearchUi.html
    @Resource
    private IPlayerPayDayService playerPayDayService;

    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;

    @Resource
    private IAppGameService appGameService;


    @RequestMapping(value = "gameChangeSearchUi.html",method = RequestMethod.GET)
    public String gameChangeSearchUi(Model model, HttpServletRequest request){
        String gameName = appGameService.getGameName(businessDataSourceUtil.getGameType());
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        model.addAttribute("gameName", gameName);
        return "WEB-INF/view/web/business/business_gameChanSear_list";
    }

    /**
     * @Author:zun.wei , @Date:2017/4/27 15:29
     * @description:商务业绩查询
     * @param gridPager
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "gameChangeSearch.html", method = RequestMethod.POST)
    public Object gameChangeSearch(String gridPager, HttpServletResponse response) throws Exception {
        businessDataSourceUtil.clearAllDynamicDataSource();
        MemberBusiness memberBusiness = (MemberBusiness) SecurityUtils.getSubject().getPrincipal();
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
        parameters.put("gameType", businessDataSourceUtil.getGameType());
        if (memberBusiness != null) {
            parameters.put("businessId", memberBusiness.getId());
        }
        // 设置分页，page里面包含了分页信息
        if (StringUtils.isBlank(parameters.get("startDate") + "") && StringUtils.isBlank(parameters.get("endDate") + "")) {
            parameters.put("startDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            parameters.put("endDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
        if(pager.getIsExport()){//判断是否是导出操作
            if(pager.getExportAllData()){
                //3.1、导出全部数据
                List<Map<String,Object>> list = playerPayDayService.queryChangeDataCountByGame(parameters);
                ExportUtils.exportAll(response, pager, list);
                return null;
            }else{
                //3.2、导出当前页数据
                ExportUtils.export(response, pager);
                return null;
            }
        }
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String, Object>> list = playerPayDayService.queryChangeDataCountByGame(parameters);
        return getReturnPage(pager, page, list);
    }

}
