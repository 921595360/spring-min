package com.silence.web.spring_min.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class DBUtil {

	private static DataSource dataSource=DruidUtil.getDataSource();
	
	private static Logger logger = Logger.getLogger(DBUtil.class);
	
	public static void executeSql(String sql) throws SQLException{
		logger.info("执行sql===>"+sql);
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		statement.execute(sql);
	}
	
	public static void executeSql(String sql,Object... params) throws SQLException{
		logger.info("执行sql===>"+sql);
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		if(null!=params){
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i+1, params[i]);
			}
		}
		statement.execute();
	}
	
	public static List<Map<String, Object>> executeQuery(String sql,Object... params ){
		try {
			Connection connection = dataSource.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			if(null!=params){
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i+1, params[i]);
				}
			}
			
			int columnCount = statement.getMetaData().getColumnCount();
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Map<String,Object>> result=new ArrayList<>();
			while(resultSet.next()){
				HashMap<String, Object> one = new HashMap<>();
				for (int i = 0; i < columnCount; i++) {
					String columnName=statement.getMetaData().getColumnName(i+1);
					Object value = resultSet.getObject(i+1);
					one.put(columnName, value);
				}
				result.add(one);
			}
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map<String, Object> executeQueryOne(String sql,Object... params ){
		
		try {
			Connection connection = dataSource.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			if(null!=params){
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i+1, params[i]);
				}
			}
			
			
			
			ResultSet resultSet = statement.executeQuery();
			
			int columnCount = statement.getMetaData().getColumnCount();
			
			Map<String,Object> result=new HashMap<>();
			
			while(resultSet.next()){
				for (int i = 0; i < columnCount; i++) {
					String columnName=statement.getMetaData().getColumnName(i+1);
					Object value = resultSet.getObject(i+1);
					result.put(columnName, value);
				}
			}
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
