package com.qs.acti.game.service;

import com.qs.acti.game.model.ActiAwardPro;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/9/15 15:57.
 * Description:
 */
public interface IActiAwardProService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardPro record);

    int insertSelective(ActiAwardPro record);

    ActiAwardPro selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardPro record);

    int updateByPrimaryKey(ActiAwardPro record);

    List<ActiAwardPro> queryListByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/9/15 16:27
     * @Description:根据奖品id获取奖品概率对象
     * @param awarId 奖品id
     * @return
     */
    ActiAwardPro selectByAwarId(Integer awarId);

    /**
     * @Author:zun.wei , @Date:2017/9/15 18:18
     * @Description:根据奖品id和活动类型更新概率对象
     * @param record
     * @return
     */
    int updateByAwardIdAndType(ActiAwardPro record);

    /**
     * @Author:zun.wei , @Date:2017/9/18 16:07
     * @Description:根据奖品id删除奖品概率对象
     * @param awardId
     * @return
     */
    int deleteByAwardId(Integer awardId);

}
