package com.school.form;

import java.io.Serializable;

public class ApplyOutForm implements Serializable{
	
	private String phone;
	/**提现账号*/
	private String account;
	/**账号名称*/
	private String accountName;
	
	/**账号类型(银行,虚拟支付宝,微信)默认 0:银行*/
	 private int accountType=0;
	/**提现成功后流水号*/
	private String serialAccount;
	
	/**处理状态 0:申请中 1 ：成功 2：失败*/
	private int status=9;
	
	private String startTime;
	private String endTime;
	private String serialno;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getSerialAccount() {
		return serialAccount;
	}

	public void setSerialAccount(String serialAccount) {
		this.serialAccount = serialAccount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	
	

}
