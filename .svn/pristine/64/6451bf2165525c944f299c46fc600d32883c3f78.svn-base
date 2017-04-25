package com.qs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * //@Author:zun.wei, @Date:2017/4/6 13:45
 *  日志库动态数据源
 * Created by zun.wei on 2017/4/6.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class LogDataSources extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceSwitch.getLogDataSourceType();
    }

}
