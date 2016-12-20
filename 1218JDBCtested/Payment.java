package tsh;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Payment {
	private JTextField phone;
	private JTextArea project;
	float total=0;
	
	Connection conn=DBManager.getConnect();
	Statement st=null;
	ResultSet rs=null;

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
		project.setText("\u6536\u8D39\u9879\u76EE\u5982\u4E0B\uFF1A");
		project.setEditable(false);
		project.setRows(10);
		project.setColumns(20);
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
				else{
					String sql,p=phone.getText().toString();
					try {
						st=conn.createStatement();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sql="select Pat_feename,Pro_num,Pat_proprice,account=Pro_num*Pat_proprice "
							+ "from Pat_charge "
							+ "where Pat_phone='"+p+"' and Pat_charged=0";
					try {
						rs=st.executeQuery(sql);
						if(rs.next()){
							do{
								project.append("\n\n药品/项目："+rs.getString("Pat_feename")+"     数量："+Integer.toString(rs.getInt("Pro_num"))+"     单价："+Float.toString(rs.getFloat("Pat_proprice"))+"     小计："+Float.toString(rs.getFloat("account")));    
								total+=rs.getFloat("account");
							}while(rs.next());
						}
						else JOptionPane.showMessageDialog(null, "无收费项或无该病人记录！","无收费项",JOptionPane.INFORMATION_MESSAGE);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					project.append("\n\n总计:"+Float.toString(total));
				}
			}	
		});
		
		pay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(total!=0){
					String sql;
					sql="update Pat_charge set Pat_charged=1 where Pat_phone='"+phone.getText().toString()+"'";
					try {
						st=conn.createStatement();
						st.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally{
						JDBCUtil.close(st, conn);
					}
					project.setText("");
					phone.setText("");
					JOptionPane.showMessageDialog(null, "完成缴费！","缴费成功",JOptionPane.INFORMATION_MESSAGE);
				}
				else JOptionPane.showMessageDialog(null, "费用为0，不需缴费！","无需缴费",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBManager.close(rs, st, conn);
				fr.dispose();
			}	
		});
	}

}
