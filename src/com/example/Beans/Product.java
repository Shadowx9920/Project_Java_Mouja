package com.example.Beans;

import java.awt.image.BufferedImage;

import com.example.DataBase.DBget;

public class Product {
    
    private int id;
    private String name;
    private String description;
    private String date;
    private BufferedImage productPicture;
    private Double price;
    

    public Product(int id, String name, String description, String date, BufferedImage productPicture, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.productPicture = productPicture;
        this.price = price;
        
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BufferedImage getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(BufferedImage productPicture) {
        this.productPicture = productPicture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Fournisseur getFournisseur() {
        return DBget.getFournisseurByProduct(this.getId());
    }

    @Override
    public String toString() {
        return "Product [date=" + date + ", description=" + description + ", id=" + id + ", name=" + name
                +"]";
    }
    
}
