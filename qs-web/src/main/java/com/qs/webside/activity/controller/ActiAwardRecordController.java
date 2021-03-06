package com.qs.webside.activity.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.constant.Constants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.Dict;
import com.qs.pub.game.service.IDictService;
import com.qs.pub.sys.model.UserEntity;
import com.qs.acti.game.model.ActiAwardRecord;
import com.qs.acti.game.service.IActiAwardRecordService;
import com.qs.webside.shiro.ShiroAuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/7 15:21.
 * Description:活动中心奖品兑换记录表
 */
@Controller
@RequestMapping(value = "/actiAwardRecord/")
public class ActiAwardRecordController extends BaseController {

    @Resource
    private IActiAwardRecordService actiAwardRecordService;

    @Resource
    private IDictService dictService;


    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        List<Dict> activityList = dictService.findDictList(Constants.Dict.ACTIVITY_ID);//活动类型选择
        model.addAttribute("activityListObj", activityList);
        String jsonString = JSON.toJSONString(activityList);
        model.addAttribute("activityList", jsonString);
        return "WEB-INF/view/web/activity/acti_award_record_list";
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
            parameters.put("name", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<ActiAwardRecord> list = actiAwardRecordService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/22 15:18
     * @Description:根据id来改变兑换记录为已审核
     * @param id 兑换记录的id
     * @return
     * @throws Exception
     */
    @RequestMapping("checkById.html")
    @ResponseBody
    public Object checkById(int id) throws Exception {
        ActiAwardRecord actiAwardRecord = actiAwardRecordService.selectByPrimaryKey(id);
        Map<String, Object> result = new HashMap<>();
        if (actiAwardRecord == null) {
            result.put(CommonContants.SUCCESS, Boolean.FALSE);
            result.put(CommonContants.MESSAGE, "审核的商品不存在！");
            return result;
        }
        UserEntity user = ShiroAuthenticationManager.getUserEntity();
        if (actiAwardRecord.getIsReview() == 0) actiAwardRecord.setIsReview(1);
        actiAwardRecord.setSendTime(new Date());
        actiAwardRecord.setRemark(user.getUserName() + " | " + user.getRoleName());//发放人
        int record = actiAwardRecordService.updateByPrimaryKeySelective(actiAwardRecord);
        if (record > 0) {
            result.put(CommonContants.SUCCESS, Boolean.TRUE);
            result.put(CommonContants.MESSAGE, "审核成功！");
            return result;
        } else {
            result.put(CommonContants.SUCCESS, Boolean.FALSE);
            result.put(CommonContants.MESSAGE, "审核失败！");
            return result;
        }
    }

}
