package com.qs.webside.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.RandomUtil;
import com.qs.common.util.SocketUtils;
import com.qs.common.util.crypto.MD5;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.service.*;
import com.qs.mainku.game.service.impl.ShareLinkSwitchSocket;
import com.qs.webside.robot.mapper.RobotMapper;
import com.qs.webside.robot.model.*;
import com.qs.webside.robot.service.*;
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
import java.util.List;
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
    private IMemberAgentService memberAgentService;

    @Resource
    private IMemberFidesService memberFidesService;

    @Resource
    private IRobotOpenRoomService robotOpenRoomService;

    @Resource
    private MemcachedClient memcachedClient;

    @Resource
    private IBaseParamService baseParamService;

    @Resource
    private IRobotRoomConfigService robotRoomConfigService;

    @Resource
    private IRobotRoomCfgDfService robotRoomCfgDfService;

    @Resource
    private IAgentRobotConfigService agentRobotConfigService;

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

    /**
     * @Author:zsf , @Date:2017/8/18 18:24
     * @Description:根据用户id查询用户是否有机器人的权利
     * @param Sssskey
     * @return
     */
    @Override
    public int queryUserRobotPower(int mid) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("mid", mid);
        map.put("date", new Date().getTime()/1000);
        return robotMapper.queryUserRobotPower(map);
    }


    @Override
    public int getOneRandomAuthCode() {
        while (true) {
            int ramdomCode = RandomUtil.generateAuthCode();
            int code = robotMapper.queryCountByAuthCode(ramdomCode);
            if (code == 0) return ramdomCode;
        }
    }

    @Override
    public Object handlePythonRequest(int type, String data, int gameType, String cIp, int cPort, HttpServletRequest request) throws IOException {
        switch (type) {
            case 1:// 添加机器人为好友请求
                return handleAddFriend(data,gameType);
            case 2:// 开房请求
                return handleOpenRoomRequest(data, gameType, request,cIp,cPort);
            case 3://请求获取开房类型代码
                //return handleRequestRoomTpye(data, gameType);
            case 4://显示配置机器人开房类型
                return handleShowRoomCfg(data, gameType);
            case 5://显示已配置机器人开房类型
                return handleShowAlreadyRoomCfg(data, gameType);
            case 6://查看指定房间类型中所有自己类型配置
                return showAgentRoomCfgByRoomType(data, gameType);
            case 7://保存机器人新的玩法配置
                return saveNewRobotGameCfg(data, gameType);
        }
        return null;
    }

    //保存机器人新的玩法配置
    private Object saveNewRobotGameCfg(String data, int gameType) {
        switch (gameType) {
            case 6:
                return saveNewGdMajiangRobotGameCfg(data);
            default:
                return saveNewGdMajiangRobotGameCfg(data);
        }
    }

    //保存广东麻将机器人新的玩法配置
    private Object saveNewGdMajiangRobotGameCfg(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int amid = Integer.parseInt(requestMap.get("amid") + "");//代理商mid
        int roomType = Integer.parseInt(requestMap.get("roomType") + "");//房间类型
        String robName = requestMap.get("robName") + "";//机器人名称
        int subset = Integer.parseInt(requestMap.get("subset") + "");//房间类型子集
        AgentRobotConfig agentRobotConfig = new AgentRobotConfig();
        agentRobotConfig.setMid(amid);
        agentRobotConfig.setRobotName(robName);
        agentRobotConfig.setRoomType(roomType);
        agentRobotConfig.setSubset(subset);
        int inUp = agentRobotConfigService.insertOrUpdate(agentRobotConfig);
        if (inUp > 0) {
            map.put(CommonContants.SUCCESS, 1);
            map.put(CommonContants.DATA, "编辑成功!");
            map.put(CommonContants.ERROR, 0);
            return JSON.toJSONString(map);
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.DATA, "失败:-1，编辑超时!");
            map.put(CommonContants.ERROR, -1);
            return JSON.toJSONString(map);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    //查看指定房间类型中所有自己类型配置
    private Object showAgentRoomCfgByRoomType(String data, int gameType) {
        switch (gameType) {
            case 6:
                return showGdMajiangAgentRoomCfgByRoomType(data);
            default:
                return showGdMajiangAgentRoomCfgByRoomType(data);
        }
    }

    //查看广东麻将指定房间类型中所有自己类型配置
    private Object showGdMajiangAgentRoomCfgByRoomType(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int amid = Integer.parseInt(requestMap.get("amid") + "");//代理商mid
        int roomType = Integer.parseInt(requestMap.get("roomType") + "");//房间类型
        //String robName = requestMap.get("robName") + "";//机器人名称
        //int code = Integer.parseInt(requestMap.get("code") + "");//开房者mid
        //long msgid = Long.parseLong(requestMap.get("msgid") + "");//消息id
        List<Map<String, Object>> cfgs = robotRoomConfigService.querySubsetByMidAndRoomType(amid, roomType);
        StringBuilder sb = new StringBuilder();
        if (cfgs != null && cfgs.size() > 0) {
            sb.append("房间类型为: ").append(roomType).append(" 的所有已配置的玩法如下:\n\n");
            for (Map<String, Object> cfg : cfgs) {
                Map<String, Integer> d = JSON.parseObject(cfg.get("data") + "", Map.class);
                sb.append("房间类型:").append(cfg.get("roomName")).append("  ").append(d.get("jushu")).append("局").append("\n");
                sb.append("玩法类型:").append(cfg.get("ext1")).append("\n").append(cfg.get("wanfa")).append("\n\n");
            }
            sb.append("请回复:").append(roomType).append("+").append("玩法类型(编号)").append("\n注意:“+”也要输入哦!");
        } else {
            sb.append("您尚未配置房间类型为: ").append(roomType).append(" 的玩法类型,请前往游戏客户端配置!");
        }
        map.put(CommonContants.SUCCESS, 1);
        map.put(CommonContants.DATA, sb.toString());
        map.put(CommonContants.ERROR, 0);
        return JSON.toJSONString(map);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //显示已配置机器人开房类型
    private Object handleShowAlreadyRoomCfg(String data, int gameType) {
        switch (gameType) {
            case 6:
                return handleShowGdMajiangAlreadyRoomCfg(data);
            default:
                return handleShowGdMajiangAlreadyRoomCfg(data);
        }
    }

    //显示广东已配置机器人开房类型
    private Object handleShowGdMajiangAlreadyRoomCfg(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int amid = Integer.parseInt(requestMap.get("amid") + "");//代理商mid
        //int code = Integer.parseInt(requestMap.get("code") + "");//开房者mid
        //long msgid = Long.parseLong(requestMap.get("msgid") + "");//消息id
        String robName = requestMap.get("robName") + "";//机器人名称
        RobotRoomConfig robotRoomConfig = robotRoomConfigService.queryRobotCfgByMidAndRobotName(amid, robName);
        if (robotRoomConfig != null) {//已经配置过了。
            Map<String, Integer> cfg = JSON.parseObject(robotRoomConfig.getData(), Map.class);
            String roomName = robotRoomConfig.getRoomName();
            Object jushu = cfg.get("jushu");
            String wanfa = robotRoomConfig.getWanfa();
            Object roomType = robotRoomConfig.getRoomType();
            Object subset = robotRoomConfig.getExt1();//子集
            StringBuffer sb = new StringBuffer();
            sb.append("房间类型:\n").append(roomName).append("  ").append(jushu).append("局").append("\n\n").append("玩法内容:\n").append(wanfa);
            map.put(CommonContants.SUCCESS, 1);
            map.put(CommonContants.DATA, sb.toString());
            map.put(CommonContants.ERROR, 0);
            return JSON.toJSONString(map);
        } else {//当前机器人未配置过
            String reData = "您还未配置过当前机器人窝！";
            map.put(CommonContants.SUCCESS, 1);
            map.put(CommonContants.DATA, reData);
            map.put(CommonContants.ERROR, 0);
            return JSON.toJSONString(map);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //“配置”，显示可选房间类型
    private Object handleShowRoomCfg(String data, int gameType) {
        return handleRequestRoomTpye(data,gameType);
    }

    // 返回各种房间类型对应的代码
    private Object handleRequestRoomTpye(String data, int gameType) {
        switch (gameType) {
            case 6:
                return handleGDMaJiangDfRoomTypeCode(data);
                default:
                    return handleGDMaJiangDfRoomTypeCode(data);
        }
    }

    //广东麻将默认对应房间类型代码
    private Object handleGDMaJiangDfRoomTypeCode(String data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int amid = Integer.parseInt(requestMap.get("amid") + "");//代理商mid
        int code = Integer.parseInt(requestMap.get("code") + "");//开房者mid
        long msgid = Long.parseLong(requestMap.get("msgid") + "");//消息id
        String robName = requestMap.get("robName") + "";//机器人名称
        int acount = memberAgentService.checkAgentIsExist(amid);
        if (acount < 1) {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -2);
            return map;
        }
        RobotOpenRoom robotOpenRoom = new RobotOpenRoom();
        robotOpenRoom.setAmid(amid);
        robotOpenRoom.setOmid(code);
        robotOpenRoom.setMsgid(msgid);
        robotOpenRoom.setRobName(robName);
        robotOpenRoom.setOdate((int) (new Date().getTime() / 1000));
        int r = robotOpenRoomService.insertSelective(robotOpenRoom);
        if (r == 1) {//select room_type as roomType,room_name as roomName
            List<Map<String, Object>> dfCfg = robotRoomCfgDfService.findRobotRoomCigInfo();
            if (dfCfg != null && dfCfg.size() > 0) {
                String reData = "请选择房间类型，发送如:编号\n\n";
                reData += "各种房间类型对应编号如下:\n";
                for (Map<String, Object> cf : dfCfg) {
                    reData += "编号:" + cf.get("roomType") + "    " + cf.get("roomName") + "\n";
                }
                //reData += "\n\n提示:如果你能记住编号，也可以直接发送:\"编号\"哦!";
                reData += "请回复相应的房间类型编号，查看该房间类型下的所有已配置的玩法类型。\n如:回复数字1";
                map.put(CommonContants.DATA, reData);
            } else {
                map.put(CommonContants.DATA, "未发现默认房间配置!");
            }
            map.put(CommonContants.SUCCESS, 1);
            map.put(CommonContants.ERROR, 0);
            return JSON.toJSONString(map);
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -1);
            return JSON.toJSONString(map);
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
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -77);//不允许代理商之外的人添加
            return map;
        } else if (result == 2) {//表示代理商要添加为好友且机器人已激活
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
                if (insertResult > 0) {//加入机器人好友表成功
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


    /////////////////////////////////////////////////////////////////////////////////////////////////


    //处理开房请求
    private Object handleOpenRoomRequest(String data, int gameType, HttpServletRequest request, String cIp, int cPort) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> requestMap = JSON.parseObject(data, Map.class);
        int code = Integer.parseInt(requestMap.get("code") + "");//代理商mid
        int amid = Integer.parseInt(requestMap.get("amid") + "");//开房者mid
        long msgid = Long.parseLong(requestMap.get("msgid") + "");//消息id
        //int roomType = Integer.parseInt(requestMap.get("roomType") + "");//开房类型
        String robName = requestMap.get("robName") + "";//机器人名称
        Map<String, Object> pa = new HashMap<>();
        pa.put("date", new Date().getTime() / 1000L);
        pa.put("code", code);
        pa.put("amid", amid);
        int coun = robotMapper.queryUserRobotPower(pa);
        if (coun < 1) {//没有找到机器人，或者已过期
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -33);
            return JSON.toJSONString(map);
        }
        RobotOpenRoom robotOpenRoom = new RobotOpenRoom();
        robotOpenRoom.setAmid(amid);
        robotOpenRoom.setOmid(code);//原来是待开房那个人，现在是代理商的授权码
        robotOpenRoom.setMsgid(msgid);
        robotOpenRoom.setRobName(robName);
        robotOpenRoom.setOdate((int) (new Date().getTime() / 1000));
        int r = robotOpenRoomService.insertSelective(robotOpenRoom);
        if (r > 0) {
            int acount = memberAgentService.checkAgentIsExist(amid);
            if (acount > 0) {//代理商存在
                return handleOpenRoom(gameType,amid,request,cIp,cPort,map,robName);
            } else {
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -3);
                return JSON.toJSONString(map);
            }
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -1);
            return JSON.toJSONString(map);
        }
    }

    //请求c++待开房
    private Object handleOpenRoom(int gameType, int amid, HttpServletRequest request, String cIp
            , int cPort,Map<String, Object> map,String robName) throws IOException {
        String ip = CommonUtils.getIpAddr(request);
        int loginGp = 0;
        int userGp = 0;
        int omid = 88888888;//规定此号为机器人待开房账号
        String sesskey = this.saveToken(omid, loginGp, userGp, ip, gameType);
        SocketUtils socketUtils = ShareLinkSwitchSocket.switchSocketUtilByGameType(gameType, loginGp, sesskey, cIp, cPort, omid);
        boolean login = socketUtils.writeToServer();//登录c++ 服务器
        int lo = socketUtils.receviveInteger();
        if (login && lo == 0) {//服务器返回0表示登录成功
            Map<String,Object> openRoom = RobotOpenRoomByGameType.switchOpenRoomByGameType(gameType, socketUtils, amid,omid,robName,robotRoomConfigService,robotRoomCfgDfService);
            if ((Boolean) openRoom.get(CommonContants.SUCCESS)) {
                Object o;
                try {
                    o = SocketUtils.callMethod(this, "checkTimeoutRecevice"
                            , new Class<?>[]{String.class,Map.class, SocketUtils.class, int.class,Map.class}
                            , new Object[]{sesskey,map, socketUtils, gameType,openRoom}
                            , 5);
                } catch (Exception e) {
                    map.put(CommonContants.SUCCESS, 0);
                    map.put(CommonContants.ERROR, -66);//c++服务器返回创建房间超时
                    return JSON.toJSONString(map);
                } finally {
                    socketUtils.close();
                    log.debug("----------::finally to close socket！");
                }
                return o;
            } else {
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -3);
                return JSON.toJSONString(map);
            }
        } else {
            map.put(CommonContants.SUCCESS, 0);
            map.put(CommonContants.ERROR, -5);
            return JSON.toJSONString(map);
        }
    }

    public Object checkTimeoutRecevice(String sesskey,Map<String, Object> map, SocketUtils socketUtils, int gameType,Map<String,Object> openRoom) throws IOException {
        return receviceServerResponse(sesskey,map, socketUtils, gameType,openRoom);
    }

    //接收c++服务器开房请求的响应
    private Object receviceServerResponse(String sesskey,Map<String, Object> map, SocketUtils socketUtils, int gameType,Map<String,Object> openRoom) throws IOException {
        long t1 = System.currentTimeMillis();
        while (true) {
            long t2 = System.currentTimeMillis();
            if (t2 - t1 > 5000) {//五秒钟循环如果未加入成功则跳出循环
                map.put(CommonContants.SUCCESS, 0);
                map.put(CommonContants.ERROR, -55);//c++服务器返回加入房间超时
                return JSON.toJSONString(map);
            } else {
                String joinResult = socketUtils.receviveInteger1(1102);
                if (StringUtils.isNotBlank(joinResult)) {
                    int recvInt = Integer.parseInt(joinResult.split("_")[1]);
                    int cmd = Integer.parseInt(joinResult.split("_")[2]);
                    int roomid = Integer.parseInt(joinResult.split("_")[3]);
                    String errorMsg = RobotOpenRoomReData.handleRecvData(cmd, recvInt,gameType);//TODO 这里需要修改
                    if (StringUtils.isBlank(errorMsg) && recvInt == 0) {
                        String robotAppUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.ROBOT_APP_URL);
                        robotAppUrl += "api/shareLink/joinViewUi.html?sesskey=%s&t=%d&d=%d&a=%d&roomid=%d";
                        robotAppUrl = String.format(robotAppUrl, sesskey,openRoom.get("t"),openRoom.get("d"),openRoom.get("a"),roomid);
                        StringBuilder sb = new StringBuilder("\n\n");
                        sb.append("房间类型:  ").append(openRoom.get("roomName")).append("   ").append(openRoom.get("jushu")).append("局\n\n");
                        sb.append("玩法如下：\n").append((openRoom.get("wanfa") + "").replaceAll(",",",  ")).append("\n\n").append(robotAppUrl);
                        map.put(CommonContants.SUCCESS, 1);
                        map.put(CommonContants.DATA, sb.toString());
                        map.put(CommonContants.ERROR, 0);//成功加入房间
                        return JSON.toJSONString(map);
                    } else {
                        map.put(CommonContants.SUCCESS, 0);
                        map.put(CommonContants.ERROR, -6);//服务器返回错误码
                        return JSON.toJSONString(map);
                    }
                }
            }
        }
    }

}
