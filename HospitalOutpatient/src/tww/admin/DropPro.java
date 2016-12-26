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

public class DropPro {

	private JFrame dropProFrame;

	/**
	 * Launch the application.
	 */
	public static void dropPro(String name,float price,String jm) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DropPro window = new DropPro(name,price,jm);
					window.dropProFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DropPro(String name,float price,String jm) {
		initialize(name,price,jm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name,float price,String jm) {
		dropProFrame = new JFrame();
		dropProFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		dropProFrame.setBounds(500, 200, 450, 350);
		dropProFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dropProFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u662F\u5426\u786E\u8BA4\u5220\u9664\uFF1F");
		label.setBounds(156, 208, 114, 15);
		dropProFrame.getContentPane().add(label);
		
		JButton button = new JButton("\u662F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="delete from Charge_list where Pro_name=? or cast(Pro_bfcode as varchar(50))=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,name);
					ps.setString(2,name);
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "删除收费项目失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功删除该收费项目！");
					}
					
					dropProFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		button.setBounds(87, 252, 70, 30);
		dropProFrame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u5426");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropProFrame.setVisible(false);
			}
		});
		button_1.setBounds(241, 252, 70, 30);
		dropProFrame.getContentPane().add(button_1);
		
		JLabel label_1 = new JLabel("\u8BE5\u6536\u8D39\u9879\u76EE\u4FE1\u606F\u5982\u4E0B\uFF1A");
		label_1.setBounds(142, 30, 154, 15);
		dropProFrame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u540D\u79F0\uFF1A");
		label_2.setBounds(123, 74, 54, 15);
		dropProFrame.getContentPane().add(label_2);
		
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setBounds(172, 74, 54, 15);
		dropProFrame.getContentPane().add(lblNewLabel);
		
		JLabel label_3 = new JLabel("\u5355\u4EF7\uFF1A");
		label_3.setBounds(123, 112, 54, 15);
		dropProFrame.getContentPane().add(label_3);
		
		String dj=String.valueOf(price);
		JLabel lblNewLabel_1 = new JLabel(dj);
		lblNewLabel_1.setBounds(172, 112, 54, 15);
		dropProFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel label_4 = new JLabel("\u7B80\u7801\uFF1A");
		label_4.setBounds(123, 152, 54, 15);
		dropProFrame.getContentPane().add(label_4);
		
		JLabel lblNewLabel_2 = new JLabel(jm);
		lblNewLabel_2.setBounds(172, 152, 54, 15);
		dropProFrame.getContentPane().add(lblNewLabel_2);
	}

}
