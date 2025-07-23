import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main extends JFrame implements ActionListener {
  JTextField usernametf;
  JPasswordField passwordtf;
  JButton login, signup, forgetpassword;

  main() {
    //Frame settings
    setSize(900, 400);
    setLocation(350, 200);
    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());
    setLayout(null);
    getContentPane().setBackground(Color.white);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
//------------
    JPanel p1 = new JPanel();
    p1.setBackground(new Color(131, 193, 233));
    p1.setBounds(0, 0, 400, 400);
    p1.setLayout(null);
    add(p1);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
    Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(100, 90, 200, 200);
    p1.add(image);

    JPanel p2 = new JPanel();
    p2.setLayout(null);
    p2.setBounds(400, 30, 450, 300);
    add(p2);

    JLabel username = new JLabel("Username");
    username.setBounds(60, 20, 100, 25);
    username.setFont(new Font("SAN-SERIF", Font.PLAIN, 20));
    p2.add(username);

    usernametf = new JTextField();
    usernametf.setBounds(60, 60, 300, 30);
    usernametf.setBorder(BorderFactory.createEmptyBorder());
    usernametf.addActionListener(this);
    p2.add(usernametf);

    JLabel password = new JLabel("Password");
    password.setBounds(60, 110, 100, 25);
    password.setFont(new Font("SAN-SERIF", Font.PLAIN, 20));
    p2.add(password);

    passwordtf = new JPasswordField();
    passwordtf.setBounds(60, 150, 300, 30);
    passwordtf.setBorder(BorderFactory.createEmptyBorder());
    passwordtf.addActionListener(this);
    p2.add(passwordtf);

    login = new JButton("Login");
    login.setBounds(60, 200, 130, 30);
    login.setBackground(new Color(133, 193, 233));
    login.setForeground(Color.white);
    login.setBorder(new LineBorder(new Color(133, 193, 233)));
    login.addActionListener(this);
    p2.add(login);

    signup = new JButton("Signup");
    signup.setBounds(230, 200, 130, 30);
    signup.setBackground(new Color(133, 193, 233));
    signup.setForeground(Color.white);
    signup.setBorder(new LineBorder(new Color(133, 193, 233)));
    signup.addActionListener(this);
    p2.add(signup);

    forgetpassword = new JButton("Forgot Password?");
    forgetpassword.setBounds(145, 250, 130, 30);
    forgetpassword.setBackground(new Color(133, 193, 233));
    forgetpassword.setForeground(Color.white);
    forgetpassword.setBorder(new LineBorder(new Color(133, 193, 233)));
    forgetpassword.addActionListener(this);
    p2.add(forgetpassword);

    setVisible(true);
  }
//actionPerformed method listens to events in frame and do the desired implementation:
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == login) {
      //Data base and SQL implementation:
      String username = usernametf.getText();
      String password = new String(passwordtf.getPassword());
      Conn conn =null;
      try {
        conn = new Conn();
        String query = "SELECT * FROM account WHERE BINARY username = ? AND  BINARY password = ?";
        PreparedStatement ps = conn.c.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
          setVisible(false); //*~~~~Calling a new Loading class Constructor ~~~~~ */
          new Loading(username);
        } else {
          JOptionPane.showMessageDialog(this, "Invalid Username or Password.", "Login Failed",
              JOptionPane.ERROR_MESSAGE);
        }

      } catch (SQLException se) {
        JOptionPane.showMessageDialog(this, "Database error occurred during login. Please try again.",
            "Database Error", JOptionPane.ERROR_MESSAGE);
        System.err.println("SQLException during login: " + se.getMessage());
        se.printStackTrace();
      } catch (NullPointerException npe) {
        JOptionPane.showMessageDialog(this, "A required field was left empty or not initialized.",
            "Input Error", JOptionPane.ERROR_MESSAGE);
        System.err.println("NullPointerException: " + npe.getMessage());
        npe.printStackTrace();
      }finally {

                if (conn != null) {
                    conn.close();
                }
            }

    }
    if (ae.getSource() == signup) {
      setVisible(false);
      new Signup();
    }
    if (ae.getSource() == forgetpassword) {
      setVisible(false);
      new Forgetpassword();
    }
  }

  public static void main(String[] args) {
    new main();
  }
}
