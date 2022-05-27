package com.example.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.example.Beans.Commande;
import com.example.Beans.Product;

import java.awt.image.BufferedImage;

public class DBset {
    public static boolean addUser(String name, String password, boolean isAdmin, BufferedImage image,String email,String phoneNumber) {
        PreparedStatement statement = null;
        byte[] imgData = ImageProcessing.convertImgtoBytes(image);
        int ID = IdManager.generateNewID(DataBase.getConnection());
        String sqlAddProductQuery = "INSERT INTO Users VALUES(?,?,?,?,?,datetime('now'),?,?);";
        
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            statement.setInt(1, ID);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setBoolean(4, isAdmin);
            statement.setBytes(5, imgData);
            statement.setString(6, email);
            statement.setString(7, phoneNumber);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delUser(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from USERS where ID=" + ID + ";";

        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addProduct(int FournisseurID, String name, String description, BufferedImage image, Double price) {
        PreparedStatement statement = null;
        Statement stmt = null;
        byte[] imgData = ImageProcessing.convertImgtoBytes(image);
        int ID = IdManager.generateNewID(DataBase.getConnection());
        String sqlAddProductQuery = "INSERT INTO Products VALUES(?,?,?,?,datetime('now'),?);";
        String addFournisseurLink = "INSERT INTO FournisseurProducts (ProductID,FournisseurID)" +
                "VALUES(" + ID + "," + FournisseurID + ")";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            stmt = DataBase.getConnection().createStatement();
            statement.setInt(1, ID);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setBytes(4, imgData);
            statement.setDouble(5,price);
            statement.executeUpdate();
            stmt.executeUpdate(addFournisseurLink);
            statement.close();
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delProduct(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Products where ID=" + ID + ";";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addFournisseur(String name) {
        Statement statement = null;
        String sqlAddFournisseurQuery = "INSERT INTO Fournisseurs (ID,name,date) " +
                "VALUES (" + IdManager.generateNewID(DataBase.getConnection()) + ", '" + name + "',datetime('now'))";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlAddFournisseurQuery);
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delProducts(int fournisseurID){
        Statement statment = null;
        String sqlQuery = "SELECT ProductID FROM FournisseurProducts WHERE FournisseurID=" + fournisseurID + ";";
        try {
            statment = DataBase.getConnection().createStatement();
            ResultSet resultSet = statment.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                delProduct(productID);
            }
            statment.close();
            return true;
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean delFournisseur(int ID) {
        delProducts(ID);
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Fournisseurs where ID=" + ID + ";";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        return false;
    }

    public static Commande addCommande(int userID,double totalPrice,Boolean isAccepted) {
        PreparedStatement statement = null;
        String sqlAddProductQuery = "INSERT INTO Commandes (ID,userID,date,totalPrice,isAccepted) " +
                "VALUES ( ? ,?,datetime('now'),?,?)";

        int ID = IdManager.generateNewID(DataBase.getConnection());
        
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            statement.setInt(1, ID);
            statement.setInt(2, userID);
            statement.setDouble(3, totalPrice);
            statement.setBoolean(4, isAccepted);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }

        Commande c =  DBget.getCommande(ID);
        return c;
    }

    public static boolean delCommande(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Commandes where ID=" + ID + ";";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addCommandeUser(int commandeID,int userID){
        PreparedStatement statement = null;
        String sqlAddProductQuery = "INSERT INTO CommandesUser (commandeID,userID) " +
                "VALUES ( ? , ? )";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            statement.setInt(1, commandeID);
            statement.setInt(2, userID);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addCommandeProducts(int CommandeID,LinkedList<Product> products){
        PreparedStatement statement = null;
        String sqlAddProductQuery = "INSERT INTO CommandeProducts (CommandeID,ProductID) " +
                "VALUES ( ? , ? )";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            for(Product p : products){
                statement.setInt(1, CommandeID);
                statement.setInt(2, p.getId());
                statement.executeUpdate();
            }
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        return false;
    }
}
