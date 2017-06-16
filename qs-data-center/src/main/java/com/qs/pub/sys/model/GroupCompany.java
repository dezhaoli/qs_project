package com.qs.pub.sys.model;

import java.util.Date;

public class GroupCompany {
    private Integer id;

    private Integer companyId;

    private Integer groupId;

    private Date createTime;
    
    private Boolean selected;
    
    
    private String companyName;
    
    private String icon;
    
    
    private Integer parentId;
    private String userGroupName;
    

    public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	public String getUserGroupName()
	{
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName)
	{
		this.userGroupName = userGroupName;
	}

	public Boolean getSelected()
	{
		return selected;
	}

	public void setSelected(Boolean selected)
	{
		this.selected = selected;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}