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
		frm.setTitle("ʹ�������ֹ�����");
		FlowLayout flowLayout=new FlowLayout(); 
		frm.setLayout(flowLayout);//����ʹ�������ֹ�����		
		//�����������ӵ�������		
		b1 = new JButton();
		b1.setText("��ѯ��ҩƷ�Ŀ����");
		b1.setBounds(200,5,100,30);
	    frm.getContentPane().add(b1);
	    b2 = new JButton();
		b2.setText("��ѯ�����ҵĹҺ������ܽ��");
		b2.setBounds(100,5,100,30);
	    frm.getContentPane().add(b2);
	    b3 = new JButton();
		b3.setText("��ѯ��ҽ���ĹҺ������ܽ��");
		b3.setBounds(100,5,100,30);
	    frm.getContentPane().add(b3);
	    b4 = new JButton();
		b4.setText("�˳�");
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
	    output.setText("��ѡ��Ҫ��ѯ�İ�ť......\n\r�ȴ������ʾ......"); 
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
			director.Search_Med();			 			 						
		}
		if(e.getSource().equals(b2)){
			output.setText(null);
			director.Search_Deptcount();
		}
		if(e.getSource().equals(b3)){
			output.setText(null);
			director.Search_Doc();				
		}
		if(e.getSource().equals(b4)){			
			System.exit(0);
		}
	}
    //��ѯҩƷ�����
    public void Search_Med(){
    	
			Connection con=null;
			//�������ݿ���������
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //�������ݿ�����
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=XXX","XXX","XXX");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	       
			Statement st=null;
			  //����������			 
			try {
				 st=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			String sql="select Med_name,Med_count from medicine where Med_name='str' or Med_bfcode='str'";
			ResultSet rs=null;
			try {
				rs=st.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()){
				output.append(" ���ƣ�"+rs.getString("Med_name"));	
				output.append(" ���룺"+rs.getString("Med_bfcode"));	
				output.append(" �۸�"+rs.getDouble("Med_price"));	
				output.append(" �������"+rs.getInt("Med_count"));	
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
	//��ѯ���ҵĹҺ������ܽ��
	public void Search_Deptcount(){
		
		Connection con=null;
			//�������ݿ���������
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //�������ݿ�����
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=XXX","XXX","XXX");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
			Statement st=null;
			  //����������			 
			try {
				 st=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			//��ѯ�������ݣ�select���ͨ����executeQuery,�᷵��һ��ResultSet�������������һ����ά��
			String sql="select Doc_dept,SUM(Doc_patnum) as Doc_patnum, SUM(Pat_proprice*Pro_number) as Total"
		    + "from Doctor join Pat_charge on Doc_id=Pat_docid"
			+"where Doc_id=Pat_docid and Pat_charged=1"
		    +"GROUP BY Doc_dept ";
			ResultSet rs=null;
			try {
				rs=st.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()){					 												
					output.append(" ���ң�"+rs.getString("Doc_dept"));	
					output.append(" �ܹҺ�����"+rs.getInt("Doc_patnum"));
					output.append(" �ܽ�"+rs.getDouble("Total"));	
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
//��ѯÿ��ҽ���ľ��������ͽ��
 public void Search_Doc(){
	 Connection con=null;
		//�������ݿ���������
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //�������ݿ�����
		try {
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=XXX","XXX","XXX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		Statement st=null;
		  //����������			 
		try {
			 st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//��ѯ�������ݣ�select���ͨ����executeQuery,�᷵��һ��ResultSet�������������һ����ά��			
		String sql="select  Doc_id,Doc_name,Doc_dept,Doc_patnum,SUM(Pat_proprice*Pro_number) as Total"
		+"from Doctor,Pat_charge where Pat_docid=Doc_id and Pat_charged=1 "
		+"GROUP BY Doc_id,Pat_id,Doc_name,Doc_dept,Doc_patnum,Pat,charged";
		ResultSet rs=null;
		try {
			rs=st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
				try {
				while(rs.next()){
					output.append(" ҽ��id��"+rs.getInt("Doc_id"));	
					output.append(" ���֣�"+rs.getString("Doc_name"));	
					output.append(" ���ڿ��ң�"+rs.getString("Med_dept"));	
					output.append(" �Һ�����"+rs.getInt("Med_patnum"));	
					output.append(" �ܽ�"+rs.getDouble("Total"));
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
