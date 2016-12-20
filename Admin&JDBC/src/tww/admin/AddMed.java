package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import tww.pool.DBManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddMed {

	private JFrame medFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void addMed() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMed window = new AddMed();
					window.medFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddMed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		medFrame = new JFrame();
		medFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		medFrame.setBounds(500, 200, 450, 350);
		medFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		medFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u6DFB\u52A0\u7684\u836F\u54C1\u4FE1\u606F");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(128, 27, 161, 19);
		medFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(100, 78, 39, 15);
		medFrame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(149, 75, 140, 21);
		medFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5355\u4EF7\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 13));
		label_2.setBounds(100, 111, 39, 19);
		medFrame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 110, 140, 21);
		medFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7B80\u7801\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 13));
		label_3.setBounds(100, 148, 54, 15);
		medFrame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(149, 145, 140, 21);
		medFrame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_4 = new JLabel("\u6570\u91CF\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 13));
		label_4.setBounds(100, 183, 39, 15);
		medFrame.getContentPane().add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(149, 180, 140, 21);
		medFrame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String medName=textField.getText();
				String sp=textField_1.getText();
				System.out.println(sp);
				float price=Float.parseFloat(sp);
				System.out.println(price);
				String jm=textField_2.getText();
				int count=Integer.parseInt(textField_3.getText());
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="insert into Medicine values(?,?,?,?)";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,medName);
					ps.setDouble(2, price);
					//ps.setFloat(2, price);
					ps.setString(3, jm);
					ps.setInt(4, count);
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "添加药品信息失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功添加药品信息！");
					}
					
					medFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
			
		});
		btnNewButton.setBounds(100, 236, 70, 30);
		medFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(237, 236, 70, 30);
		medFrame.getContentPane().add(btnNewButton_1);
	}

}
