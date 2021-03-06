package com.qs.webside.activity.service;

import com.qs.webside.activity.model.ActiAward;
import com.qs.webside.activity.model.ActiCenter;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/31 15:53.
 * Description:活动中心表
 */
public interface IActiCenterService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiCenter record);

    int insertSelective(ActiCenter record);

    ActiCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiCenter record);

    int updateByPrimaryKey(ActiCenter record);

    List<ActiCenter> queryListByPage(Map<String, Object> parameter);

    /**
     * @Author:zun.wei , @Date:2017/6/1 14:19
     * @Description:拉取启用状态下的活动中心对象
     * @return
     */
    List<Map<String,Object>> queryListActivityByStatus();

    /**
     * @Author:zun.wei , @Date:2017/6/3 14:56
     * @Description:拉取启用状态下对应活动类型的活动中心对象
     * @param type
     * @return
     */
    Map<String, Object> queryListActivityByStatusAndType(Integer type);
    
    /**
     * 获取当前用户玩大转盘页面信息
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年9月15日
     */
    Map<String,Object> getActivityCenterInfos (int mid,int gameType);
    
    /**
     * 根据参数获取单个活动对象
     * 
     * @param parameters（mid,type活动类型）
     * @return
     * @author:zyy
     * @time:2017年9月15日
     */
    ActiCenter getActivityInfo(int type);


}
