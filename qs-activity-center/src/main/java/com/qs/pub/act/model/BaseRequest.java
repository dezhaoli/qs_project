package com.qs.pub.act.model;

import java.io.Serializable;

/**
 * Created by zun.wei on 2017/5/18 16:58.
 * Description:
 */
public class BaseRequest implements Serializable{


    private String sesskey;

    public String getSesskey() {
        return sesskey;
    }

    public void setSesskey(String sesskey) {
        this.sesskey = sesskey;
    }

    @Override
    public String toString() {
        return "BaseRequest [sesskey=" + sesskey + "]";
    }

}
