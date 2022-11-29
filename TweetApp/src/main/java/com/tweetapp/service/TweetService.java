package com.tweetapp.service;

import com.tweetapp.dao.TweetAppDao;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class TweetService {
    public Connection conn;
    Tweet tweet = new Tweet();
    User user = new User();
    Scanner sc =new Scanner(System.in);

    public Tweet enterTweet(String userid){
        Scanner sc = new Scanner(System.in);
        Tweet tweet = new Tweet();
        tweet.setUserid(userid);
        System.out.println("Enter tweet:");
        tweet.setTweet(sc.nextLine());
        return tweet;
    }

    public void postNewTweet(String userid){
        conn = TweetAppDao.getConnection();
        Tweet tweet = enterTweet(userid);

        System.out.println("Uploading Tweet...");
        String sql = "insert into tweets(userid,tweet) values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tweet.getUserid());
            ps.setString(2, tweet.getTweet());


            int rowInserted = ps.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Tweet uploaded successfully");
            }

        }catch (Exception e){
            System.out.println("Something went wrong");
        }
    }
    public void getAllTweets() {
        Tweet tweet = new Tweet();
        try {
            conn = TweetAppDao.getConnection();
            PreparedStatement ps = conn.prepareStatement("Select * from tweets");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tweet.setUserid(rs.getString("userid"));
                tweet.setTweet(rs.getString("tweet"));
                System.out.print(tweet.getUserid()+"            ");
                System.out.println(tweet.getTweet());
            }

        }catch (Exception e){
            System.out.println("Something went wrong");
        }
    }

    public void getAllTweetsForUser(String userid) {
        try {
            conn = TweetAppDao.getConnection();
            PreparedStatement ps = conn.prepareStatement("Select * from tweets where userid=?");
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                tweet.setUserid(rs.getString("userid"));
                tweet.setTweet(rs.getString("tweet"));
                System.out.println(tweet.getTweet());
            }
        }catch (Exception e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }
    public void viewAllUsers(){
        try {
            conn = TweetAppDao.getConnection();
            PreparedStatement ps = conn.prepareStatement("Select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getString("userid"));
                user.setFirstName(rs.getString("fname"));
                user.setLastName(rs.getString("lname"));
                System.out.print((user.getUserId()) + "         ");
                System.out.print(user.getFirstName()+" ");
                System.out.println(user.getLastName());
            }
        }catch (Exception e){
            System.out.println("Something went Wrong!!!");
        }

    }

    public void resetPassword(String userid) {
        try {
            conn = TweetAppDao.getConnection();
            System.out.println("Enter your old password");
            String oldPassword = sc.nextLine();
            System.out.println("Enter your new password");
            String newPassword = sc.nextLine();
            PreparedStatement ps = conn.prepareStatement("update user set pwd=? where userid=? and pwd=? ");
            ps.setString(1, newPassword);
            ps.setString(2, userid);
            ps.setString(3, oldPassword);
            int n = ps.executeUpdate();
            if(n>0) {
                System.out.println("password updated successfully");
            }else System.out.println("Wrong details entered");
        }catch (Exception e){
            System.out.println("something went wrong!!!");
    }

    }

}
