package com.qs.common.util;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/7/18 15:33.
 * Description:用于发送、接收个性socket请求给c++；
 */
public class SocketUtils {

    private byte[] buffer;

    private Integer packetSize = 0;

    private Integer mid = 0;

    private Integer cmdType = 0;

    private Socket socket = new Socket();

    private List<String> strings = new ArrayList<String>();

    private List<Integer> integers = new ArrayList<Integer>();

    public InputStream getSocketInputStream() throws IOException {
        return socket.getInputStream();
    }

    /**
     * @param ip      IP地址
     * @param port    端口
     * @param cmdType C++命令
     * @return
     * @Author:zun.wei , @Date:2017/7/19 11:46
     * @Description:创建socket
     */
    public SocketUtils createSocket(String ip, int port, Integer cmdType) {
        try {
            this.socket = new Socket(ip, port);
            this.cmdType = cmdType;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * @param mid 游戏mid
     * @return
     * @Author:zun.wei , @Date:2017/7/19 11:46
     * @Description:发送到哪个用户
     */
    public SocketUtils toWhom(Integer mid) {
        packetSize += 4;
        this.mid = mid;
        return this;
    }

    /**
     * @param parameter 参数
     * @return
     * @Author:zun.wei , @Date:2017/7/19 11:47
     * @Description:写String类型的消息
     */
    public SocketUtils stringParam(String parameter) {
        strings.add(parameter);
        parameter += '\0';
        packetSize += parameter.getBytes().length + 4;
        return this;
    }

    /**
     * @param parameter 参数
     * @return
     * @Author:zun.wei , @Date:2017/7/19 11:47
     * @Description:写Integer类型的消息
     */
    public SocketUtils integerParam(Integer parameter) {
        packetSize += 4;
        integers.add(parameter);
        return this;
    }

    /**
     * @return
     * @Author:zun.wei , @Date:2017/7/19 11:47
     * @Description:构建
     */
    //顺序：包头，mid,包体长度，String类型，Integer类型；
    public SocketUtils build() {
        this.buffer = new byte[6 + packetSize];//包头6位
        this.buffer[0] = 'Q';
        this.buffer[1] = 'S';
        this.buffer[2] = (byte) (packetSize & 0xff);
        this.buffer[3] = (byte) (packetSize >> 8 & 0xff);
        this.buffer[4] = (byte) (cmdType & 0xff);
        this.buffer[5] = (byte) (cmdType >> 8 & 0xff);

        int a = 0;
        if (mid != null) a += 6;
        System.arraycopy(toLH(mid), 0, buffer, a, 4);//拷贝到buffer的第6位开始（前六位是包头）

        int b = 0;
        for (int i = 0; i < integers.size(); i++) {//Integer类型
            if (b == 0) b = a + 4;
            System.arraycopy(toLH(integers.get(i)), 0, buffer, b, 4);//告知长度
            b += 4;
        }

        for (int i = 0; i < strings.size(); i++) {//String类型
            if (b == 0) b = a + 4;
            String msg = strings.get(i);
            msg += '\0';
            Integer msgLen = msg.getBytes().length;
            System.arraycopy(toLH(msgLen), 0, buffer, b, 4);//告知文本长度
            System.arraycopy(msg.getBytes(), 0, buffer, b + 4, msgLen);//告知文本内容
            b += msgLen;
        }

        return this;
    }

    /**
     * @return
     * @Author:zun.wei , @Date:2017/7/19 11:48
     * @Description:执行写.
     */
    public boolean writeToServer() {
        if (null != socket) {
            try {
                socket.getOutputStream().write(buffer);
                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void readOverData() throws IOException {
        if (null != socket) {
            socket.shutdownInput();
        }
    }

    public void close() throws IOException {
        if (null != socket) {
            socket.close();
        }
    }

    /**
     * 将int转为低字节在前，高字节在后的byte数组
     */
    private static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    private static int bytes2Integer(byte[] byteVal) {
        int result = 0;
        for (int i = 0; i < byteVal.length; i++) {
            int tmpVal = (byteVal[i] << (8 * (3 - i)));
            switch (i) {
                case 0:
                    tmpVal = tmpVal & 0xFF000000;
                    break;
                case 1:
                    tmpVal = tmpVal & 0x00FF0000;
                    break;
                case 2:
                    tmpVal = tmpVal & 0x0000FF00;
                    break;
                case 3:
                    tmpVal = tmpVal & 0x000000FF;
                    break;
            }
            result = result | tmpVal;
        }
        return result;
    }

    /**
     * @Author:zun.wei , @Date:2017/7/19 19:28
     * @Description:获取socket返回的Integer数据
     * @param inputStream InputStream
     * @return
     * @throws IOException
     */
    public Integer getIntegerData(InputStream inputStream) throws IOException {
        byte[] recvHead = new byte[2];
        inputStream.read(recvHead, 0, 2);
        String DataStr = new String(recvHead);
        System.out.println("conpany abbreviation" + DataStr);

        byte[] recvPacketSize = new byte[2];
        inputStream.read(recvPacketSize, 0, 2);
        int len = bytes2Integer(toLH(bytes2Integer(recvPacketSize)));
        System.out.println("len=======::" + len);

        byte[] recvCmd = new byte[2];
        inputStream.read(recvCmd, 0, 2);
        int cmd = bytes2Integer(toLH(bytes2Integer(recvCmd)));
        System.out.println("cmd===::" + cmd);

        byte[] recvData = new byte[4];
        inputStream.read(recvData, 0, 4);
        System.out.println("integer data ====::" + bytes2Integer(toLH(bytes2Integer(recvData))));
        readOverData();
        close();
        return bytes2Integer(toLH(bytes2Integer(recvData)));
    }

    public String getStringData(InputStream inputStream) throws IOException {
        byte[] recvHead = new byte[2];
        inputStream.read(recvHead, 0, 2);
        String DataStr = new String(recvHead);
        System.out.println("conpany abbreviation" + DataStr);

        byte[] recvPacketSize = new byte[2];
        inputStream.read(recvPacketSize, 0, 2);
        int len = bytes2Integer(toLH(bytes2Integer(recvPacketSize)));
        System.out.println("len=======::" + len);

        byte[] recvCmd = new byte[2];
        inputStream.read(recvCmd, 0, 2);
        int cmd = bytes2Integer(toLH(bytes2Integer(recvCmd)));
        System.out.println("cmd===::" + cmd);

        byte[] recvData = new byte[len];
        inputStream.read(recvData, 0, len);
        String str = new String(recvData);
        System.out.println("string data ====::" + str);
        readOverData();
        close();
        return str;
    }

   /* public static void main(String[] args) throws IOException {
        Map<String, Object> jsonMsgMap = new HashMap<String, Object>();
        jsonMsgMap.put("type", 3);
        jsonMsgMap.put("msg", 0);
        String jsonMsg = JSON.toJSONString(jsonMsgMap);
        SocketUtils socketUtils = new SocketUtils().createSocket("192.168.1.142", 9040, 1000)//9040
                .toWhom(52171).stringParam(jsonMsg).build();
        boolean result = socketUtils.writeToServer();
        System.out.println("result = " + result);

        InputStream inputStream = socketUtils.getSocketInputStream();

        //System.out.println(socketUtils.getIntegerData(inputStream));

        byte[] recvHead = new byte[2];
        inputStream.read(recvHead, 0, 2);
        String DataStr = new String(recvHead);
        System.out.println(DataStr);

        byte[] recvPacketSize = new byte[2];
        inputStream.read(recvPacketSize, 0, 2);
        int len = bytes2Integer(toLH(bytes2Integer(recvPacketSize)));
        System.out.println("len=======::" + len);

        byte[] recvCmd = new byte[2];
        inputStream.read(recvCmd, 0, 2);
        int cmd = bytes2Integer(toLH(bytes2Integer(recvCmd)));
        System.out.println("cmd===::" + cmd);

        byte[] recvData = new byte[4];
        inputStream.read(recvData, 0, 4);
        System.out.println(bytes2Integer(toLH(bytes2Integer(recvData))));

    }*/

}
