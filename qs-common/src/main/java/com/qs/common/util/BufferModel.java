package com.qs.common.util;

import java.io.Serializable;

/**
 * Created by zun.wei on 2017/3/3.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class BufferModel implements Serializable {

    private char[] msgFlag = {'Q', 'S'};
    private int shortType = 0;
    private int intType = 1;
    private int longType = 2;
    private int stringType = 3;

    private StringBuilder strMsg = new StringBuilder();

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

    public BufferModel() {
        strMsg.append(msgFlag);
    }

    public void writePacketLen(short msgLen) {
        strMsg.append(msgLen);
    }

    public void writeCmd(short cmd) {
        strMsg.append(cmd);
    }

    public void WriteInt(int value) {
        strMsg.append(toLH(intType));//表示数据类型
        strMsg.append(toLH(4));//表示数据类型对应的长度
        strMsg.append(value);//表示具体数据的值。
    }

    public void WriteString(String value) {
        strMsg.append(2);
        strMsg.append(value.length());
        strMsg.append(value);
    }

    public void WriteLong(long value) {
        strMsg.append(3);
        strMsg.append(8);
        strMsg.append(value);
    }

    public void WriteShort(short value) {
        strMsg.append(4);
        strMsg.append(2);
        strMsg.append(value);
    }



}
