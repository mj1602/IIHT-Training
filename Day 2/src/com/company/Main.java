package com.company;
import java.util.Scanner;
import java.sql.*;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class Main {

    static final String DB_url="jdbc:mysql://localhost:3306/testdb";
    static final String user="root";
    static final String pass="pass@word1";
    static Scanner sc = new Scanner(System.in);


    static void getAllCustomer(){
        String Query="select * from customer";
        try {
            Connection conn=DriverManager.getConnection(DB_url,user,pass);
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(Query);
            while(rs.next()){
                System.out.printf(rs.getInt("cust_id")+"\t\t");
                System.out.printf(rs.getString("cust_name")+"\t\t");
                System.out.printf(rs.getString("cust_age")+"\t\t");
                System.out.printf(rs.getString("cust_address")+"\t\t");
                System.out.printf(rs.getString("cust_city")+"\t\t");
                System.out.printf(rs.getString("cust_income")+"\t\t");
                System.out.printf("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    static void getCustomerById(){
        System.out.println("Enter Customer Id");
        int custId = sc.nextInt();
        try {
            Connection conn=DriverManager.getConnection(DB_url,user,pass);
            PreparedStatement stmt=conn.prepareStatement("select * from customer where cust_id= ? ");
            stmt.setInt(1,custId);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                System.out.printf(rs.getInt("cust_id")+"\t\t");
                System.out.printf(rs.getString("cust_name")+"\t\t");
                System.out.printf(rs.getString("cust_age")+"\t\t");
                System.out.printf(rs.getString("cust_address")+"\t\t");
                System.out.printf(rs.getString("cust_city")+"\t\t");
                System.out.printf(rs.getString("cust_income")+"\t\t");
                System.out.printf("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    static void deleteCustomerById(){
        System.out.println("Enter Customer Id to delete");
        int custId = sc.nextInt();
        try {
            Connection conn=DriverManager.getConnection(DB_url,user,pass);
            PreparedStatement stmt=conn.prepareStatement("delete from customer where cust_id= ? ");
            stmt.setInt(1,custId);
            int n =stmt.executeUpdate();
            System.out.println(n+" rows deleted");;
            }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    static void updateCustomerAddress(){
        System.out.println("Enter Customer Id to update address:");
        int custId = sc.nextInt();
        System.out.println("Enter new address:");
        String custAddress = sc.next();
        try {
            Connection conn=DriverManager.getConnection(DB_url,user,pass);
            PreparedStatement stmt=conn.prepareStatement("update customer set cust_address =? where cust_id= ? ");
            stmt.setString(1,custAddress);
            stmt.setInt(2,custId);
            int n =stmt.executeUpdate();
            System.out.println("address updated to "+custAddress+" for " + custId);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    static void insertNewCustomer(){
        System.out.println("Enter Customer Id");
        int custId = sc.nextInt();
        System.out.println("Enter Customer Name:");
        String custName = sc.next();
        System.out.println("Enter Customer Age");
        int custAge = sc.nextInt();
        System.out.println("Enter Customer Address");
        String custAddress = sc.next();
        System.out.println("Enter Customer City ");
        String custCity = sc.next();
        System.out.println("Enter Customer Income");
        int custInc = sc.nextInt();
        try {
            Connection conn=DriverManager.getConnection(DB_url,user,pass);
            PreparedStatement stmt=conn.prepareStatement("insert into customer values(?,?,?,?,?,?)");
            stmt.setInt(1,custId);
            stmt.setString(2,custName);
            stmt.setInt(3,custAge);
            stmt.setInt(6,custInc);
            stmt.setString(4,custAddress);
            stmt.setString(5,custCity);
            int n =stmt.executeUpdate();
            System.out.println(n+" rows added");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        while (true) {
            System.out.println("Enter any option number");
            System.out.println("1. View all customers");
            System.out.println("2. View customer by id");
            System.out.println("3. Delete customer");
            System.out.println("4. Insert a customer");
            System.out.println("5. Update customer address");
            System.out.println("6. Exit");
            System.out.println("Choose option: ");
            int c = sc.nextInt();
            switch (c) {
                case 1:
                    getAllCustomer();
                    break;
                case 2:
                    getCustomerById();
                    break;
                case 3:
                    deleteCustomerById();
                    break;
                case 4:
                    insertNewCustomer();
                    break;
                case 5:
                    updateCustomerAddress();
                    break;
                case 6:
                    exit(0);

                default:
                    System.out.println("Enter correct option");
            }
        }
    }
}
