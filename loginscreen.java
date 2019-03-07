import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class loginscreen extends JFrame implements ActionListener
{
  JTextField t1;
  JLabel l1,l2;
  JPasswordField p1;
  JButton bcreate,blogin,bexit;
  public loginscreen()
  {
    l1=new JLabel("Username");
    l2=new JLabel("Password");
    t1=new JTextField(20);
    p1=new JPasswordField(20);
    bcreate=new JButton("Create User");
    bcreate.addActionListener(new ActionListener()
                         {
                           public void actionPerformed(ActionEvent ae)
                           {
                           createuser c=new createuser();
                           c.setSize(700,700);
                           c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           c.setVisible(true);
                           c.setTitle("Create User");
                           dispose();
			}
                         });
    blogin=new JButton("Login");
    blogin.addActionListener(this);
    bexit=new JButton("Exit");
    bexit.addActionListener(this);
    setLayout(null);
    l1.setBounds(50,50,60,50);
    t1.setBounds(130,50,120,50);
    l2.setBounds(50,120,60,50);
    p1.setBounds(130,120,120,50);
    bcreate.setBounds(50,210,150,50);
    blogin.setBounds(210,210,150,50);
    bexit.setBounds(370,210,150,50);
    add(l1);
    add(l2);
    add(t1);
    add(p1);
    add(bcreate);
    add(blogin);
    add(bexit);
  }
  public void actionPerformed(ActionEvent ae)
  {
     if(ae.getSource()==blogin)
     {
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          Connection con=DriverManager.getConnection("jdbc:odbc:inventorydsn");
          Statement stmt=con.createStatement();
          String query="select * from tbllogin where username='"+t1.getText()+"' and password='"+p1.getText()+"'";
          ResultSet rs=stmt.executeQuery(query);
          int ctr=0;
          while(rs.next())
          {
            ctr++;
          }
          if(ctr==0)
          {
            JOptionPane.showMessageDialog(null,"User does not exist");
          }
          else
          {
            JOptionPane.showMessageDialog(null,"Login Successfull");
            dispose();
            menu1 m=new menu1();
            m.setSize(1800,800);
            m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            m.setVisible(true);
            m.setTitle("Menu");
          }
       }
       catch(Exception e)
       {
         JOptionPane.showMessageDialog(null,"Exception caught:"+e);
       }
     } 
  }
  public static void main(String arg[])
  {
    loginscreen l=new loginscreen();
    l.setSize(700,700);
    l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    l.setVisible(true);
    l.setTitle("Login Page");
  } 
}