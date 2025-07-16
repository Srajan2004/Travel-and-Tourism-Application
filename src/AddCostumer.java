import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddCostumer extends JFrame implements ActionListener {
    JLabel lblusername, lablename;
    JComboBox idComboBox;
    JTextField verification_number, countrytf, addresstf, emailtf, phonenumbertf;
    JButton addButton, backButton;
    JRadioButton male, female;

    String username;

    AddCostumer(String username) {
        this.username = username;
        setBounds(450, 200, 850, 550);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        setLayout(null);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);

        JLabel usernamelbl = new JLabel("Username");
        usernamelbl.setBounds(30, 50, 150, 25);
        add(usernamelbl);

        lblusername = new JLabel();
        lblusername.setBounds(220, 50, 150, 25);
        add(lblusername);

        JLabel lblid = new JLabel("ID");
        lblid.setBounds(30, 90, 150, 25);
        add(lblid);

        idComboBox = new JComboBox<>(new String[] { "Passport", "Adhar Card", "Pan Card", "Driving License" });
        idComboBox.setBounds(220, 90, 150, 25);
        idComboBox.setBackground(Color.WHITE);
        add(idComboBox);

        JLabel lblnumber = new JLabel("Verification Number");
        lblnumber.setBounds(30, 130, 150, 25);
        add(lblnumber);

        verification_number = new JTextField();
        verification_number.setBounds(220, 130, 150, 25);
        add(verification_number);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 170, 150, 25);
        add(lblname);

        lablename = new JLabel();
        lablename.setBounds(220, 170, 150, 25);
        add(lablename);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(30, 210, 150, 25);
        add(lblgender);

        male = new JRadioButton("Male");
        male.setBounds(220, 210, 70, 25);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(300, 210, 70, 25);
        female.setBackground(Color.white);
        add(female);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        JLabel countrylable = new JLabel("Country");
        countrylable.setBounds(30, 250, 70, 25);
        add(countrylable);

        countrytf = new JTextField();
        countrytf.setBounds(220, 250, 150, 25);
        add(countrytf);

        JLabel address = new JLabel("Address");
        address.setBounds(30, 290, 150, 25);
        add(address);

        addresstf = new JTextField();
        addresstf.setBounds(220, 290, 150, 25);
        add(addresstf);

        JLabel phone = new JLabel("Mobile Number");
        phone.setBounds(30, 330, 150, 25);
        add(phone);

        phonenumbertf = new JTextField();
        phonenumbertf.setBounds(220, 330, 150, 25);
        add(phonenumbertf);

        JLabel email = new JLabel("Email");
        email.setBounds(30, 370, 150, 25);
        add(email);

        emailtf = new JTextField();
        emailtf.setBounds(220, 370, 150, 25);
        add(emailtf);

        addButton = new JButton("Add");
        addButton.setBounds(70, 430, 100, 25);
        addButton.setBackground(Color.black);
        addButton.addActionListener(this);
        addButton.setForeground(Color.white);
        add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(220, 430, 100, 25);
        backButton.addActionListener(this);
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        add(backButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/newcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 40, 450, 420);
        add(image);
        Conn con = null;
        try {
            con = new Conn();
            String query = "Select *from account Where Binary username = ?";
            PreparedStatement ps = con.c.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lblusername.setText(rs.getString("username"));
                lablename.setText(rs.getString("name"));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error occurred  Please try again.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {

            if (con != null) {
                con.close();
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == addButton) {
            String id = (String) idComboBox.getSelectedItem();
            String number = verification_number.getText();
            String name = lablename.getText();
            String country = countrytf.getText();
            String email = emailtf.getText();
            String address = addresstf.getText();
            String phonenumber = phonenumbertf.getText();
            String gender = null;
            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            }
            if (number.equals("")) {
                JOptionPane.showMessageDialog(this, "PLease enter a valid verification number of your ID");
                return;
            } else if (gender.equals("")) {
                JOptionPane.showMessageDialog(this, "Please Select the Gender");
                return;
            } else if (country.equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter the country name");
                return;
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter the email");
                return;
            } else if (address.equals("")) {
                JOptionPane.showMessageDialog(this, "PLease enter the address");
                return;
            } else if (phonenumber.equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter the phone number");
                return;
            }

            Conn con = null;
            try {
                con = new Conn();
                String deleteQuery = "DELETE FROM customer WHERE username = ?";
                PreparedStatement deletePs = con.c.prepareStatement(deleteQuery);
                deletePs.setString(1, username);
                deletePs.executeUpdate();
                
                String query = "INSERT INTO customer (username,id, number, name, gender, country, address, phonenumber,email)VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                PreparedStatement ps = con.c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, id);
                ps.setString(3, number);
                ps.setString(4, name);
                ps.setString(5, gender);
                ps.setString(6, country);
                ps.setString(7, address);
                ps.setString(8, phonenumber);
                ps.setString(9, email);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Your information has been added succesfully");
                setVisible(false);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Database error occurred while saving your details:\n" + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.close();
                }
            }

        } else if (ae.getSource() == backButton) {

            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddCostumer("srajan");
    }
}
