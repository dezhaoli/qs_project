package com.qs.webside.api.model;

/**
 * Created by zun.wei on 2017/7/21 11:34.
 * Description:
 */
public class ShareLinkRequest extends BaseRequest {
//jushu=8&roomtitle
    private String roomid;

    private String wanfa;

    private String roomtitle;

    private int jushu;

    public String getRoomtitle() {
        return roomtitle;
    }

    public void setRoomtitle(String roomtitle) {
        this.roomtitle = roomtitle;
    }

    public int getJushu() {
        return jushu;
    }

    public void setJushu(int jushu) {
        this.jushu = jushu;
    }

    public String getWanfa() {
        return wanfa;
    }

    public void setWanfa(String wanfa) {
        this.wanfa = wanfa;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }


}
