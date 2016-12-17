package cn.tww.jdbc;

import java.sql.*;
/**
 * 测试直接连接数据库的效率
 * @author lenovo
 *
 */
public class TestJDBC {

	public static void test() {

		Connection conn=null;
		Statement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtil.getConnect();
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
			JDBCUtil.close(rs,ps,conn);
		}
	}
	
	
	public static void main(String[] args) {
		long a=System.currentTimeMillis();
		for(int i=0;i<3000;i++){
			test();
		}
		long b=System.currentTimeMillis();
		System.out.println(b-a);			//40401ms
	}
}

