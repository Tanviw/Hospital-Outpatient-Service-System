package tsh;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * ����������Ϣ��ά�����Ӷ���Ĺ����������ӳع��ܣ�
 * @author Tanvi
 *
 */
public class DBManager {

	/**
	 * ������Ϣ
	 */
	private static Configuration conf;
	
	/**
	 * ���ӳض���
	 */
	private static DBConnectionPool pool;
	
	static{								//��̬�����
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
	 * ���Connection����
	 * @return
	 */
	public static Connection getConnect(){
		if(pool==null){
			pool=new DBConnectionPool();
		}
		return pool.getConnection();
		
	}
	
	/**
	 * �����µ�Connection����
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
	 * �رմ����ResultSet��Statement��Connection����
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
	 * �رմ����ResultSet����
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
	 * �رմ����ResultSet��Statement����
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
	 * �رմ����Statement��Connection����
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
	 * �رմ����Connection����
	 * @param conn
	 */
	public static void close(Connection conn){
		

		pool.close(conn);
	}

	/**
	 * ����Configuration����
	 * @return
	 */
	public static Configuration getConf() {
		return conf;
	}
	

}
