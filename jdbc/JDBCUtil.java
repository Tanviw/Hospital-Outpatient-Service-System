package cn.tww.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.*;
/**
 * 无连接池的数据库连接工具类
 * @author lenovo
 *
 */

public class JDBCUtil {

	static Properties pros=null;
	
	static{
		
		pros=new Properties();
		
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnect(){
		try {
			Class.forName(pros.getProperty("driver"));
			return 	DriverManager.getConnection(pros.getProperty("url"),
					pros.getProperty("user"),pros.getProperty("pwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static void close(ResultSet rs,Statement st,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(st!=null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void close(ResultSet rs,Statement st){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(st!=null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Statement st,Connection conn){
		try {
			if(st!=null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn){
		
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	


}
