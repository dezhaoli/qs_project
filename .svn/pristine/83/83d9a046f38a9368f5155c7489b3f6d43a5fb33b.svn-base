package com.qs.webside.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.Club;

public interface ClubMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer clubid);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(Integer clubid);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);
    
    /**
     * 根据当前用户拉去参加俱乐部信息列表
     * @param param
     * @return
     * @author:zyy
     * @time:2017年9月1日
     */
    List<Map<String,Object>> getByMidClubsInfoList(Map<String,Object> param);
}