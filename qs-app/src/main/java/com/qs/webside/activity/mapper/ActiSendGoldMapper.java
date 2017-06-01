package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiSendGold;

import java.util.Map;

public interface ActiSendGoldMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiSendGold record);

    int insertSelective(ActiSendGold record);

    ActiSendGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiSendGold record);

    int updateByPrimaryKey(ActiSendGold record);

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:03
     * @Description:防止重复插入方法
     * @param record
     * @return
     */
    int insertIgnoreSelective(ActiSendGold record);

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:08
     * @Description:根据mid查询是否去ios评论过且发放过金币（房卡）
     * @param paramegers
     * @return
     */
    ActiSendGold queryByCommont(Map<String, Object> paramegers);

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:12
     * @Description:根据mid和当天日期查询当天是否分享获得过金币（房卡）
     * @param paramegers
     * @return
     */
    ActiSendGold queryByShare(Map<String, Object> paramegers);


}