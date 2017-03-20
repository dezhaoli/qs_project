package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.Mails;

import java.util.List;
import java.util.Map;

public interface MailsMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mails record);

    int insertSelective(Mails record);

    Mails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mails record);

    int updateByPrimaryKeyWithBLOBs(Mails record);

    int updateByPrimaryKey(Mails record);

    /**
     *  查看邮件详情
     * @param parameter 查询参数
     * @return 查到邮件
     */
    public List<Mails> queryMailDetail(Map<String, Object> parameter);

}