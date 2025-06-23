import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Forgetpassword extends JFrame implements ActionListener {
    JTextField usernametf, nametf, securitytf, answertf, passwordtf;
    JButton search, retrive, back;

    Forgetpassword() {
        setBounds(350, 200, 850, 380);
        getContentPane().setBackground(Color.white);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/forgotpassword.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(580, 70, 200, 200);
        add(image);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(30, 30, 500, 280);
        add(p1);

        JLabel usernamelbl = new JLabel("Username");
        usernamelbl.setBounds(40, 20, 100, 25);
        usernamelbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(usernamelbl);

        usernametf = new JTextField();
        usernametf.setBounds(220, 20, 150, 25);
        usernametf.setBorder(BorderFactory.createEmptyBorder());
        usernametf.addActionListener(this);
        p1.add(usernametf);

        search = new JButton("Search");
        search.setBackground(Color.gray);
        search.setForeground(Color.white);
        search.setBounds(380, 20, 100, 25);
        search.addActionListener(this);
        p1.add(search);

        JLabel namelbl = new JLabel("Name");
        namelbl.setBounds(40, 60, 100, 25);
        namelbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(namelbl);

        nametf = new JTextField();
        nametf.setBounds(220, 60, 150, 25);
        nametf.setBorder(BorderFactory.createEmptyBorder());
        nametf.addActionListener(this);
        nametf.setFocusable(false);
        p1.add(nametf);

        JLabel securitylbl = new JLabel("Security Question");
        securitylbl.setBounds(40, 100, 150, 25);
        securitylbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(securitylbl);

        securitytf = new JTextField();
        securitytf.setBounds(220, 100, 250, 25);
        securitytf.setBorder(BorderFactory.createEmptyBorder());
        securitytf.setBackground(Color.white);
        securitytf.setFocusable(false);
        securitytf.addActionListener(this);
        p1.add(securitytf);

        JLabel answerlbl = new JLabel("Answer");
        answerlbl.setBounds(40, 140, 150, 25);
        answerlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(answerlbl);

        answertf = new JTextField();
        answertf.setBounds(220, 140, 150, 25);
        answertf.setBorder(BorderFactory.createEmptyBorder());
        answertf.addActionListener(this);
        p1.add(answertf);

        retrive = new JButton("Retrieve");
        retrive.setBackground(Color.gray);
        retrive.setForeground(Color.white);
        retrive.setBounds(380, 140, 100, 25);
        retrive.addActionListener(this);
        p1.add(retrive);

        JLabel passwordlbl = new JLabel("Password");
        passwordlbl.setBounds(40, 180, 150, 25);
        passwordlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(passwordlbl);

        passwordtf = new JTextField();
        passwordtf.setBounds(220, 180, 150, 25);
        passwordtf.setBorder(BorderFactory.createEmptyBorder());
        passwordtf.setBackground(Color.white);
        passwordtf.setEditable(false);
        passwordtf.addActionListener(this);
        p1.add(passwordtf);

        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.setBounds(150, 230, 100, 25);
        back.addActionListener(this);
        p1.add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == search) {
            Conn conn = new Conn();
            String username = usernametf.getText();
            try {
                String query = "SELECT * FROM account WHERE BINARY username = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nametf.setText(rs.getString("name"));
                    securitytf.setText(rs.getString("security"));
                } else {
                    JOptionPane.showMessageDialog(null, "User not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database Error occurred\n" + e.getMessage(), "DataBase Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

        else if (ae.getSource() == retrive) {
            Conn conn = new Conn();
            String username = usernametf.getText();
            String answer = answertf.getText();

            try {
                String query = "SELECT * FROM account WHERE BINARY username = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    if (rs.getString("answer").equals(answer)) {
                        passwordtf.setText(rs.getString("password"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Answer does not match.", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database Error occurred\n" + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {

            setVisible(false);
            new Login();

        }

    }

    public static void main(String[] args) {
        new Forgetpassword();
    }
}
