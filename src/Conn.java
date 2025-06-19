import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conn {
    Connection c;
    Statement s;

    Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///travelmanagementsystem", "root", "srajan2004");
            s = c.createStatement();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage(), "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("SQL Exception during connection: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred during connection: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("General Exception during connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (s != null)
                s.close();
            if (c != null)
                c.close();
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
            e.printStackTrace();
        }
    }
}