package com.example;

import com.example.Accounts.User;

public class Commande {
    private int id;
    private Product product;
    private User buyer;
    private String date;
    
    public Commande(int id,Product product, User buyer,String date) {
        this.id = id;
        this.product = product;
        this.buyer = buyer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande [buyer=" + buyer + ", date=" + date + ", id=" + id + ", product=" + product + "]";
    }
    
}
