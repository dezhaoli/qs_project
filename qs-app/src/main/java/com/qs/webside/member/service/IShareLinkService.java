package com.qs.webside.member.service;

import org.springframework.ui.Model;

import java.io.IOException;

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
    void joinRoom(int roomid, String unionid, Model model, int gp, String sesskey, String cIp, int cPort, int gameType) throws IOException;

}
