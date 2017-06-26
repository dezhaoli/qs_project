package com.qs.webside.activity.model;

import java.io.Serializable;
import java.util.Date;

public class ActiCenterDto implements Serializable{
    private Integer id;

    private String title;

    private String descr;

    private Integer type;

    private Integer sort;

    private String btnUrl;

    private String jumpUrl;

    private String actImgUrl;

    private Integer actiStatus;



    private String closeTime;

    private String periodStartTime;

    private String periodEndTime;

    private String startTime;

    private String endTime;



    private Integer status;

    private Integer cardRecord;

    private Integer reward;

    private String ext1;

    private String ext2;

    private Date createTime;

    private Integer createrId;

    private Date updateTime;

    private Integer modifierId;

    public Integer getActiStatus() {
        return actiStatus;
    }

    public void setActiStatus(Integer actiStatus) {
        this.actiStatus = actiStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBtnUrl() {
        return btnUrl;
    }

    public void setBtnUrl(String btnUrl) {
        this.btnUrl = btnUrl == null ? null : btnUrl.trim();
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl == null ? null : jumpUrl.trim();
    }

    public String getActImgUrl() {
        return actImgUrl;
    }

    public void setActImgUrl(String actImgUrl) {
        this.actImgUrl = actImgUrl == null ? null : actImgUrl.trim();
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getPeriodStartTime() {
        return periodStartTime;
    }

    public void setPeriodStartTime(String periodStartTime) {
        this.periodStartTime = periodStartTime;
    }

    public String getPeriodEndTime() {
        return periodEndTime;
    }

    public void setPeriodEndTime(String periodEndTime) {
        this.periodEndTime = periodEndTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCardRecord() {
        return cardRecord;
    }

    public void setCardRecord(Integer cardRecord) {
        this.cardRecord = cardRecord;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }
}