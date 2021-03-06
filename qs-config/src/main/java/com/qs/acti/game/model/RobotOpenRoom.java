package com.qs.acti.game.model;

public class RobotOpenRoom {
    private Integer id;

    private Integer amid;

    private Integer omid;

    private Long msgid;

    private Integer odate;

    private String robName;

    private String remark;

    private String ext1;

    private String ext2;

    private String ext3;

    public Integer getOdate() {
        return odate;
    }

    public void setOdate(Integer odate) {
        this.odate = odate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmid() {
        return amid;
    }

    public void setAmid(Integer amid) {
        this.amid = amid;
    }

    public Integer getOmid() {
        return omid;
    }

    public void setOmid(Integer omid) {
        this.omid = omid;
    }

/*    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }*/

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public String getRobName() {
        return robName;
    }

    public void setRobName(String robName) {
        this.robName = robName == null ? null : robName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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