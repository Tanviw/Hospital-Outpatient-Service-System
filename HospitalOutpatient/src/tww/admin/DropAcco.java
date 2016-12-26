package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tww.pool.DBManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class DropAcco {

	private JFrame dropAccoFrame;

	/**
	 * Launch the application.
	 */
	public static void dropAcco(int id,String dept) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DropAcco window = new DropAcco(id,dept);
					window.dropAccoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DropAcco(int id,String dept) {
		initialize(id,dept);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id,String dept) {
		dropAccoFrame = new JFrame();
		dropAccoFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		dropAccoFrame.setBounds(500, 200, 450, 350);
		dropAccoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dropAccoFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BE5\u8D26\u53F7\u4FE1\u606F\u5982\u4E0B\uFF1A");
		label.setBounds(159, 39, 110, 15);
		dropAccoFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setBounds(135, 101, 54, 15);
		dropAccoFrame.getContentPane().add(label_1);
		
		String sid=String.valueOf(id);
		JLabel lblNewLabel = new JLabel(sid);
		lblNewLabel.setBounds(184, 101, 110, 15);
		dropAccoFrame.getContentPane().add(lblNewLabel);
		
		JLabel label_2 = new JLabel("\u79D1\u5BA4\uFF1A");
		label_2.setBounds(135, 157, 54, 15);
		dropAccoFrame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel(dept);
		lblNewLabel_1.setBounds(184, 157, 110, 15);
		dropAccoFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel label_3 = new JLabel("\u662F\u5426\u786E\u8BA4\u5220\u9664\uFF1F");
		label_3.setBounds(159, 209, 135, 15);
		dropAccoFrame.getContentPane().add(label_3);
		
		JButton btnNewButton = new JButton("\u662F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="delete from Account where Account=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,id);
		
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "删除账号失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功删除该账号！");
					}
					
					dropAccoFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		btnNewButton.setBounds(93, 251, 70, 30);
		dropAccoFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5426");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropAccoFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(239, 251, 70, 30);
		dropAccoFrame.getContentPane().add(btnNewButton_1);
	}

}
