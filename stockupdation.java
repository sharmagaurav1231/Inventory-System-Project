import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class stockupdation extends JFrame implements ActionListener
{
  JLabel l,l1,l2,l3,l4;
  JTextField tname,trate,tqoh;
  JComboBox ticode;
  JButton bmodify,bdelete,bclear,bexit,bback;
  public stockupdation()
  {
    l=new JLabel("STOCK UPDATION SCREEN");
    Font f=new Font("Times New Roman",Font.BOLD|Font.ITALIC,50);
    l.setFont(f);
    getContentPane().setBackground(Color.orange);
    l1=new JLabel("Item Code:");
    l2=new JLabel("Item Name:");
    l3=new JLabel("Rate:");
    l4=new JLabel("QOH");
    ticode=new JComboBox();
    ticode.addFocusListener(new FocusAdapter()
                           {
                             public void focusLost(FocusEvent fe)
                             {
                               getdetails();
                             }
                           });
    tname=new JTextField();
    trate=new JTextField();
    tqoh=new JTextField();
    bmodify=new JButton("Modify");
    bmodify.addActionListener(this);
    bdelete=new JButton("Delete");
    bdelete.addActionListener(this);
    bclear=new JButton("Clear");
    bclear.addActionListener(this);
    bexit=new JButton("Exit");
    bexit.addActionListener(this);
    bback=new JButton("Back");
    bback.addActionListener(this);
    setLayout(null);
    l.setBounds(150,50,700,100);
    l1.setBounds(50,200,100,50);
    l2.setBounds(50,300,100,50);
    l3.setBounds(50,400,100,50);
    l4.setBounds(50,500,100,50);
    ticode.setBounds(200,200,100,50);
    tname.setBounds(200,300,100,50);
    trate.setBounds(200,400,100,50);
    tqoh.setBounds(200,500,100,50);
    bmodify.setBounds(40,600,150,50);
    bdelete.setBounds(240,600,150,50);
    bclear.setBounds(440,600,150,50);
    bexit.setBounds(640,600,150,50);
    bback.setBounds(840,600,150,50);
    add(l);
    add(l1);
    add(l2);
    add(l3);
    add(l4);
    add(ticode);
    add(tname);
    add(trate);
    add(tqoh);
    add(bmodify);
    add(bdelete);
    add(bclear);
    add(bexit);
    add(bback);
    geticode();
  }
  void geticode()
  {
    try
    {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection con=DriverManager.getConnection("jdbc:odbc:inventorydsn");
      Statement stmt=con.createStatement();
      String query="select icode from tblstock";
      ResultSet rs=stmt.executeQuery(query);
      while(rs.next())
      {
         ticode.addItem(rs.getInt("icode")); 
      }
      con.close();
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null,"Exception Caught"+e);
    } 
  }
  void getdetails()
  {
    try
    {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection con=DriverManager.getConnection("jdbc:odbc:inventorydsn");
      Statement stmt=con.createStatement();
      String query="select * from tblstock where icode="+ticode.getSelectedItem().toString();
      ResultSet rs=stmt.executeQuery(query);
      while(rs.next())
      {
         tname.setText(rs.getString("iname"));
         trate.setText(""+rs.getInt("rate"));
         tqoh.setText(""+rs.getInt("qoh"));
      }
      con.close();
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null,"Exception Caught"+e);
    }
  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==bexit)
    {
      System.exit(0);
    }
    else if(ae.getSource()==bmodify)
         {
           try
           {
              Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
              Connection con=DriverManager.getConnection("jdbc:odbc:inventorydsn");
              Statement stmt=con.createStatement();
              String query="update tblstock set iname='"+tname.getText()+"',rate="+trate.getText()+",qoh="+tqoh.getText()+" where icode="+ticode.getSelectedItem().toString();
              int x=stmt.executeUpdate(query);
              JOptionPane.showMessageDialog(null,"Data Modified");
              con.close();
           }
           catch(Exception e)
           {
              JOptionPane.showMessageDialog(null,"Exception Caught"+e);
           }  
         }
         else if(ae.getSource()==bdelete)
              {
                 int ans=JOptionPane.showConfirmDialog(null,"Do You Want To Delete?");
                 if(ans==JOptionPane.YES_OPTION)
                 {
                    try
                    {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con=DriverManager.getConnection("Jdbc:Odbc:inventorydsn");
                    Statement stmt=con.createStatement();
                    String query="delete from tblstock where icode="+ticode.getSelectedItem().toString();
                    int x=stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Record Deleted");
                    tname.setText("");
                    trate.setText("");
                    tqoh.setText("");
                    ticode.removeAllItems();
                    geticode();
                    con.close();
                    }
                    catch(Exception e)
                    {
                      JOptionPane.showMessageDialog(null,"Exception Caught:"+e);
                    }
                 }
              }
              else if(ae.getSource()==bclear)
                    {
                      tname.setText("");
                      trate.setText("");
                      tqoh.setText("");
                    }
                    else if(ae.getSource()==bback)
                         {
                            dispose();
                            menu1 m=new menu1();
                            m.setSize(1800,800);
                            m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            m.setVisible(true);
                            m.setTitle("Menu");
                         }
  }
  public static void main(String arg[])
  {
    stockupdation s1=new stockupdation();
    s1.setSize(1800,900);
    s1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    s1.setVisible(true);
    s1.setTitle("Stock Entry Screen");
  } 
}