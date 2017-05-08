package com.qs.pub.quartz.mapper;

import org.springframework.stereotype.Repository;

import com.qs.common.base.basemapper.BaseMapper;
import com.qs.pub.quartz.model.ScheduleJobEntity;

@Repository
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity, Long>{

}
