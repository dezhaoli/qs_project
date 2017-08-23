package com.qs.pub.datacenter.service;

import com.qs.pub.datacenter.model.GameRule;
import java.util.List;
import java.util.Map;

public abstract interface IGameRuleService
{
  public abstract List<GameRule> queryListByPage(Map<String, Object> paramMap);

  public abstract Long queryGameRuleCountTotals(Map<String, Object> paramMap);

  public abstract List<GameRule> queryListAll(Map<String, Object> paramMap);
}

/* Location:           C:\Users\zhengshengfei\Desktop\乐玩互娱\游戏名堂\
 * Qualified Name:     com.qs.pub.datacenter.service.IGameRuleService
 * JD-Core Version:    0.5.3
 */