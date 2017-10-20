package com.zrgj.bickrental.entity;

public class ApplyClass {
	
	private int aId;  //申请ID
	private String statId;//站点ID
	private int anum;//申请自行车数量
	private String atype;//申请类型
	private String atime;//申请时间
	private int astate;//申请状态
	
	
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId = statId;
	}
	public int getAnum() {
		return anum;
	}
	public void setAnum(int anum) {
		this.anum = anum;
	}
	
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	
	

	public int getAstate() {
		return astate;
	}
	public void setAstate(int astate) {
		this.astate = astate;
	}
	public ApplyClass(int aId, String statId, int anum, String atype,
			String atime, int astate) {
		super();
		this.aId = aId;
		this.statId = statId;
		this.anum = anum;
		this.atype = atype;
		this.atime = atime;
		this.astate = astate;
	}
	public ApplyClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
