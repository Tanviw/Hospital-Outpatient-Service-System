package doctor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class DoctorVisit extends  JFrame  implements ActionListener {
	static DoctorVisit doctor=new DoctorVisit();
	static JButton button1,button2,button3,button4;
	static JTextField input,input1,input2,input21;
	static JTextArea output,output1;
	static JLabel label1,label2,label3,label4,label5;
	static JPasswordField jp;
	public String pname,phone;//病人姓名和电话
	public int did;//改医生的ID
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm=new JFrame("全世界最好的医院");//登录界面
		frm.setLayout(new BorderLayout());
		JLabel label1=new JLabel("请先登录：");
		frm.getContentPane().add(label1,BorderLayout.NORTH);
		
		JTextField input=new JTextField();//医生ID输入框
		input.setEditable(true);
		input.setHorizontalAlignment(SwingConstants.LEFT);
		input.setColumns(6);
		frm.getContentPane().add(input,BorderLayout.NORTH);
		
		JPasswordField jp=new JPasswordField();//密码输入框
		frm.getContentPane().add(jp,BorderLayout.NORTH);
		
		JButton button1=new JButton();//登录按钮
		button1.setText("登录");
		frm.getContentPane().add(button1,BorderLayout.NORTH);
		frm.setVisible(true);
		
		JTextArea output=new JTextArea();//病人基本信息显示的文本框
		//output.setHorizontalAlignment(JTextField.LEFT);
		output.setColumns(10);
		frm.getContentPane().add(output,BorderLayout.WEST);
		
		JLabel label2=new JLabel("药品");
		frm.getContentPane().add(label2,BorderLayout.CENTER);
		JTextField input1=new JTextField();//药品简码输入框
		input1.setHorizontalAlignment(JTextField.LEFT);
		input1.setColumns(6);
		frm.getContentPane().add(input1,BorderLayout.CENTER);
		
		JLabel label4=new JLabel("药品数量");
		frm.getContentPane().add(label4,BorderLayout.CENTER);
		JTextField input2=new JTextField();//数量输入框
		input2.setHorizontalAlignment(JTextField.LEFT);
		input2.setColumns(2);
		frm.getContentPane().add(input2,BorderLayout.CENTER);
		
		JButton button2=new JButton();//添加药品按钮
		button2.setText("添加");
		frm.getContentPane().add(button2,BorderLayout.CENTER);
		JLabel label5=new JLabel();
		frm.getContentPane().add(label5,BorderLayout.CENTER);//提示输入错误标签
		
		JLabel label3=new JLabel("检查项目");
		frm.getContentPane().add(label3,BorderLayout.SOUTH);
		JTextField input21=new JTextField();//检查项目简码输入框
		input21.setHorizontalAlignment(JTextField.LEFT);
		input21.setColumns(6);
		frm.getContentPane().add(input21,BorderLayout.SOUTH);
		JButton button3=new JButton();//添加检查项目确定按钮
		button3.setText("添加");
		frm.getContentPane().add(button3,BorderLayout.SOUTH);
		
		JTextArea output1=new JTextArea();//病人基本信息显示的文本框
		//output1.setHorizontalAlignment(JTextField.LEFT);
		output1.setColumns(10);
		frm.getContentPane().add(output1,BorderLayout.EAST);
		JButton button4=new JButton();
		button4.setText("确定");
		frm.getContentPane().add(button4,BorderLayout.EAST);
		
		
		button1.addActionListener(doctor);
		button2.addActionListener(doctor);
		button3.addActionListener(doctor);
		button4.addActionListener(doctor);
		frm.setBounds(300, 200, 600, 300);
		frm.setVisible(true);

		
	}

	
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource().equals(button1)){
		doctor.denglu();
	}
	if(e.getSource().equals(button2)){
		doctor.chayao();
	}
	if(e.getSource().equals(button3)){
		doctor.xiangmu();
	}
	if(e.getSource().equals(button4)){
		doctor.clear();
		doctor.denglu();
		
	}
	
		
	}
	

public void denglu(){//得到该医生的病人排队表里号最小的病人并显示信息
	
	Connection conn=null;
	Preparedstatement ps=null;
	ResultSet rs=null;
	String s=input.getText();
	int d=Integer.parseInt(s);
	did=d;
	try {
		conn=DBManager.getConnect();
		ps=conn.Preparedstatement();
		String sql="select min(Pat_num) from Patien_queue where Doc_id=?";
		ps.setInt(1,did);
		try {
			rs=ps.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){
		output.append("姓名"+rs.getString("Pat_name"));
		output.append("\n");
		output.append("性别"+rs.getString("Pat_sex"));
		output.append("\n");
		output.append("年龄"+rs.getInt("Pat_age"));
		output.append("\n");
		pname=rs.getString("Pat_name");
		phone=rs.getString("Pat_phone");//得到当前病人的姓名和电话
		int a=rs.getInt("Pat_num");
		String sql1="delete from Patien_queue where Pat_num=a";
	//删除病人排队表里该病人的那一行
}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		DBManager.close(rs,ps,conn);
	}
	label1.setText("");
	jp.setVisible(false);
	button1.setVisible(false);
}
public void chayao(){
	String s=input1.getText();//药品简码
	String s1=input2.getText();                                                                                                                                                        
	int d=Integer.parseInt(s1);//药品数量
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		conn=DBManager.getConnect();
		ps=conn.Preparedstatement();
		String sql="select *from medicine where Med_bfcode=?";
		ps.setString(1,s);
		try {
			rs=ps.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(rs.next()){
				if(d>rs.getInt("Med_count")){
					label5.setText("库存量不足请换药");	
				}
				else{
					int a=rs.getInt("Med_count")-d;
					output1.append("药名"+rs.getString("Med_name"));
					output1.append("数量"+d);
					output1.append("\n");
					String sql1="update medicine set Med_count=a where Med_bfcode=? ";
					ps.setString(1,s);//更改药品库存量
					String s2=rs.getString("Med_name");
					double f=rs.getDouble("Med_price");
					
					String sql2="insert into Pat_charge values(?,?,d,f,0,?,did)";
					ps.setString(1, pname);
					ps.setString(2, s2);
					ps.setString(3, phone);//把药品添加到病人收费表
					
					
				}
					  
				
			}
			else {
				label5.setText("简码不存在");
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		DBManager.close(rs,ps,conn);
	}
	input1.setText("");
	input2.setText("");
}
public void xiangmu(){
	String s=input21.getText();//检查项目名
	Connection conn=null;
	Preparedstatement ps=null;
	ResultSet rs=null;
	try {
		conn=DBManager.getConnect();
		ps=conn.Preparedstatement();
		String sql="select *from Charge_list where Pro_bfcode=?";
		ps.setString(1,s);
		
		try {
			rs=ps.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
		
		if(rs.next()){
			output1.append(rs.getString("Pro_name"));//输出检查项目的名称
			output1.append("\n");
			String s1=rs.getString("Pro_name");
			double f=rs.getDouble("Pro_price");
			
			String sql2="insert into Pat_charge values(?,?,1,f,0,?,did)";
			ps.setString(1, pname);
			ps.setString(2, s1);
			ps.setString(3, phone);//把该检查项目添加到病人收费表
			
			
		}
		else {
			label5.setText("简码不存在");
		}
		}
		 catch (SQLException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(rs,ps,conn);
		}
       input21.setText("");
	
}
public void clear(){
	output.setText("");
	output1.setText("");
}

}
