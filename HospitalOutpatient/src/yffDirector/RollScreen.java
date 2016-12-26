package cn.edu.usst.yff;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RollScreen extends  JFrame  implements ActionListener  {
	static JButton b1,b2;
	static JComboBox box;
	static JTextArea output;
	static RollScreen screen=new RollScreen();
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm=new JFrame();
		frm.setTitle("ʹ�������ֹ�����");
		FlowLayout flowLayout=new FlowLayout(); 
		frm.setLayout(flowLayout);//����ʹ�������ֹ�����		
		//�����������ӵ�������					    
	    String[] likes={"�ǿ�","Ƥ����","���ڿ�","�������","�ۿ�"};
	    box=new JComboBox(likes);
	    box.setEditable(true);
	    box.setMaximumRowCount(6);
	    frm.getContentPane().add(box);
	    b1 = new JButton();
		b1.setText("��ѯ");
		b1.setBounds(100,5,100,30);
	    frm.getContentPane().add(b1);
	    
	    b2 = new JButton();
		b2.setText("�˳�");
		b2.setBounds(100,5,100,30);
	    frm.getContentPane().add(b2);
	    
     	output=new JTextArea();	    
	    output.setRows(25);
	    output.setColumns(50);
	    output.setEditable(false);
	    output.setText("��ѡ��Ҫ��ѯ�Ŀ���......\n\r�ȴ������ʾ......\n"); 
	    output.setLineWrap(true);
	    frm.getContentPane().add(output);
	    
	    
	    b1.addActionListener(screen);
	    b2.addActionListener(screen);
	    
	    frm.setBounds(800,500,800,500);
	    frm.setVisible(true);	  
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(b1)){
			output.setText(null);
			output.setText("��ѯ�Ŀ����ǣ�"+(String) box.getSelectedItem()+"\n");
			output.append("��ǰ���ŶӺ����У�\n");
			rollscreen();			
		}
		if(e.getSource().equals(b2)){
			System.exit(0);			
		}		
	}
public static void rollscreen(){
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
		con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=HospitalOutpatient","sa","M03039946");
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
	String sql="select Pat_num,Doc_dept from Doctor join Patient_queue on Doctor.Doc_id=Patient_queue.Doc_id "
               +"where cast(Doc_dept as varchar(8000))='"+(String)box.getSelectedItem()+"'"
                +"ORDER BY Pat_num ASC";               
	ResultSet rs=null;
	try {
		rs=st.executeQuery(sql);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		while(rs.next()){
		output.append("  "+rs.getInt("Pat_num"));
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

