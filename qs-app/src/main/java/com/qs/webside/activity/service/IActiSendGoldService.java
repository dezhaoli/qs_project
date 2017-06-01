package com.qs.webside.activity.service;

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
    Object sendGoldByComment(Integer mid,int gameType);

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:25
     * @Description:根据分享链接发放金币
     * @param mid
     * @return
     */
    Object sendGoldByShare(Integer mid,int gameType);

}
