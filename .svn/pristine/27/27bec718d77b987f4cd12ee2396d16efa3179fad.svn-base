package com.qs.webside.robot.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.acti.game.model.RobotLog;
import com.qs.acti.game.service.IRobotLogService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.pub.sys.model.UserEntity;
import com.qs.acti.game.model.Robot;
import com.qs.acti.game.service.IRobotService;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.member.model.MemberAgents;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zun.wei on 2017/8/18 10:03.
 * Description:机器人控制器
 */
@Controller
@RequestMapping(value = "/robot/")
public class RobotController extends BaseController {

    @Resource
    private IRobotService robotService;

    @Resource
    private IRobotLogService robotLogService;

    @Resource
    private IMemberAgentService memberAgentService;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/robot/robot_list";
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
        List<Robot> list = robotService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    @RequestMapping("addUI.html")
    public String addUI(Model model, HttpServletRequest request) {
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/robot/robot_form";
    }

    @RequestMapping("editUI.html")
    public String editUI(Model model, HttpServletRequest request, Integer id) {
        Robot record = robotService.selectByPrimaryKey(id);
        if (record != null) {
            PageUtil page = new PageUtil(request);
            long d = record.getEtime() * 1000L;
            Date dd = new Date(d);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dd);
            model.addAttribute("page", page);
            model.addAttribute("date", date);
            model.addAttribute("record", record);
        }
        return "/WEB-INF/view/web/robot/robot_form";
    }

    @RequestMapping("add.html")
    @ResponseBody
    public Object add(Robot robot) {
        Map<String, Object> map = new HashMap<String, Object>();
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if (userEntity != null) {
            int num = robot.getNum();
            int mid = robot.getMid();
            MemberAgents memberAgents = memberAgentService.selectByMid(robot.getMid());
            if (memberAgents == null) {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, "要添加机器人工必须为代理商！");
                return map;
            }
            Robot r = robotService.selectByMid(mid);
            if (r != null) {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, "已经添加过机器人了！");
                return map;
            } else {
                robot.setAuthCode(robotService.getOneRandomAuthCode());
                robot.setStime((int) (new Date().getTime() / 1000L));
                Date date = new Date();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                calendar.add(Calendar.MONTH, num);//把日期往后增加一个月.整数往后推,负数往前移动
                date = calendar.getTime();   //这个时间就是日期往后推一天的结果
                robot.setEtime((int) (date.getTime() / 1000L));
                robot.setRemark(userEntity.getRoleName());

                RobotLog robotLog = new RobotLog();
                robotLog.setMid(robot.getMid());
                robotLog.setMoney(0);
                robotLog.setRemark(userEntity.getRoleName());
                robotLog.setMonths(num);
                robotLog.setPtime((int) (new Date().getTime() / 1000L));
                robotLogService.insertSelective(robotLog);
            }
        }
        int result = robotService.insertSelective(robot);
        super.executeRequestResult(result,map);
        return map;
    }


    @RequestMapping("edit.html")
    @ResponseBody
    public Object update(Robot robot){
        Map<String, Object> map = new HashMap<String, Object>();
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if (userEntity != null) {
            Robot r = robotService.selectByPrimaryKey(robot.getId());
            if (r != null) {
                int num = robot.getNum();
                Date date = new Date();
                if (r.getEtime() < new Date().getTime() / 1000L) {//过期时间小于当前时间
                } else {
                    date = new Date(r.getEtime() * 1000L);
                }
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                calendar.add(Calendar.MONTH, num);//把日期往后增加一个月.整数往后推,负数往前移动
                date = calendar.getTime();   //这个时间就是日期往后推一天的结果
                robot.setEtime((int) (date.getTime() / 1000L));
                robot.setRemark(userEntity.getRoleName());

                RobotLog robotLog = new RobotLog();
                robotLog.setMid(r.getMid());
                robotLog.setMoney(0);
                robotLog.setRemark(userEntity.getRoleName());
                robotLog.setMonths(num);
                robotLog.setPtime((int) (new Date().getTime() / 1000L));
                robotLogService.insertSelective(robotLog);
            }
        }
        int result = robotService.updateByPrimaryKeySelective(robot);
        super.executeRequestResult(result,map);
        return map;
    }

}
