package com.qs.mainku.game.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.AppConstants;
import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.service.IMemberFidesService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by zun.wei on 2017/7/28 9:18.
 * Description:
 */
@Service
public class ShareLinkBaseServiceImpl{

    Logger log = Logger.getLogger(ShareLinkBaseServiceImpl.class);

    @Resource
    private RedisTemplate redisTemplate;

    /*@Resource
    private MemberService memberService;*/

    @Resource
    private IMemberFidesService memberFidesService;

    @Resource
    private MemcachedClient memcachedClient;

    /**
     * @Author:zun.wei , @Date:2017/7/26 20:29
     * @Description:解密玩法
     * @param roomInfoMap 房间信息
     * @param model
     * @param roomid
     * @throws IOException
     */
    public void decodeRoomInfo(Map<String, Object> roomInfoMap, Model model, int roomid) throws IOException {
        Map<String, Object> infoMap = null;
        if (roomInfoMap != null) {
            infoMap = roomInfoMap;
            redisTemplate.opsForValue().set(AppConstants.RedisKeyPrefix.SHARE_LINK_JOIN_ROOM_INFO_CACHE + roomid, roomInfoMap, 60, TimeUnit.MINUTES);
        } else {
            Object o = redisTemplate.opsForValue().get(AppConstants.RedisKeyPrefix.SHARE_LINK_JOIN_ROOM_INFO_CACHE + roomid);
            infoMap = o != null ? (Map<String, Object>) o : null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(infoMap == null ? "" : infoMap.get("wanfaEncode") + "");
        String wanfadeCode = new String(b, "utf-8");
        log.debug("wanfa encode is ----:: " + (infoMap == null ? "" : infoMap.get("wanfaEncode")) + " wanfa decode is ----:: " + wanfadeCode);
        model.addAttribute("wfList", wanfadeCode);//玩法
        model.addAttribute("roomtitle", new String(decoder.decodeBuffer(infoMap == null ? "" : infoMap.get("roomtitle") + ""), "utf-8"));//房间标题
        model.addAttribute("jushu", infoMap == null ? "" : infoMap.get("jushu"));//玩牌局数
    }

    public List<Map<String, Object>> getRoomInfo(Model model, int roomid, int gameType) throws InterruptedException, MemcachedException, TimeoutException {
        switch (gameType) {
            case 6:
                return setGDMajiangRoomInfo(model,roomid);
            case 20:
                return setJXMajiangRoomInfo(model, roomid);
        }
        return null;
    }

    //广东麻将
    private List<Map<String, Object>> setGDMajiangRoomInfo(Model model, int roomid)
            throws InterruptedException, MemcachedException, TimeoutException {
        String roomInfo = memcachedClient.get("MAJIANGROOM|" + roomid + "");
        if (StringUtils.isNotBlank(roomInfo)) {
            Map<String, Object> infoMap = JSON.parseObject(roomInfo, Map.class);
            List<Map<String, Object>> seatsList = (List<Map<String, Object>>) infoMap.get("seats");
            if (seatsList != null) {
                List<Map<String, Object>> seats = new ArrayList<>();
                for (Map<String, Object> seat : seatsList) {
                    int seatMid = (int) seat.get("mid");
                    int seatno = (int) seat.get("seatno");
                    MemberFides memberfides = memberFidesService.selectByPrimaryKey(seatMid);
                    if (memberfides != null) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("mid", memberfides.getMid());
                        map.put("name", memberfides.getName());
                        map.put("icon", memberfides.getIcon());
                        map.put("seatno", seatno);//座位号
                        seats.add(map);
                    }
                }
                String seatsJson = JSON.toJSONString(seats);
                model.addAttribute("seats", seatsJson);//玩家信息
                log.debug("setGDMajiangRoomInfo room info seats:" + seatsJson );
                return seats;
            }
        } else {
            model.addAttribute("seats", null);//玩家信息
            log.debug("setGDMajiangRoomInfo play room is not exist --------::" );
        }
        return null;
    }

    //江西麻将
    private  List<Map<String, Object>> setJXMajiangRoomInfo(Model model, int roomid) {

        return null;
    }

}
