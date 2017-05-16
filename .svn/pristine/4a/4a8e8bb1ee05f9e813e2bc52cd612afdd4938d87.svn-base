package com.qs.webside.query.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.IPlayerRecordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/16 9:35.
 * Description:牌局统计
 */
@Controller
@RequestMapping(value = "/query/")
public class BoardStatisticsController extends BaseController {

    //  /query/boardStatisticsUi.html

    @Resource
    private IPlayerRecordService playerRecordService;

    @Value("${game.gameCode}")
    private String dbName;

    /**
     * @Author:zun.wei , @Date:2017/5/16 10:18
     * @Description:牌局统计入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "boardStatisticsUi.html", method = RequestMethod.GET)
    public String boardStatisticsUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.put("endTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Integer playCount = playerRecordService.queryBoardStatisticsByCount(map);
        model.addAttribute("page", page);
        model.addAttribute("playCount", playCount);
        return "WEB-INF/view/web/query/boardStatistics_query_list";
    }

    /**
     * @Author:zun.wei , @Date:2017/5/16 10:18
     * @Description:牌局统计
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("boardStatistics.html")
    @ResponseBody
    public Object boardStatistics(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        parameters.put("dbName", dbName);
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = playerRecordService.queryBoardStatisticsByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * @Author:zun.wei , @Date:2017/5/16 10:49
     * @Description:获取牌局总数
     * @param endTime
     * @param startTime
     * @return
     * @throws Exception
     */
    @RequestMapping("queryBoardStatisticsByCount.html")
    @ResponseBody
    public Object queryBoardStatisticsByCount(String endTime,String startTime,Integer mid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("mid", mid);
        return playerRecordService.queryBoardStatisticsByCount(map);
    }

}
