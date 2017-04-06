package com.qs.log.game.util;

/**
 *  数据源切换器
 * Created by zun.wei on 2017/4/6.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class DataSourceSwitch {

    private static final ThreadLocal<String> contextHolderLog = new ThreadLocal<String>();

    private static final ThreadLocal<String> contextHolderMain = new ThreadLocal<String>();

    public static void setLogDataSourceType(String dataSourceType){
        contextHolderLog.set(dataSourceType);
    }

    public static String getLogDataSourceType(){
        return contextHolderLog.get();
    }

    public static void clearLogDataSourceType(){
        contextHolderLog.remove();
    }

    public static void setMainDataSourceType(String dataSourceType){
        contextHolderMain.set(dataSourceType);
    }

    public static String getMainDataSourceType(){
        return contextHolderMain.get();
    }

    public static void clearMainDataSourceType(){
        contextHolderMain.remove();
    }

}
