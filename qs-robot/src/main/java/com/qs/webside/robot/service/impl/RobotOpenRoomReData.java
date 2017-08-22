package com.qs.webside.robot.service.impl;

/**
 * Created by zun.wei on 2017/8/16 9:16.
 * Description:机器人开房错误码处理类
 */
public class RobotOpenRoomReData {

    /**
     * @Author:zun.wei , @Date:2017/7/26 15:00
     * @Description:操作返回值
     * @param cmd 返回的命令
     * @param recvInt 接收到的代码
     * @return
     */
    public static String handleRecvData(int cmd, int recvInt, int gameType) {
        switch (gameType) {
            case 6:
                return GDMajiangRecvData.handleGDMajiangError(cmd, recvInt);
            case 20:
                return JXMajiangRecvData.handleJXMajiangError(cmd,recvInt);
            default:
                return null;
        }
    }


    //广东麻将
    private static class GDMajiangRecvData{

        private static String handleGDMajiangError(int cmd,int recvInt) {
            switch (cmd) {
                case 1103:
                    return handleErrorCodeByCmd1103(recvInt);
                case 1001:
                    return handleErrorCodeByCmd1001(recvInt);
                default:
                    return handleErrorCodeByCmd1001(recvInt);
            }
        }

        /**
         * @Author:zun.wei , @Date:2017/7/26 15:00
         * @Description:操作命令为1103的错误代码
         * @param recvInt 接收到的代码
         * @return
         */
        private static String handleErrorCodeByCmd1103(int recvInt) {
            switch (recvInt) {
                case -1102:
                    return "房间未找到";
                case -1106:
                    return "没有加入俱乐部";
                case -1109:
                    return "ip地址重复";
                case -1110:
                    return "已在其他房间不能换房";
                default:
                    return "未知错误";
            }
        }

        /**
         * @Author:zun.wei , @Date:2017/7/26 15:01
         * @Description:操作命令为1001的错误代码
         * @param recvInt 接收到的代码
         * @return
         */
        private static String handleErrorCodeByCmd1001(int recvInt) {
            switch (recvInt) {
                case -1:
                    return "房间人数已满";
                case -2:
                    return "房间人数已满";
                default:
                    return null;
            }
        }

    }


    //江西麻将
    private static class JXMajiangRecvData{

        private static String handleJXMajiangError(int cmd,int recvInt) {
            switch (cmd) {
                case 1103:
                    return handleErrorCodeByCmd1103(recvInt);
                case 1001:
                    return handleErrorCodeByCmd1001(recvInt);
                default:
                    return handleErrorCodeByCmd1001(recvInt);
            }
        }

        /**
         * @Author:zun.wei , @Date:2017/7/26 15:00
         * @Description:操作命令为1103的错误代码
         * @param recvInt 接收到的代码
         * @return
         */
        private static String handleErrorCodeByCmd1103(int recvInt) {
            switch (recvInt) {
                case -1102:
                    return "房间未找到";
                case -1106:
                    return "没有加入俱乐部";
                case -1109:
                    return "ip地址重复";
                default:
                    return "未知错误";
            }
        }

        /**
         * @Author:zun.wei , @Date:2017/7/26 15:01
         * @Description:操作命令为1001的错误代码
         * @param recvInt 接收到的代码
         * @return
         */
        private static String handleErrorCodeByCmd1001(int recvInt) {
            switch (recvInt) {
                case -1:
                    return "房间人数已满";
                case -2:
                    return "房间人数已满";
                default:
                    return null;
            }
        }

    }

}
