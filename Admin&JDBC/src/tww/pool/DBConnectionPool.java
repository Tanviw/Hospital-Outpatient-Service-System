package tww.pool;

import java.sql.*;
import java.util.*;

/**
 * ���ӳص���
 * @author Tanvi
 *
 */
public class DBConnectionPool {

	
	/**
	 * ���ӳصĶ���
	 */
	private List<Connection> pool; 
	
	/**
	 * ���������
	 */
	private static final int POOL_MAX_SIZE=DBManager.getConf().getPoolMaxSize();
	
	/**
	 * ��С������
	 */
	private static final int POOL_MIN_SIZE=DBManager.getConf().getPoolMinSize();
	
	/**
	 * ��ʼ�����ӳأ�ʹ���е��������ﵽ��Сֵ
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
	 * �����ӳ���ȡ��һ������
	 * @return 
	 */
	public synchronized Connection getConnection(){
		int last_index=pool.size()-1;
		Connection conn=pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}
	/**
	 * �����ӷŻس���
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
