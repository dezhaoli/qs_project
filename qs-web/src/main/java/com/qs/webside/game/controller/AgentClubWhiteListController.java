package com.qs.webside.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.agent.model.AgentClubwhiteList;
import com.qs.webside.agent.service.IMemberPayMentService;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.service.IAgentClubwhiteListService;
import com.qs.webside.member.service.IMemberFidesService;

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
 * 代理商俱乐部白名单
 * @author zyy
 *
 */
@Controller
@RequestMapping(value = "/club/whiteList/")
public class AgentClubWhiteListController extends BaseController{

    @Resource
    private  IAgentClubwhiteListService agentClubwhiteListService;

    @Resource
    private IMemberFidesService memberFidesService;
    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            return "WEB-INF/view/web/member/member_clubWhiteList_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
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
        if(parameters.size()<0){
        	parameters.put("mid", null);
        }
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<AgentClubwhiteList> list = agentClubwhiteListService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    @RequestMapping("add.html")
    @ResponseBody
    public Object add(AgentClubwhiteList record) {
        Map<String, Object> map = new HashMap<String, Object>();
            int result = 0;
            
            if (null != record && null != record.getMid() && !"0".equals(record.getMid() + "")) {
            	
            	MemberFides memberFides= memberFidesService.selectByMemberMid(record.getMid());
            	if (memberFides ==null ){
            		 map.put(CommonContants.SUCCESS, Boolean.FALSE);
                     map.put(CommonContants.DATA, null);
                     map.put(CommonContants.MESSAGE, "该用户不存在！");
                     return map;
            	}
                result = agentClubwhiteListService.addSelective(record);
            }
            if (result > 0) {
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
            } else if (result < -1) {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, "添加失败（原因：已添加）!");
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
            }
            //super.executeRequestResult(result,map);
        return map;
    }
    @RequestMapping("deleteById.html")
    @ResponseBody
    public Object deleteById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = agentClubwhiteListService.deleteByPrimaryKey(id);
            super.executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/takeEffectById.html",method = RequestMethod.POST)
    public Object takeEffectById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
            int result = agentClubwhiteListService.updateTakeEffectById(id);
            if (result > 0) {
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE);
            }
      
        return map;
    }


    /*  @RequestMapping("addUI.html")
      public String addUI(Model model, HttpServletRequest request, String id) {
          try {
              PageUtil page = new PageUtil(request);
              model.addAttribute("page", page);
              return "WEB-INF/view/web/member/member_clubWhiteList_form";
          } catch (Exception e) {
              throw new SystemException(e);
          }
      }

      @RequestMapping("editUI.html")
      public String editUI(Model model, HttpServletRequest request, Integer id) {
          try {
              MemberWhiteList record = memberClubwhiteListService.selectById(id);
              PageUtil page = new PageUtil(request);
              model.addAttribute("page", page);
              model.addAttribute("record", record);
              return "WEB-INF/view/web/member/member_clubWhiteList_form";
          } catch (Exception e) {
              throw new SystemException(e);
          }
      }

     


      @RequestMapping("edit.html")
      @ResponseBody
      public Object update(MemberWhiteList record) {
          Map<String, Object> map = new HashMap<String, Object>();
          try {
              int result = memberClubwhiteListService.updateByIdSelective(record);
              super.executeRequestResult(result,map);
          } catch (Exception e) {
              throw new AjaxException(e);
          }
          return map;
      }

  */
}
