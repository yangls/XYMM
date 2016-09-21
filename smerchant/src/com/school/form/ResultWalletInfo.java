package com.school.form;

import java.io.Serializable;

public class ResultWalletInfo implements Serializable{
	private int ret;
	
	private String msg;
	//处理前金额
	private String beforeMoney;
	//处理后金额
	private String afterMoney;
	//处理金额
	private String money;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBeforeMoney() {
		return beforeMoney;
	}
	public void setBeforeMoney(String beforeMoney) {
		this.beforeMoney = beforeMoney;
	}
	public String getAfterMoney() {
		return afterMoney;
	}
	public void setAfterMoney(String afterMoney) {
		this.afterMoney = afterMoney;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	

}
