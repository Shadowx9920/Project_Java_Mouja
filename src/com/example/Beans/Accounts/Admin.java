package com.example.Beans.Accounts;

import java.awt.image.BufferedImage;

public class Admin extends Compte{
    public Admin(int id, String username, String password, String date, BufferedImage image,String email,
    String phoneNumber){
        super(id,username,password,date,image,email,phoneNumber);
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