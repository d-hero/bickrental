package com.zrgj.bickrental.dao;

import java.util.ArrayList;
import java.util.List;

import com.zrgj.bickrental.entity.Admin;
import com.zrgj.bickrental.entity.Person;
import com.zrgj.bickrental.entity.User;
import com.zrgj.utils.JDBCUtils;

/**
 * 
 * @author yuanhao
 * 
 */

public class AdminDao {

	private JDBCUtils ut = new JDBCUtils();

	public AdminDao() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据帐号密码查询制定admin
	 * 
	 * @param admin
	 * @return
	 */
	public Admin queryAdmin(Admin admin) {
		Admin admin_res = null;

		String sql = "select adminid from admin where account = ? and password = ?";
		List<Person> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(admin.getAccount());
		params.add(admin.getPassword());

		try {
			admin_res = ut.findSimpleRefResult(sql, params, Admin.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ut.releaseRes();
		}

		PersonDao personDao = new PersonDao();
		Person queryPerson = personDao.queryPerson(
				new Person(admin.getAccount(), admin.getPassword()), "admin");

		if (queryPerson != null) {

			admin_res.setAddress(queryPerson.getAddress());
			admin_res.setBirthday(queryPerson.getBirthday());
			admin_res.setName(queryPerson.getName());
			admin_res.setSex(queryPerson.getSex());
			admin_res.setTelephone(queryPerson.getTelephone());
		} else {
			admin_res = null;
		}

		return admin_res;
	}

}
