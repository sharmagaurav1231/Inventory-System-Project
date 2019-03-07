import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class receiptscreen extends JFrame implements ActionListener
{
  JLabel l,l1,l2,l3,l4,l5,l6;
  JTextField tname,trate,tqoh,tdor,tqtyreceive;
  JComboBox tcode;
  JButton breceive,bclear,bexit,bback;
  public receiptscreen()
  {
    l=new JLabel("RECEIPT SCREEN");
    Font f=new Font("Arial",Font.BOLD|Font.ITALIC,50);
    l.setFont(f);
    getContentPane().setBackground(Color.pink);
    l1=new JLabel("Item Code:");
    l2=new JLabel("Item Name:");
    l3=new JLabel("Rate:");
    l4=new JLabel("QOH");
    l5=new JLabel("DOR");
    l6=new JLabel("Qty Received");
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
    tdor=new JTextField();
    tqtyreceive=new JTextField();
    breceive=new JButton("Recieve");
    breceive.addActionListener(this);
    bclear=new JButton("Clear");
    bclear.addActionListener(this);
    bexit=new JButton("Exit");
    bexit.addActionListener(this);
    bback=new JButton("Back");
    bback.addActionListener(this);
    tname.setEnabled(false);
    trate.setEnabled(false);
    tqoh.setEnabled(false);
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
    tdor.setBounds(200,390,100,40);
    tqtyreceive.setBounds(200,470,100,40);
    breceive.setBounds(40,550,150,40);
    bclear.setBounds(240,550,150,40);
    bexit.setBounds(440,550,150,40);
    bback.setBounds(640,550,150,40);
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
    add(tdor);
    add(tqtyreceive);
    add(breceive);
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
      JOptionPane.showMessageDialog(null,"Exception caught"+e);
    }
  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==bexit)
    {
      System.exit(0);
    }
    else if(ae.getSource()==bclear)
         {
            tdor.setText("");
            tqtyreceive.setText("");
         }
         else if(ae.getSource()==breceive)
              {
                try
                {
                  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                  Connection con=DriverManager.getConnection("jdbc:odbc:inventorydsn");
                  Statement stmt=con.createStatement();
                  String query="insert into tblreceipt(icode,dor,qtyreceived) values ("+tcode.getSelectedItem().toString()+",'"+tdor.getText()+"',"+tqtyreceive.getText()+")";
                  String query1="update tblstock set qoh=qoh+"+tqtyreceive.getText()+" where icode="+tcode.getSelectedItem().toString();
                  int x=stmt.executeUpdate(query);
                  int y=stmt.executeUpdate(query1);
                  JOptionPane.showMessageDialog(null,"Quantity Received");
                  tdor.setText("");
                  tqtyreceive.setText("");
                  tcode.removeAllItems();
                  geticode();
                  con.close();
                }
                catch(Exception e)
                {
                  JOptionPane.showMessageDialog(null,"Exception Caught"+e);
                }
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
    receiptscreen r=new receiptscreen();
    r.setSize(900,1000);
    r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    r.setVisible(true);
    r.setTitle("Stock Entry Screen");
  } 
}