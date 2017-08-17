package com.qs.webside.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.RandomUtil;
import com.qs.common.util.SocketUtils;
import com.qs.common.util.crypto.MD5;
import com.qs.mainku.game.model.AgentClubMember;
import com.qs.mainku.game.model.AgentMids;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.service.*;
import com.qs.mainku.game.service.impl.RobotOpenRoomReData;
import com.qs.mainku.game.service.impl.ShareLinkHandReData;
import com.qs.mainku.game.service.impl.ShareLinkSwitchSocket;
import com.qs.webside.robot.mapper.RobotMapper;
import com.qs.webside.robot.model.Robot;
import com.qs.webside.robot.model.RobotFriends;
import com.qs.webside.robot.model.RobotOpenRoom;
import com.qs.webside.robot.service.IRobotFriendService;
import com.qs.webside.robot.service.IRobotLogService;
import com.qs.webside.robot.service.IRobotOpenRoomService;
import com.qs.webside.robot.service.IRobotService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by zun.wei on 2017/8/11 18:27.
 * Description:机器人业务层
 */
@Service
public class RobotServiceImpl implements IRobotService {

    private Log log = LogFactory.getLog(RobotServiceImpl.class);

    @Resource
    private RobotMapper robotMapper;

    @Resource
    private IRobotFriendService robotFriendService;

    @Resource
    private IRobotLogService robotLogService;

    @Resource
    private IMemberAgentService memberAgentService;

    @Resource
    private IAgentMidService agentMidService;

    @Resource
    private IMemberFidesService memberFidesService;

    @Resource
    private IRobotOpenRoomService robotOpenRoomService;

    @Resource
    private MemcachedClient memcachedClient;

    @Resource
    private AgentClubGroupService agentClubGroupService;

    @Resource
    private AgentClubMemberService agentClubMemberService;

