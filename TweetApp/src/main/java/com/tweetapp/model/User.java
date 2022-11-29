package com.tweetapp.model;

import java.sql.Date;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private Date dob;
    private boolean loggedIn;

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getFirstName() {
        return firstName;
    }
    public Date getDob() {
        return dob;
    }


    public void setDob(Date dob) {
        this.dob = dob;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}