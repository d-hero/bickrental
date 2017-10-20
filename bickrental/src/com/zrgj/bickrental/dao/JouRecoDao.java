package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgj.bickrental.entity.UseRecord;
import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author chenxincai
 * 
 */

public class JouRecoDao {
	public JDBCUtils jdbc = new JDBCUtils();

	/**
	 * 记录借车
	 * 
	 * @param userId
	 * @param bikeId
	 * @param broTime
	 * @return
	 * @throws SQLException
	 */

	public boolean addJournal_Borrow_Info(String userId, String bikeId,
			String broTime) throws SQLException {
		// sql操作语句
		String sql = "INSERT INTO journal (userId,bikeId,broTime, retTime,useTime, pay, state) "
				+ "VALUES (?,?,?,?,?,?,?)";// 因为id是自动增长的
		// 创建一个接收对象
		ArrayList<Object> params = new ArrayList<Object>();
		// 添加数据

		params.add(userId);
		params.add(bikeId);
		params.add(broTime);
		params.add("");
		params.add("");
		params.add(0.0);
		params.add("未还");
		boolean flag = jdbc.updateByPreparedStatement(sql, params);
		jdbc.releaseRes();
		return flag;

	}

	/**
	 * 记录还车
	 * 
	 * @param userId
	 * @param retTime
	 * @param useTime
	 * @param pay
	 * @return
	 * @throws SQLException
	 */

	public boolean addJournal_Return_Info(String userId, String retTime,
			String useTime, double pay) throws SQLException {
		// sql操作语句
		String sql = "UPDATE   journal   SET    retTime = ? ,  useTime = ? , pay = ?, "
				+ "state = ?   WHERE  userId = ? And State = ?  ";// 因为id是自动增长的
		// 创建一个接收对象
		ArrayList<Object> params = new ArrayList<Object>();
		// 添加数据

		params.add(retTime);
		params.add(useTime);
		params.add(pay);
		params.add("已还");
		params.add(userId);
		params.add("未还");

		// 调用jdbc的方法
		boolean flag = jdbc.updateByPreparedStatement(sql, params);

		jdbc.releaseRes();

		return flag;

	}

	/**
	 * 根据UserId查询 用户存在未还车的记录 ,存在则获取借车记录 的借车时间 和借车的bikeId
	 * 
	 * @param userID
	 * @return
	 */

	public UseRecord queryNotReturnRecord(String userID) { // 传进来的是外键
		String sql = "SELECT broTime,bikeId FROM journal WHERE userId = ? AND  state = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(userID);
		params.add("未还");
		UseRecord record = null;
		try {
			record = jdbc.findSimpleRefResult(sql, params, UseRecord.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdbc.releaseRes();
		return record;
	}

	/**
	 * 根据UserId查询所有行程记录
	 * 
	 * @param userID
	 * @return
	 */

	public List<UseRecord> queryRecord(String userID) {
		String sql = "SELECT * FROM journal WHERE userId = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(userID);
		List<UseRecord> record = null;
		try {
			record = jdbc.findMoreRefResult(sql, params, UseRecord.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdbc.releaseRes();
		return record;
	}
}
