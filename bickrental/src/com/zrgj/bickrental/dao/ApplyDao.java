package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgj.bickrental.entity.ApplyClass;
import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author yuanhao
 * 
 */

public class ApplyDao {
	// 创建一个JDBC工具类
	private JDBCUtils jdbc = new JDBCUtils();

	public ApplyDao() {
		jdbc.getConnection();
	}

	/**
	 * 获取调度申请单
	 * 
	 * @return
	 * @throws Exception
	 */

	public List<ApplyClass> getMessage() throws Exception {
		String sql = "SELECT * FROM apply";
		ArrayList<Object> params = new ArrayList<Object>();
		List<ApplyClass> apply = jdbc.findMoreRefResult(sql, null,
				ApplyClass.class);
		jdbc.releaseRes();
		return apply;
	}

	/**
	 * 根据表单id删除该条信息
	 * 
	 * @param aid
	 * @throws SQLException
	 */
	public void deleteTable(String aid) throws SQLException {
		String sql = "DELETE from apply where aId=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(aid);
		jdbc.updateByPreparedStatement(sql, params);

	}

}
