package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.ClubMids;

public interface ClubMidsMapper extends IBaseMapper {
    int insert(ClubMids record);

    int insertSelective(ClubMids record);
    
    /**
     * 删除代开房信息
     * @param record
     * @return
     * @author:zyy
     * @time:2017年9月1日
     */
    int deleteClubMidsInfo(ClubMids record);
}