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
import java.awt.event.ActionEvent;

public class AddPro {

	private JFrame proFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void addPro() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPro window = new AddPro();
					window.proFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddPro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		proFrame = new JFrame();
		proFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		proFrame.setBounds(500, 200, 450, 350);
		proFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		proFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u6DFB\u52A0\u7684\u6536\u8D39\u9879\u76EE\u4FE1\u606F");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(124, 26, 188, 19);
		proFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u540D\u79F0\uFF1A");
		label_1.setBounds(82, 76, 54, 15);
		proFrame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(124, 73, 188, 21);
		proFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5355\u4EF7\uFF1A");
		label_2.setBounds(82, 131, 54, 15);
		proFrame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 128, 188, 21);
		proFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7B80\u7801\uFF1A");
		label_3.setBounds(82, 186, 54, 15);
		proFrame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(124, 183, 188, 21);
		proFrame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proName=textField.getText();
				float price=Float.parseFloat(textField_1.getText());
				String jm=textField_2.getText();
		
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="insert into Charge_list values(?,?,?)";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,proName);
					ps.setFloat(2, price);
					ps.setString(3, jm);
		
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "添加收费项目失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功添加收费项目！");
					}
					
					proFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
			
		});
		btnNewButton.setBounds(107, 249, 70, 30);
		proFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(242, 249, 70, 30);
		proFrame.getContentPane().add(btnNewButton_1);
	}

}
