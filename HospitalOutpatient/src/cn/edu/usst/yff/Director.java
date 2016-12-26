package yff;

import java.sql.*;
import javax.swing.*;
import tww.pool.DBManager;
import java.awt.*;
import java.awt.event.*;

public class Director extends  JFrame  implements ActionListener{
	static Director director=new Director();	
	static String[] likes={"��ѯ��ҩƷ�Ŀ����","��ѯ�����ҵĹҺ������ܽ��","��ѯ��ҽ���ĹҺ������ܽ��","��ѯĳҩƷ�Ŀ����","��ѯĳ���ҵĹҺ������ܽ��","��ѯĳҽ���ĹҺ������ܽ��"};
	static JPanel topPanel,bottomPanel;	
	static JTextField input;
	static JButton b1,b2;
	static JTextArea output;
	static JComboBox box;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub			
		JFrame frm=new JFrame();
		frm.setTitle("ȫ������õ�ҽԺ");
		FlowLayout flowLayout=new FlowLayout(); 
		frm.setLayout(flowLayout);//����ʹ�������ֹ�����		
		//�����������ӵ�������		
	    box=new JComboBox(likes);
	    box.setEditable(true);
	    box.setMaximumRowCount(6);
	    frm.getContentPane().add(box);
		b1 = new JButton();
		b1.setText("ȷ��");
		b1.setBounds(200,5,100,30);
	    frm.getContentPane().add(b1);
	    b2 = new JButton();
		b2.setText("�˳�");
		b2.setBounds(100,5,100,30);
	    frm.getContentPane().add(b2);	   
	    topPanel=new JPanel();//������岢���ڴ����ϰ벿��
		frm.getContentPane().add(topPanel,BorderLayout.NORTH);
		input=new JTextField();
		input.setEditable(true);
		input.setHorizontalAlignment(SwingConstants.LEFT);
		input.setColumns(25);//�������25��			
		topPanel.add(input);
		topPanel.add(b1);
		topPanel.add(b2);
		bottomPanel=new JPanel();
		frm.getContentPane().add(bottomPanel, BorderLayout.CENTER);	
	    b1.addActionListener(director);
	    b2.addActionListener(director);	    
     	output=new JTextArea();	    
	    output.setRows(60);
	    output.setColumns(50);
	    output.setEditable(false);
	    output.setText("��ѡ��Ҫ��ѯ�İ�ť......\n������Ϣ������ҽ��id��ҩƷ���ƻ��������......\n�ȴ������ʾ......"); 
	    output.setLineWrap(true);
	    bottomPanel.add(output);
	    bottomPanel.setVisible(true);
	    frm.getContentPane().add(output);	
	    frm.setBounds(800,500,800,500);
	    frm.setVisible(true);	    
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(b1)){
			output.setText(null);
			if(box.getSelectedItem().equals(likes[0])){
				Search_Med();
			 }
            if(box.getSelectedItem().equals(likes[1])){
            	output.setText(null);
				Search_Deptcount();
			 }
            if(box.getSelectedItem().equals(likes[2])){
            	output.setText(null);
	            Search_Doc();	
             }		
            if(box.getSelectedItem().equals(likes[3])){            	
					output.setText(null);
	            search_promed();	        		          	
            }
            if(box.getSelectedItem().equals(likes[4])){
            	
					output.setText(null);
	            search_prodeptcount();	        		            	
             }
            if(box.getSelectedItem().equals(likes[5])){
            	
					output.setText(null);
            	search_prodoc();	
        		}            	
		}       											
		if(e.getSource().equals(b2)){
			System.exit(0);			
		}		
	}
    //��ѯҩƷ�����
    public void Search_Med(){
    	Connection conn=null;
		Statement ps=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnect();
			ps=conn.createStatement();
			String sql="select * from medicine";
			try {
				rs=ps.executeQuery(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()){
				output.append("    ���ƣ�"+rs.getString("Med_name"));	
				output.append("    ���룺"+rs.getString("Med_bfcode"));		
				output.append("    �������"+rs.getInt("Med_count"));	
				output.append("\n");
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
	//��ѯ���ҵĹҺ������ܽ��
	public void Search_Deptcount(){
		
		Connection conn=null;
		Statement ps=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnect();
			ps=conn.createStatement();
			//��ѯ�������ݣ�select���ͨ����executeQuery,�᷵��һ��ResultSet�������������һ����ά��
			String sql="select Doc_dept ,SUM(Doc_patnum) as Doc_patnum,SUM(Pat_proprice*Pro_num) as Pro_num "
					   +" from  Doctor join Pat_charge on Doc_id=Pat_docid "
					   +" where Pat_charged=1 "
					   +" GROUP BY Doc_dept ";
			try {
				rs=ps.executeQuery(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()){					 												
					output.append("  ���ң�"+rs.getString("Doc_dept"));	
					output.append("  �ܹҺ�����"+rs.getInt("Doc_patnum"));
					output.append("  �ܽ�"+rs.getDouble("Pro_num"));	
					output.append("\n");
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
//��ѯÿ��ҽ���ľ��������ͽ��
 public void Search_Doc(){
	 Connection conn=null;
		Statement ps=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnect();
			ps=conn.createStatement();
			//��ѯ�������ݣ�select���ͨ����executeQuery,�᷵��һ��ResultSet�������������һ����ά��			
		String sql="select Doc_id,SUM(Doc_patnum) as Doc_patnum,SUM(Pat_proprice*Pro_num) as Pro_num "
                  +" from Doctor join Pat_charge on Doc_id=Pat_docid "
                  +" where Pat_charged=1 "
                  +" GROUP BY Doc_id ";
			try {
				rs=ps.executeQuery(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()){
					output.append(" ҽ��id��"+rs.getInt("Doc_id"));	
					output.append(" �Һ�����"+rs.getInt("Doc_patnum"));	
					output.append(" �ܽ�"+rs.getDouble("Pro_num"));
					output.append("\n");
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
 public void search_promed(){
		
	 Connection conn=null;
		Statement ps=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnect();
			ps=conn.createStatement();
			String sql="select * from medicine where Med_name='"+input.getText()+"'";
			try {
				rs=ps.executeQuery(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()){			
			output.append("    ҩƷ���ƣ�"+rs.getString("Med_name"));
			output.append("\n");
			output.append("    ҩƷ���룺"+rs.getString("Med_bfcode"));	
			output.append("\n");
			output.append("    �������"+rs.getInt("Med_count"));	
			output.append("\n");		
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
	//ҽ����id��ѯ
	public void search_prodoc(){
		Connection conn=null;
		Statement ps=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnect();
			ps=conn.createStatement();
			String sql="select Doc_id,SUM(Doc_patnum ) as Doc_patnum,SUM(Pat_proprice*Pro_num) as Pro_num " 
         +" from Pat_charge join Doctor on Doc_id=Pat_docid "
         +" where  Pat_charged=1 and Doc_id="+input.getText()+" "
         +" Group By Doc_patnum,Doc_id";
			try {
				rs=ps.executeQuery(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()){						
					output.append(" ҽ��id��"+rs.getInt("Doc_id"));
					output.append("\n");
					output.append(" �Һ�����"+rs.getInt("Doc_patnum"));
					output.append("\n");
					output.append(" �ܽ�"+rs.getDouble("Pro_num"));
					output.append("\n");
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
public void search_prodeptcount(){
		
	Connection conn=null;
	Statement ps=null;
	ResultSet rs=null;
	try {
		conn=DBManager.getConnect();
		ps=conn.createStatement();
		//��ѯ�������ݣ�select���ͨ����executeQuery,�᷵��һ��ResultSet�������������һ����ά��
			String sql="select Doc_dept ,SUM(Doc_patnum) as Doc_patnum,SUM(Pat_proprice*Pro_num) as Pro_num "
					   +" from  Doctor join Pat_charge on Doc_id=Pat_docid "
					   +" where Pat_charged=1 and Doc_dept='"+input.getText()+"' "
					   +" GROUP BY Doc_dept ";
			
		try {
			rs=ps.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
				while(rs.next()){	
					output.append("  �������ƣ�"+rs.getString("Doc_dept"));
					output.append("\n");
					output.append("  �ܹҺ�����"+rs.getInt("Doc_patnum"));
					output.append("\n");
					output.append("  �ܽ�"+rs.getDouble("Pro_num"));	
					output.append("\n");
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
}
			
			
			
