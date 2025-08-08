import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ViewCustomer extends JFrame {
    JLabel usernamelable, id, number, name, gender, country, address, phone, email;
    ViewCustomer(String username) {
        
        // Set undecorated and position
        setUndecorated(true);
        setBounds(450, 180, 870, 625);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        getContentPane().setBackground(Color.white);

        Font labelFont = new Font("Tahoma", Font.PLAIN, 16);
        Font valueFont = new Font("Tahoma", Font.BOLD, 16);

        // Panel for customer info
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20, 20, 820, 430);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY, 2), "Customer Details",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 18), Color.DARK_GRAY));
        add(panel);

        // Left Column
        int x1 = 40, x2 = 180;
        int y = 40, gap = 40;

        JLabel usernamelbl = new JLabel("Username:");
        usernamelbl.setBounds(x1, y, 120, 25);
        usernamelbl.setFont(labelFont);
        panel.add(usernamelbl);

        usernamelable = new JLabel(username);
        usernamelable.setBounds(x2, y, 200, 25);
        usernamelable.setFont(valueFont);
        panel.add(usernamelable);

        y += gap;

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(x1, y, 120, 25);
        idLabel.setFont(labelFont);
        panel.add(idLabel);

        id = new JLabel();
        id.setBounds(x2, y, 200, 25);
        id.setFont(valueFont);
        panel.add(id);

        y += gap;

        JLabel verificationLabel = new JLabel("Verification No:");
        verificationLabel.setBounds(x1, y, 140, 25);
        verificationLabel.setFont(labelFont);
        panel.add(verificationLabel);

        number = new JLabel();
        number.setBounds(x2, y, 200, 25);
        number.setFont(valueFont);
        panel.add(number);

        y += gap;

        JLabel namelbl = new JLabel("Name:");
        namelbl.setBounds(x1, y, 120, 25);
        namelbl.setFont(labelFont);
        panel.add(namelbl);

        name = new JLabel();
        name.setBounds(x2, y, 200, 25);
        name.setFont(valueFont);
        panel.add(name);

        y += gap;

        JLabel genderlbl = new JLabel("Gender:");
        genderlbl.setBounds(x1, y, 120, 25);
        genderlbl.setFont(labelFont);
        panel.add(genderlbl);

        gender = new JLabel();
        gender.setBounds(x2, y, 200, 25);
        gender.setFont(valueFont);
        panel.add(gender);

        // Right Column
        int rx1 = 460, rx2 = 610;
        y = 40;

        JLabel countrylbl = new JLabel("Country:");
        countrylbl.setBounds(rx1, y, 120, 25);
        countrylbl.setFont(labelFont);
        panel.add(countrylbl);

        country = new JLabel();
        country.setBounds(rx2, y, 200, 25);
        country.setFont(valueFont);
        panel.add(country);

        y += gap + 10;

        JLabel addresslbl = new JLabel("Address:");
        addresslbl.setBounds(rx1, y, 120, 25);
        addresslbl.setFont(labelFont);
        panel.add(addresslbl);

        address = new JLabel();
        address.setBounds(rx2, y, 200, 25);
        address.setFont(valueFont);
        panel.add(address);

        y += gap + 10;

        JLabel phonelbl = new JLabel("Phone No:");
        phonelbl.setBounds(rx1, y, 120, 25);
        phonelbl.setFont(labelFont);
        panel.add(phonelbl);

        phone = new JLabel();
        phone.setBounds(rx2, y, 200, 25);
        phone.setFont(valueFont);
        panel.add(phone);

        y += gap + 10;

        JLabel emaillbl = new JLabel("E-mail:");
        emaillbl.setBounds(rx1, y, 120, 25);
        emaillbl.setFont(labelFont);
        panel.add(emaillbl);

        email = new JLabel();
        email.setBounds(rx2, y, 200, 25);
        email.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(email);

        // Back Button
        JButton back = new JButton("Back");
        back.setBounds(680, 380, 100, 30);
        back.setBackground(new Color(30, 144, 255));
        back.setForeground(Color.white);
        back.setFocusPainted(false);
        panel.add(back);

        back.addActionListener(e -> {
            setVisible(false);
            
        });

        // Bottom Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 140, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(35, 470, 800, 140); // positioned at the bottom
        add(image);

        Conn con = null;

        try {
            con = new Conn();
            String query ="Select *from customer Where BINARY username = ?";
            PreparedStatement ps = con.c.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id.setText(rs.getString("id"));
                number.setText(rs.getString("number"));
                name.setText(rs.getString("name"));
                gender.setText(rs.getString("gender"));
                country.setText(rs.getString("country"));
                address.setText(rs.getString("address"));
                phone.setText(rs.getString("phonenumber"));
                email.setText(rs.getString("email"));
            }

        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Database Error occurred\n" + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
        }




    }

    public static void main(String[] args) {
        new ViewCustomer("srajan").setVisible(true);
    }
}
