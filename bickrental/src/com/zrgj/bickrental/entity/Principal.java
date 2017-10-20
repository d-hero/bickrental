package com.zrgj.bickrental.entity;


public class Principal extends Person{
	private String pId;  //站点负责人Id

	
	

	
	
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Principal() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Principal(String account,String password) {
		super(account,password);
		// TODO Auto-generated constructor stub
	}
	
}
