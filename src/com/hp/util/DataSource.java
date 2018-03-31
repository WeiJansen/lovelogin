package com.hp.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

	private static ComboPooledDataSource c3p0DataSource = null;// 保证程序运行时, 最多只有一个变量
	
	/**
	 * 静态块
	 * 在程序运行时, 只执行一次
	 */
	static{
		
		// 操作属性文件的对象
		Properties properties = new Properties();
		try {
			properties.load(DataSource.class.getClassLoader().getResourceAsStream("db.properties"));
			
			// 取属性文件中的属性值
			String driverName = properties.getProperty("drivername");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			String maxSize = properties.getProperty("maxPoolSize");
			
			// 创建连接池对象
			c3p0DataSource = new ComboPooledDataSource();
			
			c3p0DataSource.setDriverClass(driverName);
			c3p0DataSource.setJdbcUrl(url);
			c3p0DataSource.setUser(username);
			c3p0DataSource.setPassword(password);
			
			c3p0DataSource.setMaxPoolSize(Integer.parseInt(maxSize));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 提供一个获取连接对象的方法
	public static Connection getConn() throws SQLException{
		
		return c3p0DataSource.getConnection();
	}
	
}
