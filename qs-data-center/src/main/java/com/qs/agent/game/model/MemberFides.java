package com.qs.agent.game.model;

public class MemberFides {
    private Integer mid;

    private String name;

    private Byte sex;

    private String btd;

    private String city;

    private String icon;

    private Integer invite;

    private Byte gp;

    private Integer sta;

    private Long mtime;

    private String email;

    private Integer bindtime;

    private String tel;

    private String realname;

    private String identity;

    private Byte yellowvip;

    private Byte isyearvip;

    private String passwd;

    private String salt;

    private Integer lgtm;

    private Integer lxlg;

    private String qq;

    private String address;

    private Long regip;

    private Byte activeAssets;
    
    private String mtimeStr;
    
    private Integer totals;
    

    public String getMtimeStr()
	{
		return mtimeStr;
	}

	public void setMtimeStr(String mtimeStr)
	{
		this.mtimeStr = mtimeStr;
	}

	public Integer getTotals()
	{
		return totals;
	}

	public void setTotals(Integer totals)
	{
		this.totals = totals;
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

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getBtd() {
        return btd;
    }

    public void setBtd(String btd) {
        this.btd = btd == null ? null : btd.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getInvite() {
        return invite;
    }

    public void setInvite(Integer invite) {
        this.invite = invite;
    }

    public Byte getGp() {
        return gp;
    }

    public void setGp(Byte gp) {
        this.gp = gp;
    }

    public Integer getSta() {
        return sta;
    }

    public void setSta(Integer sta) {
        this.sta = sta;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getBindtime() {
        return bindtime;
    }

    public void setBindtime(Integer bindtime) {
        this.bindtime = bindtime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public Byte getYellowvip() {
        return yellowvip;
    }

    public void setYellowvip(Byte yellowvip) {
        this.yellowvip = yellowvip;
    }

    public Byte getIsyearvip() {
        return isyearvip;
    }

    public void setIsyearvip(Byte isyearvip) {
        this.isyearvip = isyearvip;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getLgtm() {
        return lgtm;
    }

    public void setLgtm(Integer lgtm) {
        this.lgtm = lgtm;
    }

    public Integer getLxlg() {
        return lxlg;
    }

    public void setLxlg(Integer lxlg) {
        this.lxlg = lxlg;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getRegip() {
        return regip;
    }

    public void setRegip(Long regip) {
        this.regip = regip;
    }

    public Byte getActiveAssets() {
        return activeAssets;
    }

    public void setActiveAssets(Byte activeAssets) {
        this.activeAssets = activeAssets;
    }
}