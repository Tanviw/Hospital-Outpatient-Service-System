import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tww.pool.DBManager;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DoctorVisit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int did;
	public String pname,phone;
	private JTextArea textArea,textArea_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel label_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorVisit frame = new DoctorVisit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DoctorVisit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField_5 = new JTextField();
		textField_5.setBounds(157, 121, 86, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(122, 10, 25, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(157, 7, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 55, 78, 181);
		contentPane.add(textArea);
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(383, 35, 129, 181);
		contentPane.add(textArea_1);
		textField_4 = new JTextField();
		textField_4.setBounds(157, 183, 86, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_3 = new JTextField();
		textField_3.setBounds(157, 87, 86, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		JLabel label_7 = new JLabel("");
		label_7.setBounds(212, 219, 95, 30);
		contentPane.add(label_7);
		JButton button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String s=textField.getText();
				
				int d=Integer.parseInt(s);
				did=d;
				try {
					String sql="select * from Patient_queue where Doc_id=? and Pat_num=(select min(s.Pat_num) from (select * from Patient_queue where Doc_id=?) as s)";
							
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql);
					ps.setInt(1,d);
					ps.setInt(2,d);
					try {
						rs=ps.executeQuery();
						
					} catch (SQLException e6) {
						e6.printStackTrace();
					}
					try {
						while(rs.next()){
							//System.out.println(rs.getString("Pat_name"));
							//String sss=rs.getString("Pat_name");
					textArea.append("姓名"+rs.getString("Pat_name"));
					textArea.append("\n");
					textArea.append("性别"+rs.getString("Pat_sex"));
					textArea.append("\n");
					textArea.append("年龄"+rs.getInt("Pat_age"));
					textArea.append("\n");
					pname=rs.getString("Pat_name");
					phone=rs.getString("Pat_phone");//得到当前病人的姓名和电话
					int a=rs.getInt("Pat_num");
					int m=0;
					String sql1="delete from Patient_queue where cast(Pat_phone as varchar(50))=?";
					conn=DBManager.getConnect();
					ps=conn.prepareStatement(sql1);
					ps.setString(1,phone);
					m=ps.executeUpdate();
					if(m==0){
						System.out.println("删除错误");
					}
					else System.out.println("删除成功");
				//删除病人排队表里该病人的那一行
			}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} catch (Exception e5) {
					e5.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
				//label1.setText("");
				//jp.setVisible(false);
				//button1.setVisible(false);
			}
				
			}
		);
		button.setBounds(270, 7, 66, 21);
		contentPane.add(button);
		
		
		
		JLabel label_1 = new JLabel("\u836F\u540D\u7B80\u7801");
		label_1.setBounds(81, 59, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 56, 86, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("\u67E5\u627E");
		button_1.addActionListener(new ActionListener() {

			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s=textField_1.getText();//药品简码
				int m=0;
				String s1=textField_5.getText();                                                                                                                                                        
				int d=Integer.parseInt(s1);//药品数量
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try {
					conn=DBManager.getConnect();
					//ps=conn.PreparedStatement();
					String sql="select *from Medicine where cast(Med_bfcode as varchar(50))=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1,s);
					try {
						rs=ps.executeQuery();
						
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
					try{
						if(rs.next()){
							if(d>rs.getInt("Med_count")){
								label_7.setText("库存量不足请换药");	
							}
							else{
								int a=rs.getInt("Med_count")-d;
								textArea_1.append("药名"+rs.getString("Med_name"));
								textArea_1.append("数量"+d);
								textArea_1.append("\n");
								String sql1="update Medicine set Med_count=? where cast(Med_bfcode as varchar(50))=? ";
								ps=conn.prepareStatement(sql1);
								ps.setInt(1,a);
								ps.setString(2,s);//更改药品库存量
								m=ps.executeUpdate();
								String s2=rs.getString("Med_name");
								float f=rs.getFloat("Med_price");
								
								String sql2="insert into Pat_charge values(?,?,?,?,?,?,?)";
								conn=DBManager.getConnect();
								ps=conn.prepareStatement(sql2);
								ps.setString(1, phone);
								ps.setString(2, pname);
								ps.setString(3, s2);//把药品添加到病人收费表
								ps.setInt(4, d);
								ps.setFloat(5,f);
								ps.setBoolean(6, false);
								ps.setInt(7,did);
								m=ps.executeUpdate();
								if (m==0){
									label_7.setText("简码不存在");
									System.out.println("添加药品错误");
								}
								
								else System.out.println("添加药品正确");
							}		  
							
						}
					else {
							label_7.setText("简码不存在");
							
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
				textField_1.setText("");
				textField_5.setText("");	
			}}
			);
		button_1.setBounds(270, 55, 66, 23);
		contentPane.add(button_1);
		
		JLabel label_2 = new JLabel("\u68C0\u67E5\u9879\u76EE\u7B80\u7801");
		label_2.setBounds(81, 149, 78, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(157, 146, 86, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton button_2 = new JButton("\u67E5\u627E");//检查项目简码查询
		button_2.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				String s=textField_2.getText();//检查项目名
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				int m;
				try {
					conn=DBManager.getConnect();
					String sql="select *from Charge_list where cast(Pro_bfcode as varchar(50))=?";
					ps=conn.prepareStatement(sql);
					//String sql="select *from Charge_list where Pro_bfcode=?";
					ps.setString(1,s);
					
					try {
						rs=ps.executeQuery();
						
					} catch (SQLException e4) {
						e4.printStackTrace();
					}
					try{
					
					if(rs.next()){
						textArea_1.append(rs.getString("Pro_name"));//输出检查项目的名称
						textArea_1.append("\n");
						String s1=rs.getString("Pro_name");
						float f=rs.getFloat("Pro_price");
						
						String sql2="insert into Pat_charge values(?,?,?,?,?,?,?)";
						conn=DBManager.getConnect();
						ps=conn.prepareStatement(sql2);
						ps.setString(1, phone);
						ps.setString(2, pname);
						ps.setString(3, s1);//把药品添加到病人收费表
						ps.setInt(4, 1);
						ps.setFloat(5,f);
						ps.setBoolean(6, false);
						ps.setInt(7,did);
						m=ps.executeUpdate();
					}
					else {
						label_7.setText("简码不存在");
					}
					}
					 catch (SQLException e1) {
							e1.printStackTrace();
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}finally{
						DBManager.close(rs,ps,conn);
					}
				textField_2.setText("");
				
			}
			}
		);
		button_2.setBounds(270, 145, 66, 23);
		contentPane.add(button_2);
		
		
		
		JLabel label_3 = new JLabel("\u75C5\u4EBA\u75C5\u5386\u5355");
		label_3.setBounds(408, 10, 86, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u836F\u54C1\u540D\u79F0");
		label_4.setBounds(81, 98, 66, 15);
		contentPane.add(label_4);
		
		
		
		JLabel label_5 = new JLabel("\u68C0\u67E5\u9879\u76EE\u540D\u79F0");
		label_5.setBounds(81, 186, 78, 15);
		contentPane.add(label_5);
		
		
		
		JButton button_3 = new JButton("\u67E5\u627E");
		button_3.addActionListener(new ActionListener() {//药品名称查询
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				String s=textField_3.getText();//药品名称
				String s1=textField_5.getText();                                                                                                                                                        
				int d=Integer.parseInt(s1);//药品数量
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				int m;
				try {
					conn=DBManager.getConnect();
					//ps=conn.PreparedStatement();
					String sql="select *from Medicine where Med_name=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1,s);
					try {
						rs=ps.executeQuery();
						
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
					try{
						if(rs.next()){
							if(d>rs.getInt("Med_count")){
								label_7.setText("库存量不足请换药");	
							}
							else{
								int a=rs.getInt("Med_count")-d;
								textArea_1.append("药名"+rs.getString("Med_name"));
								textArea_1.append("数量"+d);
								textArea_1.append("\n");
								String sql1="update Medicine set Med_count=? where Med_name=? ";
								conn=DBManager.getConnect();
								ps=conn.prepareStatement(sql1);
								ps.setInt(1,a);
								ps.setString(2,s);//更改药品库存量
								m=ps.executeUpdate();
								String s2=rs.getString("Med_name");
								float f=rs.getFloat("Med_price");
								
								String sql2="insert into Pat_charge values(?,?,?,?,?,?,?)";
								conn=DBManager.getConnect();
								ps=conn.prepareStatement(sql2);
								ps.setString(1, phone);
								ps.setString(2, pname);
								ps.setString(3, s2);//把药品添加到病人收费表
								ps.setInt(4, d);
								ps.setFloat(5,f);
								ps.setBoolean(6, false);
								ps.setInt(7,did);
								m=ps.executeUpdate();
								
							}
								  
							
						}
						else {
							label_7.setText("简码不存在");
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}finally{
					DBManager.close(rs,ps,conn);
				}
				textField_1.setText("");
				textField_5.setText("");
			}
				
			
			}
		);
		button_3.setBounds(270, 94, 66, 23);
		contentPane.add(button_3);
		JButton button_4 = new JButton("\u67E5\u627E");
		button_4.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				String s=textField_4.getText();//检查项目名
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				int m;
				try {
					conn=DBManager.getConnect();
					String sql="select *from Charge_list where Pro_name=?";
					ps=conn.prepareStatement(sql);
					//String sql="select *from Charge_list where Pro_bfcode=?";
					ps.setString(1,s);
					
					try {
						rs=ps.executeQuery();
						
					} catch (SQLException e4) {
						e4.printStackTrace();
					}
					try{
					
					if(rs.next()){
						textArea_1.append(rs.getString("Pro_name"));//输出检查项目的名称
						textArea_1.append("\n");
						//String s1=rs.getString("Pro_bfcode");
						float f=rs.getFloat("Pro_price");
						
						String sql2="insert into Pat_charge values(?,?,?,?,?,?,?)";
						conn=DBManager.getConnect();
						ps=conn.prepareStatement(sql2);
						ps.setString(1, phone);
						ps.setString(2, pname);
						ps.setString(3, s);//把药品添加到病人收费表
						ps.setInt(4, 1);
						ps.setFloat(5,f);
						ps.setBoolean(6, false);
						ps.setInt(7,did);
						m=ps.executeUpdate();
					}
					else {
						label_7.setText("简码不存在");
					}
					}
					 catch (SQLException e1) {
							e1.printStackTrace();
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}finally{
						DBManager.close(rs,ps,conn);
					}
				textField_4.setText("");
				
			}	
			
		});
		button_4.setBounds(270, 177, 66, 23);
		contentPane.add(button_4);
		
		JLabel label_6 = new JLabel("\u6570\u91CF");
		label_6.setBounds(81, 124, 54, 15);
		contentPane.add(label_6);
		JButton button_5 = new JButton("\u786E\u5B9A");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
				textArea.setText("");
			}
		});
		button_5.setBounds(401, 226, 93, 23);
		contentPane.add(button_5);
	}
}
