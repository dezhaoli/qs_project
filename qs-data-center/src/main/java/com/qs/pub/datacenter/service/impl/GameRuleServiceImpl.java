/*    */ package com.qs.pub.datacenter.service.impl;
/*    */ 
/*    */ import com.qs.pub.datacenter.mapper.GameRuleMapper;
/*    */ import com.qs.pub.datacenter.model.GameRule;
/*    */ import com.qs.pub.datacenter.service.IGameRuleService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service
/*    */ public class GameRuleServiceImpl
/*    */   implements IGameRuleService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private GameRuleMapper gameRuleMapper;
/*    */ 
/*    */   public List<GameRule> queryListByPage(Map<String, Object> parameter)
/*    */   {
/* 43 */     return this.gameRuleMapper.queryListByPage(parameter);
/*    */   }
/*    */ 
/*    */   public Long queryGameRuleCountTotals(Map<String, Object> parameters)
/*    */   {
/* 49 */     return this.gameRuleMapper.queryGameRuleCountTotals(parameters);
/*    */   }
/*    */ 
/*    */   public List<GameRule> queryListAll(Map<String, Object> parameters)
/*    */   {
/* 55 */     return this.gameRuleMapper.queryListAll(parameters);
/*    */   }
/*    */ }

/* Location:           C:\Users\zhengshengfei\Desktop\乐玩互娱\游戏名堂\
 * Qualified Name:     com.qs.pub.datacenter.service.impl.GameRuleServiceImpl
 * JD-Core Version:    0.5.3
 */