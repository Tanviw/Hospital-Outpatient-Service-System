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
		JFrame frm=new JFrame("ȫ������õ�ҽԺ");//��¼����
		frm.setLayout(new FlowLayout());
		JTextArea output1=new JTextArea();
		output1.setText("��ӭ����������ҽԺ��ף�����տ���");
		output1.setColumns(ABORT);
		frm.getContentPane().add(output1);
		JLabel l1=new JLabel("����");
		JTextField t1=new JTextField();//���������
		frm.getContentPane().add(l1);
		frm.getContentPane().add(t1);
		JLabel l2=new JLabel("�Ա�");
		JTextField t2=new JTextField();//�Ա������
		frm.getContentPane().add(l2);
		frm.getContentPane().add(t2);
		JLabel l3=new JLabel("����");
		JTextField t3=new JTextField();//���������
		frm.getContentPane().add(l3);
		frm.getContentPane().add(t3);
		JLabel l4=new JLabel("�绰");
		JTextField t4=new JTextField();//�绰�����
		frm.getContentPane().add(l4);
		frm.getContentPane().add(t4);
		JLabel l5=new JLabel("����");
		JTextField t5=new JTextField();//���������
		frm.getContentPane().add(l5);
		frm.getContentPane().add(t5);
		JLabel l6=new JLabel("ԤԼʱ��");
		JTextField t6=new JTextField();//ԤԼʱ�������
		frm.getContentPane().add(l6);
		frm.getContentPane().add(t6);
		JButton b1=new JButton();
		b1.setText("ȷ��");
		JButton b2=new JButton();
		b2.setText("ȡ��");
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
		int d=Integer.parseInt(s3);//����
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
			ps.setString(5, s6);//�Ѳ��˵Ļ�����Ϣ�Ž�Pat_order��
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(rs,ps,conn);
		}
		jl1.setText("���Ѿ�ԤԼ�ɹ�");		
	}
	public void delete(){
		jl1.setText("ԤԼ�Ѿ�ȡ����ף���������");
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
	}
	
}
