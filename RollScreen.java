package cn.edu.usst.yff;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RollScreen extends  JFrame  implements ActionListener  {
	static JButton b1,b2;
	static JComboBox box;
	static JTextArea output;
	static RollScreen screen=new RollScreen();
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm=new JFrame();
		frm.setTitle("使用流布局管理器");
		FlowLayout flowLayout=new FlowLayout(); 
		frm.setLayout(flowLayout);//设置使用流布局管理器		
		//创建组件并添加到容器中					    
	    String[] likes={"骨科","皮肤科","神经内科","心胸外科","眼科"};
	    box=new JComboBox(likes);
	    box.setEditable(true);
	    box.setMaximumRowCount(6);
	    frm.getContentPane().add(box);
	    b1 = new JButton();
		b1.setText("查询");
		b1.setBounds(100,5,100,30);
	    frm.getContentPane().add(b1);
	    
	    b2 = new JButton();
		b2.setText("退出");
		b2.setBounds(100,5,100,30);
	    frm.getContentPane().add(b2);
	    
     	output=new JTextArea();	    
	    output.setRows(25);
	    output.setColumns(50);
	    output.setEditable(false);
	    output.setText("请选择要查询的科室......\n\r等待结果显示......\n"); 
	    output.setLineWrap(true);
	    frm.getContentPane().add(output);
	    
	    
	    b1.addActionListener(screen);
	    b2.addActionListener(screen);
	    
	    frm.setBounds(800,500,800,500);
	    frm.setVisible(true);	  
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(b1)){
			output.setText(null);
			output.setText("查询的科室是："+(String) box.getSelectedItem()+"\n");
			output.append("当前的排队号码有：\n");
			rollscreen();			
		}
		if(e.getSource().equals(b2)){
			System.exit(0);			
		}		
	}
public static void rollscreen(){
	Connection con=null;
	//加载数据库驱动程序
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    //建立数据库连接
	try {
		con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=HospitalOutpatient","sa","M03039946");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	       
	Statement st=null;
	  //创建语句对象			 
	try {
		 st=con.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	String sql="select Pat_num,Doc_dept from Doctor join Patient_queue on Doctor.Doc_id=Patient_queue.Doc_id "
               +"where cast(Doc_dept as varchar(8000))='"+(String)box.getSelectedItem()+"'"
                +"ORDER BY Pat_num ASC";               
	ResultSet rs=null;
	try {
		rs=st.executeQuery(sql);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		while(rs.next()){
		output.append("  "+rs.getInt("Pat_num"));
		output.append("\n");
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	 		
	try {
		rs.close();
		st.close();
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
  }
  	
}

