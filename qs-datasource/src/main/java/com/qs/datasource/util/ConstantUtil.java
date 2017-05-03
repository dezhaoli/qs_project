package com.qs.datasource.util;

import org.apache.log4j.Logger;


/**
 * //@Author:zun.wei, @Date:2017/4/6 14:37
 *  常量工具包
 * Created by zun.wei on 2017/4/6.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class ConstantUtil {
	
	public static final String AGENT_GAME_TYPE_SESSION_KEY="agentGameTypeSessionKey:";
	public static final String BUSINESS_GAME_TYPE_SESSION_KEY="businessGameTypeSessionKey:";
	
	public static class GameType{
		
		public static final Integer GD_MAJIANG=5;
		
		public static final Integer SC_MAJIANG=6;
	}
	
	public static class ReadWrite{
		public static final String READ="Read";
		public static final String Write="Write";
	}
	
	public static class TypeCode{
		public static final String CFG="Cfg";
		public static final String LOG="Log";
	}
	public static class GameNameGDMJ{
		
		public static final String GD_MAJIANG_READ="gdMajiangMainReadDataSource";
		
		public static final String GD_MAJIANG_CFG_READ="gdMajiangCfgReadReadDataSource";
		
		public static final String GD_MAJIANG_LOG_READ="gdMajiangLogReadReadDataSource";
		
		
		public static final String GD_MAJIANG_WRITE="gdMajiangMainWriteDataSource";
		
		public static final String GD_MAJIANG_CFG_WRITE="gdMajiangCfgWriteDataSource";
		
		public static final String GD_MAJIANG_LOG_WRITE="gdMajiangLogWriteDataSource";
	}
	
	public static class GameNameSCMJ{
		
		public static final String SC_MAJIANG_READ="scMajiangMainReadDataSource";
		
		public static final String SC_MAJIANG_CFG_READ="scMajiangCfgReadDataSource";
		
		public static final String SC_MAJIANG_LOG_READ="scMajiangLogReadDataSource";
		
		
		public static final String SC_MAJIANG_WRITE="scMajiangMainWriteDataSource";
		
		public static final String SC_MAJIANG_CFG_WRITE="scMajiangCfgWriteDataSource";
		
		public static final String SC_MAJIANG_LOG_WRITE="scMajiangLogWriteDataSource";
	}
	
	
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
        public static final String Mushi_Main= "mushiDataSource";
        public static final String Ma_Jiangjx_Main= "majiangjxDataSource";
        public static final String Beard_Kx_Main= "beardkxDataSource";
    }

    public static class GameTypeConstant{
        public static final String Ma_Jiang= "majiang";
        public static final String Run_Fast= "runfast";
        public static final String Bull= "bull";
        public static final String MuShi= "mushi";
        public static final String Ma_Jiangjx= "majiangjx";
        public static final String Beard_Kx= "beardkx";
    }
    

}