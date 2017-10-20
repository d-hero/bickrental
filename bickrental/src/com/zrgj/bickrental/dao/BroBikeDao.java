package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgj.bickrental.entity.Bike;
import com.zrgj.bickrental.entity.Person;
import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author chenxincai
 * 
 */

public class BroBikeDao {
	private JDBCUtils jdbc = new JDBCUtils();

	/**
	 * 获取用户，的名字
	 * 
	 * @param userID
	 * @return
	 */
	public Person queryPerson(String userID) {
		String sql = "SELECT name FROM user WHERE userId = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(userID);
		Person user = null;
		try {
			user = jdbc.findSimpleRefResult(sql, params, Person.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdbc.releaseRes();
		// jdbc.releaseConn();
		return user;
	}

	public BroBikeDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取被借走自行车对象
	 * 
	 * @param bikeId
	 * @return
	 */

	public Bike queryBroBike(String bikeId) {
		String sql = "SELECT * FROM t_bike WHERE bikeId = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(bikeId);

		Bike bike = null;
		try {
			bike = jdbc.findSimpleRefResult(sql, params, Bike.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdbc.releaseRes();
		// jdbc.releaseConn();
		return bike;
	}

	/**
	 * 获取站点自行车对象
	 * 
	 * @param statName
	 * @return
	 */

	public List<Bike> queryBike(String statName) { // 传进来的是外键
		String sql = "SELECT bikeId FROM t_bike,station WHERE station.statName = ? "
				+ "AND station.statId = t_bike.staId";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(statName);
		List<Bike> bike = null;
		try {
			bike = jdbc.findMoreRefResult(sql, params, Bike.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdbc.releaseRes();
		return bike;
	}

	/**
	 * 当前站点自行车-1
	 * 
	 * @param statName
	 * @return
	 * @throws SQLException
	 */

	public boolean deleteStatBikeNum(String statName) throws SQLException { // 传进来的是外键
		String sql = "UPDATE station SET bikenum = (bikenum-1) WHERE statName = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(statName);
		boolean b = jdbc.updateByPreparedStatement(sql, params);
		jdbc.releaseRes();
		return b;
	}

	/**
	 * 删除bike表里对应的自行车
	 * 
	 * @param bikeID
	 * @return
	 * @throws SQLException
	 */

	public boolean deleteBike(String bikeID) throws SQLException { // 传进来的是外键
		String sql = "DELETE FROM t_bike WHERE bikeId = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(bikeID);
		boolean b = jdbc.updateByPreparedStatement(sql, params);
		jdbc.releaseRes();
		return b;
	}

}
