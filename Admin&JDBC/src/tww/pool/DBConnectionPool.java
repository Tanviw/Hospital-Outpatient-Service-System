package tww.pool;

import java.sql.*;
import java.util.*;

/**
 * 连接池的类
 * @author Tanvi
 *
 */
public class DBConnectionPool {

	
	/**
	 * 连接池的对象
	 */
	private List<Connection> pool; 
	
	/**
	 * 最大连接数
	 */
	private static final int POOL_MAX_SIZE=DBManager.getConf().getPoolMaxSize();
	
	/**
	 * 最小连接数
	 */
	private static final int POOL_MIN_SIZE=DBManager.getConf().getPoolMinSize();
	
	/**
	 * 初始化连接池，使池中的连接数达到最小值
	 */
	public void initPool(){
		if(pool==null){
			pool=new ArrayList<Connection>();
		}
		
		while(pool.size()<DBConnectionPool.POOL_MIN_SIZE){
			pool.add(DBManager.createConnect());
		}
		
	}
	/**
	 * 从连接池中取出一个连接
	 * @return 
	 */
	public synchronized Connection getConnection(){
		int last_index=pool.size()-1;
		Connection conn=pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}
	/**
	 * 将连接放回池中
	 * @param conn
	 */
	public synchronized void close(Connection conn){
		if(pool.size()>=POOL_MAX_SIZE){
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			pool.add(conn);
		}
	
	}
	
	
	
	public DBConnectionPool() {
		initPool();
	}
	
	
	
}
