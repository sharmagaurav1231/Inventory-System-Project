import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class splashscreen extends JFrame
{
   JLabel l;
   public splashscreen()
   {
    l=new JLabel("Login and Create User Page");
    Font f=new Font("Arial",Font.BOLD,40);
    l.setFont(f); 
    setLayout(new FlowLayout());
    add(l);
   }
   public static void main(String arg[])
   {
     splashscreen s=new splashscreen();
     s.setSize(700,600);
     s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     s.setVisible(true);
     s.setTitle("Main Page");
     try
     {
        Thread.sleep(3000);
     }
     catch(Exception e)
     {}
     s.dispose();
     loginscreen l=new loginscreen();
     l.setSize(700,700);
     l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     l.setVisible(true);
     l.setTitle("Login Page");
   }
}