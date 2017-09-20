/**
 * 
 */
package com.qs.pub.sync.mapper;

import java.util.List;

import com.qs.sync.model.LogError;

/**
 * LogErrorMapper
 */
public interface LogErrorMapper {

    /**
     * 查找日志错误表
     * @param id
     * @return
     */
    public LogError find(String id);

    /**
     * 查询待处理的错误日志列表
     * @return
     */
    public List<LogError> findLogErrorList();

    
    /**
     * 保存日志错误表
     * @param logError
     */
    public void save(LogError logError);
    
    /**
     * 更新日志错误表
     * @param logError
     */
    public void update(LogError logError);
    
    /**
     * 删除日志错误表
     * @param id
     */
    public void remove(String id);
    
}
