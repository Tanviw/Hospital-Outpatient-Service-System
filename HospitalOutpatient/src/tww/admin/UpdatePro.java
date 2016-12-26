package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tww.pool.DBManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class UpdatePro {

	private JFrame updateProFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void updatePro(String mc,float dj,String jm) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePro window = new UpdatePro(mc,dj,jm);
					window.updateProFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdatePro(String mc,float dj,String jm) {
		initialize(mc,dj,jm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String mc,float dj,String jm) {
		updateProFrame = new JFrame();
		updateProFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		updateProFrame.setBounds(500, 200, 450, 350);
		updateProFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateProFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5DF2\u4E3A\u60A8\u67E5\u627E\u5230\u4EE5\u4E0B\u4FE1\u606F\uFF0C\u8BF7\u91CD\u65B0\u586B\u5199\u9700\u8981\u4FEE\u6539\u7684\u9879\uFF1A");
		label.setBounds(88, 26, 296, 15);
		updateProFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u540D\u79F0\uFF1A");
		label_1.setBounds(88, 65, 54, 15);
		updateProFrame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setText(mc);
		textField.setColumns(10);
		textField.setBounds(130, 62, 188, 21);
		updateProFrame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("\u5355\u4EF7\uFF1A");
		label_2.setBounds(88, 120, 54, 15);
		updateProFrame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		String sdj=String.valueOf(dj);
		textField_1.setText(sdj);
		textField_1.setColumns(10);
		textField_1.setBounds(130, 117, 188, 21);
		updateProFrame.getContentPane().add(textField_1);
		
		JLabel label_3 = new JLabel("\u7B80\u7801\uFF1A");
		label_3.setBounds(88, 175, 54, 15);
		updateProFrame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText(jm);
		textField_2.setColumns(10);
		textField_2.setBounds(130, 172, 188, 21);
		updateProFrame.getContentPane().add(textField_2);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proName=textField.getText();
				float price=Float.parseFloat(textField_1.getText());
				String jm=textField_2.getText();
		
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="update Charge_list set Pro_name=? ,Pro_price=?,Pro_bfcode=? where Pro_name=? or cast(Pro_bfcode as varchar(50))=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,proName);
					ps.setFloat(2, price);
					ps.setString(3, jm);
					ps.setString(4, mc);
					ps.setString(5, mc);
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "修改收费项目失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功修改收费项目！");
					}
					
					updateProFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		button.setBounds(113, 238, 70, 30);
		updateProFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProFrame.setVisible(false);
			}
		});
		button_1.setBounds(248, 238, 70, 30);
		updateProFrame.getContentPane().add(button_1);
	}

}
