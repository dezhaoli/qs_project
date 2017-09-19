package com.qs.webside.activity.model;

import java.util.Date;

public class ActiAwardPro {
    private Integer id;

    private Integer awardId;

    private String awardPro;

    private Integer type;

    private String awardProStock;

    private Date producDate;

    private Integer status;

    private String daliAddPro;

    private Date addProDate;

    private Integer proSources;

    private String remark;

    private String descr;

    private String ext1;

    private String ext2;

    private String ext3;

    private Date createTime;

    private Integer createrId;

    private Date updateTime;

    private Integer modifierId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getAwardPro() {
        return awardPro;
    }

    public void setAwardPro(String awardPro) {
        this.awardPro = awardPro == null ? null : awardPro.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAwardProStock() {
        return awardProStock;
    }

    public void setAwardProStock(String awardProStock) {
        this.awardProStock = awardProStock == null ? null : awardProStock.trim();
    }

    public Date getProducDate() {
        return producDate;
    }

    public void setProducDate(Date producDate) {
        this.producDate = producDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDaliAddPro() {
        return daliAddPro;
    }

    public void setDaliAddPro(String daliAddPro) {
        this.daliAddPro = daliAddPro == null ? null : daliAddPro.trim();
    }

    public Date getAddProDate() {
        return addProDate;
    }

    public void setAddProDate(Date addProDate) {
        this.addProDate = addProDate;
    }

    public Integer getProSources() {
        return proSources;
    }

    public void setProSources(Integer proSources) {
        this.proSources = proSources;
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

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
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