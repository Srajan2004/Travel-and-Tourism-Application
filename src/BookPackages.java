import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BookPackages extends JFrame implements ActionListener {
    Choice cpackage;
    JTextField tfpersons;
    JLabel usernamelbl, idnumber, numberlbl, total;
    JButton bookButton;
    String username;

    BookPackages(String username) {
        this.username = username;

        setLayout(null);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setBounds(350, 200, 1100, 500);

        Font font = new Font("Tahoma", Font.PLAIN, 16);

        JLabel heading = new JLabel("Book Package");
        heading.setBounds(100, 10, 200, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel usernametxt = new JLabel("Username");
        usernametxt.setBounds(40, 70, 100, 20);
        usernametxt.setFont(font);
        add(usernametxt);

        usernamelbl = new JLabel(username);
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
        cpackage.setBounds(250, 110, 200, 20);
        add(cpackage);

        JLabel npeople = new JLabel("Total Persons");
        npeople.setFont(font);
        npeople.setBounds(40, 150, 150, 25);
        add(npeople);

        tfpersons = new JTextField("1");
        tfpersons.setBounds(250, 150, 200, 25);
        add(tfpersons);

        JLabel id = new JLabel("ID");
        id.setBounds(40, 190, 100, 20);
        id.setFont(font);
        add(id);

        idnumber = new JLabel("");
        idnumber.setBounds(250, 190, 200, 20);
        idnumber.setFont(font);
        add(idnumber);

        JLabel number = new JLabel("Number");
        number.setBounds(40, 230, 100, 20);
        number.setFont(font);
        add(number);

        numberlbl = new JLabel("");
        numberlbl.setFont(font);
        numberlbl.setBounds(250, 230, 200, 20);
        add(numberlbl);

        JLabel price = new JLabel("Total Price");
        price.setBounds(40, 270, 150, 20);
        price.setFont(font);
        add(price);

        total = new JLabel("Rs 0");
        total.setBounds(250, 270, 150, 20);
        total.setFont(font);
        add(total);

        bookButton = new JButton("Book Package");
        bookButton.setBounds(250, 320, 200, 30);
        bookButton.setBackground(Color.BLACK);
        bookButton.setForeground(Color.WHITE);
        bookButton.addActionListener(this);
        add(bookButton);

        // Add image
        ImageIcon bookPackageImage = new ImageIcon(ClassLoader.getSystemResource("icons/bookpackage.jpg"));
        JLabel imageLabel = new JLabel(bookPackageImage);
        imageLabel.setBounds(600, 50, 400, 300);
        add(imageLabel);

        // 🔹 Load customer info from DB dynamically
        loadUserDetails();

        // Initial price update
        updatePrice();

        // Live price updates
        cpackage.addItemListener(e -> updatePrice());
        tfpersons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                updatePrice();
            }
        });
    }

    /**
     * Fetches ID number and phone number from the customer table dynamically.
     */
    private void loadUserDetails() {
        try {
            Conn conn = new Conn();

            String query = "SELECT phonenumber,number FROM customer WHERE BINARY username = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idnumber.setText(rs.getString("number"));
                numberlbl.setText(rs.getString("phonenumber"));
            } else {
                idnumber.setText("N/A");
                numberlbl.setText("N/A");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error retrieving customer details: " + e.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updatePrice() {
        int cost = 0;
        String pack = cpackage.getSelectedItem();
        int persons = 1;
        try {
            persons = Integer.parseInt(tfpersons.getText());
            if (persons <= 0) persons = 1;
        } catch (Exception e) {
            persons = 1;
        }
        if (pack.equals("Bali - Gold")) {
            cost = 12000 * persons;
        } else if (pack.equals("Manali - Silver")) {
            cost = 10000 * persons;
        } else if (pack.equals("Goa - Bronze")) {
            cost = 8000 * persons;
        }
        total.setText("Rs " + cost);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bookButton) {
            try {
                int persons = Integer.parseInt(tfpersons.getText());
                int price = Integer.parseInt(total.getText().replace("Rs ", ""));
                String pack = cpackage.getSelectedItem();

                Conn conn = new Conn();
                String query = "INSERT INTO bookings(username, package_name, persons, total_price) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, pack);
                ps.setInt(3, persons);
                ps.setInt(4, price);

                ps.executeUpdate();
                ps.close();
                conn.close();

                JOptionPane.showMessageDialog(this, "Package Booked Successfully!");
                setVisible(false);

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving booking!");
            }
        }
    }

    public static void main(String[] args) {
        new BookPackages("srajan").setVisible(true);;
    }
}
