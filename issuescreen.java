import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class issuescreen extends JFrame implements ActionListener
{
  JLabel l,l1,l2,l3,l4,l5,l6;
  JTextField tname,trate,tqoh,tdoi,tqtyissue;
  JButton b1,b2,b3,b4;
  JComboBox tcode;
  public issuescreen()
  {
    l=new JLabel("ISSUE SCREEN");
    Font f=new Font("Arial",Font.BOLD|Font.ITALIC,50);
    l.setFont(f);
    getContentPane().setBackground(Color.yellow);
    l1=new JLabel("Item Code:");
    l2=new JLabel("Item Name:");
    l3=new JLabel("Rate:");
    l4=new JLabel("QOH");
    l5=new JLabel("DOR");
    l6=new JLabel("QTY Issued");
    tcode=new JComboBox();
    tcode.addFocusListener(new FocusAdapter()
                          {
                            public void focusLost(FocusEvent fe)
                            {
                            getdetails();
                            }
                          });
    tname=new JTextField();
    trate=new JTextField();
    tqoh=new JTextField();
    tdoi=new JTextField();
    tqtyissue=new JTextField();
    tname.setEnabled(false);
    trate.setEnabled(false);
    tqoh.setEnabled(false);
    b1=new JButton("Issue");
    b1.addActionListener(this);
    b2=new JButton("Clear");
    b2.addActionListener(this);
    b3=new JButton("Exit");
    b3.addActionListener(this);
    b4=new JButton("Back");
    b4.addActionListener(this);
    setLayout(null);
    l.setBounds(200,5,600,90);
    l1.setBounds(50,100,100,40);
    l2.setBounds(50,170,100,40);
    l3.setBounds(50,240,100,40);
    l4.setBounds(50,310,100,40);
    l5.setBounds(50,390,100,40);
    l6.setBounds(50,470,100,40);
    tcode.setBounds(200,100,100,40);
    tname.setBounds(200,170,100,40);
    trate.setBounds(200,240,100,40);
    tqoh.setBounds(200,310,100,40);
    tdoi.setBounds(200,390,100,40);
    tqtyissue.setBounds(200,470,100,40);
    b1.setBounds(40,550,150,40);
    b2.setBounds(240,550,150,40);
    b3.setBounds(440,550,150,40);
    b4.setBounds(640,550,150,40);
    add(l);
    add(l1);
    add(l2);
    add(l3);
    add(l4);
    add(l5);
    add(l6);
    add(tcode);
    add(tname);
    add(trate);
    add(tqoh);
    add(tdoi);
    add(tqtyissue);
    add(b1);
    add(b2);
    add(b3);
    add(b4);
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
         tcode.addItem(rs.getInt("icode")); 
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
      String query="select * from tblstock where icode="+tcode.getSelectedItem().toString();
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
    if(ae.getSource()==b3)
    {
      System.exit(0);
    }
    else if(ae.getSource()==b1)
         {
            int a,b;
            a=Integer.parseInt(tqoh.getText());
            b=Integer.parseInt(tqtyissue.getText());
            if(b<0)
            {
              JOptionPane.showMessageDialog(null,"Enter a valid quantity");
            }
            else if(a>=b)
            {
            try
            {
              Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
              Connection con=DriverManager.getConnection("jdbc:odbc:inventorydsn");
              Statement stmt=con.createStatement();
              String query1="insert into tblissue(icode,doi,qtyissued) values("+tcode.getSelectedItem().toString()+",'"+tdoi.getText()+"',"+tqtyissue.getText()+")";
              String query2="update tblstock set qoh=qoh-"+tqtyissue.getText()+" where icode="+tcode.getSelectedItem().toString();
              int x=stmt.executeUpdate(query1);
              int y=stmt.executeUpdate(query2);
              JOptionPane.showMessageDialog(null,"Quantity Issued");
	      con.close();
              tname.setText("");
              trate.setText("");
              tqoh.setText("");
              tdoi.setText("");
              tqtyissue.setText("");
              tcode.removeAllItems();
              geticode();
              
            }
            catch(Exception e)
            {
              JOptionPane.showMessageDialog(null,"Exception Caught"+e);
            }
            }
            else
            {
              JOptionPane.showMessageDialog(null,"Not Enough Stock");
            }
         }
         else if(ae.getSource()==b2)
              {
                 tdoi.setText("");
                 tqtyissue.setText("");
              }
              else if(ae.getSource()==b4)
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
    issuescreen i=new issuescreen();
    i.setSize(1800,900);
    i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    i.setVisible(true);
    i.setTitle("Issue Screen");
  } 
}