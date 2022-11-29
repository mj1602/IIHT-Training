package com.tweetapp.service;

import com.tweetapp.Main;
import com.tweetapp.dao.TweetAppDao;
import com.tweetapp.model.User;

import java.sql.*;
import java.util.Scanner;

public class GuestMenuService {

    public void loginUser() {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        TweetService service = new TweetService();
        try {
            conn = TweetAppDao.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enter Email/userId");
        String userid = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        try {
            PreparedStatement st = conn.prepareStatement("update user  set logged_in = true where userid=? and pwd=?");
            st.setString(1, userid);
            st.setString(2, password);
            int n = st.executeUpdate();
            if (n == 1) {
                System.out.println(userid + " logged in successfully");
                System.out.println("Enter your choice");
                while (true) {
                    System.out.println("1.Post a tweet");
                    System.out.println("2.get all tweets");
                    System.out.println("3.View my tweets");
                    System.out.println("4.View all users");
                    System.out.println("5.Reset password");
                    System.out.println("6.Logout");
                    int ch = sc.nextInt();
                    switch (ch) {
                        case 1:
                            service.postNewTweet(userid);
                            break;

                        case 2:service.getAllTweets();
                            break;

                        case 3:service.getAllTweetsForUser(userid);
                            break;

                        case 4:service.viewAllUsers();
                            break;

                        case 5:service.resetPassword(userid);
                            break;

                        case 6:
                            logout(userid);
                            Main.main(null);
                            break;
                         default:
                             System.out.println("Invalid Choice");
                    }

                }
            } else {
                System.out.println("Invalid user");
                System.out.println("Enter valid credentials or Register");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUp() {
        Connection conn = TweetAppDao.getConnection();
        User user = getUserDetails();

        System.out.println("Inserting records into the table");
        String sql = "insert into user(userid,fname,lname,pwd,logged_in) values(?,?,?,?,false)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());

            int rowInserted = ps.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("New user has been added successfully");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void logout(String userid) {
        try {
            Connection conn = TweetAppDao.getConnection();
            PreparedStatement st = conn.prepareStatement("update user  set logged_in = false where userid=?");
            st.setString(1, userid);
            int n = st.executeUpdate();
            if (n == 1) {
                System.out.println("Logged out Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static User getUserDetails(){
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("Enter the details");
        System.out.println("Enter EmailId");
        user.setUserId(sc.nextLine());
        System.out.println("Enter Firstname ");
        user.setFirstName(sc.nextLine());
        System.out.println("Enter Lastname ");
        user.setLastName(sc.nextLine());
        System.out.println("Enter Password ");
        user.setPassword(sc.nextLine());
        user.setLoggedIn(false);

        return user;

    }
}