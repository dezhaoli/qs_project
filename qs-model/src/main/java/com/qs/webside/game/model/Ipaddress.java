package com.qs.webside.game.model;

public class Ipaddress {
    private Integer id;

    private String ipstring;

    private String name;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpstring() {
        return ipstring;
    }

    public void setIpstring(String ipstring) {
        this.ipstring = ipstring == null ? null : ipstring.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}