import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BookPackages extends JFrame{
BookPackages(){
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        getContentPane().setBackground(Color.white);
        setBounds(350,200,1100,500);
        setVisible(true);
}
public static void main(String[] args) {
new BookPackages();
}
}
