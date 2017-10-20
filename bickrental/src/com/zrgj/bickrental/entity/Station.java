package com.zrgj.bickrental.entity;

public class Station {

	private String statId;//站点号
	private String priId;//负责人编号
	private String statName;//站点名
	private String location;//站点地址
	private int bikenum;//拥有车辆
	public int getBikenum() {
		return bikenum;
	}
	public void setBikenum(int bikenum) {
		this.bikenum = bikenum;
	}
	
	public String getStatName() {
		return statName;
	}
	public void setStatName(String statName) {
		this.statName = statName;
	}
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId = statId;
	}
	public String getPriId() {
		return priId;
	}
	public void setPriId(String priId) {
		this.priId = priId;
	}
	public Station(String statId, String priId, String statName,
			String location, int bikenum) {
		super();
		this.statId = statId;
		this.priId = priId;
		this.statName = statName;
		this.location = location;
		this.bikenum = bikenum;
	}
	

}
