package com.qs.common.util;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

/**
 * java与c++通信
 * @author moys
 *
 */
public class SocketPacketUtil {

    Logger log = Logger.getLogger(SocketPacketUtil.class);
    
	private Socket socket = null;
	
	

	public Socket getSocket()
	{
		return socket;
	}


	public SocketPacketUtil(String ip,int port) {
		try {
			this.socket = new Socket(ip, port);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	private byte[] buffer;
    private Integer packetSize=0;
    private Integer msgLength=0;
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
    
	public static int bytes2Integer(byte[] byteVal) {
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
	
	  //字节到字符转换

	  public static char byteToChar(byte[] b){
	    int s=0;
	    
	    if(b[0]>0)
	      s+=b[0];

	    else
	      s+=256+b[0];

	    s*=256;

	    if(b[1]>0)
	      s+=b[1];
	    else
	      s+=256+b[1];
	    
	    char ch=(char)s;
	    return ch;

	  }



 
    public Boolean sendData(Integer cmdType,Integer mid,String jsonMsg) {
    	
    	//mid长度
        packetSize=4;
    	jsonMsg += '\0';
    	Integer msgLen = jsonMsg.length();
    	msgLength=msgLen;
    	packetSize+=msgLen+4;
    	packetSize+=6;
    	this.buffer = new byte[6 + packetSize];
    	this.buffer[0] = 'Q';
    	this.buffer[1] = 'S';
    	this.buffer[2] = (byte) (this.packetSize & 0xff);
    	this.buffer[3] = (byte) (this.packetSize >> 8 & 0xff);
    	this.buffer[4] = (byte) (cmdType & 0xff);
    	this.buffer[5] = (byte) (cmdType >> 8 & 0xff);

        
        System.arraycopy(toLH(mid), 0, buffer, 6, 4);
        System.arraycopy(toLH(msgLen), 0, buffer, 10, 4);
        System.arraycopy(jsonMsg.getBytes(), 0, buffer, 14, msgLen);
    	try {
			socket.getOutputStream().write(this.buffer);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;

    }

	
    public Boolean receiveData() {
      	byte[] recvHead =new byte[4] ;
    	try {
			socket.getInputStream().read(recvHead,0,4);
			log.debug("receiveData result==::"+this.bytes2Integer(this.toLH(this.bytes2Integer(recvHead))));
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
    public void close() {
    	if(null!=socket){
    		try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    public void receiveDataTest() {
    	
      	byte[] recvHead =new byte[2] ;
      	byte[] recvHead2 =new byte[4] ;
    	byte[] recvHead3 =new byte[msgLength-1] ;
    	
    
    	  
    	System.out.println("buffer==::"+buffer[0]);
    	recvHead[0]=this.buffer[0];
    	recvHead[1]=this.buffer[1];
    	
    	
    	recvHead2[0]=this.buffer[6];
    	recvHead2[1]=this.buffer[7];
    	recvHead2[2]=this.buffer[8];
    	recvHead2[3]=this.buffer[9];
    	
  	  System.arraycopy(buffer, 14, recvHead3, 0, msgLength-1);
  	  
  	  System.out.println("content==::"+new String(recvHead3));
    	
      System.out.println("qs==::"+new String(recvHead));
    	    	
      System.out.println("mid==::"+this.bytes2Integer(this.toLH(this.bytes2Integer(recvHead2))));
    	
        

    }
    
    
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", 3);
        map.put("msg", 0);
        String jsonMsg=JSON.toJSONString(map);
        SocketPacketUtil socketPacket = new SocketPacketUtil("",901);
       
        socketPacket.sendData(10008,123, jsonMsg);
        socketPacket.receiveData();
       
    }
    

  

}
