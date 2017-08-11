package com.qs.mainku.game.model;

import java.io.Serializable;

/**
 * @Author:zun.wei , @Date:2017/6/29 11:18
 * @Description:金币表
 */
public class Commongame implements Serializable{


	private Integer mid;

    private Long gold;

    private Byte vip;

    private String bankpasswd;

    private Long bankassets;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Byte getVip() {
        return vip;
    }

    public void setVip(Byte vip) {
        this.vip = vip;
    }

    public String getBankpasswd() {
        return bankpasswd;
    }

    public void setBankpasswd(String bankpasswd) {
        this.bankpasswd = bankpasswd == null ? null : bankpasswd.trim();
    }

    public Long getBankassets() {
        return bankassets;
    }

    public void setBankassets(Long bankassets) {
        this.bankassets = bankassets;
    }
}