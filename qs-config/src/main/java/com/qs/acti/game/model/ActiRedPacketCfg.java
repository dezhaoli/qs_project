package com.qs.acti.game.model;

public class ActiRedPacketCfg {
    private Integer id;

    private String name;

    private String gold;

    private String gailv;

    private Integer packNum;

    private Integer packStore;

    private Integer actiType;

    private String version;

    private String content;

    private String remark;

    private String descr;

    private String ext1;

    private String ext2;

    private String ext3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getGailv() {
        return gailv;
    }

    public void setGailv(String gailv) {
        this.gailv = gailv == null ? null : gailv.trim();
    }

    public Integer getPackNum() {
        return packNum;
    }

    public void setPackNum(Integer packNum) {
        this.packNum = packNum;
    }

    public Integer getPackStore() {
        return packStore;
    }

    public void setPackStore(Integer packStore) {
        this.packStore = packStore;
    }

    public Integer getActiType() {
        return actiType;
    }

    public void setActiType(Integer actiType) {
        this.actiType = actiType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
}