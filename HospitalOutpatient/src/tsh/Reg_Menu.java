package tsh;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Reg_Menu {

	private static Registration registration;
	private static Payment payment;
	
	public static void menu(){
		JFrame frm=new JFrame();
		frm.setTitle("\u6536\u8D39\u6302\u53F7\u7AEF");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setBounds(400, 300,400,200 );
		
		JMenuBar mb = new JMenuBar();
		frm.setJMenuBar(mb);
		
		JMenu mnNewMenu = new JMenu("\u5F00\u59CB");
		mb.add(mnNewMenu);
		
		JMenuItem reg = new JMenuItem("\u6302\u53F7");
		reg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				registration=new Registration();
				registration.reg_GUI();
			}	
		});
		mnNewMenu.add(reg);
		
		JMenuItem pay = new JMenuItem("\u6536\u8D39");
		pay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payment=new Payment();
				payment.payment_GUI();
			}
		});
		mnNewMenu.add(pay);
		
		JMenu exit = new JMenu("\u9000\u51FA");
		mb.add(exit);
		
		JMenuItem quit = new JMenuItem("\u9000\u51FA");
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frm.dispose();
			}		
		});
		exit.add(quit);
		
		JLabel lblNewLabel = new JLabel("          \u6B22\u8FCE\u4F7F\u7528\u6536\u8D39\u6302\u53F7\u5BA2\u6237\u7AEF");
		lblNewLabel.setFont(new Font("¿¬Ìå", Font.BOLD, 15));
		frm.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		frm.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}
	
}
