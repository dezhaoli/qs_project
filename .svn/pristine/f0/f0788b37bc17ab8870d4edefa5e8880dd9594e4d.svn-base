package com.qs.mainku.game.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.SocketUtils;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.model.Members;
import com.qs.mainku.game.service.IMemberFidesService;
import com.qs.mainku.game.service.IShareLinkService;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by zun.wei on 2017/7/21 11:16.
 * Description:
 */
@Service
public class ShareLinkServiceImpl extends ShareLinkBaseServiceImpl implements IShareLinkService {

    Logger log = Logger.getLogger(ShareLinkServiceImpl.class);

    /*@Resource
    private MemberService memberService;*/

    @Resource
    private IMemberFidesService memberFidesService;

    @Override
    public  List<Map<String, Object>> setRoomInfo(Map<String, Object> roomInfoMap, Model model, int roomid, int gameType)
            throws InterruptedException, MemcachedException, TimeoutException, IOException {
        super.decodeRoomInfo(roomInfoMap, model,roomid);
        return getRoomInfo(model, roomid, gameType);
    }

    @Override
    public Map<String, Object> getUserInfoByCookieCode(String unionid, int gameType){
        Map<String, Object> map = new HashMap<>();
        Members members = memberFidesService.findMembersBySitemid(unionid);
       /* if (StringUtils.isBlank(unionid)) {//测试用
            members = new Members();
            members.setMid(54118);
        }*/
        if (members != null) {
            MemberFides memberfides = memberFidesService.selectByPrimaryKey(members.getMid());
            if (memberfides != null) {
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put("nowUserIcon", memberfides.getIcon());
                map.put("nowUserName", memberfides.getName());
                map.put("nowUserMid", memberfides.getMid());
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
            }
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
        }
        return map;
    }

    @Override
    public Map<String, Object> joinRoom(int roomid, String unionid, Model model, int gp, String sesskey, String cIp, int cPort, int gameType)
            throws IOException, InterruptedException, MemcachedException, TimeoutException {
        Members members = memberFidesService.findMembersBySitemid(unionid);
        /*if (StringUtils.isBlank(unionid)) {//测试用
            members = new Members();
            members.setMid(54118);
        }*/
        Map<String, Object> map = new HashMap<>();
        if (members != null) {
            MemberFides memberfides = memberFidesService.selectByPrimaryKey(members.getMid());
            if (memberfides != null) {//用户存在
                requestToCServer(roomid, gp, sesskey, cIp, cPort, memberfides, map, gameType);
            } else {//用户不存在，查不到数据
                log.debug("no checkout on db ;please confirm memberfides0 table ----------::");
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.ERROR, -2);//用户不存在
                map.put(CommonContants.MESSAGE, "用户不存在");
            }
            map.put("nowUserMid", members.getMid());
            List<Map<String, Object>> roomInfo = setRoomInfo(null, model, roomid, gameType);
            map.put("roomInfo", roomInfo);
        } else {//用户不存在，未注册过
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.ERROR, -1);//用户未注册
            map.put(CommonContants.MESSAGE, "用户未注册");
        }
        model.addAttribute("roomid", roomid);
        model.addAttribute("joinRoomResult", JSON.toJSONString(map));
        log.debug("join room result --------:: roomid is " + roomid + " joinRoomResult :" + map);
        return map;
    }

    /**
     * @Author:zun.wei , @Date:2017/7/24 15:03
     * @Description:向C++服务器提交请求
     * @param roomid 房间号
     * @param gp gp
     * @param sesskey
     * @param cIp socket ip
     * @param cPort socket port
     * @param memberfides 人员
     * @param map 返回值map
     * @param gameType 游戏类型
     * @throws IOException
     */
    private void requestToCServer(int roomid, int gp, String sesskey, String cIp, int cPort, MemberFides memberfides
            , Map<String, Object> map, int gameType) throws IOException {
        int mid = memberfides.getMid();
        SocketUtils socketUtils = ShareLinkSwitchSocket.switchSocketUtilByGameType(gameType, gp, sesskey, cIp, cPort, mid);
        boolean login = socketUtils.writeToServer();//登录c++ 服务器
        log.debug("user mid is " + mid + " join room " + roomid + ";" + "socket connect and write to c++ server result ----------::" + login);
        if (login && socketUtils.receviveInteger() == 0) {//服务器返回0表示登录成功
            boolean joinRoom = ShareLinkSwitchSocket.switchJoinRoomTypeByGameType(gameType, roomid, mid, socketUtils);
            if (joinRoom) {//发送请求到c++服务器成功
                executeJoinRoom(roomid, mid, socketUtils,map,gameType);
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.ERROR, -3);//发送加入房间请求失败
                map.put(CommonContants.MESSAGE, "加入房间请求失败");
                log.debug("user mid is " + mid + " join room " + roomid + ";send join room request fail.");
            }
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.ERROR, -4);//发送登录请求失败
            map.put(CommonContants.MESSAGE, "登录请求失败");
            log.debug("share link login fail ---------:: please confirm parameters !");
        }
        socketUtils.close();
    }


    /**
     * @Author:zun.wei , @Date:2017/7/24 15:04
     * @Description:执行加入房间请求操作
     * @param roomid 房间号
     * @param mid 请求加入房间的人
     * @param socketUtils SocketUtils工具类对象
     * @param map 返回值map
     * @param gameType
     * @throws IOException
     */
    private void executeJoinRoom(int roomid, int mid, SocketUtils socketUtils, Map<String, Object> map, int gameType) throws IOException {
        long t1 = System.currentTimeMillis();
        while (true) {
            long t2 = System.currentTimeMillis();
            if (t2 - t1 > 5000) {//五秒钟循环如果未加入成功则跳出循环
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.ERROR, -5);//c++服务器返回加入房间超时
                map.put(CommonContants.MESSAGE, "加入房间超时");
                log.debug("user mid is " + mid + " join room " + roomid + ";"
                        + "join room is timeout,the time equal -------::" + 5000);
                break;
            } else {
                String joinResult = socketUtils.recviceJoinRoomResult();
                if (StringUtils.isNotBlank(joinResult)) {
                    int recvInt = Integer.parseInt(joinResult.split("_")[1]);
                    int cmd = Integer.parseInt(joinResult.split("_")[2]);
                    String errorMsg = ShareLinkHandReData.handleRecvData(cmd, recvInt,gameType);
                    if (StringUtils.isBlank(errorMsg) && recvInt > 0) {
                        map.put(CommonContants.SUCCESS, Boolean.TRUE);
                        map.put(CommonContants.ERROR, 1);//成功加入房间
                        map.put(CommonContants.MESSAGE, "成功加入房间");
                        log.debug("user mid is " + mid + " join room " + roomid + " successful !");
                        break;//加入房间成功跳出循环
                    } else {
                        map.put(CommonContants.SUCCESS, Boolean.FALSE);
                        map.put(CommonContants.ERROR, -6);//服务器返回错误码
                        map.put(CommonContants.MESSAGE,errorMsg);
                        log.debug("user mid is " + mid + " join room " + roomid + " error msg --::" + errorMsg);
                        break;
                    }
                }
            }
        }
    }

}
