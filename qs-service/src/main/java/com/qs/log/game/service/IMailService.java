package com.qs.log.game.service;

import com.qs.log.game.model.Mails;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/2.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMailService {

    int deleteById(Integer id);

    int add(Mails record);

    int addSelective(Mails record);

    Mails loadById(Integer id);

    int updateByIdSelective(Mails record);

    int updateByIdWithBLOBs(Mails record);

    int updateByPrimaryKey(Mails record);

    List<Mails> queryListByPage(Map<String, Object> parameters);

    /**
     * socket发送指令和包给C++，请求C++服务器发邮件给游戏客户端
     * @param mapType json的{type:value},value的值。
     * @param mapMsg json的{msg:value},value的值。
     * @param cmdType 命令类型
     * @param mid 0表示全部用户，其他表示指定用户
     * @param hostAddress socket的地址
     * @param hostPort 端口
     * @return
     */
    boolean sendMail(Integer mapType, Integer mapMsg, Integer cmdType
            , Integer mid,String hostAddress,int hostPort);

    boolean sendMail2(String ip,Integer port);

    /**
     * 查看邮件详情
     * @param parameter 查询参数
     * @return
     */
    public List<Mails> queryMailDetail(Map<String, Object> parameter);
}
