package cn.edu.usst.yff;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Director extends  JFrame  implements ActionListener{
	static Director director=new Director();
	static JButton b1,b2,b3,b4;
	static JTextArea output;	
	public static void main(String[] args) {
		// TODO Auto-generated method stub			
		JFrame frm=new JFrame();
		frm.setTitle("使用流布局管理器");
		FlowLayout flowLayout=new FlowLayout(); 
		frm.setLayout(flowLayout);//设置使用流布局管理器		
		//创建组件并添加到容器中		
		b1 = new JButton();
		b1.setText("查询各药品的库存量");
		b1.setBounds(200,5,100,30);
	    frm.getContentPane().add(b1);
	    b2 = new JButton();
		b2.setText("查询各科室的挂号量和总金额");
		b2.setBounds(100,5,100,30);
	    frm.getContentPane().add(b2);
	    b3 = new JButton();
		b3.setText("查询各医生的挂号量和总金额");
		b3.setBounds(100,5,100,30);
	    frm.getContentPane().add(b3);
	    b4 = new JButton();
		b4.setText("退出");
		b4.setBounds(100,5,100,30);
	    frm.getContentPane().add(b4);
	    b1.addActionListener(director);
	    b2.addActionListener(director);
	    b3.addActionListener(director);
	    b4.addActionListener(director);
	    
     	output=new JTextArea();	    
	    output.setRows(60);
	    output.setColumns(50);
	    output.setEditable(false);
	    output.setText("请选择要查询的按钮......\n\r等待结果显示......"); 
	    output.setLineWrap(true);
	    frm.getContentPane().add(output);	
	    frm.setBounds(800,500,800,500);
	    frm.setVisible(true);	    
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(b1)){
			output.setText(null);
			Search_Med();			 			 						
		}
		if(e.getSource().equals(b2)){
			output.setText(null);
			Search_Deptcount();
		}
		if(e.getSource().equals(b3)){
			output.setText(null);
			Search_Doc();				
		}
		if(e.getSource().equals(b4)){			
			System.exit(0);
		}
	}
    //查询药品库存量
    public void Search_Med(){
    	
			Connection con=null;
			//加载数据库驱动程序
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //建立数据库连接
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=HospitalOutpatient","sa","M03039946");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	       
			Statement st=null;
			  //创建语句对象			 
			try {
				 st=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			String sql="select * from medicine";
			ResultSet rs=null;
			try {
				rs=st.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()){
				output.append("    名称："+rs.getString("Med_name"));	
				output.append("    简码："+rs.getString("Med_bfcode"));		
				output.append("    库存量："+rs.getInt("Med_count"));	
				output.append("\n");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	//查询科室的挂号量和总金额
	public void Search_Deptcount(){
		
		Connection con=null;
			//加载数据库驱动程序
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //建立数据库连接
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=HospitalOutpatient","sa","M03039946");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
			Statement st=null;
			  //创建语句对象			 
			try {
				 st=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			//查询检索数据；select语句通常用executeQuery,会返回一个ResultSet结果集对象（这是一个二维表）
			String sql="select Doc_dept ,SUM(Doc_patnum) as Doc_patnum,SUM(Pat_proprice*Pro_num) as Pro_num "
					   +" from  Doctor join Pat_charge on Doc_id=Pat_docid "
					   +" where Pat_charged=1 "
					   +" GROUP BY Doc_dept ";
			ResultSet rs=null;
			try {
				rs=st.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()){					 												
					output.append("  科室："+rs.getString("Doc_dept"));	
					output.append("  总挂号量："+rs.getInt("Doc_patnum"));
					output.append("  总金额："+rs.getDouble("Pro_num"));	
					output.append("\n");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}		
//查询每个医生的就诊数量和金额
 public void Search_Doc(){
	 Connection con=null;
		//加载数据库驱动程序
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //建立数据库连接
		try {
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=HospitalOutpatient","sa","M03039946");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		Statement st=null;
		  //创建语句对象			 
		try {
			 st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//查询检索数据；select语句通常用executeQuery,会返回一个ResultSet结果集对象（这是一个二维表）			
		String sql="select Doc_id,SUM(Doc_patnum) as Doc_patnum,SUM(Pat_proprice*Pro_num) as Pro_num "
                  +" from Doctor join Pat_charge on Doc_id=Pat_docid "
                  +" where Pat_charged=1 "
                  +" GROUP BY Doc_id ";
		ResultSet rs=null;
		try {
			rs=st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
				try {
				while(rs.next()){
					output.append(" 医生id："+rs.getInt("Doc_id"));	
					output.append(" 挂号量："+rs.getInt("Doc_patnum"));	
					output.append(" 总金额："+rs.getDouble("Pro_num"));
					output.append("\n");
				}
			  } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
		try {			
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
    }        
}
