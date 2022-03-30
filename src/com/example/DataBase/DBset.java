package com.example.DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.image.BufferedImage;

public class DBset {
    public static void addUser(String name, String password, boolean isAdmin, BufferedImage image,String email,String phoneNumber) {
        PreparedStatement statement = null;
        byte[] imgData = ImageProcessing.convertImgtoBytes(image);
        int ID = IdManager.generateNewID(DataBase.getConnection());
        String sqlAddProductQuery = "INSERT INTO Users VALUES(?,?,?,?,?,datetime('now'),?,?);";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.setInt(1, ID);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setBoolean(4, isAdmin);
            statement.setBytes(5, imgData);
            statement.setString(6, email);
            statement.setString(7, phoneNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delUser(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from USERS where ID=" + ID + ";";

        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
    }

    public static void addProduct(int FournisseurID, String name, String description, BufferedImage image, Double price) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delProduct(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Products where ID=" + ID + ";";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
    }

    public static void addFournisseur(String name) {
        Statement statement = null;
        String sqlAddFournisseurQuery = "INSERT INTO Fournisseurs (ID,name,date) " +
                "VALUES (" + IdManager.generateNewID(DataBase.getConnection()) + ", '" + name + "',datetime('now'))";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlAddFournisseurQuery);
            statement.close();
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
    }

    public static void delFournisseur(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Fournisseurs where ID=" + ID + ";";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
    }

    //TODO : Add Products
    public static void addCommande(int productID, int userID,double totalPrice,Boolean isAccepted) {
        PreparedStatement statement = null;
        String sqlAddProductQuery = "INSERT INTO Commandes (ID,productID,userID,date,totalPrice,isAccepted) " +
                "VALUES ( ? , ? ,?,datetime('now'),?,?)";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            statement.setInt(1, IdManager.generateNewID(DataBase.getConnection()));
            statement.setInt(2, productID);
            statement.setInt(3, userID);
            statement.setDouble(4, totalPrice);
            statement.setBoolean(5, isAccepted);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
    }

    public static void delCommande(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Commandes where ID=" + ID + ";";
        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
