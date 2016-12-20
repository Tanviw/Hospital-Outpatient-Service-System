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

public class AlterAcco {

	private JFrame altAccoFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void alterAcco() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterAcco window = new AlterAcco();
					window.altAccoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AlterAcco() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		altAccoFrame = new JFrame();
		altAccoFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		altAccoFrame.setBounds(500, 200, 450, 350);
		altAccoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		altAccoFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u4FEE\u6539\u7684\u8D26\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(139, 42, 189, 22);
		altAccoFrame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(126, 91, 176, 21);
		altAccoFrame.getContentPane().add(textField);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String acco=textField.getText();
				int ac=Integer.parseInt(acco);
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String sql="select * from Account where Account=?";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,ac);
					rs=ps.executeQuery();
					if(rs.next()){
						UpdateAcco.updateAcco(rs.getInt(1),rs.getString(2),rs.getString(3));
					}else{
						JOptionPane.showMessageDialog(null, "该账号不存在！请检查您的输入信息是否正确。");
					}
						
					altAccoFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
				
			}
		});
		button.setBounds(106, 181, 70, 30);
		altAccoFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altAccoFrame.setVisible(false);
			}
		});
		button_1.setBounds(245, 181, 70, 30);
		altAccoFrame.getContentPane().add(button_1);
	}

}
