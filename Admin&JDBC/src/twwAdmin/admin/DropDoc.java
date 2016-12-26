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

public class DropDoc {

	private JFrame dropDocFrame;

	/**
	 * Launch the application.
	 */
	public static void dropDoc(int id,String name,String dept,int patNum) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DropDoc window = new DropDoc(id,name,dept,patNum);
					window.dropDocFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DropDoc(int id,String name,String dept,int patNum) {
		initialize(id,name,dept,patNum);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id,String name,String dept,int patNum) {
		dropDocFrame = new JFrame();
		dropDocFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		dropDocFrame.setBounds(500, 200, 450, 350);
		dropDocFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dropDocFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BE5\u533B\u751F\u4FE1\u606F\u5982\u4E0B\uFF1A");
		label.setBounds(161, 24, 127, 15);
		dropDocFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5DE5\u53F7:");
		label_1.setBounds(108, 68, 54, 15);
		dropDocFrame.getContentPane().add(label_1);
		
		String gh=String.valueOf(id);
		JLabel lblNewLabel = new JLabel(gh);
		lblNewLabel.setBounds(161, 68, 127, 15);
		dropDocFrame.getContentPane().add(lblNewLabel);
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		label_2.setBounds(108, 106, 54, 15);
		dropDocFrame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel(name);
		lblNewLabel_1.setBounds(161, 106, 127, 15);
		dropDocFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel label_3 = new JLabel("\u79D1\u5BA4\uFF1A");
		label_3.setBounds(108, 141, 54, 15);
		dropDocFrame.getContentPane().add(label_3);
		
		JLabel lblNewLabel_2 = new JLabel(dept);
		lblNewLabel_2.setBounds(161, 141, 127, 15);
		dropDocFrame.getContentPane().add(lblNewLabel_2);
		
		JLabel label_4 = new JLabel("\u5C31\u8BCA\u6570\uFF1A");
		label_4.setBounds(108, 175, 54, 15);
		dropDocFrame.getContentPane().add(label_4);
		
		String pnum=String.valueOf(patNum);
		JLabel lblNewLabel_3 = new JLabel(pnum);
		lblNewLabel_3.setBounds(161, 175, 127, 15);
		dropDocFrame.getContentPane().add(lblNewLabel_3);
		
		JLabel label_5 = new JLabel("\u662F\u5426\u786E\u8BA4\u5220\u9664\uFF1F");
		label_5.setBounds(161, 216, 113, 15);
		dropDocFrame.getContentPane().add(label_5);
		
		JButton btnNewButton = new JButton("\u662F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="delete from Doctor where Doc_id=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,id);
		
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "删除医生失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功删除该医生！");
					}
					
					dropDocFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		btnNewButton.setBounds(92, 260, 70, 30);
		dropDocFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5426");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropDocFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(246, 260, 70, 30);
		dropDocFrame.getContentPane().add(btnNewButton_1);
	}

}
