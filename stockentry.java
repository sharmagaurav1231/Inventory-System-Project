import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class stockentry extends JFrame implements ActionListener
{
  JLabel l,l1,l2,l3,l4;
  JTextField tcode,tname,trate,tqoh;
  JButton binsert,bclear,bexit,bback;
  public stockentry()
  {
    l=new JLabel("STOCK ENTRY SCREEN");
    Font f=new Font("Arial",Font.BOLD|Font.ITALIC,50);
    l.setFont(f);
    getContentPane().setBackground(Color.green);
    l1=new JLabel("Item Code:");
    l2=new JLabel("Item Name:");
    l3=new JLabel("Rate:");
    l4=new JLabel("QOH");
    tcode=new JTextField();
    tcode.setEnabled(false);
    tname=new JTextField();
    trate=new JTextField();
    tqoh=new JTextField();
    binsert=new JButton("Insert");
    binsert.addActionListener(this);
    bclear=new JButton("Clear");
    bclear.addActionListener(new ActionListener()
                             {
                               public void actionPerformed(ActionEvent ae)
                               {
                                  tname.setText("");
                                  trate.setText("");
                                  tqoh.setText("");
                               }
                             }
                             );
    bexit=new JButton("Exit");
    bexit.addActionListener(this);
    bback=new JButton("Back");
    bback.addActionListener(this);
    setLayout(null);
    setContentPane(new JLabel(new ImageIcon("3.png")));
    l.setBounds(150,50,600,100);
    l1.setBounds(50,200,100,50);
    l2.setBounds(50,300,100,50);
    l3.setBounds(50,400,100,50);
    l4.setBounds(50,500,100,50);
    tcode.setBounds(200,200,100,50);
    tname.setBounds(200,300,100,50);
    trate.setBounds(200,400,100,50);
    tqoh.setBounds(200,500,100,50);
    binsert.setBounds(40,600,150,50);
    bclear.setBounds(240,600,150,50);
    bexit.setBounds(440,600,150,50);
    bback.setBounds(640,600,150,50);
    add(l);
    add(l1);
    add(l2);
    add(l3);
    add(l4);
    add(tcode);
    add(tname);
    add(trate);
    add(tqoh);
    add(binsert);
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
      int ic=0;
      while(rs.next())
      {
        ic=rs.getInt("icode");
      }
      if(ic==0)
      {
        tcode.setText("1001");
      }
      else
      {
         ic=ic+1;
         tcode.setText(""+ic);
      }
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null,"Exception Caught:"+e);
    }
  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==bexit)
    {
      System.exit(0);
    }
    else if(ae.getSource()==binsert)
         {
           try
           {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           Connection con=DriverManager.getConnection("jdbc:odbc:inventorydsn");
           Statement stmt=con.createStatement();
           String query="insert into tblstock(icode,iname,rate,qoh) values ("+tcode.getText()+",'"+tname.getText()+"',"+trate.getText()+","+tqoh.getText()+")";
           int x=stmt.executeUpdate(query);
           JOptionPane.showMessageDialog(null,"Stock Entered");
           tname.setText("");
           trate.setText("");
           tqoh.setText("");
           geticode();
           con.close();
           }
           catch(Exception e)
           {
             JOptionPane.showMessageDialog(null,"Excetion Caught"+e);
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
    stockentry s=new stockentry();
    s.setSize(1800,900);
    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    s.setVisible(true);
    s.setTitle("Stock Entry Screen");
  } 
}