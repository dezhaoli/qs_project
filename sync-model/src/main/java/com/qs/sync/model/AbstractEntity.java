/**
 * 
 */
package com.qs.sync.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linxueqin
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable{

    /**
     * Id
     */
    protected String id;
    
    protected String name;
    
    /**
     * 创建时间
     */
    protected Date createTime;
    
    /**
     * 最后修改时间
     */
    protected Date modifyTime;
    
    /**
     * 创建者
     */
    protected String creatorId;
    
    /**
     * 修改者
     */
    protected String modifierId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    @Override
    public String toString() {
        return "AbstractEntity [id=" + id + "]";
    }

  
}
