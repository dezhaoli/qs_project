package com.qs.webside.activity.service.impl;

import com.qs.common.constant.Constants;
import com.qs.pub.game.model.Dict;
import com.qs.pub.game.service.IDictService;
import com.qs.webside.activity.mapper.ActiAwardMapper;
import com.qs.webside.activity.model.ActiAward;
import com.qs.webside.activity.service.IActiAwardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/5 17:28.
 * Description:活动中心奖品表
 */
@Service
public class ActiAwardServiceImpl implements IActiAwardService {

    @Resource
    private ActiAwardMapper actiAwardMapper;

    @Resource
    private IDictService dictService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiAwardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiAward record) {
        return actiAwardMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiAward record) {
        return actiAwardMapper.insertSelective(record);
    }

    @Override
    public ActiAward selectByPrimaryKey(Integer id) {
        return actiAwardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiAward record) {
        return actiAwardMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiAward record) {
        return actiAwardMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> queryListByPage(Map<String, Object> parameter) {
        List<Dict> activityList = dictService.findDictList(Constants.Dict.ACTIVITY_ID);//活动类型选择
        List<Map<String, Object>> actiAwardList = actiAwardMapper.queryListByPage(parameter);
        if (actiAwardList != null && activityList != null) {
            for (Map<String, Object> map : actiAwardList) {
                for (Dict dict : activityList) {
                    if ((map.get("type") + "").equals(dict.getCode())) {
                        map.put("type", dict.getName());
                    }
                }
            }
        }
        return actiAwardList;
    }

}
