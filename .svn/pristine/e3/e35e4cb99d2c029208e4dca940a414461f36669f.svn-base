package com.qs.sync.service;

import com.qs.sync.model.LogError;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;

/**
 * 数据同步服务
 */
public interface DataSyncService {
    /**
     * 同步用户
     * @param syncObject
     * @return
     */
    public int syncUser(SyncUser syncUser,boolean isSaveLog);
    /**
     * 同步组织
     * @param syncObject
     * @return
     */
    public int syncOrg(SyncOrganization org,boolean isSaveLog);
    
    /**
     * http单个同步用户
     * @param syncUser
     * @param sysCode
     * @param isSaveLog
     * @return
     */
    int httpSyncUser(SyncUser syncUser,String sysCode,boolean isSaveLog);
    /**
     * http单个同步组织
     * @param org
     * @param sysCode
     * @param isSaveLog
     * @return
     */
    int httpSyncOrg(SyncOrganization org,String sysCode, boolean isSaveLog);
}
