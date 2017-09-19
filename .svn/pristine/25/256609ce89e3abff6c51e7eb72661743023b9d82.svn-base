package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiAwardPro;

import java.util.List;
import java.util.Map;

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
     * @Author:zun.wei , @Date:2017/9/18 14:01
     * @Description:根据活动类型，奖品id获取奖品概率信息以及奖品信息
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryListByActiType(Map<String, Object> parameters);

}