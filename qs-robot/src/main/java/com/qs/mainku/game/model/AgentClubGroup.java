package com.qs.mainku.game.model;

import java.util.Date;

public class AgentClubGroup {
    private Integer id;

    private Integer cmid;

    private String name;

    private Date createTime;

    private String payYourself;

    private String payBig;

    private String payReplace;

    private String extend1;

    private String extend2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmid() {
        return cmid;
    }

    public void setCmid(Integer cmid) {
        this.cmid = cmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayYourself() {
        return payYourself;
    }

    public void setPayYourself(String payYourself) {
        this.payYourself = payYourself == null ? null : payYourself.trim();
    }

    public String getPayBig() {
        return payBig;
    }

    public void setPayBig(String payBig) {
        this.payBig = payBig == null ? null : payBig.trim();
    }

    public String getPayReplace() {
        return payReplace;
    }

    public void setPayReplace(String payReplace) {
        this.payReplace = payReplace == null ? null : payReplace.trim();
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }
}