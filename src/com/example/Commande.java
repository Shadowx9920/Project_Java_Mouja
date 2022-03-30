package com.example;

import java.util.LinkedList;

import com.example.Accounts.User;

public class Commande {
    private int id;
    private LinkedList<Product> products;
    private User buyer;
    private String date;
    private double totalPrice;
    private boolean isaccepted;

    public Commande(int id, LinkedList<Product> products, User buyer, String date, double totalPrice,
            boolean isaccepted) {
        this.id = id;
        this.products = products;
        this.buyer = buyer;
        this.date = date;
        this.totalPrice = totalPrice;
        this.isaccepted = isaccepted;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isIsaccepted() {
        return isaccepted;
    }

    public void setIsaccepted(boolean isaccepted) {
        this.isaccepted = isaccepted;
    }

    public static Double getTotalPrice(Commande c){
        double total = 0;
        for (Product p : c.getProducts()) {
            total += p.getPrice();
        }
        return total;
    }
}
