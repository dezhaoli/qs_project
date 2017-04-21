package com.qs.webside.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.MajiangGameRecord;
import com.qs.log.game.service.IMajiangGameRecordService;
import com.qs.webside.util.BusinessDataSourceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商务后台查看用户牌局记录控制器
 *
 * Created by zun.wei on 2017/3/21.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/business/")
public class UserCardRecordController extends BaseController {

    @Resource
    private IMajiangGameRecordService majiangGameRecordService;
    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;
    /**
     *  商务后台查看牌局记录入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "showUserCardRecordUi.html",method = RequestMethod.GET)
    public String showUserCardRecordUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("gameType", "majiang");//后期可以根据不同的游戏类型设置不同的datagird
            return "/WEB-INF/view/web/user/member_userCardRecord_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("showUserCardRecord.html")
    @ResponseBody
    public Object showUserCardRecord(String gridPager) throws Exception {
        businessDataSourceUtil.readType();//@Author:zun.wei, @Date:2017/4/21 20:27 切换数据源
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }

        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        if ("majiang".equals(parameters.get("gameType"))) {
            if (!"".equals(parameters.get("mid"))) {
                List<MajiangGameRecord> list = majiangGameRecordService.queryListByPage(parameters);
                return getReturnPage(pager, page, list);
            }
        }
        return getReturnPage(pager, page, new ArrayList<Map<String,Object>>());//如果没有参数，返回一个空的list。
    }

}
