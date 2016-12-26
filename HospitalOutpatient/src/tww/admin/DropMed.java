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

public class DropMed {

	private JFrame dropMedFrame;

	/**
	 * Launch the application.
	 */
	public static void dropMed(String name,float price,String bfCode,int count) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DropMed window = new DropMed(name,price,bfCode,count);
					window.dropMedFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DropMed(String name,float price,String bfCode,int count) {
		initialize(name,price,bfCode,count);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name,float price,String bfCode,int count) {
		dropMedFrame = new JFrame();
		dropMedFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		dropMedFrame.setBounds(500, 200, 450, 350);
		dropMedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dropMedFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BE5\u836F\u54C1\u4FE1\u606F\u5982\u4E0B\uFF1A");
		label.setBounds(164, 22, 118, 15);
		dropMedFrame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(124, 82, 54, 15);
		dropMedFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(name);
		lblNewLabel_1.setBounds(178, 82, 124, 15);
		dropMedFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u5355\u4EF7\uFF1A");
		label_1.setBounds(124, 107, 54, 15);
		dropMedFrame.getContentPane().add(label_1);
		
		String dj=String.valueOf(price);
		JLabel lblNewLabel_2 = new JLabel(dj);
		lblNewLabel_2.setBounds(178, 107, 124, 15);
		dropMedFrame.getContentPane().add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("\u7B80\u7801\uFF1A");
		label_2.setBounds(124, 131, 54, 15);
		dropMedFrame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_3 = new JLabel(bfCode);
		lblNewLabel_3.setBounds(178, 131, 124, 15);
		dropMedFrame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u6570\u91CF\uFF1A");
		lblNewLabel_4.setBounds(124, 156, 54, 15);
		dropMedFrame.getContentPane().add(lblNewLabel_4);
		
		String sl=String.valueOf(count);
		JLabel lblNewLabel_5 = new JLabel(sl);
		lblNewLabel_5.setBounds(178, 156, 124, 15);
		dropMedFrame.getContentPane().add(lblNewLabel_5);
		
		JLabel label_3 = new JLabel("\u662F\u5426\u786E\u8BA4\u5220\u9664\uFF1F");
		label_3.setBounds(164, 206, 106, 15);
		dropMedFrame.getContentPane().add(label_3);
		
		JButton btnNewButton = new JButton("\u662F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				PreparedStatement ps=null;
				String sql="delete from Medicine where Med_name=? or cast(Med_bfcode as varchar(50))=?;";
				try {
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setString(1,name);
					ps.setString(2,name);
					int i=ps.executeUpdate();
					if(i==0){
						JOptionPane.showMessageDialog(null, "删除药品失败！请检查您的输入信息是否正确。");
					}else{
						JOptionPane.showMessageDialog(null, "已成功删除该药品！");
					}
					
					dropMedFrame.setVisible(false);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(ps,conn);
				}
			}
		});
		btnNewButton.setBounds(98, 247, 70, 30);
		dropMedFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5426");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropMedFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(247, 247, 70, 30);
		dropMedFrame.getContentPane().add(btnNewButton_1);
	}

}
