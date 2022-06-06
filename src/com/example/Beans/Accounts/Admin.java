package com.example.Beans.Accounts;

import java.awt.image.BufferedImage;

import com.example.Beans.Fournisseur;
import com.example.DataBase.DBset;

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

    public static void removeUserByID(int Id) {
        DBset.delUser(Id);
    }

    public static void addProduct(String FournisseurName, String Productname, String description, BufferedImage image, Double price){
        if (Fournisseur.searchForFournisseur(FournisseurName)) {
            Fournisseur tmp = Fournisseur.getFournisseur(FournisseurName);
            DBset.addProduct(tmp.id, Productname, description, image, price);
        }else{
            DBset.addFournisseur(FournisseurName);
            Fournisseur tmp = Fournisseur.getFournisseur(FournisseurName);
            DBset.addProduct(tmp.id, Productname, description, image, price);
        }
    }

    public static void removeProductByID(int Id) {
        DBset.delProduct(Id);
    }
}
