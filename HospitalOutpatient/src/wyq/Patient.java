import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tww.pool.DBManager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Patient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patient frame = new Patient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Patient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u6765\u5230\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		lblNewLabel.setBounds(159, 10, 162, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(12, 35, 35, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(66, 32, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5E74\u9F84");
		label_1.setBounds(228, 35, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(295, 35, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(12, 102, 44, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(66, 99, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\u79D1\u5BA4");
		label_3.setBounds(229, 102, 35, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(295, 99, 66, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("\u624B\u673A\u53F7");
		label_4.setBounds(10, 144, 46, 15);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(60, 141, 133, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_5 = new JLabel("\u65F6\u95F4");
		label_5.setBounds(228, 144, 54, 15);
		contentPane.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(292, 141, 111, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		JLabel label_6 = new JLabel("");
		label_6.setBounds(139, 236, 125, 15);
		contentPane.add(label_6);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String s1=textField.getText();
				String s2=textField_1.getText();
				String s3=textField_2.getText();
				int d=Integer.parseInt(s2);//年龄
				String s4=textField_3.getText();
				String s5=textField_4.getText();
				String s6=textField_5.getText();
				SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// date = null;
				//try {
					//date = formatter.format(s6);
				//} catch (ParseException e2) {
					// TODO Auto-generated catch block
					//e2.printStackTrace();
				//}
				int a=0;
				try{
					conn=DBManager.getConnect();
					String sql="insert into Pat_order values(?,?,?,?,?,?)";
					ps=conn.prepareStatement(sql);
					ps.setString(1, s1);
					ps.setString(2, s3);
					ps.setInt(3, d);
					ps.setString(4, s5);
					ps.setString(5, s4);
					ps.setString(6, s6);//把病人的基本信息放进Pat_order表
					 a=ps.executeUpdate();
					 if(a==0){
						 label_6.setText("预约失败");
					 }
					 else label_6.setText("您已经预约成功");
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
				//label_6.setText("您已经预约成功");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
			}
			}
		);
		button.setBounds(60, 182, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label_6.setText("您已经取消预约");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
			}
		});
		button_1.setBounds(228, 182, 93, 23);
		contentPane.add(button_1);
		
		
	}

}
