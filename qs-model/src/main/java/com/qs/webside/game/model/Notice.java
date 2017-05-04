package com.qs.webside.game.model;

public class Notice {
    private Integer id;

    private String title;

    private String img;

    private String url;

    private Byte type;

    private Integer stime;

    private Integer etime;

    private Integer inter;

    private Byte gametype;

    private String content;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getStime() {
        return stime;
    }

    public void setStime(Integer stime) {
        this.stime = stime;
    }

    public Integer getEtime() {
        return etime;
    }

    public void setEtime(Integer etime) {
        this.etime = etime;
    }

    public Integer getInter() {
        return inter;
    }

    public void setInter(Integer inter) {
        this.inter = inter;
    }

    public Byte getGametype() {
        return gametype;
    }

    public void setGametype(Byte gametype) {
        this.gametype = gametype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}