package com.example.Accounts;

import java.awt.image.BufferedImage;

public class Compte {

    private int id;
    private String username;
    private String password;
    private String date;
    private BufferedImage image;

    public Compte(int id, String username, String password, String date, BufferedImage image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
        this.image = image;
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

    @Override
    public String toString() {
        return "Compte [date=" + date + ", id=" + id + ", password=" + password + ", username="
                + username + "]";
    }
    
}
