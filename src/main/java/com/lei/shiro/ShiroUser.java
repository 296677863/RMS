package com.lei.shiro;

import java.io.Serializable;

public class ShiroUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6590040057232207487L;
	private Integer userId;
	private String account;
	
	public ShiroUser(){
		super();
	}

	public ShiroUser(Integer userId, String account) {
		super();
		this.userId=userId;
		this.account=account;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return account;
	}
	
	

}
