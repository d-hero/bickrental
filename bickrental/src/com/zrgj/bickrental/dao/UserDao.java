package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgj.bickrental.entity.Person;
import com.zrgj.bickrental.entity.User;
import com.zrgj.utils.JDBCUtils;
import com.zrgj.utils.StringUtils;

/**
 * 
 * @author caiyikai
 * 
 */

public class UserDao {
	public JDBCUtils ut = new JDBCUtils();

	public UserDao() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询用户表中最后一个Id
	 * 
	 * @return
	 */

	public String getUserId_last() {
		String sql = "select   userId   from   user   order   by  userId   DESC   limit   1";
		User user = null;
		try {
			user = ut.findSimpleRefResult(sql, null, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}
		if (user != null) {
			return user.getUserId();
		} else {
			return null;
		}

	}

	/**
	 * 根据帐号(手机号)修改用户密码
	 * 
	 * @param user
	 * @return
	 */

	public boolean updateUserPassword(User user) {
		boolean res = false;

		String id = getUserId_last();
		id = StringUtils.dealId(id);

		String sql = " update user set password = ?   where account = ? ";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();

		params.add(user.getPassword());
		params.add(user.getAccount());

		try {
			res = ut.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		return res;
	}

	/**
	 * 根据用户Id修改用户 姓名 性别 生日
	 * 
	 * @param user
	 * @return
	 */

	public boolean updateUser(User user) {

		boolean res = false;

		String id = getUserId_last();
		id = StringUtils.dealId(id);

		String sql = " update user set name = ? ,sex = ? , birthday = ?   where userId = ? ";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();

		params.add(user.getName());
		params.add(user.getSex());
		params.add(user.getBirthday());
		params.add(user.getUserId());

		try {
			res = ut.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		return res;

	}

	/**
	 * 根据UserId ,修改pledge
	 * 
	 * @param user
	 * @return
	 */

	public boolean updateUserPledge(User user) {

		boolean res = false;

		String id = getUserId_last();
		id = StringUtils.dealId(id);

		String sql = " update user set pledge =  ?   where userId = ? ";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();

		params.add(user.getPledge());
		params.add(user.getUserId());

		try {
			res = ut.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		return res;

	}

	/**
	 * 根据用户Id修改用户余额
	 * 
	 * @param user
	 * @return
	 */

	public boolean updateUserBalance(User user) {

		boolean res = false;

		String id = getUserId_last();
		id = StringUtils.dealId(id);

		String sql = " update user set balance = balance + ?   where userId = ? ";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();

		params.add(user.getBalance());
		params.add(user.getUserId());

		try {
			res = ut.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		return res;

	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */

	public boolean addUser(User user) {

		boolean res = false;

		String id = getUserId_last();
		id = StringUtils.dealId(id);

		String sql = " insert into  user(userId,account,password,sex,birthday,address,pledge,balance,name) values(?,?,?,?,?,?,?,?,?) ";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		params.add(user.getAccount());
		params.add(user.getPassword());
		params.add(user.getSex());
		params.add("");
		params.add("");
		params.add(0.0);
		params.add(0.0);
		params.add("");

		try {
			res = ut.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		return res;
	}

	/**
	 * 查询指定account的帐号余额,押金
	 * 
	 * @param user
	 * @return
	 */

	public Double[] queryUserBalance_And_Pledge(User user) {
		Double dou[] = new Double[2];
		User user_res = null;

		String sql = "select balance,pledge from user where userId = ? ";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user.getUserId());

		try {
			user_res = ut.findSimpleRefResult(sql, params, User.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}
		if (user_res == null) {
			return null;
		}
		dou[0] = user_res.getBalance();
		dou[1] = user_res.getPledge();
		return dou;
	}

	/**
	 * 根据帐号查询用户Id
	 * 
	 * @param user
	 * @return
	 */

	public User queryUserId(User user) {
		User user_res = null;

		String sql = "select userId from user where account = ? ";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user.getAccount());

		try {
			user_res = ut.findSimpleRefResult(sql, params, User.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		return user_res;
	}

	/**
	 * 根据帐号密码查询User所有信息
	 * 
	 * @param user
	 * @return
	 */

	public User queryUser(User user) {
		User user_res = null;

		String sql = "select userId,pledge,balance from user where account = ? and password = ?";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user.getAccount());
		params.add(user.getPassword());

		try {
			user_res = ut.findSimpleRefResult(sql, params, User.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		PersonDao personDao = new PersonDao();
		Person queryPerson = personDao.queryPerson(new Person(
				user.getAccount(), user.getPassword()), "user");

		if (queryPerson != null) {

			user_res.setAddress(queryPerson.getAddress());
			user_res.setBirthday(queryPerson.getBirthday());
			user_res.setName(queryPerson.getName());
			user_res.setSex(queryPerson.getSex());
		} else {
			user_res = null;
		}
		return user_res;
	}

}
