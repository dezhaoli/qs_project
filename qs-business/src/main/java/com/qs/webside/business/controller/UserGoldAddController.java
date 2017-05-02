package com.qs.webside.business.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;
import com.qs.common.constant.CommonContants;
import com.qs.log.game.model.BusiGoldLog;
import com.qs.log.game.model.GoldLog;
import com.qs.log.game.service.IBusiGoldLogService;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.IBizGroupAccessService;
import com.qs.webside.util.BusinessDataSourceUtil;


/**
 * 商务添加金币功能
 * @author zyy
 *
 */
@Controller
@RequestMapping(value = "/business/")
public class UserGoldAddController {
	
	Logger log = Logger.getLogger(UserGoldAddController.class);

	@Autowired
	private BusinessDataSourceUtil businessDataSourceUtil;
	
	@Autowired
	private IBusiGoldLogService busiGoldLogService;
	
	@Autowired
	private IBizGroupAccessService bizGroupAccessService;
	
	@Value("${game.goldhost}")
    private String goldHost;
    
    @Value("${game.goldport}")
    private int goldPort;
	 /**
     * 金币添加功能
     * @return
     * @author:zyy
     * @time:2017年4月27日
     */
    @RequestMapping("user/goldAddUi.html")
    public String goldAddUi(){
    	
    	return "/WEB-INF/view/web/business/agent_goldAdd_from";
    }
    
    /**
     * 添加金币用户保存
     * @return
     * @author:zyy
     * @time:2017年4月27日
     */
    @RequestMapping("user/goldAddSubmit.html")
    @ResponseBody
    public Map<String,Object> goldAddSubmit(BusiGoldLog busiGoldLogint ){
    	int mid=busiGoldLogint.getMid();
    	int gold=busiGoldLogint.getGold();
    	String remark=busiGoldLogint.getRemark();
    	/*int 　mid
    	int gold,String cause；*/
    	businessDataSourceUtil.setWriteAllDataSourceType();
    	Map<String,Object> result=new HashMap<String, Object>();
    	log.debug("into goldAddSubmit!====================::");
        MemberBusiness memberBusiness = (MemberBusiness)SecurityUtils.getSubject().getPrincipal();
    	/*String acids=bizGroupAccessService.selectCountAcids(memberBusiness.getId());
    	if (StringUtils.isBlank(acids)){
    		result.put(CommonContants.ERROR, false);
    		result.put(CommonContants.MESSAGE, "该用户没用权限！");
    		return result;
    	}*/
    	
    	result=busiGoldLogService.goldAdd(mid,gold,remark,goldHost, goldPort,businessDataSourceUtil.getGameType());
    	
    	return result;
    }
}
