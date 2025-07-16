import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends JFrame implements ActionListener {

    JButton addpersonaldetail, viewpersonaldetail, deletepersonaldetail, checkpackages,
            bookpackage, viewpackage, viewhotels, bookhotel, viewbookedbookhotel, destinations, payments, calculator,
            notepad, about, exit;
    String username;
    AddCostumer var1;
    ViewCustomer var2;
    CheckPackages var3;

    Dashboard(String username) {
        this.username = username;
        var1 = new AddCostumer(username);
        var2 = new ViewCustomer(username);
        var3 = new CheckPackages();
        // Frame Settings:
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        getContentPane().setBackground(Color.white);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0, 0, 102));
        p1.setBounds(0, 0, 1600, 65);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.png"));
        Image i2 = i1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel icon = new JLabel(i3);
        icon.setBounds(5, 0, 70, 70);
        p1.add(icon);

        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(80, 10, 300, 40);
        heading.setForeground(Color.white);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        p1.add(heading);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(0, 0, 102));
        p2.setBounds(0, 65, 300, 750);
        add(p2);

        addpersonaldetail = new JButton("Update Personal Details");
        addpersonaldetail.setBounds(0, 0, 300, 47);
        addpersonaldetail.setBackground(new Color(0, 0, 102));
        addpersonaldetail.setForeground(Color.white);
        addpersonaldetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addpersonaldetail.setMargin(new Insets(0, 0, 0, 30));
        addpersonaldetail.addActionListener(this);
        p2.add(addpersonaldetail);

        viewpersonaldetail = new JButton("View Details");
        viewpersonaldetail.setBounds(0, 47, 300, 47);
        viewpersonaldetail.setBackground(new Color(0, 0, 102));
        viewpersonaldetail.setForeground(Color.white);
        viewpersonaldetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewpersonaldetail.setMargin(new Insets(0, 0, 0, 130));
        viewpersonaldetail.addActionListener(this);
        p2.add(viewpersonaldetail);

        deletepersonaldetail = new JButton("Delete Personal Details");
        deletepersonaldetail.setBounds(0, 94, 300, 47);
        deletepersonaldetail.setBackground(new Color(0, 0, 102));
        deletepersonaldetail.setForeground(Color.white);
        deletepersonaldetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deletepersonaldetail.setMargin(new Insets(0, 0, 0, 30));
        deletepersonaldetail.addActionListener(this);
        p2.add(deletepersonaldetail);

        checkpackages = new JButton("Check Package");
        checkpackages.setBounds(0, 141, 300, 47);
        checkpackages.setBackground(new Color(0, 0, 102));
        checkpackages.setForeground(Color.white);
        checkpackages.setFont(new Font("Tahoma", Font.PLAIN, 20));
        checkpackages.setMargin(new Insets(0, 0, 0, 100));
        checkpackages.addActionListener(this);
        p2.add(checkpackages);

        bookpackage = new JButton("Book Package");
        bookpackage.setBounds(0, 188, 300, 47);
        bookpackage.setBackground(new Color(0, 0, 102));
        bookpackage.setForeground(Color.white);
        bookpackage.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bookpackage.setMargin(new Insets(0, 0, 0, 110));
        bookpackage.addActionListener(this);
        p2.add(bookpackage);

        viewpackage = new JButton("View Package");
        viewpackage.setBounds(0, 235, 300, 47);
        viewpackage.setBackground(new Color(0, 0, 102));
        viewpackage.setForeground(Color.white);
        viewpackage.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewpackage.setMargin(new Insets(0, 0, 0, 110));
        viewpackage.addActionListener(this);
        p2.add(viewpackage);

        viewhotels = new JButton("View Hotels");
        viewhotels.setBounds(0, 282, 300, 47);
        viewhotels.setBackground(new Color(0, 0, 102));
        viewhotels.setForeground(Color.white);
        viewhotels.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewhotels.setMargin(new Insets(0, 0, 0, 130));
        viewhotels.addActionListener(this);
        p2.add(viewhotels);

        bookhotel = new JButton("Book Hotels");
        bookhotel.setBounds(0, 329, 300, 47);
        bookhotel.setBackground(new Color(0, 0, 102));
        bookhotel.setForeground(Color.white);
        bookhotel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bookhotel.setMargin(new Insets(0, 0, 0, 130));
        bookhotel.addActionListener(this);
        p2.add(bookhotel);

        viewbookedbookhotel = new JButton("View Booked Hotels");
        viewbookedbookhotel.setBounds(0, 376, 300, 47);
        viewbookedbookhotel.setBackground(new Color(0, 0, 102));
        viewbookedbookhotel.setForeground(Color.white);
        viewbookedbookhotel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewbookedbookhotel.setMargin(new Insets(0, 0, 0, 60));
        viewbookedbookhotel.addActionListener(this);
        p2.add(viewbookedbookhotel);

        destinations = new JButton("Destinations");
        destinations.setBounds(0, 423, 300, 47);
        destinations.setBackground(new Color(0, 0, 102));
        destinations.setForeground(Color.white);
        destinations.setFont(new Font("Tahoma", Font.PLAIN, 20));
        destinations.setMargin(new Insets(0, 0, 0, 130));
        destinations.addActionListener(this);
        p2.add(destinations);

        payments = new JButton("Payments");
        payments.setBounds(0, 470, 300, 55);
        payments.setBackground(new Color(0, 0, 102));
        payments.setForeground(Color.white);
        payments.setFont(new Font("Tahoma", Font.PLAIN, 20));
        payments.setMargin(new Insets(0, 0, 0, 150));
        payments.addActionListener(this);
        p2.add(payments);

        calculator = new JButton("Calculator");
        calculator.setBounds(0, 525, 300, 52);
        calculator.setBackground(new Color(0, 0, 102));
        calculator.setForeground(Color.white);
        calculator.setFont(new Font("Tahoma", Font.PLAIN, 20));
        calculator.setMargin(new Insets(0, 0, 0, 150));
        calculator.addActionListener(this);
        p2.add(calculator);

        notepad = new JButton("Notepad");
        notepad.setBounds(0, 577, 300, 50);
        notepad.setBackground(new Color(0, 0, 102));
        notepad.setForeground(Color.white);
        notepad.setFont(new Font("Tahoma", Font.PLAIN, 20));
        notepad.setMargin(new Insets(0, 0, 0, 160));
        notepad.addActionListener(this);
        p2.add(notepad);

        about = new JButton("About");
        about.setBounds(0, 627, 300, 53);
        about.setBackground(new Color(0, 0, 102));
        about.setForeground(Color.white);
        about.setFont(new Font("Tahoma", Font.PLAIN, 20));
        about.setMargin(new Insets(0, 0, 0, 175));
        about.addActionListener(this);
        p2.add(about);

        exit = new JButton("Exit");
        exit.setBounds(0, 680, 300, 53);
        exit.setBackground(new Color(0, 0, 102));
        exit.setForeground(Color.white);
        exit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        exit.setMargin(new Insets(0, 0, 0, 190));
        exit.addActionListener(this);
        p2.add(exit);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i5 = i4.getImage().getScaledInstance(1650, 1000, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(0, 0, 1650, 1000);
        add(image);

        JLabel text = new JLabel("Narmada Travel Agency");
        text.setBounds(690, 70, 1000, 70);
        text.setForeground(Color.white);
        text.setFont(new Font("Tahoma", Font.PLAIN, 40));
        image.add(text);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addpersonaldetail) {
            var2.setVisible(false);
            var3.setVisible(false);
            var1.setVisible(true);
        } else if (ae.getSource() == viewpersonaldetail) {
            var1.setVisible(false);
            var3.setVisible(false);
            var2.setVisible(true);
        } else if (ae.getSource() == checkpackages) {
            var1.setVisible(false);
            var2.setVisible(false);
            var3.setVisible(true);
        } else if (ae.getSource() == exit) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Dashboard("srajan");
    }
}
