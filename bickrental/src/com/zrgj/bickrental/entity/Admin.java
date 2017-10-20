package com.zrgj.bickrental.entity;



public class Admin extends Person{
private String adminid;  //管理员Id



public Admin(){
	
}

public Admin(String account,String password){
	super(account,password);
}




public String getAdminid() {
	return adminid;
}

public void setAdminid(String adminid) {
	this.adminid = adminid;
}


}
