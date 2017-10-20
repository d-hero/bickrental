package com.zrgj.bickrental.dao;

import java.util.ArrayList;
import java.util.List;

import com.zrgj.bickrental.entity.Person;

import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author caiyikai
 * 
 */

public class PersonDao {
	private JDBCUtils ut = new JDBCUtils();

	public PersonDao() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据帐号和密码来查询制定Person
	 * 
	 * @param person
	 * @param tableName
	 * @return
	 */

	public Person queryPerson(Person person, String tableName) {
		Person per_res = null;
		String sql = null;

		if (tableName.equals("user")) {
			sql = "select name,account,password,sex,birthday,address from  user  where account = ? and password = ?";
		} else if (tableName.equals("admin")) {
			sql = "select name,account,password,sex,birthday,telephone,address from  admin  where account = ? and password = ?";
		}

		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(person.getAccount());
		params.add(person.getPassword());

		try {
			per_res = ut.findSimpleRefResult(sql, params, Person.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		return per_res;
	}

}
