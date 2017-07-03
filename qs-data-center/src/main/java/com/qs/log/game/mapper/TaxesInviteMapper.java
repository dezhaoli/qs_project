package com.qs.log.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.TaxesInvite;

public interface TaxesInviteMapper extends IBaseMapper {
    int insert(TaxesInvite record);

    int insertSelective(TaxesInvite record);

	List<TaxesInvite> queryListByPageOfArppu(Map<String, Object> parameters);
}