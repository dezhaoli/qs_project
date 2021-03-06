package com.qs.acti.game.mapper;

import com.qs.acti.game.model.ActiAwardPro;
import com.qs.common.base.basemapper.IBaseMapper;

public interface ActiAwardProMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardPro record);

    int insertSelective(ActiAwardPro record);

    ActiAwardPro selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardPro record);

    int updateByPrimaryKey(ActiAwardPro record);

    /**
     * @Author:zun.wei , @Date:2017/9/15 16:27
     * @Description:根据奖品id获取奖品概率对象
     * @param AwarId 奖品id
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

    /**
     * @Author:zun.wei , @Date:2017/9/22 17:16
     * @Description: 根据活动类型还原概率库存
     * @param type 活动类型
     * @return
     */
    int updateProByType(Integer type);

}