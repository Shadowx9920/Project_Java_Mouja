package com.example.Beans;

import java.util.LinkedList;

public class Fournisseur {
    public  int id;
    public String name;
    public String date; 
    public LinkedList<Product> products;

    public Fournisseur(int id,String name,String date,LinkedList<Product> products) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Fournisseur [date=" + date + ", id=" + id + ", name=" + name + ", products=" + products + "]";
    }
    
}
