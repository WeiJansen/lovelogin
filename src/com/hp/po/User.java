package com.hp.po;

import java.util.Date;

public class User {
	/**
	 * 成员变量一般使用private权限 通过get,set方法访问成员变量
	 */
	private int id;

	private String name;

	private String pwd;

	private int role;
	
	private String sex;
	private String hobby;
	private String address;
	private String email;
	private Date date;
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User() {

	}

	public User(int id, String name, String pwd, int role) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
