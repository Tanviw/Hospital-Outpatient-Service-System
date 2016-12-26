package tww.pool;

import java.sql.*;
/**
 * 测试连接池的效率
 * @author Tanvi
 *
 */
public class TestPool {

	public static void test(){
		Connection conn=null;
		Statement ps=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnect();
			ps=conn.createStatement();
			String sql="select * from Doctor";
			try {
				rs=ps.executeQuery(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()){
					System.out.print(" id："+rs.getInt("Doc_id"));
					System.out.print(" name："+rs.getString("Doc_name"));
					System.out.print(" dept："+rs.getString("Doc_dept"));
					System.out.print(" patnum："+rs.getInt("Doc_patnum"));
					System.out.println();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(rs,ps,conn);
		}
	}
	
	public static void main(String[] args) {
		long a=System.currentTimeMillis();
		for(int i=0;i<3000;i++){
			test();
		}
		long b=System.currentTimeMillis();
		System.out.println(b-a);			//2490ms
	}
}
