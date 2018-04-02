package com.hp.dao;

import java.util.ArrayList;
import java.util.List;

import com.hp.po.User;
import com.hp.util.DBUtil;
import com.hp.util.Page;

/**
 * dao：data access object 专门用于和数据库打交道 凡是关于用户的数据库操作，都放到UserDao中
 * 
 * @author HP
 *
 */
public class UserDao {
	private DBUtil db = new DBUtil();

	public void updateUser(User u){
		String sql = "update user set pwd = ? , role = ? where id = ?";
		try {
			db.update(sql, new Object[]{u.getPwd(),u.getRole(),u.getId()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User findUserById(int id) {
		String sql = "select * from user where id = ?";
		User u = new User();
		try {
			u =  db.getObject(sql, new Object[] {id}, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	/**
	 * 根据用户名和密码判断是否存在
	 * 
	 * @param u：包含用户名和密码
	 * @return：User，查询出的用户对象 null，没查询出数据
	 */
	public User findUser(User u) {
		String sql = "select * from user where name = ? and pwd = ?";
		User u2 = new User();
		
		u2 = db.getObject( sql, new Object[] { u.getName(), u.getPwd() } ,User.class);
		
		 return u2;
	}

	public List findAllUsers() {
		String sql = "select * from user";
		/**
		 * 1.没有一个变量接收返回值 2.return null
		 */
		List userList = new ArrayList();
		try {
			userList = db.query( sql, new Object[] {} ,User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * 根据用户名模糊查询
	 * 
	 * @param name
	 * @return 根据模糊搜索得到结果集
	 */
	public List findUserByName(String name) {
		String sql = "select * from user where name like '%" + name + "%'";
		List userList = new ArrayList();
		try {
			userList = db.query(sql, new Object[] {},User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	public static void main(String[] args) {
		UserDao ud = new UserDao();
		User u = ud.findUser(new User(1, "zhang", "1234", 0));

		List userList = ud.findUserByName("zha");
	}

	public Page search(String name, String sex, String address, String date,Page page)  {
		String sql = "select * from user where name like ? ";
		ArrayList<Object> params =new ArrayList<>();
		params.add("%"+name+"%");
		
		if(sex != null && sex.length() > 0) {
			params.add(sex);
			sql+= " and sex = ? ";
		}
		if(address != null && address.length() > 0) {
			params.add("%"+address+"%");
			sql+= " and address like ? ";
		}
		if(date != null && date.length() > 0) {
			params.add(date);
			sql+= " and date_format(date,'%Y-%m-%d') like date_format(?,'%Y-%m-%d') ";
		}
		
			return  db.query(sql, params.toArray(), page);
		
	}
}
