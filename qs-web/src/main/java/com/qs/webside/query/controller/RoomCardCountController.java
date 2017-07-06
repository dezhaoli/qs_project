package com.qs.webside.query.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.GoldLog;
import com.qs.log.game.model.RoomCardCount;
import com.qs.log.game.service.IGoldLogService;
import com.qs.log.game.service.IRoomCardCountService;
import org.springframework.beans.factory.annotation.Value;
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
 * Created by zun.wei on 2017/7/4 17:38.
 * Description:房卡统计
 */
@Controller
@RequestMapping(value = "/roomCardCount/")
public class RoomCardCountController extends BaseController {

    @Resource
    private IGoldLogService goldLogService;

    @Resource
    private IRoomCardCountService roomCardCountService;

    //@Value("${game.gameCode}")
    //private String dbName;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String gameRecordUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        return "WEB-INF/view/web/query/room_count_log_list";
    }

    /*@RequestMapping("list.html")
    @ResponseBody
    public Object orderCount(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        parameters.put("memberagents", dbName + ".memberagents");
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = goldLogService.queryCardCountByDate(parameters);
        return getReturnPage(pager, page, list);
    }*/

    @RequestMapping("list.html")
    @ResponseBody
    public Object orderCount(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<RoomCardCount> list = roomCardCountService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }


}
