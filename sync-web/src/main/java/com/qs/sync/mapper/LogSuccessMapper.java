/**
 * 
 */
package com.qs.sync.mapper;

import java.util.List;

import com.qs.sync.model.LogSuccess;

/**
 * LogSuccessMapper
 */
public interface LogSuccessMapper {

    /**
     * 查找日志成功表
     * @param id
     * @return
     */
    public LogSuccess find(String id);
    
    /**
     * 保存日志成功表
     * @param logSuccess
     */
    public void save(LogSuccess logSuccess);
    
    /**
     * 更新日志成功表
     * @param logSuccess
     */
    public void update(LogSuccess logSuccess);
    
    /**
     * 删除日志成功表
     * @param id
     */
    public void remove(String id);
    
}
