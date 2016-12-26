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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DeleteAcco {

	private JFrame delAccoFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void deleteAcco() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAcco window = new DeleteAcco();
					window.delAccoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteAcco() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		delAccoFrame = new JFrame();
		delAccoFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		delAccoFrame.setBounds(500, 200, 450, 350);
		delAccoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		delAccoFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u5220\u9664\u7684\u8D26\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(132, 60, 189, 22);
		delAccoFrame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(119, 109, 176, 21);
		delAccoFrame.getContentPane().add(textField);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String acco=textField.getText();
				int ac=Integer.parseInt(acco);
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String sql="select * from Account where Account=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,ac);
					rs=ps.executeQuery();
					if(rs.next()){
						DropAcco.dropAcco(rs.getInt(1),rs.getString(3));
					}else{
						JOptionPane.showMessageDialog(null, "该账号不存在！请检查您的输入信息是否正确。");
					}
						
					delAccoFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
			}
		});
		button.setBounds(99, 199, 70, 30);
		delAccoFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delAccoFrame.setVisible(false);
			}
		});
		button_1.setBounds(238, 199, 70, 30);
		delAccoFrame.getContentPane().add(button_1);
	}

}
