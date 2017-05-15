/**
 * 
 */
package com.qs.sync.service;

import com.qs.sync.model.LogError;

/**
 * 日志错误表服务
 */
public interface LogErrorService {

    /**
     * 查找日志错误表
     * @param id
     * @return
     */
    public LogError find(String id);
    
    /**
     * 保存日志错误表
     * @param logError
     * @return
     */
    public LogError save(LogError logError);
    
    /**
     * 更新日志错误表
     * @param logError
     * @return
     */
    public LogError update(LogError logError);
    
    /**
     * 删除日志错误表
     * @param id
     */
    public void remove(String id);
}
