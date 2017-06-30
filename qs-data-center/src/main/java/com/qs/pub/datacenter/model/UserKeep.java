package com.qs.pub.datacenter.model;

import java.util.Date;

public class UserKeep {
    private Integer id;

    private Integer userId;

    private String userName;

    private String appId;

    private Date createDate;

    private Integer one;

    private Integer two;

    private Integer three;

    private Integer four;

    private Integer five;

    private Integer six;

    private Integer seven;

    private Integer fifteen;

    private Integer thirty;

    private String extend1;

    private String extend2;

    private String extend3;

    private String extend4;

    private String extend5;
    
    
    private Double onePercent;
    private Double twoPercent;
    private Double threePercent;
    private Double fourPercent;
    private Double fivePercent;
    private Double sixPercent;
    private Double sevenPercent;
    private Double fifteenPercent;
    private Double thirtyPercent;
    private String createDateStr;
    
    
    public String getCreateDateStr()
	{
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr)
	{
		this.createDateStr = createDateStr;
	}

	private Integer totals;
    
    

    public Integer getTotals()
	{
		return totals;
	}

	public void setTotals(Integer totals)
	{
		this.totals = totals;
	}

	public Double getOnePercent()
	{
		return onePercent;
	}

	public void setOnePercent(Double onePercent)
	{
		this.onePercent = onePercent;
	}

	public Double getTwoPercent()
	{
		return twoPercent;
	}

	public void setTwoPercent(Double twoPercent)
	{
		this.twoPercent = twoPercent;
	}

	public Double getThreePercent()
	{
		return threePercent;
	}

	public void setThreePercent(Double threePercent)
	{
		this.threePercent = threePercent;
	}

	public Double getFourPercent()
	{
		return fourPercent;
	}

	public void setFourPercent(Double fourPercent)
	{
		this.fourPercent = fourPercent;
	}

	public Double getFivePercent()
	{
		return fivePercent;
	}

	public void setFivePercent(Double fivePercent)
	{
		this.fivePercent = fivePercent;
	}

	public Double getSixPercent()
	{
		return sixPercent;
	}

	public void setSixPercent(Double sixPercent)
	{
		this.sixPercent = sixPercent;
	}

	public Double getSevenPercent()
	{
		return sevenPercent;
	}

	public void setSevenPercent(Double sevenPercent)
	{
		this.sevenPercent = sevenPercent;
	}

	public Double getFifteenPercent()
	{
		return fifteenPercent;
	}

	public void setFifteenPercent(Double fifteenPercent)
	{
		this.fifteenPercent = fifteenPercent;
	}

	public Double getThirtyPercent()
	{
		return thirtyPercent;
	}

	public void setThirtyPercent(Double thirtyPercent)
	{
		this.thirtyPercent = thirtyPercent;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOne() {
        return one;
    }

    public void setOne(Integer one) {
        this.one = one;
    }

    public Integer getTwo() {
        return two;
    }

    public void setTwo(Integer two) {
        this.two = two;
    }

    public Integer getThree() {
        return three;
    }

    public void setThree(Integer three) {
        this.three = three;
    }

    public Integer getFour() {
        return four;
    }

    public void setFour(Integer four) {
        this.four = four;
    }

    public Integer getFive() {
        return five;
    }

    public void setFive(Integer five) {
        this.five = five;
    }

    public Integer getSix() {
        return six;
    }

    public void setSix(Integer six) {
        this.six = six;
    }

    public Integer getSeven() {
        return seven;
    }

    public void setSeven(Integer seven) {
        this.seven = seven;
    }

    public Integer getFifteen() {
        return fifteen;
    }

    public void setFifteen(Integer fifteen) {
        this.fifteen = fifteen;
    }

    public Integer getThirty() {
        return thirty;
    }

    public void setThirty(Integer thirty) {
        this.thirty = thirty;
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

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4 == null ? null : extend4.trim();
    }

    public String getExtend5() {
        return extend5;
    }

    public void setExtend5(String extend5) {
        this.extend5 = extend5 == null ? null : extend5.trim();
    }
}