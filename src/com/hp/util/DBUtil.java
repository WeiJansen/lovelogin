package com.hp.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {

	// 加载数据库驱动, 创建连接对象并返回
	public Connection getConn() throws ClassNotFoundException, SQLException{
		
		
		
		/*
		 * 
		 * 1. 加载驱动
		 * 2. 创建连接对象 	// 经过三次握手
		 * 3. 创建执行对象
		 * 4. 执行SQL
		 * 5. 处理返回结果
		 * 6. 释放资源		// 经过四次握手
		 * 
		 * 数据连接池: 在一个容器中-> 池, 创建好一定的数据连接对象 , 应用程序需要连接对象 时, 直接从容器中取, 使用完毕之后, 再将连接对象放回到容器中, 而不是直接关闭. 
		 */
	
		return DataSource.getConn();
	}
	
	// 释放资源  resultset statement connection
	public void close(ResultSet rs, Statement stmt, Connection conn){
		try {
			if (rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (stmt != null){
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (conn != null){
				conn.close();
				// 使用连接池时, close方法, 并不是将连接对象关闭, 而是将其放回到连接中.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Map getObject(String sql, Object[] params){
		
		// 获取查询结果列表
		List<Map<String, Object>> list = query(sql, params);
		
		// 只返回第一个
		if (list.size() > 0){
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	public <T> T getObject(String sql, Object[] params, Class<T> cls){
		// 查询出一条记录
		Map map = getObject(sql, params);
		
		if (map != null){
			// 将Map转换成实体类对象
			return (T) toObj(map, cls);
		} else {
			return null;
		}
		
	}
	
	// 获取多个对象, 并将每个对象保存到一个Map中, 并且将多个对象保存到List
	
	public List<Map<String, Object>> query(String sql, Object[] params){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			
			conn = getConn();
			// 
			stmt = conn.prepareStatement(sql);
			
			// 当参数数组不为空时, 进行参数绑定, 此句必须有, 否则params.length会报NullPointerException
			if(params != null){	//
				for (int i=0; i<params.length; i++){
					stmt.setObject(i+1, params[i]);
				}
			}
			
			// 
			rs = stmt.executeQuery();
			
			// 
			while (rs.next()){
				
				// 由于 查询时, 可能针对的是不同的表, 不同的表结构是不一样的. 能不能有一种统一的数据类型, 能够接收这种可变的数据?
				/*
				 * Map接口
				 * 以key-value的形式存放多个数据
				 * 
				 * 
				 * HashMap
				 */
				HashMap<String, Object> map = new HashMap<>();
				
				int colCount = rs.getMetaData().getColumnCount();
				
				for (int i=1; i<=colCount; i++){
					String colName = rs.getMetaData().getColumnName(i);
					Object value = rs.getObject(colName);
					
					map.put(colName, value);
				}
				
				// 将单条记录的map放到一个数组中.
				
				list.add(map);
			}
			
			// 返回数组
			return list;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt, conn);
		}
		return null;
	}
	
	public <T> List<T> query(String sql, Object[] params, Class<T> cls){
		List<Map<String, Object>> list = query(sql, params);
		
		List<T> res = new ArrayList<>();
		
		for (Map<String, Object> map : list) {
			
			T obj = toObj(map, cls);
			
			res.add(obj);
		}
		
		return res;
	}
	
	private <T> T toObj(Map<String, Object> map, Class<T> cls){
		
		try {
			// 实体类对象的创建
			T obj = cls.newInstance();
			
			// 将Map中的数据设置到obj中
			// beanInfo中保存是类相关的信息
			BeanInfo info = Introspector.getBeanInfo(cls);
			// 获取类的每一个属性的信息
			PropertyDescriptor[] properties = info.getPropertyDescriptors();
			
			for (PropertyDescriptor prop : properties) {
				// 获取属性的名字
				String propName = prop.getName();	// -> name
				// 获取属性的set方法
				Method setter = prop.getWriteMethod();	// -> setName(String );
				
				// 取出map中属性对应的值
				Object value = map.get(propName);
				
				if (value != null){
					// 调用 setter设置值
					try {
						// 实体类属性的设置
						setter.invoke(obj, value);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			return obj;
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public int update(String sql, Object[] params) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		ResultSet rs = null;
		try {
			
			conn = getConn();
			// 
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			// 当参数数组不为空时, 进行参数绑定, 此句必须有, 否则params.length会报NullPointerException
			if(params != null){	//
				for (int i=0; i<params.length; i++){
					stmt.setObject(i+1, params[i]);
				}
			}
			
			// 
			stmt.executeUpdate();
		    rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt, conn);
		}
		
		return res;
	}

	// 添加一个支持分页的查询方法
	public Page query(String sql, Object[] params, Page page){
		
		List all = query(sql, params);
		
		all.size(); // 全部查询的时候, 查出多少条记录
		page.setSum(all.size());
		int maxNum = (all.size() + page.getCount() - 1)/page.getCount(); // 7/3=? 2
		
		/**
		 * 一页显示3条
		 * 
		 * 1 -> 1	1/3=0	3/3
		 * 2 -> 1	2/3=0	4/3
		 * 3 -> 1	3/3=1	5/3
		 * 4 -> 2	4/3=1	6/3
		 * 5 -> 2	5/3=1	7/3
		 * 6 -> 2	6/3=2
		 * 7 -> 3	7/3=2
		 * 8 -> 3	8/3=2
		 * 9 -> 3	9/3=3
		 * 
		 */
		
		page.setMaxNum(maxNum);	
		
		
		sql += " limit "+(page.getPageNum() - 1) * page.getCount()+", "+page.getCount()+" ";
		
/*		ArrayList<Object> paramList = new ArrayList<>();
		for (int i=0; i<params.length; i++){
			paramList.add(params[i]);
		}
		
		paramList.add((page.getPageNum() - 1) * page.getCount());
		paramList.add(page.getCount());*/
		
		List list = query(sql, params);
		
		page.setList(list);
		
		return page;
	}
}









