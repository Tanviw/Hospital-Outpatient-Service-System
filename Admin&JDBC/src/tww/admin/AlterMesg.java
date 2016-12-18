package tww.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterMesg {

	private JFrame alterFrame;

	/**
	 * Launch the application.
	 */
	public static void alterMesg() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterMesg window = new AlterMesg();
					window.alterFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AlterMesg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alterFrame = new JFrame();
		alterFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		alterFrame.setBounds(100, 100, 450, 350);
		alterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		alterFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4FEE\u6539\u4FE1\u606F");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label.setBounds(166, 28, 80, 30);
		alterFrame.getContentPane().add(label);
		
		JRadioButton docButton = new JRadioButton("\u4FEE\u6539\u533B\u751F\u79D1\u5BA4");
		docButton.setSelected(true);
		docButton.setBounds(156, 90, 121, 23);
		alterFrame.getContentPane().add(docButton);
		
		JRadioButton medButton = new JRadioButton("\u4FEE\u6539\u836F\u54C1");
		medButton.setBounds(156, 140, 121, 23);
		alterFrame.getContentPane().add(medButton);
		
		JRadioButton proButton = new JRadioButton("\u4FEE\u6539\u6536\u8D39\u9879\u76EE");
		proButton.setBounds(156, 190, 121, 23);
		alterFrame.getContentPane().add(proButton);
		
		JButton confirmButton = new JButton("\u786E\u5B9A");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(docButton.isSelected()){
					
				}else if(medButton.isSelected()){
					
				}else if(proButton.isSelected()){
					
				}
			}
		});
		confirmButton.setBounds(105, 251, 70, 30);
		alterFrame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterFrame.setVisible(false);;
			}
		});
		cancelButton.setBounds(241, 251, 70, 30);
		alterFrame.getContentPane().add(cancelButton);
	}

}
