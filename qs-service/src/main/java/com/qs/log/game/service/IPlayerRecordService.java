package com.qs.log.game.service;

import com.qs.log.game.model.PlayerRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/9.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IPlayerRecordService {

    int deleteByPrimaryKey(Long id);

    int insert(PlayerRecord record);

    int insertSelective(PlayerRecord record);

    PlayerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerRecord record);

    int updateByPrimaryKey(PlayerRecord record);

    List<PlayerRecord> queryListByPage(Map<String, Object> parameters);

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
