package tww.admin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin {

	private JFrame welcomeFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.welcomeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		welcomeFrame = new JFrame();
		welcomeFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		welcomeFrame.setBounds(100, 100, 450, 350);
		welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeFrame.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("\u6B22\u8FCE\u6765\u5230\u7BA1\u7406\u7AEF");
		welcomeLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		welcomeLabel.setBounds(137, 10, 126, 33);
		welcomeFrame.getContentPane().add(welcomeLabel);
		
		JLabel chooseLabel = new JLabel("\u8BF7\u9009\u62E9\u60A8\u8981\u8FDB\u884C\u7684\u64CD\u4F5C");
		chooseLabel.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		chooseLabel.setBounds(137, 55, 134, 33);
		welcomeFrame.getContentPane().add(chooseLabel);
		
		JRadioButton addButton = new JRadioButton("\u6DFB\u52A0\u4FE1\u606F");
		addButton.setSelected(true);
		addButton.setBounds(161, 110, 121, 23);
		welcomeFrame.getContentPane().add(addButton);
		
		JRadioButton deleteButton = new JRadioButton("\u5220\u9664\u4FE1\u606F");
		deleteButton.setBounds(161, 151, 121, 23);
		welcomeFrame.getContentPane().add(deleteButton);
		
		JRadioButton alterButton = new JRadioButton("\u4FEE\u6539\u4FE1\u606F");
		alterButton.setBounds(161, 193, 121, 23);
		welcomeFrame.getContentPane().add(alterButton);
		
		JButton confirmButton = new JButton("\u786E\u5B9A");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addButton.isSelected()){
					AddMesg.addMesg();
				}else if(deleteButton.isSelected()){
					DeleteMesg.deleteMesg();
				}else if(alterButton.isSelected()){
					AlterMesg.alterMesg();
				}
				
			}
		});
		confirmButton.setBounds(93, 241, 70, 30);
		welcomeFrame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomeFrame.dispose();
			}
		});
		cancelButton.setBounds(238, 241, 70, 30);
		welcomeFrame.getContentPane().add(cancelButton);
	}
}
