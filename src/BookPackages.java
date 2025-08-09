import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookPackages extends JFrame {
        Choice cpackage;
        JTextField tfpersons;

        BookPackages() {
                setLayout(null);
                ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
                setIconImage(logo.getImage());
                getContentPane().setBackground(Color.white);
                setBounds(350, 200, 1100, 500);

                Font font = new Font("Tahoma",Font.PLAIN,16);

                JLabel heading = new JLabel("Book Package");
                heading.setBounds(100, 10, 200, 30);
                heading.setFont(new Font("Tahoma", Font.BOLD, 20));
                add(heading);

                JLabel username = new JLabel("Username");
                username.setBounds(40, 70, 100, 20);
                username.setFont(font);
                add(username);

                JLabel usernamelbl = new JLabel();
                usernamelbl.setFont(font);
                usernamelbl.setBounds(250, 70, 200, 20);
                add(usernamelbl);

                JLabel select = new JLabel("Select Package");
                select.setBounds(40, 110, 150, 20);
                select.setFont(font);
                add(select);

                cpackage = new Choice();
                cpackage.add("Bali - Gold");
                cpackage.add("Manali - Silver");
                cpackage.add("Goa - Bronze");
                cpackage.setBounds(250,110,200,20);
                add(cpackage);


                JLabel npeople = new JLabel("Total Persons");
                npeople.setFont(font);
                npeople.setBounds(40,150,150,25);
                add(npeople);

                tfpersons = new JTextField();
                tfpersons.setBounds(250,150,200,25);
                add(tfpersons);

                JLabel id = new JLabel("ID");
                id.setBounds(40,190,100,20);
                id.setFont(font);
                add(id);

                JLabel idnumber = new JLabel();
                idnumber.setBounds(250,190,100,20);
                idnumber.setFont(font);
                add(idnumber);

                JLabel number  = new JLabel("Number");
                number.setBounds(40,230,100,20);
                number.setFont(font);
                add(number);

                JLabel numberlbl = new JLabel();
                numberlbl.setFont(font);
                numberlbl.setBounds(250,230,100,20);
                add(numberlbl);

                

                


                
        }

        public static void main(String[] args) {
                new BookPackages().setVisible(true);
        }
}
