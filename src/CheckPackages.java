import java.awt.*;
import javax.swing.*;

public class CheckPackages extends JFrame {

    CheckPackages() {
        setBounds(450, 200, 900, 600);
        setTitle("Travel Packages");
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        setIconImage(logo.getImage());
        
        JTabbedPane tab = new JTabbedPane();

        // GOLD Package: Bali, Indonesia
        String[] goldFeatures = {
            "VIP Airport Assistance", "Private Villa Stay", "Daily Buffet Breakfast",
            "Complimentary Spa Session", "Full Day Ubud & Kintamani Tour", "Personal English-speaking Guide"
        };
        tab.addTab("Bali - Gold", null, createPackage(
            "BALI LUXURY ESCAPE", "6 Days / 5 Nights", goldFeatures, "Rs 1,25,000/-", "icons/package1.jpg"));

        // SILVER Package: Manali, India
        String[] silverFeatures = {
            "Pickup from Chandigarh Airport", "3-Star Hotel Accommodation", "Daily Breakfast & Dinner",
            "Solang Valley Adventure Activities", "Rohtang Pass Day Trip", "Evening Bonfire with Music"
        };
        tab.addTab("Manali - Silver", null, createPackage(
            "MANALI ADVENTURE RETREAT", "5 Days / 4 Nights", silverFeatures, "Rs 45,000/-", "icons/package2.jpg"));

        // BRONZE Package: Goa, India
        String[] bronzeFeatures = {
            "Railway Station Pickup & Drop", "2-Star Beachside Stay", "Complimentary Breakfast",
            "North Goa Sightseeing Tour", "Evening at Baga Beach", "Tourist Guide Booklet"
        };
        tab.addTab("Goa - Bronze", null, createPackage(
            "GOA BUDGET GETAWAY", "4 Days / 3 Nights", bronzeFeatures, "Rs 22,000/-", "icons/package3.jpg"));

        add(tab);
    
    }

    private JPanel createPackage(String name, String duration, String[] features, String price, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel(name);
        title.setBounds(40, 5, 500, 30);
        title.setForeground(new Color(255, 165, 0));
        title.setFont(new Font("Tahoma", Font.BOLD, 25));
        panel.add(title);

        JLabel durationLabel = new JLabel(duration);
        durationLabel.setBounds(30, 50, 400, 30);
        durationLabel.setForeground(Color.DARK_GRAY);
        durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(durationLabel);

        int y = 100;
        for (int i = 0; i < features.length; i++) {
            JLabel featureLabel = new JLabel("• " + features[i]);
            featureLabel.setBounds(30, y, 400, 30);
            featureLabel.setForeground(i % 2 == 0 ? new Color(60, 60, 200) : new Color(200, 50, 50));
            featureLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            panel.add(featureLabel);
            y += 35;
        }

        JLabel book = new JLabel("Book Now");
        book.setBounds(30, y + 10, 300, 30);
        book.setForeground(new Color(0, 128, 0));
        book.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(book);

        JLabel offer = new JLabel("Limited Period Offer!");
        offer.setBounds(30, y + 50, 300, 30);
        offer.setForeground(Color.MAGENTA);
        offer.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(offer);

        JLabel priceLabel = new JLabel(price);
        priceLabel.setBounds(500, y + 50, 300, 30);
        priceLabel.setForeground(Color.BLUE);
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        panel.add(priceLabel);

        try {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
            Image img = icon.getImage().getScaledInstance(400, 280, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setBounds(420, 20, 440, 280);
            panel.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
        }

        return panel;
    }

    public static void main(String[] args) {
        new CheckPackages();
    }
}
