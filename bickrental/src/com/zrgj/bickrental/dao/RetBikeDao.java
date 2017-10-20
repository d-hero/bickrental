package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zrgj.bickrental.entity.Bike;
import com.zrgj.bickrental.entity.Station;
import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author chenxincai
 * 
 */

public class RetBikeDao {
	private JDBCUtils jdbc = new JDBCUtils();

	/**
	 * 获取当前站对象
	 * 
	 * @param statName
	 * @return
	 */

	public Station getNowStation(String statName) { // 传进来的是外键
		String sql = "SELECT * FROM station WHERE statName =?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(statName);
		Station station = null;
		try {
			station = jdbc.findSimpleRefResult(sql, params, Station.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdbc.releaseRes();
		return station;
	}

	/**
	 * 当前站点自行车数量+1
	 * 
	 * @param statName
	 * @return
	 * @throws SQLException
	 */

	public boolean addStatBikeNum(String statName) throws SQLException { // 传进来的是外键
		String sql = "UPDATE station SET bikenum = (bikenum+1) WHERE statName =?";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(statName);
		boolean b = jdbc.updateByPreparedStatement(sql, params);
		jdbc.releaseRes();
		return b;
	}

	/**
	 * 新增bike表里对应的自行车
	 * 
	 * @param bikeId
	 * @param staId
	 * @return
	 * @throws SQLException
	 */

	public boolean addBike(String bikeId, String staId) throws SQLException { // 传进来的是外键
		String sql = "INSERT INTO t_bike (bikeId,staId) " + "VALUES (?,?) ";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(bikeId);
		params.add(staId);
		boolean b = jdbc.updateByPreparedStatement(sql, params);
		jdbc.releaseRes();
		return b;
	}

}
