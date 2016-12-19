package tsh;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Payment {
	private JTextField phone;
	private JTextArea project;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void payment_GUI(){
		JFrame frm=new JFrame();
		frm.setTitle("\u6536\u8D39");
		frm.setBounds(400, 300, 450, 400);
		frm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
		JPanel panel = new JPanel();
		frm.getContentPane().add(panel, BorderLayout.NORTH);	
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u75C5\u4EBA\u7535\u8BDD\u53F7\u7801\uFF1A");
		panel.add(label);
		phone = new JTextField();
		panel.add(phone);
		phone.setColumns(15);	
		JButton btn_search = new JButton("\u67E5\u8BE2");
		panel.add(btn_search);	
		JScrollPane scrollPane = new JScrollPane();
		frm.getContentPane().add(scrollPane, BorderLayout.CENTER);	
		project = new JTextArea();
		project.setText("\u6536\u8D39\u9879\u76EE\u4FE1\u606F\uFF1A");
		scrollPane.setViewportView(project);	
		JPanel panel_1 = new JPanel();
		frm.getContentPane().add(panel_1, BorderLayout.SOUTH);	
		JButton btn_pay = new JButton("\u7F34\u8D39");
		panel_1.add(btn_pay);	
		JButton btn_cancel = new JButton("\u53D6\u6D88");
		panel_1.add(btn_cancel);
		payment(btn_search,btn_pay,btn_cancel,frm);
		frm.setVisible(true);
	}
	
	public void payment(JButton search,JButton pay,JButton cancel,JFrame fr){
		
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!phone.getText().matches("\\d+"))
					JOptionPane.showMessageDialog(null, "请输入正确的电话号码！","输入错误",JOptionPane.ERROR_MESSAGE);
				/*else{
					String sql;
					ResultSet rs=null;
					DBManager db=new DBManager();
					int total=0;
					sql="select Pat_feename,Pro_number,Pat_proprice,sum=Pro_number*Pro_proprice"
							+ "from Pat_charge"
							+ "where Pat_phone='"+phone.getText().toString()+"' and Pat_charged=0";
					rs=db.getSet(sql);
					while(!rs.next()){
						project.append("\n项目/药品："+rs.getString("Pat_feename")+" 数量："+Integer.toString(rs.getInt("Pro_number"))+" 单价："+Integer.toString(rs.getInt("Pro_proprice"))+" 小计："+Integer.toString(rs.getInt("sum")));    
						total+=rs.getInt("sum");
					}
					project.append("\n总计:"+Integer.toString(total));
				}*/
			}	
		});
		
		pay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*String sql;
				ResultSet rs=null;
				DBManager db=new DBManager();
				sql="update charge_list set Pat_charged=1 where Pat_phone='"+phone.getText().toString()+"'";
				db.excuteSql(sql);
				JOptionPane.showMessageDialog(null, "完成缴费！","缴费成功",JOptionPane.INFORMATION_MESSAGE);
				rs.close();*/
			}
		});
		
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fr.dispose();
			}	
		});
	}

}
