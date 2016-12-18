package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tww.pool.DBManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
		docFrame.setBounds(100, 100, 450, 350);
		docFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		docFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u9700\u6DFB\u52A0\u7684\u533B\u751F\u4FE1\u606F");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		label.setBounds(139, 25, 169, 15);
		docFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5DE5\u53F7\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_1.setBounds(85, 75, 44, 21);
		docFrame.getContentPane().add(label_1);
		
		idText = new JTextField();
		idText.setBounds(139, 75, 162, 21);
		docFrame.getContentPane().add(idText);
		idText.setColumns(10);
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		label_2.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_2.setBounds(85, 110, 44, 21);
		docFrame.getContentPane().add(label_2);
		
		nameText = new JTextField();
		nameText.setBounds(139, 110, 162, 21);
		docFrame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		JLabel label_3 = new JLabel("\u79D1\u5BA4\uFF1A");
		label_3.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_3.setBounds(85, 145, 39, 21);
		docFrame.getContentPane().add(label_3);
		
		deptText = new JTextField();
		deptText.setBounds(139, 145, 162, 21);
		docFrame.getContentPane().add(deptText);
		deptText.setColumns(10);
		
		JLabel label_4 = new JLabel("\u5C31\u8BCA\u6570\uFF1A");
		label_4.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
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
				Statement ps=null;
				
				try {
					conn=DBManager.getConnect();
					ps=conn.createStatement();
					String sql="insert into Doctor values("+id+",'"+name+"','"+dept+"',"+patnum+");";
					try {
						ps.executeUpdate(sql);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
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
