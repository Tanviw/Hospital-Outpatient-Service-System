package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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

public class AddAcco {

	private JFrame accoFrame;
	private JTextField accoText;
	private JPasswordField pwdText;
	private JTextField deptText;

	/**
	 * Launch the application.
	 */
	public static void addAcco() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAcco window = new AddAcco();
					window.accoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddAcco() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		accoFrame = new JFrame();
		accoFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		accoFrame.setBounds(500, 200, 450, 350);
		accoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accoFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u6DFB\u52A0\u7684\u8D26\u53F7\u4FE1\u606F");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(140, 25, 154, 33);
		accoFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(90, 80, 39, 15);
		accoFrame.getContentPane().add(label_1);
		
		accoText = new JTextField();
		accoText.setBounds(140, 80, 154, 21);
		accoFrame.getContentPane().add(accoText);
		accoText.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 13));
		label_2.setBounds(90, 120, 39, 15);
		accoFrame.getContentPane().add(label_2);
		
		pwdText = new JPasswordField();
		pwdText.setBounds(140, 120, 154, 21);
		accoFrame.getContentPane().add(pwdText);
		pwdText.setColumns(10);
		
		JLabel label_3 = new JLabel("\u90E8\u95E8\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 13));
		label_3.setBounds(90, 160, 39, 15);
		accoFrame.getContentPane().add(label_3);
		
		deptText = new JTextField();
		deptText.setBounds(140, 160, 154, 21);
		accoFrame.getContentPane().add(deptText);
		deptText.setColumns(10);
		
		JButton cofirButton = new JButton("\u786E\u5B9A");
		cofirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int acco=Integer.parseInt(accoText.getText());
				char[] pswd=pwdText.getPassword();
				String pwd=String.valueOf(pswd);
				String dept=deptText.getText();
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="insert into Account values(?,?,?)";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,acco);
					ps.setString(2, pwd);
					ps.setString(3, dept);
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "添加账号失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功添加账号！");
					}
					
					accoFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		cofirButton.setBounds(103, 229, 70, 30);
		accoFrame.getContentPane().add(cofirButton);
		
		JButton cancButton = new JButton("\u53D6\u6D88");
		cancButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accoFrame.setVisible(false);
			}
		});
		cancButton.setBounds(238, 229, 70, 30);
		accoFrame.getContentPane().add(cancButton);
	}
}
