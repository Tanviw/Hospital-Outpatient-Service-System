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

public class DeletePro {

	private JFrame delProFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void deletePro() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePro window = new DeletePro();
					window.delProFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeletePro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		delProFrame = new JFrame();
		delProFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		delProFrame.setBounds(500, 200, 450, 350);
		delProFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		delProFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u5220\u9664\u7684\u6536\u8D39\u9879\u76EE\u540D\u79F0\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(121, 68, 189, 22);
		delProFrame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(121, 127, 176, 21);
		delProFrame.getContentPane().add(textField);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
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
						DropPro.dropPro(rs.getString(1),rs.getFloat(2),rs.getString(3));
					}else{
						JOptionPane.showMessageDialog(null, "该收费项目不存在！请检查您的输入信息是否正确。");
					}
						
					delProFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
			}
		});
		button.setBounds(101, 217, 70, 30);
		delProFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delProFrame.setVisible(false);
			}
		});
		button_1.setBounds(240, 217, 70, 30);
		delProFrame.getContentPane().add(button_1);
	}

}
