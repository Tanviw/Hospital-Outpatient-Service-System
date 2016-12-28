package yff;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import tww.pool.DBManager;

public class RollScreen extends  JFrame  implements ActionListener  {
	static JButton b1,b2;
	static JTextField input;
	static JTextArea output;
	static RollScreen screen=new RollScreen();
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm=new JFrame();
		frm.setTitle("全世界最好的医院");
		FlowLayout flowLayout=new FlowLayout(); 
		frm.setLayout(flowLayout);		
		//创建组件并添加到容器中					    
		input=new JTextField();
		input.setEditable(true);
		input.setHorizontalAlignment(SwingConstants.LEFT);
		input.setColumns(25);//左侧输入25列	
		frm.getContentPane().add(input);
		
	    b1 = new JButton();
		b1.setText("查询");
		b1.setBounds(100,5,100,30);
	    frm.getContentPane().add(b1);
	    
	    b2 = new JButton();
		b2.setText("退出");
		b2.setBounds(100,5,100,30);
	    frm.getContentPane().add(b2);
	    
     	output=new JTextArea();	    
	    output.setRows(25);
	    output.setColumns(50);
	    output.setEditable(false);
	    output.setText("请输入要查询的科室名称......\n\r等待结果显示......\n"); 
	    output.setLineWrap(true);
	    frm.getContentPane().add(output);
	    	    
	    b1.addActionListener(screen);
	    b2.addActionListener(screen);
	    
	    frm.setBounds(400,150,800,500);
	    frm.setVisible(true);	  
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(b1)){
			output.setText(null);
			output.setText("查询的科室是："+(String) input.getText()+"\n");
			rollscreen();			
		}
		if(e.getSource().equals(b2)){
			System.exit(0);			
		}		
	}
public static void rollscreen(){
	Connection conn=null;
	Statement ps=null;
	ResultSet rs=null;
	try {
		conn=DBManager.getConnect();
		ps=conn.createStatement();
		String sql="select Doctor.Doc_id,Pat_num,Doc_dept from Doctor join Patient_queue on Doctor.Doc_id=Patient_queue.Doc_id "
	               +"where cast(Doc_dept as varchar(8000))='"+(String) input.getText()+"'"
	               +"ORDER BY Pat_num ASC ,Doc_id";   
		try {
			rs=ps.executeQuery(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){	
				    output.append("医生id："+rs.getInt("Doc_id"));
					output.append("    排队号："+rs.getInt("Pat_num"));
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
	
	