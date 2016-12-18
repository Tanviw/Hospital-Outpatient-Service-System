package tww.admin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMesg {

	private JFrame addFrame;

	/**
	 * Launch the application.
	 */
	public static void addMesg() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMesg window = new AddMesg();
					window.addFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddMesg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addFrame = new JFrame();
		addFrame.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662");
		addFrame.setBounds(100, 100, 450, 350);
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6DFB\u52A0\u4FE1\u606F");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label.setBounds(172, 23, 84, 32);
		addFrame.getContentPane().add(label);
		
		JRadioButton docButton = new JRadioButton("\u6DFB\u52A0\u533B\u751F\u79D1\u5BA4");
		docButton.setSelected(true);
		docButton.setBounds(153, 90, 121, 23);
		addFrame.getContentPane().add(docButton);
		
		JRadioButton medButton = new JRadioButton("\u6DFB\u52A0\u836F\u54C1");
		medButton.setBounds(153, 140, 121, 23);
		addFrame.getContentPane().add(medButton);
		
		JRadioButton proButton = new JRadioButton("\u6DFB\u52A0\u6536\u8D39\u9879\u76EE");
		proButton.setBounds(153, 190, 121, 23);
		addFrame.getContentPane().add(proButton);
		
		JButton confirmButton = new JButton("\u786E\u5B9A");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(docButton.isSelected()){
					AddDoc.addDoc();
				}else if(medButton.isSelected()){
					
				}else if(proButton.isSelected()){
					
				}
			}
		});
		confirmButton.setBounds(91, 252, 70, 30);
		addFrame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFrame.setVisible(false);
			}
		});
		cancelButton.setBounds(245, 252, 70, 30);
		addFrame.getContentPane().add(cancelButton);
	}

}
