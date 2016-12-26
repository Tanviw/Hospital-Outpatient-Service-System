package pat;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.JTextArea;



public class Patient extends  JFrame  implements ActionListener {
	static Patient pat=new Patient();
	static JTextArea output1;
	static JButton b2,b1;
	static JTextField t1,t2,t3,t4,t5,t6;
	static JLabel jl1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm=new JFrame("全世界最好的医院");//登录界面
		frm.setLayout(new FlowLayout());
		JTextArea output1=new JTextArea();
		output1.setText("欢迎您来到我们医院，祝您早日康复");
		output1.setColumns(ABORT);
		frm.getContentPane().add(output1);
		JLabel l1=new JLabel("姓名");
		JTextField t1=new JTextField();//姓名输入框
		frm.getContentPane().add(l1);
		frm.getContentPane().add(t1);
		JLabel l2=new JLabel("性别");
		JTextField t2=new JTextField();//性别输入框
		frm.getContentPane().add(l2);
		frm.getContentPane().add(t2);
		JLabel l3=new JLabel("年龄");
		JTextField t3=new JTextField();//年龄输入框
		frm.getContentPane().add(l3);
		frm.getContentPane().add(t3);
		JLabel l4=new JLabel("电话");
		JTextField t4=new JTextField();//电话输入框
		frm.getContentPane().add(l4);
		frm.getContentPane().add(t4);
		JLabel l5=new JLabel("科室");
		JTextField t5=new JTextField();//科室输入框
		frm.getContentPane().add(l5);
		frm.getContentPane().add(t5);
		JLabel l6=new JLabel("预约时间");
		JTextField t6=new JTextField();//预约时间输入框
		frm.getContentPane().add(l6);
		frm.getContentPane().add(t6);
		JButton b1=new JButton();
		b1.setText("确定");
		JButton b2=new JButton();
		b2.setText("取消");
		frm.getContentPane().add(b1);
		frm.getContentPane().add(b2);
		JLabel jl1=new JLabel();
		frm.getContentPane().add(jl1);
		b1.addActionListener(pat);
		b2.addActionListener(pat);
		frm.setBounds(300, 200, 600, 300);
		frm.setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(b1)){
		pat.sure();
	}
		if(e.getSource().equals(b2)){
			pat.delete();
		}

}
	public void sure(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String s1=t1.getText();
		String s2=t2.getText();
		String s3=t3.getText();
		int d=Integer.parseInt(s3);//年龄
		String s4=t4.getText();
		String s5=t5.getText();
		String s6=t6.getText();
		try{
			conn=DBManager.getConnect();
			ps=conn.Preparedstatement();
			String sql="insert into Pat_order values(?,?,d,?,?,?)";
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setString(3, s4);
			ps.setString(4, s5);
			ps.setString(5, s6);//把病人的基本信息放进Pat_order表
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(rs,ps,conn);
		}
		jl1.setText("您已经预约成功");		
	}
	public void delete(){
		jl1.setText("预约已经取消，祝您生活愉快");
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
	}
	
}
