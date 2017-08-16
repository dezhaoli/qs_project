package com.qs.webside.activity.service;

import com.qs.webside.activity.model.ActiSendGold;

/**
 * Created by zun.wei on 2017/6/1 18:23.
 * Description:活动中心发放金币
 */
public interface IActiSendGoldService {

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:24
     * @Description:查询用户是否去评论过
     * @param mid
     * @return
     */
    Object checkUserIsComment(Integer mid);

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:25
     * @Description:根据评论发放金币
     * @param mid
     * @return
     */
    Object sendGoldByComment(Integer mid, int gameType, String sesskey, String sendGoldUrl);

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:25
     * @Description:根据分享链接发放金币
     * @param mid
     * @return
     */
    Object sendGoldByShare(Integer mid, int gameType, String sesskey, String sendGoldUrl);

    /**
     * @Author:zun.wei , @Date:2017/6/26 19:41
     * @Description: 发送金币
     * @param sesskey session key
     * @param goldNum 发送金币数量
     * @param sendType 属于什么类型的发放金币
     * @param signCode 10位数，时间戳
     * @param sendGoldUrl 发送金币的url接口地址
     * @return 返回值
     */
    String sendGold(String sesskey, int goldNum, int sendType, long signCode, String sendGoldUrl);

    /**
     * @Author:zun.wei , @Date:2017/6/29 15:52
     * @Description:如果不存在就插入，存在就更新
     * @param record
     * @return
     */
    int insertOrUpate(ActiSendGold record);
    
    /**
     * @Author:zun.wei , @Date:2017/6/1 19:24
     * @Description:查询用户是否发过链接
     * @param mid type 活动类型
     * @return
     */
	Object checkUserIsLink(Integer mid,int type);

}
