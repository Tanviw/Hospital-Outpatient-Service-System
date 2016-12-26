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
import java.sql.*;
import java.awt.event.ActionEvent;

public class AlterPro {

	private JFrame altProFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void alterPro() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterPro window = new AlterPro();
					window.altProFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AlterPro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		altProFrame = new JFrame();
		altProFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		altProFrame.setBounds(500, 200, 450, 350);
		altProFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		altProFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u4FEE\u6539\u7684\u6536\u8D39\u9879\u76EE\u540D\u79F0\u6216\u7B80\u7801\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(114, 73, 227, 22);
		altProFrame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(120, 133, 176, 21);
		altProFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proName=textField.getText();
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String sql="select * from Charge_list where Pro_name=? or cast(Pro_bfcode as varchar(50))=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,proName);
					ps.setString(2,proName);
					rs=ps.executeQuery();
					if(rs.next()){
						UpdatePro.updatePro(rs.getString(1),rs.getFloat(2),rs.getString(3));
					}else{
						JOptionPane.showMessageDialog(null, "该收费项目不存在！请检查您的输入信息是否正确。");
					}
						
					altProFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
			}
		});
		btnNewButton.setBounds(100, 223, 70, 30);
		altProFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.setBounds(239, 223, 70, 30);
		altProFrame.getContentPane().add(btnNewButton_1);
	}

}
