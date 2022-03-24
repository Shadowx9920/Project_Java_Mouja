package com.example.Accounts;

import java.awt.image.BufferedImage;

public class User extends Compte{
    public User(int id, String username, String password, String date, BufferedImage image,String email,
    String phoneNumber){
        super(id,username,password,date,image,email,phoneNumber);
    }
    public void orderSomething(){}
    public void checkIndividualProduct(){}

    @Override
    public String toString() {
        return "Admin = false :: "+super.toString();
    }
}
