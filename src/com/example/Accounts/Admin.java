package com.example.Accounts;

import java.awt.image.BufferedImage;

public class Admin extends Compte{
    public Admin(int id, String username, String password, String date, BufferedImage image){
        super(id,username,password,date,image);
    }
    
    //Handling Products
    public void addProduct(){}
    public void delProduct(){}
    public void modifyProduct(){}
    
    //Handling Fournisseur
    public void addFournisseur(){}
    public void delFournisseur(){}
    public void viewFournisseur(){}
    
    //Handling Products Globally
    public void checkProducts(){}
    public void checkIndividualProduct(){}
    public void downloadData(){}

    @Override
    public String toString() {
        return "Admin = true :: "+super.toString();
    }
}
