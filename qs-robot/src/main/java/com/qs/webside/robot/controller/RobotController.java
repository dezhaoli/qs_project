package com.qs.webside.robot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.robot.model.RobotRoomCfgDf;
import com.qs.webside.robot.model.RobotRoomConfig;
import com.qs.webside.robot.service.IRobotRoomCfgDfService;
import com.qs.webside.robot.service.IRobotRoomConfigService;
import com.qs.webside.robot.service.IRobotService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/11 15:56.
 * Description:机器人控制器
 */
@Controller
@RequestMapping(value = "/api/robot/")
public class RobotController extends BaseController{

    private Logger log = Logger.getLogger(RobotController.class);

    @Resource
    private IRobotService robotService;

    @Resource
    private RedisTemplate redisTemplate;
    
    @Autowired 
    private IRobotRoomConfigService robotRoomConfigService;
    
    @Autowired
    private IRobotRoomCfgDfService robotRoomCfgDfService;
    

    @Value("${game.gametype}")
    private int gameType;

    @Value("${game.goldhost}")
    private String cIp;

    @Value("${game.goldport}")
    private int cPort;

    /**
     * @Author:zun.wei , @Date:2017/8/10 19:40
     * @Description:接收python发送过来的数据请求
     * @param data 请求参数
     * @param type 请求类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sendMsgToJava.html", method = RequestMethod.POST)
    public Object responseMsgToPython(String data,int type,HttpServletRequest request) throws IOException {
        return robotService.handlePythonRequest(type, data,gameType,cIp,cPort,request);
    }


    /**
     * @Author:zsf , @Date:2017/8/17 18:24
     * @Description:用户存储机器人创建房间的配置
     * @param wanfa 中文玩法名字
     * @param data  机器人开房的数据
     * @param roomType  标题
     * @param ownuser 用户上次给机器人配置房间的信息
     * @param subset  子集
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveRobotGameCfg.html", method = RequestMethod.POST)
    public Object robotRoomConfig(BaseRequest baseRequest,@RequestParam(name="wanfa",defaultValue="")String wanfa,
    		@RequestParam(name="data",defaultValue="")String data,int roomType,@RequestParam(name="ownuser",defaultValue="")String ownuser,
    		@RequestParam(name="roomName",defaultValue="")String roomName,int subset) throws IOException {
    	
    	Map<String,Object> result=new HashMap<>();
		int count=0;
        wanfa = wanfa.replaceAll(" ", "+");
        roomName = roomName.replaceAll(" ", "+");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(wanfa);
        byte[] c = decoder.decodeBuffer(roomName);
        String wf = new String(b, "utf-8");
        String rn = new String(c, "utf-8");
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        int mid = token.getMid();
        RobotRoomConfig rrc=new RobotRoomConfig();
        rrc.setMid(mid);
        rrc.setWanfa(wf);
        rrc.setData(data);
        rrc.setRoomType(roomType);
        rrc.setOwnuser(ownuser);
        rrc.setGameType(gameType);
        rrc.setExt1(String.valueOf(subset));
        rrc.setRoomName(rn);
        System.out.println(rrc.toString());
        count=robotRoomConfigService.insertOrUpdate(rrc);
        if (count>0){
			result.put(CommonContants.SUCCESS,true);
			result.put(CommonContants.MESSAGE,"编辑成功！");
		}else {
			result.put(CommonContants.SUCCESS,false);
			result.put(CommonContants.MESSAGE,"编辑失败！");
		}
		return this.getReturnData(result, AppConstants.Result.SUCCESS);
    }
    
    
    /**
     * 根据游戏id拉取机器人最后的默认创建房间的配置
     * @author zsf
     * @param baseRequest
     * @return用户机器人配置房间的配置
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "pullRobotGameCfg.html", method = RequestMethod.POST)
    public Object robotRoomConfig(BaseRequest baseRequest) throws IOException {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        List<Map<String,Object>> rrcList=robotRoomConfigService.getRobotRoomCfg(token.getMid());
        if (rrcList == null || rrcList.size() < 1) {
            rrcList = robotRoomCfgDfService.queryRobotConfig();
        }
        return this.getReturnData(rrcList, AppConstants.Result.SUCCESS);
    }
   
    /**
     * @Author:zsf , @Date:2017/8/18 18:24
     * @Description:根据用户id查询用户是否有机器人的权利
     * @param Sssskey
     * @return 返回map,ture代表有这个权限，false代表没有这个权限
     */
    @ResponseBody
    @RequestMapping(value = "queryUserRobotPower.html", method = RequestMethod.POST)
    public Object queryUserRobotPower(BaseRequest baseRequest) throws IOException {
    	
    	Map<String,Object> result=new HashMap<>();
		int count=0;
        
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        int mid = token.getMid();
        
        count=robotService.queryUserRobotPower(mid);
        if (count>0){
			result.put(CommonContants.SUCCESS,true);
			result.put(CommonContants.MESSAGE,"该用户具有机器人权限！");
		}else {
			result.put(CommonContants.SUCCESS,false);
			result.put(CommonContants.MESSAGE,"该用户没有机器人权限！");
		}
		return this.getReturnData(result, AppConstants.Result.SUCCESS);
    }
    
    
    /**
     * @Author:zsf , @Date:2017/8/17 18:24
     * @Description:用户存储机器人创建房间的配置
     * @param wanfa 中文玩法名字
     * @param data  机器人开房的数据
     * @param roomType  标题
     * @param ownuser 用户上次给机器人配置房间的信息
     * @return map
     */
    @ResponseBody
    @RequestMapping(value = "saveRobotGameCfgDf.html", method = RequestMethod.POST)
    public Object robotRoomConfigDf(BaseRequest baseRequest,@RequestParam(name="wanfa",defaultValue="")String wanfa,
    		@RequestParam(name="data",defaultValue="")String data,int roomType,@RequestParam(name="ownuser",defaultValue="")String ownuser,
    		@RequestParam(name="roomName",defaultValue="")String roomName) throws IOException {
    	
    	Map<String,Object> result=new HashMap<>();
		int count=0;
        wanfa = wanfa.replaceAll(" ", "+");
        roomName = roomName.replaceAll(" ", "+");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(wanfa);
        byte[] c = decoder.decodeBuffer(roomName);
        String wf = new String(b, "utf-8");
        String rn = new String(c, "utf-8");
        RobotRoomCfgDf rrc=new RobotRoomCfgDf();
        rrc.setWanfa(wf);
        rrc.setData(data);
        rrc.setRoomType(roomType);
        rrc.setOwnuser(ownuser);
        rrc.setGameType(gameType);
        rrc.setRoomName(rn);
        System.out.println(rrc.toString());
        count=robotRoomCfgDfService.insertOrUpdate(rrc);
        if (count>0){
			result.put(CommonContants.SUCCESS,true);
			result.put(CommonContants.MESSAGE,"编辑成功！");
		}else {
			result.put(CommonContants.SUCCESS,false);
			result.put(CommonContants.MESSAGE,"编辑失败！");
		}
		return this.getReturnData(result, AppConstants.Result.SUCCESS);
    }
}
