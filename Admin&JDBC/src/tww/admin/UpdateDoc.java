package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import tww.pool.DBManager;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class UpdateDoc {

	private JFrame updateDocFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void updateDoc(int id,String name,String dept,int pat_num) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDoc window = new UpdateDoc(id,name,dept,pat_num);
					window.updateDocFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateDoc(int id,String name,String dept,int pat_num) {
		initialize(id,name,dept,pat_num);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id,String name,String dept,int pat_num) {
		updateDocFrame = new JFrame();
		updateDocFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		updateDocFrame.setBounds(500, 200, 450, 350);
		updateDocFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateDocFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5DF2\u4E3A\u60A8\u67E5\u627E\u5230\u4EE5\u4E0B\u4FE1\u606F\uFF0C\u8BF7\u91CD\u65B0\u586B\u5199\u9700\u8981\u4FEE\u6539\u7684\u9879\uFF1A");
		label.setBounds(79, 33, 299, 15);
		updateDocFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5DE5\u53F7\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(100, 74, 44, 21);
		updateDocFrame.getContentPane().add(label_1);
		
		textField = new JTextField();
		String sid=String.valueOf(id);
		textField.setText(sid);
		textField.setColumns(10);
		textField.setBounds(154, 74, 162, 21);
		updateDocFrame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 13));
		label_2.setBounds(100, 109, 44, 21);
		updateDocFrame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setText(name);
		textField_1.setColumns(10);
		textField_1.setBounds(154, 109, 162, 21);
		updateDocFrame.getContentPane().add(textField_1);
		
		JLabel label_3 = new JLabel("\u79D1\u5BA4\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 13));
		label_3.setBounds(100, 144, 39, 21);
		updateDocFrame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText(dept);
		textField_2.setColumns(10);
		textField_2.setBounds(154, 144, 162, 21);
		updateDocFrame.getContentPane().add(textField_2);
		
		JLabel label_4 = new JLabel("\u5C31\u8BCA\u6570\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 13));
		label_4.setBounds(100, 179, 54, 21);
		updateDocFrame.getContentPane().add(label_4);
		
		textField_3 = new JTextField();
		String spat_num=String.valueOf(pat_num);
		textField_3.setText(spat_num);
		textField_3.setColumns(10);
		textField_3.setBounds(154, 179, 162, 21);
		updateDocFrame.getContentPane().add(textField_3);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Sid=textField.getText();
				int docId=Integer.parseInt(Sid);
				String dname=textField_1.getText();
				String sdept=textField_2.getText();
				int dpat_num=Integer.parseInt(textField_3.getText());
		
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="update Doctor set Doc_id=? ,Doc_name=?,Doc_dept=?,Doc_patnum=? where Doc_id=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,docId);
					ps.setString(2, dname);
					ps.setString(3,sdept);
					ps.setInt(4,dpat_num);
					ps.setInt(5, id);
		
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "修改医生信息失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功修改医生信息！");
					}
					
					updateDocFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
				
			}
		});
		button.setBounds(125, 248, 70, 30);
		updateDocFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDocFrame.setVisible(false);
			}
		});
		button_1.setBounds(253, 248, 70, 30);
		updateDocFrame.getContentPane().add(button_1);
	}
}
