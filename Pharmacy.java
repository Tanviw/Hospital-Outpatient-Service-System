package tsh;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Pharmacy {
	private static JTextField input;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void pharmacy(){
		JFrame fr_p=new JFrame();
		fr_p.setTitle("\u836F\u623F\u7AEF");
		fr_p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr_p.setBounds(400, 300,350,200 );		
		JPanel north_panel = new JPanel();
		fr_p.getContentPane().add(north_panel, BorderLayout.NORTH);	
		JLabel welcome = new JLabel("\u836F\u623F\u5BA2\u6237\u7AEF");
		north_panel.add(welcome);	
		JPanel center_panel = new JPanel();
		fr_p.getContentPane().add(center_panel, BorderLayout.CENTER);	
		JLabel tip_input = new JLabel("\u8BF7\u8F93\u5165\u75C5\u4EBA\u53F7\u7801\uFF1A");
		center_panel.add(tip_input);	
		input = new JTextField();
		center_panel.add(input);
		input.setColumns(15);	
		JButton btn_confirm = new JButton("\u786E\u5B9A");
		center_panel.add(btn_confirm);	
		JButton btn_cancel = new JButton("\u9000\u51FA");
		center_panel.add(btn_cancel);	
		JPanel south_panel=new JPanel();
		fr_p.getContentPane().add(south_panel, BorderLayout.SOUTH);	
		JLabel tip_output = new JLabel("");
		south_panel.add(tip_output);
	
		btn_cancel.addActionListener(new ActionListener(){  //点击取消按钮则关闭窗口
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fr_p.dispose();
			}
		});
		
		btn_confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pat_phone=input.getText().toString();
				if(pat_phone.equals("")||!pat_phone.matches("\\d*"))
					JOptionPane.showMessageDialog(null, "请输入正确的电话号码！","未缴费",JOptionPane.ERROR_MESSAGE);
				/*else{
					DBmanager db=new DBmanager(); //数据库操作类的对象
					ResultSet rs=null;
					String sql;
					sql="select Pat_feeName from Pat_charge where Pat_phone='"+pat_phone+"' and Pat_charged=0";
					rs=db.getSet(sql); //得到结果集
					if(!rs.last())
						JOptionPane.showMessageDialog(null, "请拿好您的药品！","取药成功",JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "请先去缴费！","未缴费",JOptionPane.ERROR_MESSAGE);
				}*/
			}	
		});
		fr_p.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pharmacy();

	}

}
