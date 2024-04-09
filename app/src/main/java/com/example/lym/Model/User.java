package com.example.lym.Model;

public class User {
    public String email;
    public String name;
    public String avatar;
    public String birthdate;

    public User(){

    }

    public User(String email, String name, String avatar, String birthdate) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.birthdate = birthdate;
    }
}
