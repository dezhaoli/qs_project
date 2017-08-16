package com.qs.pub.sync.model;

import java.io.Serializable;

public class SyncUser implements Serializable{
	private Integer id;
	private Integer userId;
	private String name;
	private Integer age;
	private Double salary;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "SyncUser [id=" + id + ", userId=" + userId + ", name=" + name + ", age=" + age + ", salary=" + salary
				+ "]";
	}
	
}
