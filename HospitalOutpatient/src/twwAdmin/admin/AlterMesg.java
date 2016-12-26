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
		alterFrame.setBounds(500, 200, 450, 350);
		alterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		alterFrame.getContentPane().setLayout(null);
		
		ButtonGroup btnGroup=new ButtonGroup();
		JLabel label = new JLabel("\u4FEE\u6539\u4FE1\u606F");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(168, 10, 80, 30);
		alterFrame.getContentPane().add(label);
		
		JRadioButton accoButton = new JRadioButton("\u4FEE\u6539\u8D26\u53F7");
		accoButton.setSelected(true);
		accoButton.setBounds(156, 66, 121, 23);
		alterFrame.getContentPane().add(accoButton);
		btnGroup.add(accoButton);
		
		JRadioButton docButton = new JRadioButton("\u4FEE\u6539\u533B\u751F\u79D1\u5BA4");
		docButton.setBounds(156, 107, 121, 23);
		alterFrame.getContentPane().add(docButton);
		btnGroup.add(docButton);
		
		JRadioButton medButton = new JRadioButton("\u4FEE\u6539\u836F\u54C1");
		medButton.setBounds(156, 151, 121, 23);
		alterFrame.getContentPane().add(medButton);
		btnGroup.add(medButton);
		
		JRadioButton proButton = new JRadioButton("\u4FEE\u6539\u6536\u8D39\u9879\u76EE");
		proButton.setBounds(156, 196, 121, 23);
		alterFrame.getContentPane().add(proButton);
		btnGroup.add(proButton);
		
		JButton confirmButton = new JButton("\u786E\u5B9A");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accoButton.isSelected()){
					AlterAcco.alterAcco();  				//修改账号
				}else if(docButton.isSelected()){
					AlterDoc.alterDoc();					//修改医生
				}else if(medButton.isSelected()){
					AlterMed.alterMed();  					//修改药品
				}else if(proButton.isSelected()){
					AlterPro.alterPro();					//修改收费项目
				}
			}
		});
		confirmButton.setBounds(78, 251, 70, 30);
		alterFrame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterFrame.setVisible(false);;
			}
		});
		cancelButton.setBounds(279, 251, 70, 30);
		alterFrame.getContentPane().add(cancelButton);
		
		
	}

}
