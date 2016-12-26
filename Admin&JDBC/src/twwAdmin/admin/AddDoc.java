package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tww.pool.DBManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class AddDoc {

	private JFrame docFrame;
	private JTextField idText;
	private JTextField nameText;
	private JTextField deptText;
	private JTextField patnumText;

	/**
	 * Launch the application.
	 */
	public static void addDoc() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDoc window = new AddDoc();
					window.docFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddDoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		docFrame = new JFrame();
		docFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		docFrame.setBounds(500, 200, 450, 350);
		docFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		docFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u6DFB\u52A0\u7684\u533B\u751F\u4FE1\u606F");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(140, 25, 170, 15);
		docFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5DE5\u53F7\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(85, 75, 44, 21);
		docFrame.getContentPane().add(label_1);
		
		idText = new JTextField();
		idText.setBounds(139, 75, 162, 21);
		docFrame.getContentPane().add(idText);
		idText.setColumns(10);
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 13));
		label_2.setBounds(85, 110, 44, 21);
		docFrame.getContentPane().add(label_2);
		
		nameText = new JTextField();
		nameText.setBounds(139, 110, 162, 21);
		docFrame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		JLabel label_3 = new JLabel("\u79D1\u5BA4\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 13));
		label_3.setBounds(85, 145, 39, 21);
		docFrame.getContentPane().add(label_3);
		
		deptText = new JTextField();
		deptText.setBounds(139, 145, 162, 21);
		docFrame.getContentPane().add(deptText);
		deptText.setColumns(10);
		
		JLabel label_4 = new JLabel("\u5C31\u8BCA\u6570\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 13));
		label_4.setBounds(85, 180, 54, 21);
		docFrame.getContentPane().add(label_4);
		
		patnumText = new JTextField();
		patnumText.setBounds(139, 180, 162, 21);
		docFrame.getContentPane().add(patnumText);
		patnumText.setColumns(10);
		
		JButton confirmButton = new JButton("\u786E\u5B9A");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(idText.getText());
				String name=nameText.getText();
				String dept=deptText.getText();
				int patnum=Integer.parseInt(patnumText.getText());
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="insert into Doctor values(?,?,?,?)";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2, name);
					ps.setString(3, dept);
					ps.setInt(4, patnum);
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "添加医生信息失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功添加医生信息！");
					}
					
					docFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
				
			
		});
		confirmButton.setBounds(110, 249, 70, 30);
		docFrame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				docFrame.setVisible(false);
			}
		});
		cancelButton.setBounds(238, 249, 70, 30);
		docFrame.getContentPane().add(cancelButton);
	}
}
