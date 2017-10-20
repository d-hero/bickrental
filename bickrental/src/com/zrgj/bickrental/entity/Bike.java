package com.zrgj.bickrental.entity;

public class Bike {

	private String  bikeId;//自行车Id
  private String staId;//站点Id

	public String getBikeId() {
		return bikeId;
	}
	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}
	
	
	
	public String getStaId() {
		return staId;
	}
	public void setStaId(String staId) {
		this.staId = staId;
	}
	public Bike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bike(String bikeId, String staId) {
		super();
		this.bikeId = bikeId;
		this.staId = staId;
	}
	@Override
	public String toString() {
		return "Bike [bikeId=" + bikeId +  ", staId="
				+ staId + "]";
	}


}