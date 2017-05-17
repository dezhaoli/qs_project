package com.qs.webside.agent.model;

public class AgentClubwhiteList {
    private Integer id;

    private Integer mid;

    private String name;

    private String icon;

    private Integer clubType;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getClubType() {
        return clubType;
    }

    public void setClubType(Integer clubType) {
        this.clubType = clubType;
    }
}