package com.qs.webside.activity.service.impl;

import com.qs.webside.activity.mapper.ActiAwardRecordMapper;
import com.qs.webside.activity.model.ActiAwardRecord;
import com.qs.webside.activity.service.IActiAwardRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/7 15:16.
 * Description:活动中心奖品兑换记录表
 */
@Service
public class ActiAwardRecordServiceImpl implements IActiAwardRecordService {

    @Resource
    private ActiAwardRecordMapper actiAwardRecordMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiAwardRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiAwardRecord record) {
        return actiAwardRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiAwardRecord record) {
        return actiAwardRecordMapper.insertSelective(record);
    }

    @Override
    public ActiAwardRecord selectByPrimaryKey(Integer id) {
        return actiAwardRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiAwardRecord record) {
        return actiAwardRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiAwardRecord record) {
        return actiAwardRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiAwardRecord> queryListByPage(Map<String, Object> parameters) {
        return actiAwardRecordMapper.queryListByPage(parameters);
    }

    @Override
    public int checkAwardRecordSumByActiType(Map<String,Object> parameters) {
        if (actiAwardRecordMapper.checkAwardRecordSumByActiType(parameters) == null) {
            return 0;
        }
        return actiAwardRecordMapper.checkAwardRecordSumByActiType(parameters);
    }

	@Override
	public  List<Map<String,Object>> selectByMidKey(Integer mid) {
		return actiAwardRecordMapper.selectByMidKey(mid);
	}

	@Override
	public int countAwardNumber(ActiAwardRecord record) {
		return actiAwardRecordMapper.countAwardNumber(record);
	}

}
