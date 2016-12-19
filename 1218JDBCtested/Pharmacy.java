package tsh;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pharmacy {
	private static JTextField input;
	
	static Connection conn=JDBCUtil.getConnect();
	static Statement st=null;
	static ResultSet rs=null;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void pharmacy(){
		JFrame fr_p=new JFrame();
		fr_p.setTitle("\u836F\u623F\u7AEF");
		fr_p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr_p.setBounds(400, 300,350,350 );		
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
		
		JTextArea medicine = new JTextArea();
		center_panel.add(medicine);
		medicine.setRows(10);
		medicine.setColumns(25);
		medicine.setEditable(false);
		JButton btn_confirm = new JButton("\u786E\u5B9A");
		center_panel.add(btn_confirm);	
		JButton btn_cancel = new JButton("\u9000\u51FA");
		center_panel.add(btn_cancel);	
		
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
					JOptionPane.showMessageDialog(null, "请输入正确的电话号码！","输入错误",JOptionPane.ERROR_MESSAGE);
				else{
					ResultSet ms=null;
					String sql;
					try {
						st=conn.createStatement();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					sql="select Pat_feeName from Pat_charge where Pat_phone='"+pat_phone+"' and Pat_charged=0";
					try {
						rs=st.executeQuery(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if(!rs.next()){
							sql="select Pat_feename,Pro_num from Pat_charge,Medicine where Pat_feename=Med_name and Pat_phone='"+pat_phone+"'";
							ms=st.executeQuery(sql);
							if(ms.next()){
								medicine.setText("该病人的药品信息：");
								do{
									medicine.append("\n药品："+ms.getString("Pat_feename")+"  数量："+ms.getInt("Pro_num"));
								}while(ms.next());
								medicine.append("\n请取好您的药品！");
							}
							else medicine.setText("该病人不需取药或无该病人记录！");
						}
						else
							JOptionPane.showMessageDialog(null, "该病人有未缴费项目，请先去缴费！","未缴费",JOptionPane.ERROR_MESSAGE);
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally{
						JDBCUtil.close(rs);
						JDBCUtil.close(ms);
						JDBCUtil.close(st, conn);
					}
				}
			}	
		});
		JPanel south_panel=new JPanel();
		fr_p.getContentPane().add(south_panel, BorderLayout.SOUTH);	
		JLabel tip_output = new JLabel("");
		south_panel.add(tip_output);
		fr_p.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pharmacy();
	}

}
