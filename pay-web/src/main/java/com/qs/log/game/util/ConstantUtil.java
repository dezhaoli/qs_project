package com.qs.log.game.util;

import aj.org.objectweb.asm.Type;

/**
 * //@Author:zun.wei, @Date:2017/4/6 14:37
 *  常量工具包
 * Created by zun.wei on 2017/4/6.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class ConstantUtil {

    public static class LogDataSourceConstantKey{
        public static final String Run_Fast_Log= "runfastLogDataSource";
        public static final String Ma_Jiang_Log= "majiangLogDataSource";
        public static final String Bull_Log= "bullLogDataSource";
        public static final String Mushi_Log= "mushiLogDataSource";
        public static final String Ma_Jiangjx_Log= "majiangjxLogDataSource";
        public static final String Beard_Kx_Log= "beardkxLogDataSource";
    }

    public static class MainDataSourceConstantKey{
        public static final String Run_Fast_Main= "runfastAgentDataSource";
        public static final String Ma_Jiang_Main= "majiangAgentDataSource";
        public static final String Bull_Main= "bullAgentDataSource";
        public static final String Mushi_Main= "mushiAgentDataSource";
        public static final String Ma_Jiangjx_Main= "majiangjxAgentDataSource";
        public static final String Beard_Kx_Main= "beardkxAgentDataSource";
    }

    public static class GameTypeConstant{
        public static final String Ma_Jiang= "majiang";//麻将
        public static final String Run_Fast= "runfast";//跑得快
        public static final String Bull= "bull";//斗牛
        public static final String MuShi= "mushi";//木虱
        public static final String Ma_Jiangjx= "majiangjx";//金溪麻将
        public static final String Beard_Kx= "beardkx";//开心跑的快
    }
    
    
    public static class GameType{
        public static final int Ma_Jiang= 4;
        public static final int Run_Fast= 2;
        public static final int Bull= 3;
        public static final int Mushi= 9;
        public static final int Ma_Jiangjx= 5;
        public static final int Beard_Kx = 7;
    }
    
    /**
     *
     *不支付的mid
     *
     */
    public static class NoPay{
    	 public static final String RUNFAST_MID ="100002,100004,100007,100008,100029,100110,100130,100163,101647,102918,106400,100069,100246,141214";
    	 public static final String MAJIANG_MID ="100001,100009,100025,100032,100062,100325,100541,100677,100692,101172,128004,100378,103177";
    	 public static final String BULL_MID ="100842,103456,105387,103868";
    	 public static final String MAJIANXJX_MID ="100405";
    }

    
    /**
     * 
     * @标题: ConstantToType 
     * @描述:  
     *
     * @参数信息
     *    @param gameType
     *    @return
     *
     * @返回类型 int
     * @开发者 qs
     * @可能抛出异常
     */
    public static String getNoPayMid(String gameType){
    	String typeConstant = gameType;
    	String val ="";
    	switch (typeConstant)
		{
		case GameTypeConstant.Ma_Jiang:
			val = NoPay.MAJIANG_MID;
			break;
		case GameTypeConstant.Run_Fast:
			val = NoPay.RUNFAST_MID;
			break;
		case GameTypeConstant.Bull:
			val = NoPay.BULL_MID;
			break;
		
		case GameTypeConstant.Ma_Jiangjx:
			val = NoPay.MAJIANXJX_MID;
			break;
		default:
			break;
		}
    	return val;
    }

}
