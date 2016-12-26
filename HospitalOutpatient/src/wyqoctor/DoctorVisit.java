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
	public String pname,phone;//���������͵绰
	public int did;//��ҽ����ID
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm=new JFrame("ȫ������õ�ҽԺ");//��¼����
		frm.setLayout(new BorderLayout());
		JLabel label1=new JLabel("���ȵ�¼��");
		frm.getContentPane().add(label1,BorderLayout.NORTH);
		
		JTextField input=new JTextField();//ҽ��ID�����
		input.setEditable(true);
		input.setHorizontalAlignment(SwingConstants.LEFT);
		input.setColumns(6);
		frm.getContentPane().add(input,BorderLayout.NORTH);
		
		JPasswordField jp=new JPasswordField();//���������
		frm.getContentPane().add(jp,BorderLayout.NORTH);
		
		JButton button1=new JButton();//��¼��ť
		button1.setText("��¼");
		frm.getContentPane().add(button1,BorderLayout.NORTH);
		frm.setVisible(true);
		
		JTextArea output=new JTextArea();//���˻�����Ϣ��ʾ���ı���
		//output.setHorizontalAlignment(JTextField.LEFT);
		output.setColumns(10);
		frm.getContentPane().add(output,BorderLayout.WEST);
		
		JLabel label2=new JLabel("ҩƷ");
		frm.getContentPane().add(label2,BorderLayout.CENTER);
		JTextField input1=new JTextField();//ҩƷ���������
		input1.setHorizontalAlignment(JTextField.LEFT);
		input1.setColumns(6);
		frm.getContentPane().add(input1,BorderLayout.CENTER);
		
		JLabel label4=new JLabel("ҩƷ����");
		frm.getContentPane().add(label4,BorderLayout.CENTER);
		JTextField input2=new JTextField();//���������
		input2.setHorizontalAlignment(JTextField.LEFT);
		input2.setColumns(2);
		frm.getContentPane().add(input2,BorderLayout.CENTER);
		
		JButton button2=new JButton();//���ҩƷ��ť
		button2.setText("���");
		frm.getContentPane().add(button2,BorderLayout.CENTER);
		JLabel label5=new JLabel();
		frm.getContentPane().add(label5,BorderLayout.CENTER);//��ʾ��������ǩ
		
		JLabel label3=new JLabel("�����Ŀ");
		frm.getContentPane().add(label3,BorderLayout.SOUTH);
		JTextField input21=new JTextField();//�����Ŀ���������
		input21.setHorizontalAlignment(JTextField.LEFT);
		input21.setColumns(6);
		frm.getContentPane().add(input21,BorderLayout.SOUTH);
		JButton button3=new JButton();//��Ӽ����Ŀȷ����ť
		button3.setText("���");
		frm.getContentPane().add(button3,BorderLayout.SOUTH);
		
		JTextArea output1=new JTextArea();//���˻�����Ϣ��ʾ���ı���
		//output1.setHorizontalAlignment(JTextField.LEFT);
		output1.setColumns(10);
		frm.getContentPane().add(output1,BorderLayout.EAST);
		JButton button4=new JButton();
		button4.setText("ȷ��");
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
	

public void denglu(){//�õ���ҽ���Ĳ����Ŷӱ������С�Ĳ��˲���ʾ��Ϣ
	
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
		output.append("����"+rs.getString("Pat_name"));
		output.append("\n");
		output.append("�Ա�"+rs.getString("Pat_sex"));
		output.append("\n");
		output.append("����"+rs.getInt("Pat_age"));
		output.append("\n");
		pname=rs.getString("Pat_name");
		phone=rs.getString("Pat_phone");//�õ���ǰ���˵������͵绰
		int a=rs.getInt("Pat_num");
		String sql1="delete from Patien_queue where Pat_num=a";
	//ɾ�������Ŷӱ���ò��˵���һ��
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
	String s=input1.getText();//ҩƷ����
	String s1=input2.getText();                                                                                                                                                        
	int d=Integer.parseInt(s1);//ҩƷ����
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
					label5.setText("����������뻻ҩ");	
				}
				else{
					int a=rs.getInt("Med_count")-d;
					output1.append("ҩ��"+rs.getString("Med_name"));
					output1.append("����"+d);
					output1.append("\n");
					String sql1="update medicine set Med_count=a where Med_bfcode=? ";
					ps.setString(1,s);//����ҩƷ�����
					String s2=rs.getString("Med_name");
					double f=rs.getDouble("Med_price");
					
					String sql2="insert into Pat_charge values(?,?,d,f,0,?,did)";
					ps.setString(1, pname);
					ps.setString(2, s2);
					ps.setString(3, phone);//��ҩƷ��ӵ������շѱ�
					
					
				}
					  
				
			}
			else {
				label5.setText("���벻����");
				
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
	String s=input21.getText();//�����Ŀ��
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
			output1.append(rs.getString("Pro_name"));//��������Ŀ������
			output1.append("\n");
			String s1=rs.getString("Pro_name");
			double f=rs.getDouble("Pro_price");
			
			String sql2="insert into Pat_charge values(?,?,1,f,0,?,did)";
			ps.setString(1, pname);
			ps.setString(2, s1);
			ps.setString(3, phone);//�Ѹü����Ŀ��ӵ������շѱ�
			
			
		}
		else {
			label5.setText("���벻����");
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
