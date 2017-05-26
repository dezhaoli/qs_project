/**
 * 
 */
package com.qs.pub.sync.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qs.pub.sync.mapper.LogSuccessMapper;
import com.qs.pub.sync.service.LogSuccessService;
import com.qs.sync.model.LogSuccess;

/**
 * 日志成功表服务实现
 */
@Service
public class LogSuccessServiceImpl implements LogSuccessService {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(LogSuccessServiceImpl.class);
    
    @Autowired
    private LogSuccessMapper logSuccessMapper;
    
  
    @Transactional(readOnly = true)
    public LogSuccess find(String id) {
        return logSuccessMapper.find(id);
    }



    @Transactional(rollbackFor = Exception.class)
    public LogSuccess save(LogSuccess logSuccess) {
        logSuccess.setCreateTime(new Date());
        logSuccessMapper.save(logSuccess);
        if (logger.isInfoEnabled()) {
            logger.info("保存日志成功表{}", logSuccess);
        }
        return logSuccess;
    }

    @Transactional(rollbackFor = Exception.class)
    public LogSuccess update(LogSuccess logSuccess) {
        logSuccess.setModifyTime(new Date());
        logSuccessMapper.update(logSuccess);
        if (logger.isInfoEnabled()) {
            logger.info("更新日志成功表{}", logSuccess);
        }
        return logSuccess;
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(String id) {
        logSuccessMapper.remove(id);
        if (logger.isInfoEnabled()) {
            logger.info("删除日志成功表{}", id);
        }
    }

}
