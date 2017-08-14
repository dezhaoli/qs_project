package com.qs.mainku.game.service;

import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by zun.wei on 2017/7/21 11:15.
 * Description:
 */
public interface IShareLinkService {

    /**
     * @Author:zun.wei , @Date:2017/7/21 11:23
     * @Description:分享链接加入房间
     * @param roomid 房间号
     * @param unionid 微信unionid
     * @param model spring 模型
     * @param gameType
     */
    Map<String, Object> joinRoom(int roomid, String unionid, Model model, int gp, String sesskey, String cIp, int cPort
            , int gameType) throws IOException, InterruptedException, MemcachedException, TimeoutException;


    /**
     * @Author:zun.wei , @Date:2017/7/27 19:44
     * @Description:设置房间信息
     * @param roomInfoMap
     * @param model
     * @param roomid
     * @param gameType
     * @throws InterruptedException
     * @throws MemcachedException
     * @throws TimeoutException
     * @throws IOException
     */
    List<Map<String, Object>> setRoomInfo(Map<String, Object> roomInfoMap, Model model, int roomid, int gameType)
            throws InterruptedException, MemcachedException, TimeoutException, IOException;

    /**
     * @Author:zun.wei , @Date:2017/7/27 19:44
     * @Description:根据cookie中的code获取用户信息
     * @param unionid
     * @param gameType
     * @return
     */
    Map<String, Object> getUserInfoByCookieCode(String unionid, int gameType);

}
