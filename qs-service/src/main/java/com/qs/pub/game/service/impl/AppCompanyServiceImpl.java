package com.qs.pub.game.service.impl;

import com.qs.pub.game.mapper.AppCompanyMapper;
import com.qs.pub.game.model.AppCompany;
import com.qs.pub.game.service.IAppCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  分公司表
 * Created by zun.wei on 2017/3/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class AppCompanyServiceImpl implements IAppCompanyService {

    @Resource
    private AppCompanyMapper appCompanyMapper;

    @Override
    public int deleteByPrimaryKey(Byte cid) {
        return appCompanyMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public int insert(AppCompany record) {
        return appCompanyMapper.insert(record);
    }

    @Override
    public int insertSelective(AppCompany record) {
        return appCompanyMapper.insertSelective(record);
    }

    @Override
    public AppCompany selectByPrimaryKey(Byte cid) {
        return appCompanyMapper.selectByPrimaryKey(cid);
    }

    @Override
    public int updateByPrimaryKeySelective(AppCompany record) {
        return appCompanyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppCompany record) {
        return appCompanyMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AppCompany> queryListAll() {
        return appCompanyMapper.queryListAll();
    }

}
