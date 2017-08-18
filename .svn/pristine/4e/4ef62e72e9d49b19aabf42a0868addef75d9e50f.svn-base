package com.qs.webside.robot.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.robot.model.Robot;

import java.util.Map;

public interface RobotMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Robot record);

    int insertSelective(Robot record);

    Robot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Robot record);

    int updateByPrimaryKey(Robot record);

    /**
     * @Author:zun.wei , @Date:2017/8/14 10:21
     * @Description:检查授权码存在且是否在有效期内
     * @param parameters
     * @return 0表示授权码所有者，1表示待开房者，-1表示没有找到
     */
    int checkAuthCodeOrMidExist(Map<String,Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/8/15 19:22
     * @Description:更新
     * @param record
     * @return
     */
    int updateActivationTo1(Robot record);

    /**
     * @Author:zsf , @Date:2017/8/18 18:24
     * @Description:根据用户id查询用户是否有机器人的权利
     * @param Sssskey
     * @return
     */
	int queryUserRobotPower(Map<String, Object> map);

	int queryCountByAuthCode(int ramdomCode);


}