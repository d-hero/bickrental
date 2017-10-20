package com.zrgj.bickrental.entity;

public class User extends Person{
	private String userId; //用户ID
	private Double pledge; //押金
	private Double balance;  //余额  
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public User(String account,String password,String user){
		super(account,password,user);
	}
	
	
	public User(String account,String password){
		super(account,password);
	}
	public User(String account){
		super(account);
	}

	public User(String name, String sex, String birthday, String telephone) {
		super(name, sex, birthday,  telephone);
	}
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getPledge() {
		return pledge;
	}

	public void setPledge(Double pledge) {
		this.pledge = pledge;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", pledge=" + pledge + ", balance="
				+ balance + "]";
	}
	
}
