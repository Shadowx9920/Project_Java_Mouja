package com.example.Beans.Accounts;

import java.awt.image.BufferedImage;

import com.example.Beans.Fournisseur;
import com.example.DataBase.DBset;

public class Admin extends Compte{
    public Admin(int id, String username, String password, String date, BufferedImage image,String email,
    String phoneNumber){
        super(id,username,password,date,image,email,phoneNumber);
    }

    @Override
    public String toString() {
        return "Admin = true :: "+super.toString();
    }

    public static void removeUserByID(int Id) {
        DBset.removeUserCommandes(Id);
        DBset.delUser(Id);
    }

    public static void addProduct(String FournisseurName, String Productname, String description, BufferedImage image, Double price,int quantity){
        if (Fournisseur.searchForFournisseur(FournisseurName)) {
            Fournisseur tmp = Fournisseur.getFournisseur(FournisseurName);
            DBset.addProduct(tmp.id, Productname, description, image, price,quantity);
        }else{
            DBset.addFournisseur(FournisseurName);
            Fournisseur tmp = Fournisseur.getFournisseur(FournisseurName);
            DBset.addProduct(tmp.id, Productname, description, image, price,quantity);
        }
    }

    public static void removeProductByID(int Id) {
        DBset.removeProductCommande(Id);
        DBset.delProduct(Id);
    }
}
