package com.qs.pub.act.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.pub.act.model.ActGiveGold;
import com.qs.pub.act.service.IActGiveGoldService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/5/18 15:34.
 * Description:活动中心，活动送金币控制器
 */
@Controller
@RequestMapping(value = "/act/")
public class ActGiveGoldController extends BaseController {

    @Resource
    private IActGiveGoldService actGiveGoldService;


    /**
     * @Author:zun.wei , @Date:2017/5/18 15:55
     * @Description:前往评论送金币活动页面
     * @param model
     * @return
     */
    @RequestMapping(value = "go2CommentGiveGoldActivityViewUi.html",method = RequestMethod.GET)
    public String go2CommentGiveGoldActivityViewUi(Model model,ActGiveGold actGiveGold) {
        logger.debug("go2CommentGiveGoldActivityViewUi : " + actGiveGold.toString());
        AccessToken token= ContextUtil.getAccessTokenInfo(actGiveGold.getSesskey());
        Integer mid = token.getMid();
        return "/WEB-INF/view/web/act/com_give_gold_ui";
    }


   /* @ResponseBody
    @RequestMapping(value = "go2ActivityViewUi.html",method = RequestMethod.POST)
    public Object go2ActivityViewUi(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("mid", null);
        }
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<ActGiveGold> list = actGiveGoldService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }*/

}
