import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Signup extends JFrame implements ActionListener {
    JTextField usernametf, nametf, answertf;
    JPasswordField passwordtf;
    JButton create, back;
    Choice securitydb;

    Signup() {
        // *Frame Settings */
        setBounds(350, 200, 900, 360);
        getContentPane().setBackground(Color.white);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        // *--- */
        /* Panels,labels,TextFields and Buttons Decleration and settings: */
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(133, 193, 233));
        p1.setBounds(0, 0, 500, 400);
        p1.setLayout(null);
        add(p1);

        JLabel username = new JLabel("Username");
        username.setFont(new Font("Tahoma", Font.BOLD, 14));
        username.setBounds(50, 20, 125, 25);
        p1.add(username);

        usernametf = new JTextField();
        usernametf.setBounds(190, 20, 180, 25);
        usernametf.setBorder(BorderFactory.createEmptyBorder());
        usernametf.addActionListener(this);
        p1.add(usernametf);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        name.setBounds(50, 60, 125, 25);
        p1.add(name);

        nametf = new JTextField();
        nametf.setBounds(190, 60, 180, 25);
        nametf.setBorder(BorderFactory.createEmptyBorder());
        nametf.addActionListener(this);
        p1.add(nametf);

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Tahoma", Font.BOLD, 14));
        password.setBounds(50, 100, 125, 25);
        p1.add(password);

        passwordtf = new JPasswordField();
        passwordtf.setBounds(190, 100, 180, 25);
        passwordtf.setBorder(BorderFactory.createEmptyBorder());
        passwordtf.addActionListener(this);
        p1.add(passwordtf);

        JLabel security = new JLabel("Security Question");
        security.setFont(new Font("Tahoma", Font.BOLD, 14));
        security.setBounds(50, 140, 140, 25);
        p1.add(security);

        securitydb = new Choice();
        securitydb.add("In what city were you born?");
        securitydb.add("What is the name of your favorite childhood friend?");
        securitydb.add("What is your favorite movie?");
        securitydb.add("What is your favorite color?");
        securitydb.setBounds(190, 140, 290, 25);
        p1.add(securitydb);

        JLabel answer = new JLabel("Answer");
        answer.setFont(new Font("Tahoma", Font.BOLD, 14));
        answer.setBounds(50, 180, 140, 25);
        p1.add(answer);

        answertf = new JTextField();
        answertf.setBounds(190, 180, 180, 25);
        answertf.setBorder(BorderFactory.createEmptyBorder());
        answertf.addActionListener(this);
        p1.add(answertf);

        create = new JButton("Create");
        create.setBackground(Color.white);
        create.setForeground(new Color(133, 193, 233));
        create.setFont(new Font("Tahoma", Font.BOLD, 14));
        create.setBounds(80, 250, 100, 30);
        create.addActionListener(this);
        p1.add(create);

        back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setForeground(new Color(133, 193, 233));
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.setBounds(250, 250, 100, 30);
        back.addActionListener(this);
        p1.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(580, 50, 250, 250);
        add(image);

        // *------------------ */

        setVisible(true);// Frame visibility setting:
    }

    // *Action performed method to handel the action listners */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String username = usernametf.getText();
            String name = nametf.getText();
            String password = new String(passwordtf.getPassword());
            String question = securitydb.getSelectedItem();
            String answer = answertf.getText();
            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "UserName is Required");
                return;
            }
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Requierd");
                return;
            }
            if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "Password is Requierd");
                return;
            }
            if (!password.matches(".*[A-Z].*")) {
                JOptionPane.showMessageDialog(null, "Password should have at least one uppercase character");
                return;
            }

            if (!password.matches(".*[^a-zA-Z0-9].*")) {
                JOptionPane.showMessageDialog(null, "Password should have at least one special character");
                return;
            }

            if (answer.equals("")) {
                JOptionPane.showMessageDialog(null, "You should answer the question:");
                return;
            }
            Conn con = null;
            try {
                con = new Conn();
                String checkQuery = "SELECT * FROM account WHERE BINARY username = ?";// *Check if username already exists
                PreparedStatement ps1 = con.c.prepareStatement(checkQuery);
                ps1.setString(1, username);
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose another.");
                    return;
                }
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Database Error accoured\n" + e.getMessage(), "DataBase Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            try {
                //*Inserting the value to the table: */
                con = new Conn();
                String query = "INSERT INTO account(username,name,password,security,answer)Values(?,?,?,?,?)";
                PreparedStatement ps = con.c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setString(4, question);
                ps.setString(5, answer);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "Account Created Successfully\nUsername: " + username + "\nPassword: " + password);
                setVisible(false);
                new Login();

            } catch (SQLException sqlEx) {
                JOptionPane.showMessageDialog(this,
                        "Database error occurred while saving your details:\n" + sqlEx.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("SQLException in SignupOne: " + sqlEx.getMessage());
                sqlEx.printStackTrace();
            } catch (NullPointerException nullEx) {
                JOptionPane.showMessageDialog(this,
                        "A required field was missing or null. Please ensure all inputs are filled correctly.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("NullPointerException in Signup: " + nullEx.getMessage());
                nullEx.printStackTrace();
            }

            finally {
                if (con != null) {
                    con.close();
                }
            }
        }
        if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
