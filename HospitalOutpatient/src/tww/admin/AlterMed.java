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
import javax.swing.JComboBox;

public class AlterMed {

	private JFrame alterMedFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void alterMed() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterMed window = new AlterMed();
					window.alterMedFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AlterMed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alterMedFrame = new JFrame();
		alterMedFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		alterMedFrame.setBounds(500, 200, 450, 350);
		alterMedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		alterMedFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u8981\u4FEE\u6539\u7684\u836F\u54C1\u540D\u79F0\u6216\u7B80\u7801\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(111, 63, 218, 22);
		alterMedFrame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(122, 118, 176, 21);
		alterMedFrame.getContentPane().add(textField);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String medName=textField.getText();
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String sql="select * from Medicine where Med_name=? or cast(Med_bfcode as varchar(50))=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,medName);
					ps.setString(2,medName);
					rs=ps.executeQuery();
					if(rs.next()){
						UpdateMed.updateMed(rs.getString(1),rs.getFloat(2),rs.getString(3),rs.getInt(4));
					}else{
						JOptionPane.showMessageDialog(null, "该药品不存在！请检查您的输入信息是否正确!");
					}
						
					alterMedFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
			}
		});
		button.setBounds(102, 208, 70, 30);
		alterMedFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterMedFrame.setVisible(false);
			}
		});
		button_1.setBounds(241, 208, 70, 30);
		alterMedFrame.getContentPane().add(button_1);
	}
}
