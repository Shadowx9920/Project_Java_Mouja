package com.example.DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.image.BufferedImage;

public class DBset {
    public static void addUser(String name, String password, boolean isAdmin, BufferedImage image) {
        PreparedStatement statement = null;
        byte[] imgData = ImageProcessing.convertImgtoBytes(image);
        int ID = IdManager.generateNewID(DataBase.getConnection());
        String sqlAddProductQuery = "INSERT INTO Users VALUES(?,?,?,?,?,datetime('now'));";
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
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sqlDelUserQuery);
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(int FournisseurID, String name, String description, BufferedImage image) {
        PreparedStatement statement = null;
        Statement stmt = null;
        byte[] imgData = ImageProcessing.convertImgtoBytes(image);
        int ID = IdManager.generateNewID(DataBase.getConnection());
        String sqlAddProductQuery = "INSERT INTO Products VALUES(?,?,?,?,datetime('now'));";
        String addFournisseurLink = "INSERT INTO FournisseurProducts (ProductID,FournisseurID)" +
                "VALUES(" + ID + "," + FournisseurID + ")";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            stmt = DataBase.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.setInt(1, ID);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setBytes(4, imgData);
            statement.executeUpdate();
            stmt.executeUpdate(addFournisseurLink);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
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
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sqlDelUserQuery);
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addFournisseur(String name) {
        Statement statement = null;
        String sqlAddFournisseurQuery = "INSERT INTO Fournisseurs (ID,name,date) " +
                "VALUES (" + IdManager.generateNewID(DataBase.getConnection()) + ", '" + name + "',datetime('now'))";
        try {
            statement = DataBase.getConnection().createStatement();
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sqlAddFournisseurQuery);
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delFournisseur(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Fournisseurs where ID=" + ID + ";";

        try {
            statement = DataBase.getConnection().createStatement();
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sqlDelUserQuery);
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCommande(int productID, int userID) {
        Statement statement = null;
        String sqlAddFournisseurQuery = "INSERT INTO Commandes (ID,productID,userID,date) " +
                "VALUES (" + IdManager.generateNewID(DataBase.getConnection()) + ", " + productID + "," + userID
                + ",datetime('now'))";
        try {
            statement = DataBase.getConnection().createStatement();
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sqlAddFournisseurQuery);
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delCommande(int ID) {
        Statement statement = null;
        String sqlDelUserQuery = "DELETE from Commandes where ID=" + ID + ";";

        try {
            statement = DataBase.getConnection().createStatement();
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sqlDelUserQuery);
        } catch (Exception e) {
            System.err.println("SQL query failed");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
