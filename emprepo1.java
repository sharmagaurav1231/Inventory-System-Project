import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.sql.*;

public class emprepo1 extends JFrame implements ActionListener
{
JLabel heading;	
JScrollPane jsp1;
JPanel jp,jp1,jp2;

JTable jt1;
int i,j;

DefaultTableModel tm=new DefaultTableModel(1,5);

String head[ ]={"Ecode","Ename","Dept","Desig","Salary"};
JButton b4;
String url="jdbc:odbc:inventorydsn";
String driver="sun.jdbc.odbc.JdbcOdbcDriver";
Connection connection;
Statement statement;
ResultSet rs;
  	
public emprepo1()
{
	
	
	jp=new JPanel();
	jp.setLayout(new BorderLayout());

	jp2=new JPanel();
	jp2.setLayout(new FlowLayout());
	
	heading=new JLabel("Employee Details");
	heading.setFont(new Font("Arial Black",Font.PLAIN,24));
//	heading.setBounds(180,10,200,30);
	jp2.add(heading);
	jp.add(jp2,BorderLayout.NORTH);
	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

	jp1=new JPanel();
	jp1.setLayout(new FlowLayout());
	jt1=new JTable();
	jt1.setRowHeight(30);
	jt1.setFont(new Font("Arial",Font.PLAIN,12));
	jt1.setModel(tm);

for(j=0;j<5;j++)
{tm.setValueAt(head[j],0,j);
jt1.getColumnModel().getColumn(j).setPreferredWidth(80);
jt1.getColumnModel().getColumn(j).setCellRenderer(rightRenderer);

}
	jp1.add(jt1);


	jp.add(jp1,BorderLayout.CENTER);

	b4=new JButton("Exit");
	b4.addActionListener(this);
	jp.add(b4,BorderLayout.SOUTH);
	
	int v,h;
	v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	jsp1=new JScrollPane(jp,v,h);
	Container c=getContentPane();
	c.add(jsp1);
	addWindowListener(new WindowAdapter()
			    {
				public void windowClosing(WindowEvent we)
				{
					System.exit(0);
				}
			     });
	getFields();
}


 public void getFields()
  	    { 
  	       try
  	            {
  	          	 Class.forName(driver);
  	          	 connection=DriverManager.getConnection(url);
  	          	 statement=connection.createStatement();
  	          	 String query="select * from emp";
  	          	 rs=statement.executeQuery(query);

			while(rs.next())
			 {

			String [] r={String.valueOf(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),String.valueOf(rs.getInt(5))};
	
			tm.addRow(r);

         		  }
			 connection.close();
  	          	 }
  	          	 catch(ClassNotFoundException c)
  	          	 {
  	          	 	 System.err.println(c);
  	          	 }
  	          	 catch(SQLException sql)
  	          	 {
  	          	 	 System.err.println(sql);
  	          	 }
  	   }
public void actionPerformed(ActionEvent ae)
{
	System.exit(0);
}



public static void main(String args[])
{

	emprepo1 e=new emprepo1();
	e.setBounds(10,10,510,410);
	e.show();
}
}