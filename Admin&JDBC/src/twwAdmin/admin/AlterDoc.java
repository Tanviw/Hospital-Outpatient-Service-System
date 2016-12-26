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

public class AlterDoc {

	private JFrame alterDocFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void alterDoc() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterDoc window = new AlterDoc();
					window.alterDocFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AlterDoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alterDocFrame = new JFrame();
		alterDocFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		alterDocFrame.setBounds(500, 200, 450, 350);
		alterDocFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		alterDocFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u4FEE\u6539\u7684\u533B\u751F\u5DE5\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(125, 64, 189, 22);
		alterDocFrame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(125, 123, 176, 21);
		alterDocFrame.getContentPane().add(textField);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = textField.getText();
				int id=Integer.parseInt(sid);
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String sql="select * from Doctor where Doc_id=?";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,id);
					rs=ps.executeQuery();
					if(rs.next()){
						UpdateDoc.updateDoc(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
					}else{
						JOptionPane.showMessageDialog(null, "该医生不存在！请检查您的输入信息是否正确。");
					}
						
					alterDocFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
			}
		});
		button.setBounds(105, 213, 70, 30);
		alterDocFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterDocFrame.setVisible(false);
			}
		});
		button_1.setBounds(244, 213, 70, 30);
		alterDocFrame.getContentPane().add(button_1);
	}

}
