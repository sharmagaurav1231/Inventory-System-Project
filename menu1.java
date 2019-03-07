import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class menu1 extends JFrame implements ActionListener
{
  JMenuBar mbar;
  JMenu m1,m2,m3,m4;
  ImageIcon i;
  JLabel l1;
  JMenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9;
  public menu1()
  {
    mbar=new JMenuBar();
    setJMenuBar(mbar);
    m1=new JMenu("Stock");
    mbar.add(m1);
    i1=new JMenuItem("Add Item");
    i1.addActionListener(this);
    m1.add(i1);
    i2=new JMenuItem("Update Item");
    i2.addActionListener(this);
    m1.add(i2);
    m2=new JMenu("Transaction");
    mbar.add(m2);
    i3=new JMenuItem("Issue");
    i3.addActionListener(this);
    m2.add(i3);
    i4=new JMenuItem("Receive");
    i4.addActionListener(this);
    m2.add(i4);
    m3=new JMenu("Reports");
    mbar.add(m3);
    i5=new JMenuItem("All Item");
    i5.addActionListener(this);
    m3.add(i5);
    i6=new JMenuItem("All issue");
    i6.addActionListener(this);
    m3.add(i6);
    i7=new JMenuItem("All receipts");
    i7.addActionListener(this);
    m3.add(i7);
    i8=new JMenuItem("Single Item");
    i8.addActionListener(this);
    m3.add(i8);
    m4=new JMenu("Exit");
    mbar.add(m4);
    i9=new JMenuItem("Quit app");
    i9.addActionListener(this);
    m4.add(i9);
    i=new ImageIcon("2.png");
    l1=new JLabel(i);
    add(l1);
  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==i9)
    {
      System.exit(0);
    }
    else if(ae.getSource()==i1)
         { 
            dispose();
            stockentry s=new stockentry();
            s.setSize(1800,900);
            s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            s.setVisible(true);
            s.setTitle("Stock Entry Screen");
         }
         else if(ae.getSource()==i2)
              {
                 dispose();
                 stockupdation s1=new stockupdation();
                 s1.setSize(1800,900);
                 s1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 s1.setVisible(true);
                 s1.setTitle("Stock Entry Screen");
              }
              else if(ae.getSource()==i3)
                   {
                      dispose();
                      issuescreen i=new issuescreen(); 
                      i.setSize(1800,900);
                      i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                      i.setVisible(true);
                      i.setTitle("Stock Entry Screen");
                   }
                   else if(ae.getSource()==i4)
                        {
                           dispose();
                           receiptscreen r=new receiptscreen();
                           r.setSize(1800,1000);
                           r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           r.setVisible(true);
                           r.setTitle("Stock Entry Screen");
                        }
                        else if(ae.getSource()==i5)
                             {
                               dispose();
                               table1 t=new table1();
                               t.setSize(1700,900);
                               t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                               t.setVisible(true);
                               t.setTitle("All Items");
                             }
                             else if(ae.getSource()==i6)
                                  {
                                       tbl2 t2=new tbl2();
                                       t2.setSize(1700,900);
                                       t2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                       t2.setVisible(true);
                                       t2.setTitle("All Issues");
                                  }
                                  else if(ae.getSource()==i7)
                                       {
                                          table3 t3=new table3();
                                          t3.setSize(1700,900);
                                          t3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                          t3.setVisible(true);
                                          t3.setTitle("All Receipts");
                                       }
  }
  public static void main(String arg[])
  {
    menu1 m=new menu1();
    m.setSize(1800,800);
    m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    m.setVisible(true);
    m.setTitle("Menu");
  }
}