package com.example.myapplication;

public class User {

    private String name;
    private String phoneNumber;
    private static User user = null;

    public User() {}

    public static User getInstance() {
        if (user == null) user = new User();
        return user;
    }

    public void setInfo(String n, String pno) {
        name = n;
        phoneNumber = pno;
    }

    public String getName() {
        return name;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }
}
