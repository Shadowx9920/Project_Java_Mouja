package com.example.Beans.Accounts;

import java.awt.image.BufferedImage;

public class Compte {

    private int id;
    private String username;
    private String password;
    private String date;
    private BufferedImage image;
    private String email;
    private String phoneNumber;
    
    public Compte(int id, String username, String password, String date, BufferedImage image, String email,
            String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
        this.image = image;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "Compte [date=" + date + ", email=" + email + ", id=" + id + ", password=" + password + ", phoneNumber="
                + phoneNumber + ", username=" + username + "]";
    }
    
}
