package com.silence.web.spring_min.util;

import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidUtil {
	
	private static Properties properties = PropertiesUti.getProperties("/db.properties");
	
	private static DataSource dataSource;
	
	static{
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(properties.getProperty("jdbc.url"));
		druidDataSource.setUsername(properties.getProperty("jdbc.username"));
		druidDataSource.setPassword(properties.getProperty("jdbc.password"));
		dataSource=druidDataSource;
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	
}
