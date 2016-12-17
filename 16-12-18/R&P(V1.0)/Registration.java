package tsh;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Registration {
	private static JTextField phone_search;
	private static JTextField name;
	private static JTextField sex;
	private static JTextField phone;
	private static JTextField dept;
	private static JTextField orderTime;
	private static JTextField pat_id;
	private static JTextField doctor;
	private static JTextField doc_id;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void reg_GUI(){
		JFrame frm=new JFrame();
		frm.setTitle("\u6302\u53F7");
		frm.setBounds(400, 300, 450, 400);
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
		JLabel lblNewLabel_1 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		panel_4.add(lblNewLabel_1);	
		phone = new JTextField();
		panel_4.add(phone);
		phone.setColumns(15);	
		JPanel panel_5 = new JPanel();
		frm.getContentPane().add(panel_5);	
		JLabel lblNewLabel_2 = new JLabel("\u5C31\u8BCA\u79D1\u5BA4\uFF1A");
		panel_5.add(lblNewLabel_2);	
		dept = new JTextField();
		panel_5.add(dept);
		dept.setColumns(15);	
		JPanel panel_6 = new JPanel();
		frm.getContentPane().add(panel_6);	
		JLabel label_3 = new JLabel("\u9884\u7EA6\u65F6\u95F4\uFF1A");
		panel_6.add(label_3);
		orderTime = new JTextField();
		orderTime.setText("\u65E0");
		orderTime.setEditable(false);
		panel_6.add(orderTime);
		orderTime.setColumns(15);
		JPanel panel_7 = new JPanel();
		frm.getContentPane().add(panel_7);
		JLabel lblNewLabel_3 = new JLabel("       \u770B\u75C5\u53F7\uFF1A");
		panel_7.add(lblNewLabel_3);
		pat_id = new JTextField();
		pat_id.setEditable(false);
		pat_id.setText("");
		panel_7.add(pat_id);
		pat_id.setColumns(10);
		JButton btn_getId = new JButton("\u53D6\u53F7");
		panel_7.add(btn_getId);	
		JPanel panel_8 = new JPanel();
		frm.getContentPane().add(panel_8);
		JLabel label_4 = new JLabel("\u5C31\u8BCA\u533B\u751F\uFF1A");
		panel_8.add(label_4);
		doctor = new JTextField();
		doctor.setEditable(false);
		panel_8.add(doctor);
		doctor.setColumns(15);
		JPanel panel_9 = new JPanel();
		frm.getContentPane().add(panel_9);			
		JLabel lblid = new JLabel("      \u533B\u751Fid\uFF1A");
		panel_9.add(lblid);		
		doc_id = new JTextField();
		doc_id.setEditable(false);
		panel_9.add(doc_id);
		doc_id.setColumns(15);	
		JPanel panel_10 = new JPanel();
		frm.getContentPane().add(panel_10);
		JButton btn_confirm = new JButton("\u786E\u8BA4");
		panel_10.add(btn_confirm);
		JButton btn_cancel = new JButton("\u53D6\u6D88");
		panel_10.add(btn_cancel);
		registration(btn_search,btn_getId,btn_confirm,btn_cancel,frm);
		frm.setVisible(true);
	}
	
	public static void registration(JButton search,JButton id,JButton confirm,JButton cancel,JFrame fr){
		
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
	
	public static void jdgOrder(){ //判断该病人是否预约
		String p_Num=phone_search.getText().toString();
		if(!p_Num.matches("\\d+"))
			JOptionPane.showMessageDialog(null, "请输入正确的电话号码！","输入错误",JOptionPane.ERROR_MESSAGE);
		/*else{
			DBmanager db=new DBmanager(); //数据库操作类的对象
			ResultSet rs=null;
			String sql;
			sql="select * from Pat_order where pat_phone='"+p_Num+"'";
			rs=db.getSet(sql);//返回的数据集
			if(!rs.last())
				JOptionPane.showMessageDialog(null, "该病人无预约记录,请输入病人信息！","搜索结果",JOptionPane.INFORMATION_MESSAGE);
			else{
				rs.next();
				name.setText(rs.getString("Pat_name"));
				sex.setText(rs.getString("Pat_sex"));
				dept.setText(rs.getString("Order_dept"));
				phone.setText(rs.getString("Pat_phone"));
				orderTime.setText(rs.getString("Order_time"));		
			}
			rs.close();
		}*/
	}
	
	public static void handout(){ //确定预约
		if(name.getText().length()==0||sex.getText().length()==0||phone.getText().length()==0||dept.getText().length()==0||pat_id.getText().length()==0||doctor.getText().length()==0){
			JOptionPane.showMessageDialog(null, "请将病人信息补充完整！","信息遗漏",JOptionPane.ERROR_MESSAGE);
		}
		/*else{
			DBmanager db=new DBmanager();
			String sql;
			String pid=pat_id.getText().toString(),did=doc_id.getText().toString();
			int patient=Integer.parseInt(pid),doctor=Integer.parseInt(did);
			sql="insert into Patient_Queue (Pat_name,Pat_sex,Pat_id,Pat_docid,Pat_dept,Pat_phone) values ('"+name.getText()+"','"+sex.getText()+"',"+patient+","+doctor+",'"+dept.getText()+"','"+phone.getText()+"')";    
			db.excuteSql(sql); //执行增删改数据库方法
			sql="update Doctor"
						+ "set Doc_patnum=Doc_patnum+1"
						+ "where Doc_id='"+doc_id.getText().toString()+"'";
			db.excuteSql(sql);
			sql="insert into Pat_charge (Pat_name,Pat_feename,Pro_number,Pat_proprice,Pat_charged,Pat_phone,Pat_docid) values ( '"+name.getText()+"','挂号费',1,18,1,'"+phone.getText()+"',"+doctor+")";
			JOptionPane.showMessageDialog(null, "挂号成功，看病号是"+doc_id.getText(),"挂号成功",JOptionPane.INFORMATION_MESSAGE);
		}*/
	}
	
	public static void getID(){
		if(name.getText().length()==0||sex.getText().length()==0||phone.getText().length()==0||dept.getText().length()==0){
			JOptionPane.showMessageDialog(null, "请将病人信息填写完整！","信息遗漏",JOptionPane.ERROR_MESSAGE);
		}
		/*else{
			String order=orderTime.getText().toString();
			String sql;
			DBmanager db=new DBmanager();
			ResultSet rs=null;
			long dif=2;
			if(!order.equals("无")){ //计算时间差
				SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-DD HH:mm");
				Date cur=new Date(System.currentTimeMillis());
				String present=formatter.format(cur);
				try {
					Date d1=formatter.parse(order);
					Date d2=formatter.parse(present);
					dif=( d1.getTime()-d2.getTime() )/(1000*60*60); //时间差
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("时间出错！");
				}
			}
			//查找空闲的医生_没有病人的医生
			sql="select Doc_id,Doc_name"
					+ "from Doctor,Patient_Queue"
					+ "where Doc_id not in ("
					+ "select Pat_docid from Patient_Queue)"
					+ "and Doc_dept='"+dept.getText().toString()+"'";
			rs=db.getSet(sql);
			if(!rs.first()){
				rs.next();
				pat_id.setText("1");
				doctor.setText(rs.getString("Doc_name"));
				doc_id.setText(rs.getString("Doc_id"));
			}
			else{ //全部医生都有病人,查找最空闲的医生
				sql="select Pat_docid"
					+ "from ( "
						+ "select Pat_docid,count(Pat_id) as num"
						+ "from Doctor,Patient_Queue"
						+ "where Doc_dept='"+dept.getText().toString()+"' and Doc_id=Pat_docid ) as A"
					+ "where num=("
					    + "select max(B.num)"
					    + "from ("
					       + "select count(Pat_id) as num"
					       + "from Doctor,Patient_Queue"
					       + "where Doc_dept='"+dept.getText().toString()+"' and Doc_id=Pat_docid) as B)";
				rs=db.getSet(sql);
				rs.next();
				doc_id.setText(Integer.toString(rs.getInt("Pat_docid")));
				sql="select Doc_name from Doctor where Doc_id="+Integer.parseInt(doc_id.getText().toString());
				rs=db.getSet(sql);
				rs.next();
				doctor.setText(rs.getString("Doc_name"));
			}
			if(pat_id.getText().length()==0&&(order.equals("无")||dif>1||dif<-1)){
				sql="select MAX(Pat_id) as id from Patient_Queue where Pat_docid='"+doc_id.getText().toString()+"'";
				rs=db.getSet(sql);
				rs.next();
				pat_id.setText( Integer.toString( (rs.getInt("id")+1) ) );
			}
			if(pat_id.getText().length()==0&&(dif<1&&dif>-1)){
				sql="select MIN(Pat_id) as id from Patient_Queue where Pat_docid='"+doc_id.getText().toString()+"'";
				rs=db.getSet(sql);
				rs.next();
				pat_id.setText( Integer.toString( (rs.getInt("id")-1) ) );
			}
			rs.close();
		}*/
	}
}
