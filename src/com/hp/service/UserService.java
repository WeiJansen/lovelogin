package com.hp.service;

import java.util.ArrayList;
import java.util.List;

import com.hp.dao.UserDao;
import com.hp.po.User;
import com.hp.util.Page;

/**
 * 处理用户相关业务
 * 
 * @author HP
 *
 */
public class UserService {
	/**
	 * 声明：UserDao ud，int i 定义：int i =5; UserDao ud = new UserDao();
	 */
	private UserDao ud = new UserDao();
	
	public void updateUser(User u){
		ud.updateUser(u);
	}
	
	public User findUserById(int id){
		return ud.findUserById(id);
	}

	public List findUserByName(String name){
		return ud.findUserByName(name);
	}
	public List findAllUsers2() {
		return ud.findAllUsers();
	}

	public User findUser(User u) {
		return ud.findUser(u);
	}

	public List findAllUsers() {
		/**
		 * 使用泛型可以定义集合中存放的数据类型
		 */
		List<User> userList = new ArrayList<User>();
		/**
		 * list中的两个add方法构成了方法的重载 重载的特征： 1：在同一个类中 2：方法名称相同，参数的个数或类型不同
		 * 3：单纯依靠返回值无法判断是否重载 int add(int x, int y) void add(int x,int y)
		 */
		userList.add(new User(1, "zhang", "123", 1));
		userList.add(new User(2, "wang", "123", 1));
		userList.add(new User(3, "li", "123", 2));
		userList.add(new User(4, "zhao", "123", 2));

		return userList;
	}

	public Page search(String name, String sex, String address, String date,Page page)  {
		// TODO Auto-generated method stub
		return ud.search(name,sex,address,date,page);
	}
}
