/**
 * 
 */
package com.qs.sync.service;

import com.qs.sync.model.LogSuccess;

/**
 * 日志成功表服务
 */
public interface LogSuccessService {

    /**
     * 查找日志成功表
     * @param id
     * @return
     */
    public LogSuccess find(String id);
    

    
    /**
     * 保存日志成功表
     * @param logSuccess
     * @return
     */
    public LogSuccess save(LogSuccess logSuccess);
    
    /**
     * 更新日志成功表
     * @param logSuccess
     * @return
     */
    public LogSuccess update(LogSuccess logSuccess);
    
    /**
     * 删除日志成功表
     * @param id
     */
    public void remove(String id);
}
