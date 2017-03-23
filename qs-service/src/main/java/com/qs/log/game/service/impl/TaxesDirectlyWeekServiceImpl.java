package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.TaxesDirectlyWeekMapper;
import com.qs.log.game.model.TaxesDirectlyWeek;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 代理商周统计表
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class TaxesDirectlyWeekServiceImpl implements ITaxesDirectlyWeekService {

    @Value("${webside.url}")
    private String websideUrl;

    @Resource
    private TaxesDirectlyWeekMapper taxesDirectlyWeekMapper;

    @Override
    public int insert(TaxesDirectlyWeek record) {
        return taxesDirectlyWeekMapper.insert(record);
    }

    @Override
    public int insertSelective(TaxesDirectlyWeek record) {
        return taxesDirectlyWeekMapper.insertSelective(record);
    }

    @Override
    public List<Map<String,Object>> queryListPageByAgentBelongIdAndSunDayDate(Map<String, Object> belongIdAndDate) {
        if (belongIdAndDate.get("date") == null || "".equals(belongIdAndDate.get("date")))
            return new ArrayList<Map<String,Object>>();
        if (websideUrl != null) {
            int start = websideUrl.lastIndexOf("/");
            int offset = websideUrl.lastIndexOf("?");
            String dbName = websideUrl.substring(start + 1, offset);
            belongIdAndDate.put("dbTable", dbName + ".memberagents");
        } else {
            belongIdAndDate.put("dbTable", "sc_majiang.memberagents");
        }
        return taxesDirectlyWeekMapper.queryListPageByAgentBelongIdAndSunDayDate(belongIdAndDate);
    }

}
