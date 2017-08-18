package com.qs.acti.game.service;


import com.qs.acti.game.model.RobotFriends;

import java.util.Map;

/**
 * Created by zun.wei on 2017/8/14 9:09.
 * Description:机器人好友
 */
public interface IRobotFriendService {

    int deleteByPrimaryKey(Integer id);

    int insert(RobotFriends record);

    int insertSelective(RobotFriends record);

    RobotFriends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RobotFriends record);

    int updateByPrimaryKey(RobotFriends record);

    /**
     * @Author:zun.wei , @Date:2017/8/15 19:42
     * @Description:根据授权码和mid查询机器人好友
     * @param parameters map包装的参数
     * @return
     */
    RobotFriends queryRobotFriendByCodeAndMid(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/8/17 16:45
     * @Description:检查代理商是否已经授权激活加这个机器人做好友过。
     * @param parameters code：代理商mid,roboName 机器人昵称
     * @return 查找到的条数
     */
    int checkAgentHasAuth(Map<String, Object> parameters);

}
