package com.example;

import java.awt.image.BufferedImage;

public class Product {
    
    private int id;
    private String name;
    private String description;
    private String date;
    private BufferedImage productPicture;

    public Product(int id,String name, String description, BufferedImage productPicture,String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productPicture = productPicture;
        this.date = date;
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

    @Override
    public String toString() {
        return "Product [date=" + date + ", description=" + description + ", id=" + id + ", name=" + name
                +"]";
    }
    
}
