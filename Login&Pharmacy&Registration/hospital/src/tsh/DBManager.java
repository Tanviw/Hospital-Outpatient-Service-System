package tsh;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @author Tanvi
 *
 */
public class DBManager {

	/**
	 * 配置信息
	 */
	private static Configuration conf;
	
	/**
	 * 连接池对象
	 */
	private static DBConnectionPool pool;
	
	static{								//静态代码块
		Properties pros=new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		conf=new Configuration();
		conf.setDriver(pros.getProperty("driver"));
		conf.setUrl(pros.getProperty("url"));
		conf.setUser(pros.getProperty("user"));
		conf.setPwd(pros.getProperty("pwd"));

		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
	}
	
	/**
	 * 获得Connection对象
	 * @return
	 */
	public static Connection getConnect(){
		if(pool==null){
			pool=new DBConnectionPool();
		}
		return pool.getConnection();
		
	}
	
	/**
	 * 创建新的Connection对象
	 * @return
	 */
	public static Connection createConnect(){
		try {
			Class.forName(conf.getDriver());
			return 	DriverManager.getConnection(conf.getUrl(),
					conf.getUser(),conf.getPwd());           
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 关闭传入的ResultSet、Statement、Connection对象
	 * @param rs
	 * @param st
	 * @param conn
	 */
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

		pool.close(conn);
		
	}
	
	/**
	 * 关闭传入的ResultSet对象
	 * @param rs
	 */
	public static void close(ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 关闭传入的ResultSet、Statement对象
	 * @param rs
	 * @param st
	 */
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
	
	/**
	 * 关闭传入的Statement、Connection对象
	 * @param st
	 * @param conn
	 */
	public static void close(Statement st,Connection conn){
		try {
			if(st!=null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pool.close(conn);
	}
	
	/**
	 * 关闭传入的Connection对象
	 * @param conn
	 */
	public static void close(Connection conn){
		

		pool.close(conn);
	}

	/**
	 * 返回Configuration对象
	 * @return
	 */
	public static Configuration getConf() {
		return conf;
	}
	

}
