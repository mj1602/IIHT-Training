package com.tweetapp;


import com.tweetapp.service.GuestMenuService;
import com.tweetapp.service.TweetService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        {
            Scanner sc=new Scanner(System.in);
            TweetService ts=new TweetService();
            GuestMenuService gs=new GuestMenuService();

            while(true) {
                System.out.println("Select you Choice");
                System.out.println("______________________");
                System.out.println("1.REGISTER");
                System.out.println("2.LOGIN");
                System.out.println("3.FORGOT PASSWORD");
                System.out.println("4.EXIT");
                int option=sc.nextInt();
                switch(option) {
                    case 1:System.out.println("register");
                        gs.signUp();
                        break;

                    case 2:System.out.println("login");
                        gs.loginUser();
                        break;

                    case 3:System.out.println("Forgot Password");
                        System.out.println("Enter user Id");
                        ts.resetPassword(sc.next());
                        break;
                    case 4:System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid" +
                                " choice");
                }

            }
        }
    }
}
