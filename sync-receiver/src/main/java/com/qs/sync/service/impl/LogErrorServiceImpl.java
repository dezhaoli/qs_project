/**
 * 
 */
package com.qs.sync.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qs.sync.mapper.LogErrorMapper;
import com.qs.sync.model.LogError;
import com.qs.sync.model.LogSuccess;
import com.qs.sync.service.LogErrorService;

/**
 * 日志错误表服务实现
 */
@Service
public class LogErrorServiceImpl implements LogErrorService {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(LogErrorServiceImpl.class);
    
    @Autowired
    private LogErrorMapper logErrorMapper;
    
  
    @Transactional(readOnly = true)
    public LogError find(String id) {
        return logErrorMapper.find(id);
    }


    @Transactional(rollbackFor = Exception.class)
    public LogError save(LogError logError) {
        logError.setCreateTime(new Date());
        logError.setSendNum("1");
        logErrorMapper.save(logError);
        if (logger.isInfoEnabled()) {
            logger.info("保存日志错误表{}", logError);
        }
        return logError;
    }

    @Transactional(rollbackFor = Exception.class)
    public LogError update(LogError logError) {
        logError.setModifyTime(new Date());
        logErrorMapper.update(logError);
        if (logger.isInfoEnabled()) {
            logger.info("更新日志错误表{}", logError);
        }
        return logError;
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(String id) {
        logErrorMapper.remove(id);
        if (logger.isInfoEnabled()) {
            logger.info("删除日志错误表{}", id);
        }
    }

}
