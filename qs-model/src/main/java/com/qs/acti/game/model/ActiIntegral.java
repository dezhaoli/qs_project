package com.qs.acti.game.model;

import java.io.Serializable;

public class ActiIntegral implements Serializable {

    private Integer id;

    private Integer mid;

    private Long nowIntegral;

    private Long usedIntegral;

    private Integer type;

    private String remark;

    private String descr;

    private String ext1;

    private String ext2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Long getNowIntegral() {
        return nowIntegral;
    }

    public void setNowIntegral(Long nowIntegral) {
        this.nowIntegral = nowIntegral;
    }

    public Long getUsedIntegral() {
        return usedIntegral;
    }

    public void setUsedIntegral(Long usedIntegral) {
        this.usedIntegral = usedIntegral;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }
}