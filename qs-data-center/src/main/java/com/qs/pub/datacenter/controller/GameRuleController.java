/*     */ package com.qs.pub.datacenter.controller;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.github.pagehelper.Page;
/*     */ import com.github.pagehelper.PageHelper;
/*     */ import com.qs.common.base.basecontroller.BaseController;
/*     */ import com.qs.common.dtgrid.model.Pager;
/*     */ import com.qs.common.dtgrid.util.ExportUtils;
/*     */ import com.qs.pub.datacenter.model.GameRule;
/*     */ import com.qs.pub.datacenter.service.IGameRuleService;
/*     */ import com.qs.pub.sys.model.UserEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
		  import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.SecurityUtils;
/*     */ import org.apache.shiro.subject.Subject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.redis.core.RedisTemplate;
/*     */ import org.springframework.data.redis.core.ValueOperations;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/gameRule/"})
/*     */ public class GameRuleController extends BaseController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private IGameRuleService gameRuleService;
/*     */ 
/*     */   @Autowired
/*     */   private RedisTemplate<String, String> redisTemplate;
/*     */ 
/*     */   @RequestMapping({"togameRuleListUi.html"})
/*     */   public String toUserLoginLogListUi(Model model)
/*     */   {
/*  55 */     return "WEB-INF/view/web/game/game_rule_list"; }
/*     */ 
/*     */   @RequestMapping({"gameRuleList.html"})
/*     */   @ResponseBody
/*     */   public Object userLoginLogList(String startTime, String endTime) {
/*  60 */     UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
/*  61 */     ValueOperations valueOper = this.redisTemplate.opsForValue();
/*  62 */     String gameCode = (String)valueOper.get("dataCenterGameCode:" + userEntity.getId());
/*  63 */     Map parameters = new HashMap();
/*  64 */     parameters.put("startTime", startTime);
/*  65 */     parameters.put("endTime", endTime);
/*  66 */     parameters.put("gameCode", gameCode);
/*     */ 
/*  68 */     List<GameRule> list = gameRuleService.queryListAll(parameters);
/*  69 */     List list2 = new ArrayList();
/*  70 */     List list3 = new ArrayList();
/*  71 */     for (GameRule data : list) {
/*  72 */       list2.add(data.getName());
/*  73 */       list3.add(data.getTotals());
/*     */     }
/*  75 */     String json2 = JSON.toJSONString(list2);
/*  76 */     String json3 = JSON.toJSONString(list3);
/*  77 */     return json2 + "@" + json3;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"queryListByPage.html"})
/*     */   @ResponseBody
/*     */   public Object queryListByPage(String gridPager, HttpServletResponse response)
/*     */   {
/*     */     try {
/*  85 */       UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
/*  86 */       ValueOperations valueOper = this.redisTemplate.opsForValue();
/*  87 */       String gameCode = (String)valueOper.get("dataCenterGameCode:" + userEntity.getId());
/*  88 */       Map parameters = null;
/*     */ 
/*  90 */       Pager pager = (Pager)JSON.parseObject(gridPager, Pager.class);
/*     */ 
/*  92 */       parameters = pager.getParameters();
/*  93 */       parameters.put("gameCode", gameCode);
/*     */ 
/*  95 */       if (pager.getIsExport())
/*     */       {
/*  97 */         if (pager.getExportAllData())
/*     */         {
/* 100 */           List list = this.gameRuleService
/* 101 */             .queryListByPage(parameters);
/* 102 */           ExportUtils.exportAll(response, pager, list);
/* 103 */           return null;
/*     */         }
/*     */ 
/* 107 */         ExportUtils.export(response, pager);
/* 108 */         return null;
/*     */       }
/*     */ 
/* 113 */       Page page = PageHelper.startPage(pager.getNowPage(), 
/* 114 */         pager.getPageSize());
/* 115 */       List list = this.gameRuleService
/* 116 */         .queryListByPage(parameters);
/* 117 */       return getReturnPage(pager, page, list);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 121 */       e.printStackTrace(); }
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"queryGameRuleCountTotals.html"})
/*     */   @ResponseBody
/*     */   public Object queryUserLoginCountTotals(String stime, String etime)
/*     */   {
/*     */     try
/*     */     {
/* 131 */       UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
/* 132 */       ValueOperations valueOper = this.redisTemplate.opsForValue();
/* 133 */       String gameCode = (String)valueOper.get("dataCenterGameCode:" + userEntity.getId());
/* 134 */       Map parameters = new HashMap();
/*     */ 
/* 136 */       parameters.put("gameCode", gameCode);
/* 137 */       parameters.put("startTime", stime);
/* 138 */       parameters.put("endTime", etime);
/* 139 */       Long totals = this.gameRuleService.queryGameRuleCountTotals(parameters);
/* 140 */       return Long.valueOf((totals == null) ? 0L : totals.longValue());
/*     */     }
/*     */     catch (Exception e) {
/* 143 */       e.printStackTrace(); }
/* 144 */     return Integer.valueOf(0);
/*     */   }
 }

