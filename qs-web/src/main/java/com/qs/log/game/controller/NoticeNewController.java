package com.qs.log.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.NoticeNew;
import com.qs.log.game.service.INoticeNewService;
import com.qs.pub.sys.model.UserEntity;
import com.qs.webside.game.model.MobileVersion;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/30 14:22.
 * Description:新公告控制器
 */
@Controller
@RequestMapping(value = "/noticeNew/")
public class NoticeNewController extends BaseController {

    @Resource
    private INoticeNewService noticeNewService;

    @Value("${game.goldhost}")
    private String goldhost;

    @Value("${game.goldport}")
    private int goldport;

    @Value("${game.gametype}")
    private int gametype;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/notice/notice_new_list";
    }

    @RequestMapping("list.html")
    @ResponseBody
    public Object list(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<NoticeNew> list = noticeNewService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    @RequestMapping("addUI.html")
    public String addUI(Model model, HttpServletRequest request,int noticeType) {
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        model.addAttribute("pushType", noticeType);
        if (noticeType == 2) {//定时公告
            return "/WEB-INF/view/web/notice/notice_new_form";
        } else if (noticeType == 3) {//滚动公告
            return "/WEB-INF/view/web/notice/notice_roll_form";
        } else if (noticeType == 4) {//更新公告
            return "/WEB-INF/view/web/notice/notice_update_form";
        } else {//在线公告
            return "/WEB-INF/view/web/notice/notice_new_online_form";
        }
    }

    @RequestMapping("editUI.html")
    public String editUI(Model model, HttpServletRequest request, Integer id) {
        NoticeNew record = noticeNewService.selectByPrimaryKey(id);
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        model.addAttribute("record", record);
        int pushType = record.getPushType();
        model.addAttribute("pushType", pushType);
        if (pushType == 2) {//定时公告
            return "/WEB-INF/view/web/notice/notice_new_form";
        }else if (pushType == 3) {//滚动公告
            return "/WEB-INF/view/web/notice/notice_roll_form";
        } else if (pushType == 3) {//更新公告
            return "/WEB-INF/view/web/notice/notice_update_form";
        } else {//在线公告
            return "/WEB-INF/view/web/notice/notice_new_online_form";
        }
    }

    @RequestMapping("add.html")
    @ResponseBody
    public Object add(NoticeNew record) throws ParseException {
        choosePushType(record);
        Map<String, Object> map = new HashMap<String, Object>();
        int result = noticeNewService.insertSelective(record,goldhost,goldport,gametype);
        super.executeRequestResult(result,map);
        return map;
    }


    @RequestMapping("edit.html")
    @ResponseBody
    public Object update(NoticeNew record) throws ParseException {
        choosePushType(record);
        Map<String, Object> map = new HashMap<String, Object>();
        int result = noticeNewService.updateByPrimaryKeyWithBLOBs(record,goldhost,goldport,gametype);
        super.executeRequestResult(result,map);
        return map;
    }

    @RequestMapping("deleteById.html")
    @ResponseBody
    public Object deleteById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = noticeNewService.deleteByPrimaryKey(id);
        executeRequestResult(result,map);
        return map;
    }

    /**
     * @Author:zun.wei , @Date:2017/7/3 9:09
     * @Description:    选择发布类型
     * @param noticeNew
     * @throws ParseException
     */
    private void choosePushType(NoticeNew noticeNew) throws ParseException {
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        int pushType = noticeNew.getPushType();
        if (pushType == 2) {//发布定时公告
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int stime = (int) (simpleDateFormat.parse(noticeNew.getStartTime()).getTime() / 1000);
            int etime = (int)(simpleDateFormat.parse(noticeNew.getEndTime()).getTime() / 1000);
            noticeNew.setStime(stime);
            noticeNew.setEtime(etime);
            noticeNew.setPushTime(new Date());
            noticeNew.setPushUserName(userEntity.getUserName());
            //noticeNew.setPushType(2);
        } else if (pushType == 3) {//发布滚动公告
            noticeNew.setPushTime(new Date());
            noticeNew.setPushUserName(userEntity.getUserName());
            //noticeNew.setPushType(3);
        } else if (pushType == 4) {//更新公告
            noticeNew.setIsEnable("0");
            noticeNew.setPushTime(new Date());
            noticeNew.setPushUserName(userEntity.getUserName());
        } else {//在线公告
            noticeNew.setIsEnable("0");
            noticeNew.setPushTime(new Date());
            noticeNew.setPushUserName(userEntity.getUserName());
            //noticeNew.setPushType(1);
        }
    }

}
