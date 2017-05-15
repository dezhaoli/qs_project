package com.qs.sync.model;

import java.util.Date;

/**
 * @ClassName: SyncUser 
 * @描述: 用户信息
 * @author moyousheng
 * @date 2016-5-27下午15:40:11
 */
@SuppressWarnings("serial")
public class SyncUser extends SyncObject{
	//用户信息
    protected String code;
    protected String userName;
    protected String empName;
    protected String orgUnitId;
    protected String type;
    protected String state;
    protected String strategy;
    protected String authMode;
    protected Date validTime;
    protected Date invalidTime;
    //调整后
    //protected String empId;
    //雇员信息
    protected String sn;
    protected String jobNumber;
    protected String sex;
    protected String idNumber;
    protected String nativePlace;
    protected String education;
    protected String school;
    protected String birthday;
    protected String contactAddress;
    protected String photo;
    protected String ophOne;
    protected String ophTwo;
    protected String mobileOne;
    protected String mobileTwo;
    protected String fax;
    protected String personalEmail;
    protected String enterpriseEmail;
    protected String administrativeLevel;
    protected String duty;
    protected String empState;

    protected String remark;
    protected String dutyType;
    
    

    
	public String getDutyType() {
		return dutyType;
	}
	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}
	public String getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getAuthMode() {
		return authMode;
	}
	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}
	public Date getValidTime() {
		return validTime;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	public Date getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
   
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getOphOne() {
		return ophOne;
	}
	public void setOphOne(String ophOne) {
		this.ophOne = ophOne;
	}
	public String getOphTwo() {
		return ophTwo;
	}
	public void setOphTwo(String ophTwo) {
		this.ophTwo = ophTwo;
	}
	public String getMobileOne() {
		return mobileOne;
	}
	public void setMobileOne(String mobileOne) {
		this.mobileOne = mobileOne;
	}
	public String getMobileTwo() {
		return mobileTwo;
	}
	public void setMobileTwo(String mobileTwo) {
		this.mobileTwo = mobileTwo;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getEnterpriseEmail() {
		return enterpriseEmail;
	}
	public void setEnterpriseEmail(String enterpriseEmail) {
		this.enterpriseEmail = enterpriseEmail;
	}
	public String getAdministrativeLevel() {
		return administrativeLevel;
	}
	public void setAdministrativeLevel(String administrativeLevel) {
		this.administrativeLevel = administrativeLevel;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getEmpState() {
		return empState;
	}
	public void setEmpState(String empState) {
		this.empState = empState;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
 
}