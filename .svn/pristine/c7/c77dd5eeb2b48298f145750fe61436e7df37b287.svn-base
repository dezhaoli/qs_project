package com.qs.webside.util;

import com.qs.datasource.game.util.ConstantUtil;
import com.qs.datasource.game.util.DataSourceSwitch;
import com.qs.datasource.game.util.DateSourcesUtil;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.webside.shiro.ShiroAuthenticationManager;

import javax.annotation.Resource;

/**
 * //@Author:zun.wei, @Date:2017/4/21 20:04
 *
 * Created by zun.wei on 2017/4/21.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class BusinessDataSourceUtil {

    @Resource
    private DateSourcesUtil dateSourcesUtil;

    public Integer getGameType() {
        MemberBusiness memberBusiness = ShiroAuthenticationManager.getBusiness();
        return Integer.parseInt
                (dateSourcesUtil.getKey(ConstantUtil.BUSINESS_GAME_TYPE_SESSION_KEY + memberBusiness.getId()));
    }

    public void writeType() {
        DataSourceSwitch.setMainDataSourceType //@Author:zun.wei, @Date:2017/4/21 20:19  主库写
                (dateSourcesUtil.getDateBaseName(ConstantUtil.ReadWrite.Write, getGameType(), null));
        DataSourceSwitch.setLogDataSourceType //@Author:zun.wei, @Date:2017/4/21 20:20  日志库写
                (dateSourcesUtil.getDateBaseName(ConstantUtil.ReadWrite.Write, getGameType(), ConstantUtil.TypeCode.LOG));
    }

    public void readType() {
        DataSourceSwitch.setMainDataSourceType //@Author:zun.wei, @Date:2017/4/21 20:20  主库读
                (dateSourcesUtil.getDateBaseName(ConstantUtil.ReadWrite.READ, getGameType(), null));
        DataSourceSwitch.setLogDataSourceType //@Author:zun.wei, @Date:2017/4/21 20:20  日志库读
                (dateSourcesUtil.getDateBaseName(ConstantUtil.ReadWrite.READ, getGameType(), ConstantUtil.TypeCode.LOG));
    }

}
