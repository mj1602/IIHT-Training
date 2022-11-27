
import java.util.Scanner;
import java.sql.*;

public class FoodApp {
    static final String DB_url = "jdbc:mysql://localhost:3306/testdb";
    static final String user = "root";
    static final String pass = "pass@word1";


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println("FOOD APP");
        System.out.println("1. Signup");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("Enter the option number you want to choose");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        while (true) {
            switch (i) {
                case 1:
                    signup();
                    break;
                case 2:
                    signin();
                    break;
                case 3:
                    System.out.println("Thanks for visiting");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private static void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email id");
        String emailId = sc.next();
        System.out.println("Enter new password");
        String password = sc.next();
        String sql = "insert into foodlogin(emailid,password) values (?,?)";
        try (Connection conn = DriverManager.getConnection(DB_url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emailId);
            ps.setString(2, password);
            int n = ps.executeUpdate();
            if (n > 0) {
                System.out.println("user register successfully");
            } else {
                System.out.println("Not registered");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }



    }


    private static void signin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter registered email id:");
        String emailid = sc.next();
        System.out.println("Enter password:");
        String pass = sc.next();
        String sql = "select * from foodlogin where emailid = ? and password = ?";
        try (Connection conn = DriverManager.getConnection(DB_url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emailid);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                System.out.println("1. View Menu");
                System.out.println("2. Exit");
                System.out.println("Enter your choice: ");
                int i = sc.nextInt();
                switch (i) {
                    case 1:
                        viewMenu();
                        break;
                    case 2:
                        System.out.println("Thanks for visiting");
                        System.exit(0);
                        ;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }

            } else {
                System.out.println("Invalid login details...");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private static void viewMenu() {
        String sql = "select * from foodmenu";
        try {
            Connection conn = DriverManager.getConnection(DB_url, user, pass);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.printf(rs.getInt("item_id") + "\t\t");
                System.out.printf(rs.getString("item_name") + "\t\t");
                System.out.printf(rs.getString("item_price") + "\t\t");
                System.out.printf("\n");
            }
            System.out.println("Call on 9999999999 to place your order");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

