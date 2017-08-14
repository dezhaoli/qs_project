package com.qs.webside.robot.service;

import com.qs.webside.robot.model.Robot;

/**
 * Created by zun.wei on 2017/8/11 18:27.
 * Description:机器人业务层
 */
public interface IRobotService {

    int deleteByPrimaryKey(Integer id);

    int insert(Robot record);

    int insertSelective(Robot record);

    Robot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Robot record);

    int updateByPrimaryKey(Robot record);

    /**
     * @Author:zun.wei , @Date:2017/8/14 9:23
     * @Description:操作python请求
     * @param type 请求类型
     * @param data 请求数据参数
     * @param gameType 游戏类型
     * @param cIp
     *@param cPort @return 返回值
     */
    Object handlePythonRequest(int type, String data, int gameType, String cIp, int cPort);

}
