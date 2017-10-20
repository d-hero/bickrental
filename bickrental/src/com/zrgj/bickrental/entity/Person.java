package com.zrgj.bickrental.entity;

public class Person {
	
	
	private String name;// 用户姓名
	private String account; //帐号
	private String password;// 密码
	public String  sex;// 性别
	private String birthday;// 出生日期
	private String telephone;// 手机号
	private String address;// 地址
	
     
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String account, String password,String sex) {
		super();
		this.account = account;
		this.password = password;
		this.sex = sex;
	}
	
	
	
	
	
	
	public Person(String name, String sex, String birthday, String telephone) {
		super();
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.telephone = telephone;
	}

	public Person(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	public Person(String account) {
		super();
		this.account = account;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", password=" + password + ", sex="
				+ sex + ", birthday=" + birthday + ", telephone=" + telephone
				+ ", address=" + address + "]";
	}

}
