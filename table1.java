import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
public class table1 extends JFrame implements ActionListener
{
  JTable t1;
  DefaultTableModel tm=new DefaultTableModel(1,4);
  JButton bexit,bback;
  String a[]={"icode","iname","rate","QOH"};
  public table1()
  {
    bexit=new JButton("EXIT");
    bexit.addActionListener(this);
    bback=new JButton("BACK");
    bback.addActionListener(this);
    t1=new JTable();
    t1.setRowHeight(30);
    t1.setFont(new Font("Arial",Font.BOLD,13));
    t1.setModel(tm);
    for(int i=0;i<4;i++)
    {
      tm.setValueAt(a[i],0,i);
    }
    setLayout(null);
    t1.setBounds(300,50,600,130);
    bexit.setBounds(400,200,100,50);
    bback.setBounds(650,200,100,50);
    add(t1);
    add(bexit);
    add(bback);
    getdetails();
  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==bback)
    {
       dispose();
       menu1 m=new menu1();
       m.setSize(1800,800);
       m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       m.setVisible(true);
       m.setTitle("Menu");
    }
    else if(ae.getSource()==bexit)
         {
            System.exit(0);
         }
  }
  public void getdetails()
  {
    try
    {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection con=DriverManager.getConnection("Jdbc:Odbc:inventorydsn");
      Statement stmt=con.createStatement();
      String query="select * from tblstock";
      ResultSet rs=stmt.executeQuery(query);
      while(rs.next())
      {
        String r[] ={String.valueOf(rs.getInt("icode")),rs.getString("iname"),String.valueOf(rs.getInt("rate")),String.valueOf(rs.getInt("qoh"))};
        tm.addRow(r);
      }
      con.close();
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null,"Exception Caught:"+e);
    }
  }
  public static void main(String arg[])
  {
    table1 t=new table1();
    t.setSize(1700,900);
    t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    t.setVisible(true);
    t.setTitle("All Items");
  }
}