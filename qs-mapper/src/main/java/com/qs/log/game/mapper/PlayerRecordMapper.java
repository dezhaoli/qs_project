package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.PlayerRecord;

import java.util.List;
import java.util.Map;

public interface PlayerRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerRecord record);

    int insertSelective(PlayerRecord record);

    PlayerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerRecord record);

    int updateByPrimaryKey(PlayerRecord record);

    /**
     * //@Author:zun.wei, @Date:2017/4/18 18:37
     * 查看牌局记录查询
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryCardRecordByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/5/16 10:15
     * @Description:牌局统计
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryBoardStatisticsByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/5/16 10:39
     * @Description:根据牌局统计时间段的牌局总数
     * @param parameters
     * @return
     */
    Integer queryBoardStatisticsByCount(Map<String, Object> parameters);

}