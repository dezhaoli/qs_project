package com.qs.cfg.sys.model;

public class SystemRoom {
    private Integer id;

    private Integer ju;

    private Integer cost;

    private Integer begin;

    private Integer end;

    private String type;

    private String name;

    private Integer serverid;

    private Integer taxation;

    private Integer bettime;

    private Byte taxesMode;

    private Integer sb;

    private Integer min;

    private Integer max;

    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJu() {
        return ju;
    }

    public void setJu(Integer ju) {
        this.ju = ju;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getServerid() {
        return serverid;
    }

    public void setServerid(Integer serverid) {
        this.serverid = serverid;
    }

    public Integer getTaxation() {
        return taxation;
    }

    public void setTaxation(Integer taxation) {
        this.taxation = taxation;
    }

    public Integer getBettime() {
        return bettime;
    }

    public void setBettime(Integer bettime) {
        this.bettime = bettime;
    }

    public Byte getTaxesMode() {
        return taxesMode;
    }

    public void setTaxesMode(Byte taxesMode) {
        this.taxesMode = taxesMode;
    }

    public Integer getSb() {
        return sb;
    }

    public void setSb(Integer sb) {
        this.sb = sb;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}