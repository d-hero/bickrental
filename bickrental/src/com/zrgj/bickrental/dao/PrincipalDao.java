package com.zrgj.bickrental.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Application.TempData;

import com.zrgj.bickrental.entity.Person;
import com.zrgj.bickrental.entity.Principal;
import com.zrgj.bickrental.entity.Station;
import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author zhaoyaojian
 * 
 */

public class PrincipalDao {
	private JDBCUtils jdbc = new JDBCUtils();

	public PrincipalDao() {

		jdbc.getConnection();
	}

	/**
	 * 获取站点负责人表的信息
	 * 
	 * @return
	 */

	public List<Principal> queryPrincipal() {
		List<Principal> principal_res = null;

		String sql = "select pId from principal ";

		try {
			List<Principal> findMoreRefResult = jdbc.findMoreRefResult(sql,
					null, Principal.class);
			principal_res = findMoreRefResult;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbc.releaseRes();
		}

		if (principal_res != null) {
			PersonDao personDao = new PersonDao();
			String sql2 = null;

			for (Principal pri : principal_res) {
				Person findRes = null;
				sql2 = "select name,account,password,sex,birthday,telephone,address from principal where  pId= ?";
				List<Person> list = null;
				ArrayList<Object> params = new ArrayList<Object>();
				params.add(pri.getpId());

				try {
					findRes = jdbc.findSimpleRefResult(sql2, params,
							Person.class);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pri.setName(findRes.getName());
				pri.setAccount(findRes.getAccount());
				pri.setPassword(findRes.getPassword());
				pri.setSex(findRes.getSex());
				pri.setBirthday(findRes.getBirthday());
				pri.setTelephone(findRes.getTelephone());
				pri.setAddress(findRes.getAddress());

			}

		}
		return principal_res;
	}

	/**
	 * 获取站点负责人表的最后一条信息的Id
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getPid() throws Exception {
		String sql = "select   pId   from   principal   order   by  pId   DESC   limit   1";
		ArrayList<Object> params = new ArrayList<Object>();
		Principal p = jdbc.findSimpleRefResult(sql, params, Principal.class);
		String p1 = p.getpId();

		return p1;
	}

	/**
	 * 增加数据到站点负责人表
	 * 
	 * @param pId
	 * @param name
	 * @param account
	 * @param password
	 * @param sex
	 * @param birthday
	 * @param address
	 * @param telephone
	 * @throws SQLException
	 */

	public void insertTable(String pId, String name, String account,
			String password, String sex, String birthday, String address,
			String telephone) throws SQLException {
		String sql = "INSERT into principal values(?,?,?,?,?,?,?,?)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pId);
		params.add(name);
		params.add(account);
		params.add(password);
		params.add(sex);
		params.add(birthday);
		params.add(address);
		params.add(telephone);
		jdbc.updateByPreparedStatement(sql, params);

	}

	/**
	 * 根据帐号密码查询指定Principal 的PId
	 * 
	 * @param principal
	 * @return
	 */

	public String queryPrincipalId(Principal principal) {
		Principal principal_res = null;

		String sql = "select pId from principal where account = ? and password = ?";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(principal.getAccount());
		params.add(principal.getPassword());

		try {
			principal_res = jdbc.findSimpleRefResult(sql, params,
					Principal.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbc.releaseRes();
		}

		if (principal_res != null) {
			return principal_res.getpId();
		} else {
			return null;
		}

	}

	/**
	 * 更新站点负责人表
	 * 
	 * @param p
	 * @throws SQLException
	 */

	public void updateTable(Principal p) throws SQLException {
		String sql = "update principal set pId=?,name=?,account=?,"
				+ "password=?,sex=?,birthday=?,address=?,telephone=? where pId=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(p.getpId());
		params.add(p.getName());
		params.add(p.getAccount());
		params.add(p.getPassword());
		params.add(p.getSex());
		params.add(p.getBirthday());
		params.add(p.getAddress());
		params.add(p.getTelephone());
		params.add(p.getpId());
		jdbc.updateByPreparedStatement(sql, params);

	}
}
