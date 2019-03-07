import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
public class tbl2 extends JFrame implements ActionListener
{
  JTable t1;
  DefaultTableModel tm=new DefaultTableModel(1,3);
  JButton bexit,bback;
  String a[]={"icode","doi","qtyissued"};
  public tbl2()
  {
    bexit=new JButton("EXIT");
    bexit.addActionListener(this);
    bback=new JButton("BACK");
    bback.addActionListener(this);
    t1=new JTable();
    t1.setRowHeight(30);
    t1.setFont(new Font("Arial",Font.BOLD,13));
    t1.setModel(tm);
    for(int i=0;i<3;i++)
    {
      tm.setValueAt(a[i],0,i);
    }
    setLayout(null);
    t1.setBounds(300,50,600,420);
    bexit.setBounds(400,500,100,50);
    bback.setBounds(650,500,100,50);
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
      String query="select * from tblissue";
      ResultSet rs=stmt.executeQuery(query);
      while(rs.next())
      {
        String r[] ={String.valueOf(rs.getInt("icode")),rs.getString("doi"),String.valueOf(rs.getInt("qtyissued"))};
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
    tbl2 t2=new tbl2();
    t2.setSize(1700,900);
    t2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    t2.setVisible(true);
    t2.setTitle("All Issues");
  }
}