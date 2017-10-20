package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Application.TempData;

import com.zrgj.bickrental.dao.*;
import com.zrgj.bickrental.entity.Admin;
import com.zrgj.bickrental.entity.ApplyClass;
import com.zrgj.bickrental.entity.Person;
import com.zrgj.bickrental.entity.Station;

import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author yuanhao
 * 
 */

public class MangerDao {
	private JDBCUtils jdbc = new JDBCUtils();

	public MangerDao() {
		super();
		// TODO Auto-generated constructor stub
		jdbc.getConnection();
	}

	/**
	 * 给站点增加车辆数
	 * 
	 * @param statId
	 * @param anum
	 * @return
	 * @throws Exception
	 */

	public boolean updateAddnum(String statId, int anum) throws Exception {
		String sql = "UPDATE station SET bikenum =bikenum+? WHERE statId = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(anum);
		params.add(statId);
		boolean flag = jdbc.updateByPreparedStatement(sql, params);

		return flag;
	}

	/**
	 * 给站点减少车辆数
	 * 
	 * @param statId
	 * @param anum
	 * @return
	 * @throws Exception
	 */

	public boolean updateMinusnum(String statId, int anum) throws Exception {
		String sql = "UPDATE station SET bikenum =bikenum-? WHERE statId = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(anum);
		params.add(statId);
		boolean flag = jdbc.updateByPreparedStatement(sql, params);
		// jdbc.releaseConn();
		return flag;
	}

	/**
	 * 根据表单号获取站点号
	 * 
	 * @param aId
	 * @return
	 * @throws Exception
	 */

	public String getStatId(String aId) throws Exception {
		String sql = "SELECT * FROM apply WHERE aId= ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(aId);
		ApplyClass a = jdbc.findSimpleRefResult(sql, params, ApplyClass.class);
		String sId = a.getStatId();
		return sId;

	}

}
