package com.school.bean.merchant;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.school.common.IDEntity;

/**
 * 商家信息
 * @author acode
 *
 */
@Entity
@Table(name="t_sm_merchant_info")
public class MerChantInfo extends IDEntity{
	
	private String phone;
	private String passWord;
	//审核状态0：处理中，1：审核通过2：审核失败
	private int status;
	
	//审核状态信息
	private String satausMsg;
	
	public MerChantInfo() {
		super();
		
	}
	
	public MerChantInfo(String phone, String passWord) {
		super();
		this.phone = phone;
		this.passWord = passWord;
		setCreateTime(new Date());
		setUpdateTime(new Date());
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSatausMsg() {
		return satausMsg;
	}
	public void setSatausMsg(String satausMsg) {
		this.satausMsg = satausMsg;
	}
	
	

}
