package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiAwardRecord;

public interface ActiAwardRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardRecord record);

    int insertSelective(ActiAwardRecord record);

    ActiAwardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardRecord record);

    int updateByPrimaryKey(ActiAwardRecord record);

    /**
     * @Author:zun.wei , @Date:2017/6/8 17:31
     * @Description:根据活动类型查询，该活动类型对应的奖品兑换记录总个数
     * @param actiType
     * @return
     */
    int checkAwardRecordSumByActiType(int actiType);

}