    @Resource
    private IBaseParamService baseParamService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return robotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Robot record) {
        return robotMapper.insert(record);
    }

    @Override
    public int insertSelective(Robot record) {
        return robotMapper.insertSelective(record);
    }

    @Override
    public Robot selectByPrimaryKey(Integer id) {
        return robotMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Robot record) {
        return robotMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Robot record) {
        return robotMapper.updateByPrimaryKey(record);
    }

    @Override
    public Object handlePythonRequest(int type, String data, int gameType, String cIp, int cPort, HttpServletRequest request) throws IOException {
        switch (type) {
            case 1:// 添加机器人为好友请求
                return handleAddFriend(data,gameType);
            case 2:// 开房请求
                return handleOpenRoomRequest(data, gameType, request,cIp,cPort);
        }
        return null;
    }

    @Override
    public String saveToken(Integer mid, Integer gp, Integer userGp, String ip, int gameType) {
        String key = AppConstants.MemcacheKeyPrefix.SESSKEY + mid;
        String ipKey = AppConstants.MemcacheKeyPrefix.IP + mid;
        String istestuser = "0";
        long motime = System.currentTimeMillis();
        String sign = MD5.encrypt(mid + "#" + motime + "#" + AppConstants.SAFECODE);
        String mokey = mid + "-" + motime + "-" + gp + "-" + sign + "-" + userGp + "-" + gameType;
        log.debug("mokey==========::" + mokey);
        try {
            memcachedClient.set(key, 6 * 3600, mokey);
            memcachedClient.set(ipKey, 6 * 3600, ip);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            e.printStackTrace();
        }
        return mokey;
    }

    @Override
    public int getOneRandomAuthCode() {
        while (true) {
            int ramdomCode = RandomUtil.generateAuthCode();
            int code = robotMapper.queryCountByAuthCode(ramdomCode);
            if (code == 0) return ramdomCode;
        }
    }

    //添加机器人为好友
    private Object handleAddFriend(String data,int gameType) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int code = Integer.parseInt(requestMap.get("authCode") + "");
        int addMid = Integer.parseInt(requestMap.get("mid") + "");
        String roboName = requestMap.get("robName") + "";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("code", code);
        parameters.put("addMid", addMid);
        parameters.put("roboName", roboName);
        int result = robotMapper.checkAuthCodeOrMidExist(parameters);
        if (result == 0) {//用授权码添加好友
            int acount = memberAgentService.checkAgentIsExist(addMid);
            if (acount > 0) {//代理商存在
                RobotFriends rf = robotFriendService.queryRobotFriendByCodeAndMid(parameters);
                if (rf != null) {//已经添加过好友的。
                    return respAddFriendSuccess(map, addMid);
                }
                RobotFriends robotFriends = new RobotFriends();
                robotFriends.setAtime((int) (new Date().getTime() / 1000));
                robotFriends.setAuthCode(code);
                robotFriends.setMid(addMid);
                robotFriends.setRobName(roboName);
                robotFriends.setOwner(1);
                int insertResult = robotFriendService.insertSelective(robotFriends);
                Robot robot = new Robot();
                robot.setActivation(1);
                robot.setAuthCode(code);
                robot.setMid(addMid);
                int updateResult = robotMapper.updateActivationTo1(robot);//更新为激活状态
                if (insertResult > 0 && updateResult > 0) {//加入机器人好友表成功
                    return respAddFriendSuccess(map, addMid);
                } else {//加入失败
                    map.put(CommonContants.SUCCESS, 0);
                    map.put(CommonContants.ERROR, -1);
                    return map;
                }
            } else {//代理商不存在
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -2);
                return map;
            }
        } else if (result == 1) {//用代理商mid添加
            int ck = robotFriendService.checkAgentHasAuth(parameters);
            if (ck < 1) {//代理商并未激活过这个机器人
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -9);
                return map;
            }
            int acount = memberAgentService.checkAgentIsExist(code);
            if (acount > 0) {
                RobotFriends rf = robotFriendService.queryRobotFriendByCodeAndMid(parameters);
                if (rf != null) {//已经添加过好友的
                    return respAddFriendSuccess(map, addMid);
                }
                boolean b = checkAddFriendIsInDbByGameType(gameType, addMid, code);
                if (b) {//与待开房表匹配正确
                    RobotFriends robotFriends = new RobotFriends();
                    robotFriends.setAtime((int) (new Date().getTime() / 1000));
                    robotFriends.setAuthCode(code);
                    robotFriends.setMid(addMid);
                    robotFriends.setRobName(roboName);
                    robotFriends.setOwner(0);
                    int insertResult = robotFriendService.insertSelective(robotFriends);
                    if (insertResult > 0) {//加入机器人好友表成功
                        return respAddFriendSuccess(map, addMid);
                    } else {//加入失败
                        map.put(CommonContants.SUCCESS, 0);
                        map.put(CommonContants.ERROR, -3);
                        return map;
                    }
                } else {//与待开房表匹配错误
                    map.put(CommonContants.SUCCESS, 0);
                    map.put(CommonContants.ERROR, -4);
                    return map;
                }
            } else {//代理商不存在
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -7);
                return map;
            }
        } else {//代理商未购买机器人，查找不到
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -8);
            return map;
        }
    }

    // 响应添加好友成功给python机器人
    private Object respAddFriendSuccess(Map<String, Object> map, int addMid) {
        Map<String, Object> resultData = new HashMap<>();
        MemberFides memberFides = memberFidesService.selectByPrimaryKey(addMid);
        if (memberFides != null) {
            resultData.put("name", memberFides.getName());
        }
        map.put(CommonContants.SUCCESS, 1);
        map.put(CommonContants.DATA, resultData);
        map.put(CommonContants.ERROR, 0);
        return map;
    }

    // 检查要添加好友的用户是否在
    private boolean checkAddFriendIsInDbByGameType(int gameType, int addMid, int code) {
        switch (gameType) {
            case 6:
                return clubOpenRoomAddFriend(addMid, code);
            default:
                return commonOpenRoomAddFriend(addMid, code);
        }
    }

    //俱乐部开房添加好友
    private boolean clubOpenRoomAddFriend(int addMid,int code) {
        Map<String, Object> mids = new HashMap<>();
        mids.put("amid", code);
        mids.put("omid", addMid);
        AgentClubMember agentClubMember = agentClubMemberService.getMemberInfoByAmidOmid(mids);
        if (agentClubMember != null) {
            return true;
        } else {
            return false;
        }
    }

    //公共的添加好友
    private boolean commonOpenRoomAddFriend(int addMid,int code) {
        AgentMids agentMids = agentMidService.getAgentGrantByMid(addMid);
        int amid = agentMids.getAmid();
        int mid = agentMids.getMid();
        if (amid == code && mid == addMid) {//与待开房表匹配正确
            return true;
        } else {
            return false;
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////


    //处理开房请求
    private Object handleOpenRoomRequest(String data, int gameType, HttpServletRequest request, String cIp, int cPort) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int amid = Integer.parseInt(requestMap.get("amid") + "");//代理商mid
        int omid = Integer.parseInt(requestMap.get("mid") + "");//开房者mid
        long msgid = Long.parseLong(requestMap.get("msgid") + "");//消息id
        String robName = requestMap.get("robName") + "";//机器人名称
        int acount = memberAgentService.checkAgentIsExist(amid);
        int ocount = memberAgentService.checkAgentIsExist(omid);
        if (acount > 0 && ocount == 0) {
            return switchOpenRoomModeByGameType(map, gameType, ocount, amid, omid, msgid, robName,request,cIp,cPort);
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -3);
            return map;
        }
    }

    //根据游戏类型切换开房请求
    private Object switchOpenRoomModeByGameType(Map<String,Object> map,int gameType,int ocount,int amid,int omid
            ,long msgid,String robName, HttpServletRequest request, String cIp, int cPort) throws IOException {
        switch (gameType) {
            case 6:
                return handleClubGroupOpenRoomRequest(map,gameType, ocount, amid, omid, msgid, robName, request, cIp, cPort);
            default:
                return handleCommonOpenRoomRequest(map,gameType, ocount, amid, omid, msgid, robName, request, cIp, cPort);
        }
    }

    //操作俱乐部待开房请求
    private Object handleClubGroupOpenRoomRequest(Map<String, Object> map, int gameType, int ocount, int amid, int omid
            , long msgid, String robName, HttpServletRequest request, String cIp, int cPort) throws IOException {
        Map<String, Object> mids = new HashMap<>();
        mids.put("amid", amid);
        mids.put("omid", omid);
        AgentClubMember agentClubMember = agentClubMemberService.getMemberInfoByAmidOmid(mids);
        if (agentClubMember != null && agentClubMember.getCmid() == amid && agentClubMember.getMid() == omid) {
            return executeOpenRoom(map, amid, omid, msgid, robName, gameType, request, cIp, cPort);
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -2);
            return map;
        }
    }

    //默认公共的待开房请求操作
    private Object handleCommonOpenRoomRequest(Map<String,Object> map,int gameType,int ocount,int amid,int omid,long msgid
            ,String robName, HttpServletRequest request, String cIp, int cPort) throws IOException {
        AgentMids agentMids = agentMidService.getAgentGrantByMid(ocount);
        if (agentMids != null && agentMids.getAmid() == amid && agentMids.getMid() == omid) {
            return executeOpenRoom(map, amid, omid, msgid, robName, gameType, request, cIp, cPort);
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -2);
            return map;
        }
    }

    //执行待开房
    private Object executeOpenRoom(Map<String, Object> map, int amid, int omid, long msgid, String robName,int gameType
            ,HttpServletRequest request,String cIp,int cPort) throws IOException {
        RobotOpenRoom robotOpenRoom = new RobotOpenRoom();
        robotOpenRoom.setAmid(amid);
        robotOpenRoom.setOmid(omid);
        robotOpenRoom.setMsgid(msgid);
        robotOpenRoom.setRobName(robName);
        robotOpenRoom.setOdate((int) (new Date().getTime() / 1000));
        int r = robotOpenRoomService.insertSelective(robotOpenRoom);
        if (r > 0) {
            return handleOpenRoom(gameType,amid,omid,request,cIp,cPort,map);
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -1);
            return map;
        }
    }

    //请求c++待开房
    private Object handleOpenRoom(int gameType, int amid, int omid, HttpServletRequest request, String cIp
            , int cPort,Map<String, Object> map) throws IOException {
        String ip = CommonUtils.getIpAddr(request);
        int loginGp = 0;
        int userGp = 0;
        String sesskey = this.saveToken(omid, loginGp, userGp, ip, gameType);
        SocketUtils socketUtils = ShareLinkSwitchSocket.switchSocketUtilByGameType(gameType, loginGp, sesskey, cIp, cPort, omid);
        boolean login = socketUtils.writeToServer();//登录c++ 服务器
        if (login && socketUtils.receviveInteger() == 0) {//服务器返回0表示登录成功
            boolean openRoom = RobotOpenRoomByGameType.switchOpenRoomByGameType(gameType, socketUtils, amid);
            if (openRoom) {
                //return receviceServerResponse(map, socketUtils, gameType);
                Object o;
                try {
                    o = SocketUtils.callMethod(this, "checkTimeoutRecevice"
                            , new Class<?>[]{String.class,Map.class, SocketUtils.class, int.class}
                            , new Object[]{sesskey,map, socketUtils, gameType}
                            , 5);
                } catch (Exception e) {
                    map.put(CommonContants.SUCCESS, 0);
                    map.put(CommonContants.ERROR, -66);//c++服务器返回创建房间超时
                    return map;
                } finally {
                    socketUtils.close();
                    log.debug("----------::finally to close socket！");
                }
                return o;
            } else {
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -3);
                return map;
            }
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -5);
            return map;
        }
    }

    public Object checkTimeoutRecevice(String sesskey,Map<String, Object> map, SocketUtils socketUtils, int gameType) throws IOException {
        return receviceServerResponse(sesskey,map, socketUtils, gameType);
    }

    //接收c++服务器开房请求的响应
    private Object receviceServerResponse(String sesskey,Map<String, Object> map, SocketUtils socketUtils, int gameType) throws IOException {
        long t1 = System.currentTimeMillis();
        while (true) {
            long t2 = System.currentTimeMillis();
            if (t2 - t1 > 5000) {//五秒钟循环如果未加入成功则跳出循环
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -55);//c++服务器返回加入房间超时
                return map;
            } else {
                String joinResult = socketUtils.recviceOpenRoomResult();
                if (StringUtils.isNotBlank(joinResult)) {
                    int recvInt = Integer.parseInt(joinResult.split("_")[1]);
                    int cmd = Integer.parseInt(joinResult.split("_")[2]);
                    int roomid = Integer.parseInt(joinResult.split("_")[3]);
                    String errorMsg = RobotOpenRoomReData.handleRecvData(cmd, recvInt,gameType);//TODO 这里需要修改
                    if (StringUtils.isBlank(errorMsg) && recvInt > 0) {
                        String robotAppUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.ROBOT_APP_URL);
                        robotAppUrl += "api/shareLink/joinViewUi.html?sesskey=%s&roomtitle=%s&wanfa=%s&jushu=%d&roomid=%d";
                        robotAppUrl = String.format(robotAppUrl, sesskey,"title","wanfa",8,roomid);
                        /*Map<String, Object> data = new HashMap<>();
                        data.put("roomid", roomid);
                        data.put("roomtitle", "8888");
                        data.put("jushu", 8);*/
                        map.put(CommonContants.SUCCESS, 1);
                        map.put(CommonContants.DATA, robotAppUrl);
                        map.put(CommonContants.ERROR, 0);//成功加入房间
                        return JSON.toJSONString(map);
                    } else {
                        map.put(CommonContants.SUCCESS, 0);
                        map.put(CommonContants.ERROR, -6);//服务器返回错误码
                        return map;
                    }
                }
            }
        }
    }

}
