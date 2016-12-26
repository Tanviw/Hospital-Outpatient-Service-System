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

public class DeleteDoc {

	private JFrame delDocFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void deleteDoc() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteDoc window = new DeleteDoc();
					window.delDocFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteDoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		delDocFrame = new JFrame();
		delDocFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		delDocFrame.setBounds(500, 200, 450, 350);
		delDocFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		delDocFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u5220\u9664\u7684\u533B\u751F\u5DE5\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(127, 59, 189, 22);
		delDocFrame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(127, 118, 176, 21);
		delDocFrame.getContentPane().add(textField);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = textField.getText();
				int id=Integer.parseInt(sid);
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String sql="select * from Doctor where Doc_id=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,id);
					rs=ps.executeQuery();
					if(rs.next()){
						DropDoc.dropDoc(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
					}else{
						JOptionPane.showMessageDialog(null, "该医生不存在！请检查您的输入信息是否正确。");
					}
						
					delDocFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
			}
		});
		button.setBounds(107, 208, 70, 30);
		delDocFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delDocFrame.setVisible(false);
			}
		});
		button_1.setBounds(246, 208, 70, 30);
		delDocFrame.getContentPane().add(button_1);
	}

}
