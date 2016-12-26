package tsh;

import javax.swing.*;

import tww.pool.DBManager;

import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registration {
	private JTextField phone_search;
	private JTextField name;
	private JTextField sex;
	private JTextField phone;
	private JTextField dept;
	private JTextField orderTime;
	private JTextField pat_id;
	private JTextField doctor;
	private JTextField doc_id;
	private JTextField age;
	
	private Connection conn=DBManager.getConnect();
	private Statement st=null;
	private ResultSet rs=null;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void reg_GUI(){
		JFrame frm=new JFrame();
		frm.setTitle("\u6302\u53F7");
		frm.setBounds(400, 200, 450, 450);
		frm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frm.getContentPane().setLayout(new BoxLayout(frm.getContentPane(), BoxLayout.Y_AXIS));
		JPanel panel = new JPanel();
		frm.getContentPane().add(panel);
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u75C5\u4EBA\u7684\u7535\u8BDD\u53F7\u7801\uFF1A");
		panel.add(label);	
		phone_search = new JTextField();
		panel.add(phone_search);
		phone_search.setColumns(15);	
		JButton btn_search = new JButton("\u67E5\u627E");
		panel.add(btn_search);	
		JPanel panel_1 = new JPanel();
		frm.getContentPane().add(panel_1);	
		JLabel label_1 = new JLabel("\u75C5\u4EBA\u4FE1\u606F");
		panel_1.add(label_1);	
		JPanel panel_2 = new JPanel();
		frm.getContentPane().add(panel_2);
		JLabel label_2 = new JLabel("         \u59D3\u540D\uFF1A");
		panel_2.add(label_2);
		name = new JTextField();
		panel_2.add(name);
		name.setColumns(15);
		JPanel panel_3 = new JPanel();
		frm.getContentPane().add(panel_3);
		JLabel lblNewLabel = new JLabel("         \u6027\u522B\uFF1A");
		panel_3.add(lblNewLabel);
		sex = new JTextField();
		panel_3.add(sex);
		sex.setColumns(15);	
		JPanel panel_4 = new JPanel();
		frm.getContentPane().add(panel_4);
		JLabel label_5 = new JLabel("         \u5E74\u9F84\uFF1A");
		panel_4.add(label_5);
		age = new JTextField();
		panel_4.add(age);
		age.setColumns(15);
		JPanel panel_5 = new JPanel();
		frm.getContentPane().add(panel_5);	
		JLabel lblNewLabel_1 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		panel_5.add(lblNewLabel_1);
		phone = new JTextField();
		panel_5.add(phone);
		phone.setColumns(15);	
		JPanel panel_6 = new JPanel();
		frm.getContentPane().add(panel_6);	
		JLabel lblNewLabel_2 = new JLabel("\u5C31\u8BCA\u79D1\u5BA4\uFF1A");
		panel_6.add(lblNewLabel_2);
		dept = new JTextField();
		panel_6.add(dept);
		dept.setColumns(15);	
		JPanel panel_7 = new JPanel();
		frm.getContentPane().add(panel_7);
		JLabel label_3 = new JLabel("\u9884\u7EA6\u65F6\u95F4\uFF1A");
		panel_7.add(label_3);
		orderTime = new JTextField();
		panel_7.add(orderTime);
		orderTime.setText("\u65E0");
		orderTime.setEditable(false);
		orderTime.setColumns(15);
		JPanel panel_8 = new JPanel();
		frm.getContentPane().add(panel_8);
		JLabel lblNewLabel_3 = new JLabel("       \u770B\u75C5\u53F7\uFF1A");
		panel_8.add(lblNewLabel_3);
		pat_id = new JTextField();
		panel_8.add(pat_id);
		pat_id.setEditable(false);
		pat_id.setText("");
		pat_id.setColumns(10);
		JButton btn_getId = new JButton("\u53D6\u53F7");
		panel_8.add(btn_getId);
		JPanel panel_9 = new JPanel();
		frm.getContentPane().add(panel_9);			
		JLabel label_4 = new JLabel("\u5C31\u8BCA\u533B\u751F\uFF1A");
		panel_9.add(label_4);
		doctor = new JTextField();
		panel_9.add(doctor);
		doctor.setEditable(false);
		doctor.setColumns(15);
		JPanel panel_10 = new JPanel();
		frm.getContentPane().add(panel_10);
		JLabel lblid = new JLabel("      \u533B\u751Fid\uFF1A");
		panel_10.add(lblid);
		doc_id = new JTextField();
		panel_10.add(doc_id);
		doc_id.setEditable(false);
		doc_id.setColumns(15);	
		JPanel panel_11 = new JPanel();
		frm.getContentPane().add(panel_11);
		JButton btn_confirm = new JButton("\u786E\u8BA4");
		panel_11.add(btn_confirm);
		JButton btn_cancel = new JButton("\u53D6\u6D88");
		panel_11.add(btn_cancel);
		registration(btn_search,btn_getId,btn_confirm,btn_cancel,frm);
		frm.setVisible(true);
	}
	
	public void registration(JButton search,JButton id,JButton confirm,JButton cancel,JFrame fr){
		
		search.addActionListener(new ActionListener(){  //判断该病人是否已预约
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jdgOrder();
			}
		});
		
		cancel.addActionListener(new ActionListener(){ //点击取消，关闭窗口
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DBManager.close(rs, st, conn);
				fr.dispose();
			}	
		});
		
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handout();
			}			
		});
		
		id.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				getID();
			}		
		});
		
	}
	
	public void jdgOrder(){ //判断该病人是否预约
		String p_Num=phone_search.getText().toString();
		if(!p_Num.matches("\\d+")){
			JOptionPane.showMessageDialog(null, "请输入正确的电话号码！","输入错误",JOptionPane.ERROR_MESSAGE);
		}
		else{
			String sql;
			sql="select * from Pat_order where cast(Pat_phone as varchar(100))='"+p_Num+"'";
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				if(!rs.next())
					JOptionPane.showMessageDialog(null, "该病人无预约记录,请输入病人信息！","搜索结果",JOptionPane.INFORMATION_MESSAGE);
				else{
					name.setText(rs.getString("Pat_name"));
					sex.setText(rs.getString("Pat_sex"));
					age.setText(rs.getString("Pat_age"));
					dept.setText(rs.getString("Order_dept"));
					phone.setText(rs.getString("Pat_phone"));
					orderTime.setText(rs.getString("Order_time"));		
				}
				phone_search.setText("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void handout(){ 
		if(name.getText().length()==0||sex.getText().length()==0||phone.getText().length()==0||dept.getText().length()==0||pat_id.getText().length()==0||doctor.getText().length()==0||!age.getText().matches("\\d+")){
			JOptionPane.showMessageDialog(null, "病人信息没有填写完整或病人信息格式错误！","信息遗漏",JOptionPane.ERROR_MESSAGE);
		}
		else{
			String sql1,sql2,sql3,sql4;
			int patient=Integer.parseInt(pat_id.getText().toString()),doctorid=Integer.parseInt(doc_id.getText().toString()),pa=Integer.parseInt(age.getText().toString());
			sql1="insert into Patient_queue (Pat_name,Pat_sex,Pat_num,Doc_id,Pat_phone,Pat_age) "
					+ "values ('"+name.getText()+"','"+sex.getText()+"',"+patient+","+doctorid+",'"+phone.getText()+"',"+pa+")";    
			sql2="update Doctor "
					+ "set Doc_patnum=Doc_patnum+1 "
					+ "where Doc_id="+doctorid;	
			sql3="insert into Pat_charge (Pat_name,Pat_feename,Pro_num,Pat_proprice,Pat_charged,Pat_phone,Pat_docid) "
					+ "values ( '"+name.getText()+"','挂号费',1,18,1,'"+phone.getText()+"',"+doctorid+")";
			sql4="delete from Pat_order where cast(Pat_phone as varchar(50))='"+phone.getText()+"'";
			try {
				st=conn.createStatement();
				st.executeUpdate(sql1);
				st.executeUpdate(sql2);
				st.executeUpdate(sql3);
				st.executeUpdate(sql4);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "挂号成功，看病号是"+pat_id.getText(),"挂号成功",JOptionPane.INFORMATION_MESSAGE);
			phone_search.setText("");
			name.setText("");
			sex.setText("");
			phone.setText("");
			dept.setText("");
			orderTime.setText("无");
			pat_id.setText("");
			doctor.setText("");
			doc_id.setText("");
			age.setText("");
		}
	}
	
	public void getID(){
		if(name.getText().length()==0||sex.getText().length()==0||!phone.getText().matches("\\d+")||dept.getText().length()==0||!age.getText().matches("\\d+")){
			JOptionPane.showMessageDialog(null, "病人信息没有填写完整或病人信息格式错误！","信息遗漏",JOptionPane.ERROR_MESSAGE);
		}
		else{
			String order=orderTime.getText().toString();
			String sql;
			double dif=2;
			if(!order.equals("无")){ //计算时间差
				SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date cur=new Date(System.currentTimeMillis());
				String present=formatter.format(cur);
				try {
					Date d1=formatter.parse(order);
					Date d2=formatter.parse(present);
					dif=( d1.getTime()-d2.getTime() )/(1000.0*60.0*60.0);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("时间出错！");
				}
			}
			//查找空闲的医生_没有病人的医生
			try {
				st=conn.createStatement();
				sql="select Doc_id,Doc_name "
						+ "from Doctor "
						+ "where Doc_id not in ( "
						+ "select Doc_id from Patient_queue) "
						+ "and cast(Doc_dept as varchar(50))='"+dept.getText().toString()+"'";
				rs=st.executeQuery(sql);
				if(rs.next()){
					pat_id.setText("1");
					doctor.setText(rs.getString("Doc_name"));
					doc_id.setText(rs.getString("Doc_id"));
				}
				//全部医生都有病人,查找最空闲的医生
				else{ 
					sql="select docid "
						+ "from ( "
							+ "select Patient_queue.Doc_id as docid,count(Patient_queue.Doc_id) as num "
							+ "from Doctor,Patient_queue "
							+ "where cast(Doc_dept as varchar(50))='"+dept.getText().toString()+"' and Doctor.Doc_id=Patient_queue.Doc_id "
							+ "group by Patient_queue.Doc_id) as A "
						+ "where num=( "
						    + "select min(B.n) "
						    + "from ( "
						       + "select count(Patient_queue.Doc_id) as n "
						       + "from Doctor,Patient_Queue "
						       + "where cast(Doc_dept as varchar(50))='"+dept.getText().toString()+"' and Doctor.Doc_id=Patient_queue.Doc_id "
						       + "group by Doctor.Doc_id ) as B)";
					rs=st.executeQuery(sql);
					rs.next();
					doc_id.setText(Integer.toString(rs.getInt("docid")));
					sql="select Doc_name from Doctor where Doc_id="+Integer.parseInt(doc_id.getText().toString());
					rs=st.executeQuery(sql);
					rs.next();
					doctor.setText(rs.getString("Doc_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(pat_id.getText().length()==0&&(order.equals("无")||dif>1||dif<-1)){
				sql="select MAX(Pat_num) as id from Patient_Queue where Doc_id="+Integer.parseInt(doc_id.getText().toString());
				try {
					st=conn.createStatement();
					rs=st.executeQuery(sql);
					rs.next();
					pat_id.setText( Integer.toString( (rs.getInt("id")+1) ) );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(pat_id.getText().length()==0&&(dif<1&&dif>-1)){
				sql="select MIN(Pat_num) as id from Patient_Queue where Doc_id="+Integer.parseInt(doc_id.getText().toString());
				try {
					st=conn.createStatement();
					rs=st.executeQuery(sql);
					rs.next();
					pat_id.setText( Integer.toString( (rs.getInt("id")-1) ) );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
