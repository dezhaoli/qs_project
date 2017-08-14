package com.qs.webside.robot.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.robot.model.Robot;

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
     * @param code
     * @return 0表示授权码所有者，1表示待开房者，-1表示没有找到
     */
    int checkAuthCodeOrMidExist(Integer code);

}