import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Loading extends JFrame implements Runnable{

    Thread t;
    JProgressBar bar;
    String username;

    Loading(String username) {
         this.username =username;
        t=new Thread(this);
        setBounds(500, 200, 650, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel text = new JLabel("Travel and Tourisim Application");
        text.setForeground(Color.blue);
        text.setFont(new Font("Raleway", Font.BOLD, 35));
        text.setBounds(50, 20, 600, 40);
        add(text);

        bar = new JProgressBar();
        bar.setBounds(150, 100, 300, 35);
        bar.setStringPainted(true);
        add(bar);

        JLabel loading = new JLabel("Loading Please Wait....");
        loading.setForeground(Color.RED);
        loading.setFont(new Font("Raleway", Font.BOLD, 16));
        loading.setBounds(225, 130, 200, 30);
        add(loading);

        JLabel usernamelbl = new JLabel("Welcome "+username);
        usernamelbl.setBounds(20, 310, 400, 40);
        usernamelbl.setForeground(Color.BLUE);
        usernamelbl.setFont(new Font("Raleway", Font.BOLD, 16));
        add(usernamelbl);

        t.start();
        setVisible(true);
    }
    @Override
    public void run() {
        try {
            for(int i=1;i<=101;i++){
                int max = bar.getMaximum();
                int value=bar.getValue();
                if (value<max) {
                    bar.setValue(bar.getValue()+1);
                }
                else{

                    setVisible(false);
                    new Dashboard().setVisible(true);
                    //*Calling a new class Object: */
                }
                Thread.sleep(40);
            }
    
            
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Unexpected Error Has Occoured While Loading"+e.getMessage(),"Thread Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        new Loading("srajan");
    }
}
