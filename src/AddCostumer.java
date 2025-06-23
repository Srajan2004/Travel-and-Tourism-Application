import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddCostumer extends JFrame {
    JLabel lblusername;
    JComboBox id;
    JTextField verification_number;

    AddCostumer(String username) {
        setBounds(450, 200, 850, 550);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel usernamelbl = new JLabel("Username");
        usernamelbl.setBounds(30, 50, 150, 25);
        add(usernamelbl);

        lblusername = new JLabel(username);
        lblusername.setBounds(220, 50, 150, 25);
        add(lblusername);

        JLabel lblid = new JLabel("ID");
        lblid.setBounds(30, 90, 150, 25);
        add(lblid);

        id = new JComboBox<>(new String[] { "Passport", "Adhar Card", "Pan Card", "Driving License" });
        id.setBounds(200, 90, 150, 25);
        id.setBackground(Color.WHITE);
        add(id);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(30, 130, 150, 25);
        add(lblnumber);

        verification_number = new JTextField();
        verification_number.setBounds(200, 130, 150, 25);
        add(verification_number);

        JLabel lblname = new JLabel();
        lblname.setBounds(30, 170, 150, 25); // Example position and size, adjust as needed
        add(lblname);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddCostumer("Srajan Patel");
    }
}
