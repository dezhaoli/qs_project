package com.qs.pub.datacenter.controller;


import com.hzw.monitor.mysqlbinlog.connection.Connection;
import com.hzw.monitor.mysqlbinlog.connection.ConnectionFactory;
import com.hzw.monitor.mysqlbinlog.handlers.BinlogEventParseHandler;
import com.hzw.monitor.mysqlbinlog.netty.MonitorQueue;
import com.hzw.monitor.mysqlbinlog.netty.NettyServer;
import com.hzw.monitor.mysqlbinlog.utils.LoggerUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

/**
 * @Author：wangzhen
 * @Date 2017/9/7 13:49
 * @Description:
 * @Modified By:
 */
@Controller
public class DataSyncController extends BinlogEventParseHandler implements ApplicationListener<ContextRefreshedEvent>{
    private static final Logger logger = LogManager.getLogger(DataSyncController.class);
    private static final String name = "com.mysql.jdbc.Driver";

    static {// 加载1次就可以了,启动时加载mysql驱动程序，免得后面耗时
        try {
            Class.forName(name);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            LoggerUtils.error(logger, e.toString());
        }
    }



    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LoggerUtils.debug(logger, "system begins...");
        // 0启动Netty TCP服务器线程
        NettyServer.start();

        // 1 创建连接并抛到Netty队列里
        LoggerUtils.debug(logger, "try to create connection to mysql...");
        Connection conn = ConnectionFactory.makeObject();
        if (null == conn) {
            LoggerUtils.error(logger, "create socket  failed");
            System.exit(-1);
        } else {
            LoggerUtils.debug(logger, "create socket succeed: " + conn.getSocketChannel());
        }
        // 尝试纳入netty的管理范围
        MonitorQueue.addObject(conn.getSocketChannel());
    }
}
