package com.qs.log.game.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.CacheConstan;
import com.qs.common.util.DateUtil;
import com.qs.common.util.SocketPacket;
import com.qs.common.util.SocketPacketUtil;
import com.qs.log.game.mapper.MailsMapper;
import com.qs.log.game.model.Mails;
import com.qs.log.game.service.IMailService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * Created by zun.wei on 2017/3/2.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MailServiceImpl implements IMailService {

    @Resource
    private MailsMapper mailsMapper;

    @Override
    public int deleteById(Integer id) {
        return mailsMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value={CacheConstan.EMAIL_CACHE_STORE_NAME},allEntries=true)
    public int add(Mails record) {
        record.setMktime(new Date(System.currentTimeMillis()));
        String fujian = record.getFujian();
        if (fujian != null) {
            String[] ms = fujian.split(",");
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (String m : ms) {
                Map<String, Object> midMap = new HashMap<>();
                midMap.put("number", m);
                mapList.add(midMap);
            }
            String mm = JSON.toJSONString(mapList);
            StringBuffer resultMids = new StringBuffer("{\"gold\":");
            resultMids.append(mm).append("}");
            record.setFujian(resultMids.toString());
            //这里邮件未发送.
        }
        return mailsMapper.insert(record);
    }

    @Override
    @CacheEvict(value={CacheConstan.EMAIL_CACHE_STORE_NAME},allEntries=true)
    public int addSelective(Mails record) {
        record.setMktime(new Date(System.currentTimeMillis()));
        String fujian = record.getFujian();
        if (fujian != null) {
            String[] ms = fujian.split(",");
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (String m : ms) {
                Map<String, Object> midMap = new HashMap<>();
                midMap.put("number", m);
                mapList.add(midMap);
            }
            String mm = JSON.toJSONString(mapList);
            StringBuffer resultMids = new StringBuffer("{\"gold\":");
            resultMids.append(mm).append("}");
            record.setFujian(resultMids.toString());
            this.sendMail2("127.0.0.1",8888);
            //System.out.println("resultMids = " + resultMids.toString());
        }
        return mailsMapper.insertSelective(record);
    }

    @Override
    public Mails loadById(Integer id) {
        return mailsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(Mails record) {
        return mailsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByIdWithBLOBs(Mails record) {
        return mailsMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Mails record) {
        return mailsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Mails> queryListByPage(Map<String, Object> parameters) {
        return mailsMapper.queryListByPage(parameters);
    }

    @Override
    public boolean sendMail(Integer mapType, Integer mapMsg, Integer cmdType,
                            Integer mid,String hostAddress,int hostPort) {
        try {
            Socket socket = new Socket(hostAddress, hostPort);
            String content = SocketPacket.getSendMailContent(mapType, mapMsg, cmdType, mid);
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
            pw.write(content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendMail2(String ip,Integer port) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("type", 3);
            map.put("msg", 0);
            String jsonMsg=JSON.toJSONString(map);
            SocketPacketUtil socketPacket = new SocketPacketUtil(ip,port);
            socketPacket.sendData(10008,123, jsonMsg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Mails> queryMailDetail(Map<String, Object> parameter) {
        return mailsMapper.queryMailDetail(parameter);
    }


}
