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

public class UpdateMed {

	private JFrame updateMedFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void updateMed(String medName,float price,String bfCode,int count) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMed window = new UpdateMed(medName,price,bfCode,count);
					window.updateMedFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateMed(String medName,float price,String bfCode,int count) {
		initialize(medName,price,bfCode,count);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String medName,float price,String bfCode,int count) {
		updateMedFrame = new JFrame();
		updateMedFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		updateMedFrame.setBounds(500, 200, 450, 350);
		updateMedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateMedFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5DF2\u4E3A\u60A8\u67E5\u627E\u5230\u4EE5\u4E0B\u4FE1\u606F\uFF0C\u8BF7\u91CD\u65B0\u586B\u5199\u9700\u8981\u4FEE\u6539\u7684\u9879\uFF1A");
		label.setBounds(67, 19, 297, 15);
		updateMedFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u540D\u79F0\uFF1A");
		label_1.setBounds(67, 58, 54, 15);
		updateMedFrame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setText(medName);
		textField.setColumns(10);
		textField.setBounds(109, 55, 188, 21);
		updateMedFrame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("\u5355\u4EF7\uFF1A");
		label_2.setBounds(67, 99, 54, 15);
		updateMedFrame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		String sdj=String.valueOf(price);
		textField_1.setText(sdj);
		textField_1.setColumns(10);
		textField_1.setBounds(109, 96, 188, 21);
		updateMedFrame.getContentPane().add(textField_1);
		
		JLabel label_3 = new JLabel("\u7B80\u7801\uFF1A");
		label_3.setBounds(67, 138, 54, 15);
		updateMedFrame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText(bfCode);
		textField_2.setColumns(10);
		textField_2.setBounds(109, 135, 188, 21);
		updateMedFrame.getContentPane().add(textField_2);
		
		JLabel label_4 = new JLabel("\u6570\u91CF\uFF1A");
		label_4.setBounds(67, 175, 54, 15);
		updateMedFrame.getContentPane().add(label_4);
		
		textField_3 = new JTextField();
		String sl=String.valueOf(count);
		textField_3.setText(sl);
		textField_3.setColumns(10);
		textField_3.setBounds(109, 172, 188, 21);
		updateMedFrame.getContentPane().add(textField_3);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				float mprice=Float.parseFloat(textField_1.getText());
				String jm=textField_2.getText();
				int msl=Integer.parseInt(textField_3.getText());
		
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="update Medicine set Med_name=?,Med_price=?,Med_bfcode=?,Med_count=? where  Med_name=? or cast(Med_bfcode as varchar(50))=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,name);
					ps.setFloat(2, mprice);
					ps.setString(3, jm);
					ps.setInt(4, msl);
					ps.setString(5, medName);
					ps.setString(6, medName);
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "修改药品信息失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功修改药品信息！");
					}
					
					updateMedFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		button.setBounds(92, 231, 70, 30);
		updateMedFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMedFrame.setVisible(false);
			}
		});
		button_1.setBounds(227, 231, 70, 30);
		updateMedFrame.getContentPane().add(button_1);
		
		
	}

}
