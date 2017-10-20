package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import Application.TempData;

import com.zrgj.bickrental.entity.Station;
import com.zrgj.bickrental.entity.Principal;
import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author zhaoyaojian
 *
 */



public class StationDao {
	// 创建一个JDBC工具类
	private JDBCUtils jdbc = new JDBCUtils();

	public StationDao() {

		jdbc.getConnection();
	}

	/**
	 * 根据负责人id获取车辆数
	 * 
	 * @param priId
	 * @return
	 * @throws Exception
	 */

	public int getNum(String priId) throws Exception {
		String sql = "SELECT * FROM station WHERE priId= ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(TempData.priId);
		Station st = jdbc.findSimpleRefResult(sql, params, Station.class);
		int bnum = st.getBikenum();

		return bnum;
	}

	/**
	 * 根据负责人id查询站点id
	 * 
	 * @param priId
	 * @return
	 * @throws Exception
	 */

	public String getStatId(String priId) throws Exception {
		String sql = "SELECT * FROM station WHERE priId= ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(TempData.priId);
		Station st = jdbc.findSimpleRefResult(sql, params, Station.class);
		String sId = st.getStatId();
		return sId;
	}

	/**
	 * 添加一条申请单信息
	 * 
	 * @param statId
	 * @param anum
	 * @param atype
	 * @param atime
	 * @throws SQLException
	 */

	public void insertTable(String statId, int anum, String atype, String atime)
			throws SQLException {

		String sql = "INSERT into apply values(null,?,?,?,?)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(statId);
		params.add(anum);
		params.add(atype);
		params.add(atime);
		jdbc.updateByPreparedStatement(sql, params);

	}

}
