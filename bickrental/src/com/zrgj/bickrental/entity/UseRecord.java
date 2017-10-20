package com.zrgj.bickrental.entity;
/**
 * 使用时间=还车时间 - 借车时间；
 * @author Administrator
 *
 */
public class UseRecord {

	private long jourId; //行程次数ID
	private String userId;//用户ID
	private String  bikeId;//车辆ID
	private String broTime;//借用时间
	private String retTime;//归还时间
	private String useTime;//使用时间
	private String state;//借车状态
	private double pay;//结算费用
	
	public  long getJourId() {
		return jourId;
	}
	public void setJourId( long jourId) {
		this.jourId = jourId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId( String userId) {
		this.userId = userId;
	}
	public  String getBikeId() {
		return bikeId;
	}
	public void setBikeId( String bikeId) {
		this.bikeId = bikeId;
	}
	
	public String getRetTime() {
		return retTime;
	}
	public void setRetTime(String retuTime) {
		this.retTime = retuTime;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime= useTime;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	

	public String getBroTime() {
		return broTime;
	}
	public void setBroTime(String broTime) {
		this.broTime = broTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public UseRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UseRecord( long jourId, String userId, String bikeId,
			String broTime, String retTime, String useTimee, double pay) {
		super();
		this.jourId = jourId;
		this.userId = userId;
		this.bikeId = bikeId;
		this.broTime = broTime;
		this.retTime = retTime;
		this.useTime = useTime;
		this.pay = pay;
	}
	

}
