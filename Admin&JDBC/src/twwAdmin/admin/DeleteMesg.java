package tww.admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class DeleteMesg {

	private JFrame deleteFrame;

	/**
	 * Launch the application.
	 */
	public static void deleteMesg() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteMesg window = new DeleteMesg();
					window.deleteFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteMesg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		deleteFrame = new JFrame();
		deleteFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		deleteFrame.setBounds(500, 200, 450, 350);
		deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deleteFrame.getContentPane().setLayout(null);
		
		ButtonGroup btnGroup=new ButtonGroup();
		JLabel label = new JLabel("\u5220\u9664\u4FE1\u606F");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label.setBounds(168, 33, 75, 27);
		deleteFrame.getContentPane().add(label);
		
		JRadioButton accoButton = new JRadioButton("\u5220\u9664\u8D26\u53F7");
		accoButton.setSelected(true);
		accoButton.setBounds(156, 75, 121, 23);
		deleteFrame.getContentPane().add(accoButton);
		btnGroup.add(accoButton);
		
		JRadioButton docButton = new JRadioButton("\u5220\u9664\u533B\u751F\u79D1\u5BA4");
		docButton.setBounds(156, 115, 121, 23);
		deleteFrame.getContentPane().add(docButton);
		btnGroup.add(docButton);
		
		JRadioButton medButton = new JRadioButton("\u5220\u9664\u836F\u54C1");
		medButton.setBounds(156, 154, 121, 23);
		deleteFrame.getContentPane().add(medButton);
		btnGroup.add(medButton);
		
		JRadioButton proButton = new JRadioButton("\u5220\u9664\u6536\u8D39\u9879\u76EE");
		proButton.setBounds(156, 190, 121, 23);
		deleteFrame.getContentPane().add(proButton);
		btnGroup.add(proButton);
		
		JButton confirmButton = new JButton("\u786E\u5B9A");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accoButton.isSelected()){
					DeleteAcco.deleteAcco(); 				//É¾³ýÕËºÅ
				}else if(docButton.isSelected()){
					DeleteDoc.deleteDoc(); 					//É¾³ýÒ½Éú
				}else if(medButton.isSelected()){
					DeleteMed.deleteMed(); 					//É¾³ýÒ©Æ·
				}else if(proButton.isSelected()){
					DeletePro.deletePro(); 					//É¾³ýÊÕ·ÑÏîÄ¿
				}
			}
		});
		confirmButton.setBounds(95, 248, 70, 30);
		deleteFrame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteFrame.setVisible(false);;
			}
		});
		cancelButton.setBounds(231, 248, 70, 30);
		deleteFrame.getContentPane().add(cancelButton);
		
	}

}
