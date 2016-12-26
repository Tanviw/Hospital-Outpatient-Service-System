package tsh;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import cn.edu.usst.yff.Director;
import tww.admin.Admin;
import tww.pool.DBManager;
import wyqoctor.DoctorVisit;

public class Login {
	private static JTextField account;
	private static String [] opt={"","药房","挂号收费","医生","管理员","院长"};
	private static JComboBox<String> dept;
	
	private static Connection conn=DBManager.getConnect();
	private static Statement st=null;
	private static ResultSet rs=null;
	private static JPasswordField pwd;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void login_GUI(){
		JFrame frm=new JFrame();
		frm.setTitle("\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662\u2014\u2014\u5BA2\u6237\u7AEF\u767B\u5F55");
		frm.setBounds(400, 200,350,300);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(new BoxLayout(frm.getContentPane(), BoxLayout.Y_AXIS));		
		JPanel panel = new JPanel();
		frm.getContentPane().add(panel);		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5168\u4E16\u754C\u6700\u597D\u7684\u533B\u9662\u5BA2\u6237\u7AEF\uFF0C\u8BF7\u5148\u767B\u5F55");
		panel.add(label);		
		JPanel panel_1 = new JPanel();
		frm.getContentPane().add(panel_1);		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		panel_1.add(label_1);		
		account = new JTextField();
		panel_1.add(account);
		account.setColumns(15);	
		JPanel panel_2 = new JPanel();
		frm.getContentPane().add(panel_2);	
		JLabel label_2 = new JLabel("    \u5BC6\u7801\uFF1A");
		panel_2.add(label_2);
		
		pwd = new JPasswordField();
		pwd.setColumns(15);
		panel_2.add(pwd);
		JPanel panel_3 = new JPanel();
		frm.getContentPane().add(panel_3);	
		JLabel label_3 = new JLabel("\u90E8\u95E8\uFF1A");
		panel_3.add(label_3);	
		dept = new JComboBox<String>(opt);
		dept.setMaximumRowCount(15);
		panel_3.add(dept);	
		JPanel panel_4 = new JPanel();
		frm.getContentPane().add(panel_4);	
		JButton confirm = new JButton("\u767B\u5F55");
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login(frm);
			}	
		});
		panel_4.add(confirm);	
		JButton cancel = new JButton("\u53D6\u6D88");
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBManager.close(rs, st, conn);
				frm.dispose();
			}		
		});
		panel_4.add(cancel);	
		frm.setVisible(true);
	}
	
	public static void login(JFrame frm){
		char [] p=pwd.getPassword();
		String pass=new String(p);
		if(account.getText().length()==0||pass.equals("")||dept.getSelectedItem().equals(""))
			JOptionPane.showMessageDialog(null, "请将登录信息填写完整！","信息遗漏",JOptionPane.ERROR_MESSAGE);
		else{
			String sql;
			int index=dept.getSelectedIndex();
			sql="select * from Account "
					+ "where Department='"+dept.getSelectedItem()+"' and Account="+Integer.parseInt(account.getText().toString())+" and Password='"+pass+"'" ;
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				if(rs.next()){  
					switch(index){
					    case 1: Pharmacy.main(null);
					            DBManager.close(rs, st, conn);
					            frm.dispose();
					            break;
					    case 2: Reg_Menu.main(null);
					            DBManager.close(rs, st, conn);
					            frm.dispose();
					            break;
					    case 3: DoctorVisit.main(null);
					              DBManager.close(rs,st,conn);
			                   break; 
					    case 4: Admin.main(null);
					           DBManager.close(rs,st,conn);						
                                             break;
					    case 5: Director.main(null);
					    DBManager.close(rs,st,conn);					
                                                  break;
					    default:break;
					}
				}
				else{  
					JOptionPane.showMessageDialog(null, "账号、密码及部门不匹配，请检查您的输入！","登录失败",JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		login_GUI();
	}
}










