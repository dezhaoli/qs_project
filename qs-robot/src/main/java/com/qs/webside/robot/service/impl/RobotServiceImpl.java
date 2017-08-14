package com.qs.webside.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.SocketUtils;
import com.qs.mainku.game.model.AgentMids;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.service.IAgentMidService;
import com.qs.mainku.game.service.IMemberAgentService;
import com.qs.mainku.game.service.IMemberFidesService;
import com.qs.mainku.game.service.impl.ShareLinkSwitchSocket;
import com.qs.webside.robot.mapper.RobotMapper;
import com.qs.webside.robot.model.Robot;
import com.qs.webside.robot.model.RobotFriends;
import com.qs.webside.robot.model.RobotOpenRoom;
import com.qs.webside.robot.service.IRobotFriendService;
import com.qs.webside.robot.service.IRobotLogService;
import com.qs.webside.robot.service.IRobotOpenRoomeService;
import com.qs.webside.robot.service.IRobotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/11 18:27.
 * Description:机器人业务层
 */
@Service
public class RobotServiceImpl implements IRobotService {

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
    private IRobotOpenRoomeService robotOpenRoomeService;

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
    public Object handlePythonRequest(int type, String data, int gameType, String cIp, int cPort) {
        switch (type) {
            case 1:// 添加机器人为好友请求
                return handleAddFriend(data);
            case 2:// 开房请求
                if (gameType == 6) {
                    return handleOpenRoomRequestGDMajiang(data, gameType);
                }
                return handleOpenRoomRequest(data,gameType);
        }
        return null;
    }

    //添加机器人为好友
    private Object handleAddFriend(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int code = (int) requestMap.get("authCode");
        int addMid = (int) requestMap.get("mid");
        String roboName = requestMap.get("robName") + "";
        int result = robotMapper.checkAuthCodeOrMidExist(code);
        if (result == 0) {//用授权码添加好友
            int acount = memberAgentService.checkAgentIsExist(addMid);
            if (acount > 0) {//代理商存在
                RobotFriends robotFriends = new RobotFriends();
                robotFriends.setAtime((int) (new Date().getTime() / 1000));
                robotFriends.setAuthCode(code);
                robotFriends.setMid(addMid);
                robotFriends.setRobName(roboName);
                robotFriends.setOwner(1);
                int insertResult = robotFriendService.insertSelective(robotFriends);
                if (insertResult > 0) {//加入机器人好友表成功
                    Map<String, Object> resultData = new HashMap<>();
                    MemberFides memberFides = memberFidesService.selectByPrimaryKey(addMid);
                    if (memberFides != null) {
                        resultData.put("name", memberFides.getName());
                    }
                    map.put(CommonContants.SUCCESS, 1);
                    map.put(CommonContants.DATA, resultData);
                    map.put(CommonContants.ERROR, 0);
                    return map;
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
            int acount = memberAgentService.checkAgentIsExist(code);
            if (acount > 0) {
                AgentMids agentMids = agentMidService.getAgentGrantByMid(addMid);
                if (agentMids != null) {//在待开房
                    int amid = agentMids.getAmid();
                    int mid = agentMids.getMid();
                    if (amid == code && mid == addMid) {//与待开房表匹配正确
                        RobotFriends robotFriends = new RobotFriends();
                        robotFriends.setAtime((int) (new Date().getTime() / 1000));
                        robotFriends.setAuthCode(code);
                        robotFriends.setMid(addMid);
                        robotFriends.setRobName(roboName);
                        robotFriends.setOwner(0);
                        int insertResult = robotFriendService.insertSelective(robotFriends);
                        if (insertResult > 0) {//加入机器人好友表成功
                            Map<String, Object> resultData = new HashMap<>();
                            MemberFides memberFides = memberFidesService.selectByPrimaryKey(addMid);
                            if (memberFides != null) {
                                resultData.put("name", memberFides.getName());
                            }
                            map.put(CommonContants.SUCCESS, 1);
                            map.put(CommonContants.ERROR, 0);
                            map.put(CommonContants.DATA, resultData);
                            return map;
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
                } else {//没有被任何人授权待开房过
                    map.put(CommonContants.SUCCESS, 0);
                    map.put(CommonContants.ERROR, -6);
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

    //处理开房请求
    private Object handleOpenRoomRequest(String data, int gameType) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int amid = (int) requestMap.get("amid");//代理商mid
        int omid = (int) requestMap.get("mid");//开房者mid
        int msgid = (int) requestMap.get("msgid");//消息id
        String robName = requestMap.get("robName") + "";//机器人名称
        int acount = memberAgentService.checkAgentIsExist(amid);
        int ocount = memberAgentService.checkAgentIsExist(omid);
        if (acount > 0 && ocount == 0) {
            AgentMids agentMids = agentMidService.getAgentGrantByMid(ocount);
            if (agentMids != null && agentMids.getAmid() == amid && agentMids.getMid() == omid) {
                RobotOpenRoom robotOpenRoom = new RobotOpenRoom();
                robotOpenRoom.setAmid(amid);
                robotOpenRoom.setOmid(omid);
                robotOpenRoom.setMsgid(msgid);
                robotOpenRoom.setRobName(robName);
                robotOpenRoom.setOdate((int) (new Date().getTime() / 1000));
                int r = robotOpenRoomeService.insertSelective(robotOpenRoom);
                if (r > 0) {
                    //TODO 执行代理开房
                    Map<String, Object> resultData = new HashMap<>();

                    map.put(CommonContants.SUCCESS, 1);
                    map.put(CommonContants.DATA, resultData);
                    map.put(CommonContants.ERROR, 0);
                    return map;
                } else {
                    map.put(CommonContants.SUCCESS, 0);
                    map.put(CommonContants.ERROR, -1);
                    return map;
                }
            } else {
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -2);
                return map;
            }
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -3);
            return map;
        }
    }

    //处理广东麻将开房请求
    private Object handleOpenRoomRequestGDMajiang(String data, int gameType) {

        return null;
    }

    //请求c++待开房
    private void handleOpenRoom(int gameType, int amid, int omid) {
        SocketUtils socketUtils = ShareLinkSwitchSocket.switchSocketUtilByGameType(gameType, gp, sesskey, cIp, cPort, mid);

    }

}
