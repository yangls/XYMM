package com.school.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.school.common.IDEntity;

@Entity
@Table(name="t_admin_user")
public class AdminUser extends IDEntity{
	
	private String userName;
	private String passWord;
	
	
	
	
	public AdminUser() {
		super();
	}
	
	
	public AdminUser(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		setCreateTime(new Date());
		setUpdateTime(new Date());
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	

}
