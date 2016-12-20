package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tww.pool.DBManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class UpdateAcco {

	private JFrame updateAccoFrame;
	private JTextField textField;
	private JPasswordField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void updateAcco(int zh,String mm,String bm) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAcco window = new UpdateAcco(zh,mm,bm);
					window.updateAccoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateAcco(int zh,String mm,String bm) {
		initialize(zh,mm,bm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int zh,String mm,String bm) {
		updateAccoFrame = new JFrame();
		updateAccoFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		updateAccoFrame.setBounds(500, 200, 450, 350);
		updateAccoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateAccoFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5DF2\u4E3A\u60A8\u67E5\u627E\u5230\u4EE5\u4E0B\u4FE1\u606F\uFF0C\u8BF7\u91CD\u65B0\u586B\u5199\u9700\u8981\u4FEE\u6539\u7684\u9879\uFF1A");
		label.setBounds(75, 29, 314, 15);
		updateAccoFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setBounds(75, 68, 54, 15);
		updateAccoFrame.getContentPane().add(label_1);
		
		textField = new JTextField();
		String szh=String.valueOf(zh);
		textField.setText(szh);
		textField.setColumns(10);
		textField.setBounds(117, 65, 188, 21);
		updateAccoFrame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(75, 123, 54, 15);
		updateAccoFrame.getContentPane().add(label_2);
		
		textField_1 = new JPasswordField();
		textField_1.setText(mm);
		textField_1.setColumns(10);
		textField_1.setBounds(117, 120, 188, 21);
		updateAccoFrame.getContentPane().add(textField_1);
		
		JLabel label_3 = new JLabel("\u90E8\u95E8\uFF1A");
		label_3.setBounds(75, 179, 39, 15);
		updateAccoFrame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText(bm);
		textField_2.setBounds(117, 176, 188, 21);
		updateAccoFrame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String szh=textField.getText();
				int acco=Integer.parseInt(szh);
				String pwd=String.valueOf(textField_1.getPassword());
				String dept=textField_2.getText();
		
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="update Account set Account=? ,Password=?,Department=? where Account=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,acco);
					ps.setString(2,pwd);
					ps.setString(3, dept);
					ps.setInt(4, zh);
		
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "修改账号信息失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功修改账号信息！");
					}
					
					updateAccoFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		button.setBounds(100, 241, 70, 30);
		updateAccoFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAccoFrame.setVisible(false);
			}
		});
		button_1.setBounds(235, 241, 70, 30);
		updateAccoFrame.getContentPane().add(button_1);
		
		
		
		
	}

}
